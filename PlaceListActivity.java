package com.example.abevillalobos.lakbayph;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceListActivity extends AppCompatActivity {
    private ArrayAdapter<PlaceList> pladapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        Intent GetExtra = getIntent();
        final String search = GetExtra.getStringExtra("TYPE");

        TextView places = (TextView) findViewById(R.id.pl_place);
        String placesearch = "PLACES > " + search;
        places.setText(placesearch);

        pladapter = new ArrayAdapter<PlaceList>(this, android.R.layout.simple_list_item_1);
        final ListView pList = (ListView) findViewById(R.id.pl_list_place);
        pList.setAdapter(pladapter);

        downloadPlaceList(search);

        pList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LakbayApp app = (LakbayApp) getApplication();
                Intent ListtoInfoIntent = new Intent(PlaceListActivity.this, PlaceInfoActivity.class);
                String url = pladapter.getItem(position).get_url();
                String name = pladapter.getItem(position).get_name();
                downloadPlaceInfo(url);
                ListtoInfoIntent.putExtra("NAME", name);
                ListtoInfoIntent.putExtra("TYPE", search);
                ListtoInfoIntent.putExtra("LOCATION", app.getPiloc());
                ListtoInfoIntent.putExtra("CONTENT", app.getPicont());
                startActivity(ListtoInfoIntent);
            }
        });

    }

    private void parsePlacesList(String jsonStr, List<PlaceList> placeLists) throws JSONException{
        JSONObject rootObj = new JSONObject(jsonStr);
        JSONArray placeArr = rootObj.getJSONArray("results");

        for (int iIdx = 0; iIdx < placeArr.length(); iIdx++){
            JSONObject placeObj = placeArr.getJSONObject(iIdx);

            String url = placeObj.getString("url");
            String name = placeObj.getString("name");

            PlaceList pl = new PlaceList(url, name);
            placeLists.add(pl);
        }
    }

    private void downloadPlaceList(final String search){
        AsyncTask<Void, String, List<PlaceList>> downloadTask = new AsyncTask<Void, String, List<PlaceList>>() {
            @Override
            protected List<PlaceList> doInBackground(Void... params) {
                LakbayApp app = (LakbayApp) getApplication();
                HttpClient hc = app.get_httpClient();

                String apiURL = "http://wiki-sherpa.appspot.com/api/1/search/en/";
                apiURL += search;

                HttpGet apiREQ = new HttpGet(apiURL);

                List<PlaceList> listofplaces = new ArrayList<PlaceList>();
                try{
                    HttpResponse response = hc.execute(apiREQ);
                    int retStatus = response.getStatusLine().getStatusCode();
                    if(retStatus!= HttpStatus.SC_OK){
                        Log.e("Error", "Invalid Status Code");
                    }
                    String jsonStr = EntityUtils.toString(response.getEntity());
                    //publishProgress(apiURL);
                    parsePlacesList(jsonStr, listofplaces);

                } catch (Exception e) {
                    publishProgress("Exception occured: " + e.getMessage());
                }

                return listofplaces;
            }

            @Override
            protected void onPostExecute(List<PlaceList> placeLists) {
                pladapter.clear();
                pladapter.addAll(placeLists);

                super.onPostExecute(placeLists);
            }

            @Override
            protected void onProgressUpdate(String... values) {
                Toast.makeText(PlaceListActivity.this, values[0], Toast.LENGTH_SHORT).show();
                super.onProgressUpdate(values);
            }
        };
        downloadTask.execute();
    }

    private void parsePlacesData(String jsonStr) throws JSONException {
        JSONObject rootObj = new JSONObject(jsonStr);
        LakbayApp app = (LakbayApp) getApplication();

        app.setPiloc(rootObj.getString("location"));

        JSONArray sectArr = rootObj.getJSONArray("sections");
        for (int iIdx = 0; iIdx < sectArr.length(); iIdx++){
            JSONObject secObj = sectArr.getJSONObject(iIdx);

            JSONArray contArr = secObj.getJSONArray("sections");
            for (int jIdx = 0; jIdx < contArr.length(); jIdx++) {
                JSONObject contObj = contArr.getJSONObject(jIdx);

                app.setPicont(contObj.getString("text"));
            }
        }
    }

    private void downloadPlaceInfo(final String page){
        AsyncTask<Void, Void, Void> downloadTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                LakbayApp app = (LakbayApp) getApplication();
                HttpClient hc = app.get_httpClient();
                String apiURL = "http://wiki-sherpa.appspot.com/api/1/page";
                apiURL += page;

                HttpGet apiREQ = new HttpGet(apiURL);

                try{
                    HttpResponse response = hc.execute(apiREQ);
                    int retStatus = response.getStatusLine().getStatusCode();
                    if(retStatus!= HttpStatus.SC_OK){
                        Log.e("Error", "Invalid Status Code");
                    }
                    String jsonStr = EntityUtils.toString(response.getEntity());
                    parsePlacesData(jsonStr);

                } catch (Exception e) {
                    Log.e("Error", "Exception occurred: " + e.getMessage());
                }
                return null;
            }
        };
        downloadTask.execute();
    }
}
