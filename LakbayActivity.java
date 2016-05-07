package com.example.abevillalobos.lakbayph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class LakbayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lakbay);


        ImageButton lBtnLakbay = (ImageButton) findViewById(R.id.l_btn_lakbay);
        lBtnLakbay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LakbayActivity.this, "Starting app...", Toast.LENGTH_SHORT).show();
                Intent LakbaytoLoginIntent = new Intent(LakbayActivity.this, LoginActivity.class);
                startActivity(LakbaytoLoginIntent);

            }
        });


    }
}
