package com.example.abevillalobos.lakbayph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        LakbayApp app = (LakbayApp) getApplication();
        app.restoreUserData();
        String username = app.get_username();
        String password = app.get_password();
        String status = app.getStatus();

        final EditText edtStatus = (EditText) findViewById(R.id.editStatus_text);
        final EditText edtPassword = (EditText) findViewById(R.id.editPassword_text);
        final TextView txvName = (TextView) findViewById(R.id.userinfo_username);

        edtStatus.setText(status);
        edtPassword.setText(password);
        txvName.setText(username);

        ImageButton btnSave = (ImageButton) findViewById(R.id.btn_Save);
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newPassword = edtPassword.getText().toString();
                        String newStatus = edtStatus.getText().toString();

                        QwitterApplication app = (QwitterApplication) getApplication();
                        app.set_password(newPassword);
                        app.set_status(newStatus);
                        app.saveUserData();

                        Intent returnToMessage = new Intent(UserInfo.this, Message.class);
                        startActivity(returnToMessage);
                    }
                }
        );



    }
}
