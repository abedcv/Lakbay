package com.example.abevillalobos.lakbayph;

import android.app.ListActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.util.Log;

import android.widget.ArrayAdapter;


import java.util.ArrayList;
public class UserActivity extends ListActivity {

    ArrayList<String> visitedPlaces = new ArrayList<String>();
    ArrayAdapter<String> sndAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        LakbayApp app = (LakbayApp) getApplication();
        app.restoreUserData();
        String username = app.get_username();
        boolean bRemember = app.is_bRemember();
        String hometown = app.get_hometown();


        TextView txvUser = (TextView) findViewById(R.id.txv_logged_username);
        txvUser.setText(username);
        TextView txvHometown = (TextView) findViewById(R.id.txv_hometown);
        txvHometown.setText(hometown);

//        ImageButton btnEdit = (ImageButton) findViewById(R.id.editButton);
//        btnEdit.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent logToEdit = new Intent(Message.this, UserInfo.class);
//                        startActivity(logToEdit);
//                        return;
//                    }
//
//                }
//        );

        sndAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, visitedPlaces);
        setListAdapter(sndAdapter);
        final EditText edtMessage = (EditText) findViewById(R.id.edt_status);

        Button btnMessage = (Button) findViewById(R.id.btn_post);
        btnMessage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String message = edtMessage.getText().toString();
                        if (message == null){
                            return;
                        }
                        visitedPlaces.add(0, message);
                        sndAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        LakbayApp app = (LakbayApp) getApplication();
        String messages = app.getMessageData();

        String msgArr[] = messages.split(";");

        for (String msg : msgArr) {
            Log.i("INFO", "Found message: " + msg);
            if (!msg.equals("")) {
                visitedPlaces.add(msg);
            }
        }

        super.onStart();
        return;
    }
    @Override
    protected void onStop() {
        LakbayApp app = (LakbayApp) getApplication();
        boolean bRemember = app.is_bRemember();

        String messages = "";
        for (String msg : visitedPlaces) {
            if (!msg.equals("")) {
                messages += msg;
                messages += ";";
            }
        }
        if (bRemember == true) {
            app.saveMessageData(messages);
        }
        else
        {
            app.clearStoredUserData();
        }
        super.onStop();
        return;
    }
}

