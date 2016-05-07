package com.example.abevillalobos.lakbayph;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PlaceInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        Button hBtnUser= (Button) findViewById(R.id.pi_btn_user);
        hBtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PlaceInfotoUserIntent = new Intent(PlaceInfoActivity.this, UserActivity.class);
                startActivity(PlaceInfotoUserIntent);
            }
        });

        LakbayApp app = (LakbayApp) getApplication();
        Intent GetExtra = getIntent();
        String name = GetExtra.getStringExtra("NAME");
        String type = GetExtra.getStringExtra("TYPE");

        TextView places = (TextView) findViewById(R.id.pi_place);
        String placesearch = "PLACES > " + type + " > " + name;
        places.setText(placesearch);

        TextView piName = (TextView) findViewById(R.id.pi_pname);
        TextView piLocation = (TextView) findViewById(R.id.pi_loc);
        TextView piContent = (TextView) findViewById(R.id.pi_content);

        piName.setText(app.getAname());
        piLocation.setText("Location: " + app.getAloc());
        piContent.setText(app.getAcont());
    }


}
