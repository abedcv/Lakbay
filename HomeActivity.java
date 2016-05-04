package com.example.abevillalobos.lakbayph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button hBtnUser= (Button) findViewById(R.id.h_btn_user);
        hBtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoUserIntent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(HometoUserIntent);
            }
        });

        Button hBtnAbout= (Button) findViewById(R.id.h_btn_about);
        hBtnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoAboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(HometoAboutIntent);
            }
        });

        Button hBtnBeach= (Button) findViewById(R.id.h_imb_beach);
        hBtnBeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoPlaceListIntent = new Intent(HomeActivity.this, PlaceListActivity.class);
                String type = "beach";
                HometoPlaceListIntent.putExtra("TYPE", type);
                startActivity(HometoPlaceListIntent);
            }
        });

        Button hBtnCulture= (Button) findViewById(R.id.h_imb_culture);
        hBtnCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoPlaceListIntent = new Intent(HomeActivity.this, PlaceListActivity.class);
                String type = "culture";
                HometoPlaceListIntent.putExtra("TYPE", type);
                startActivity(HometoPlaceListIntent);
            }
        });

        Button hBtnNature= (Button) findViewById(R.id.h_imb_nature);
        hBtnNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoPlaceListIntent = new Intent(HomeActivity.this, PlaceListActivity.class);
                String type = "nature";
                HometoPlaceListIntent.putExtra("TYPE", type);
                startActivity(HometoPlaceListIntent);
            }
        });

        Button hBtnFestivals= (Button) findViewById(R.id.h_imb_festivals);
        hBtnFestivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoPlaceListIntent = new Intent(HomeActivity.this, PlaceListActivity.class);
                String type = "festivals";
                HometoPlaceListIntent.putExtra("TYPE", type);
                startActivity(HometoPlaceListIntent);
            }
        });

        Button hBtnNearby= (Button) findViewById(R.id.h_imb_nearby);
        hBtnNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HometoPlaceListIntent = new Intent(HomeActivity.this, PlaceListActivity.class);
                String type = "nearby";
                HometoPlaceListIntent.putExtra("TYPE", type);
                startActivity(HometoPlaceListIntent);
            }
        });

    }
}
