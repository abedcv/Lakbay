package com.example.abevillalobos.lakbayph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.content.Intent;
public class LoginActivity extends AppCompatActivity {

    boolean _bShouldRemember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LakbayApp app = (LakbayApp) getApplication();

        final EditText edtUsername = (EditText) findViewById(R.id.edt_username);
        final EditText edtHometown = (EditText) findViewById(R.id.edt_hometown);
        final CheckBox chbRemember = (CheckBox) findViewById(R.id.chb_remember);
        chbRemember.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        _bShouldRemember = isChecked;
                    }
                }
        );

        app.restoreUserData();
        String username = app.get_username();
        final String hometown = app.get_hometown();
        edtUsername.setText(username);
        edtHometown.setText(hometown);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = edtUsername.getText().toString();
                        String hometown = edtHometown.getText().toString();

                        Intent logToMainIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        String nbyraw = edtHometown.getText().toString();
                        String nby = nbyraw.replace(" ", "%20");
                        logToMainIntent.putExtra("NBY", nby);
                        logToMainIntent.putExtra("HOME", nbyraw);
//                        logToMainIntent.putExtra("USERNAME", username);
//                        logToMainIntent.putExtra("PASSWORD", password);
//                        logToMainIntent.putExtra("REMEMBER", _bShouldRemember);

                        app.set_username(username);
                        app.set_hometown(hometown);
                        app.set_bRemember(_bShouldRemember);

                        if (_bShouldRemember == true) {
                            app.saveUserData();
                        } else {
                            app.clearStoredUserData();
                        }

                        startActivity(logToMainIntent);
                        return;
                    }
                }
        );

        return;
    }
}
