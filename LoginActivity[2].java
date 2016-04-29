package com.example.abevillalobos.lakbayph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
public class LoginActivity extends AppCompatActivity {

    boolean _bShouldRemember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LakbayApp app = (LakbayApp) getApplication();

        if (!app.getRemember()) {
            app.clearStoredUserData();
        }

        final EditText edtUsername = (EditText) findViewById(R.id.edt_username);
        final EditText edtPassword = (EditText) findViewById(R.id.edt_password);
        final CheckBox chbRemember = (CheckBox) findViewById(R.id.checkbox);
        chbRemember.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        _bShouldRemember = isChecked;
                        return;
                    }
                }
        );

        app.restoreUserData();
        String username = app.get_username();
        String password = app.get_password();
        edtUsername.setText(username);
        edtPassword.setText(password);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = edtUsername.getText().toString();
                        String password = edtPassword.getText().toString();

                        Intent logToMainIntent = new Intent(LoginActivity.this, HomeActivity.class);
//                        logToMainIntent.putExtra("USERNAME", username);
//                        logToMainIntent.putExtra("PASSWORD", password);
//                        logToMainIntent.putExtra("REMEMBER", _bShouldRemember);

                        app.set_username(username);
                        app.set_password(password);
                        app.set_bRemember(_bShouldRemember);

                        app.saveUserData();

                        startActivity(logToMainIntent);
                        return;
                    }
                }
        );

        return;
    }
}
