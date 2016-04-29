package com.example.abevillalobos.lakbayph;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedInputStream;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Created by Agassi on 29/04/2016.
 */
public class LakbayApp extends Application{ private String _username = "";
    private String _password = "";
    private boolean _bRemember = false;
    private String _status = "";
    private String messages = "";
    private HttpClient _httpClient = new DefaultHttpClient();

    //SharedPreferences _prefs = null;

    public String get_password() {
        return _password;
    }

    public String get_username() {
        return _username;
    }

    public String getStatus() {
        return _status;
    }

    public boolean getRemember() {
        return _bRemember;
    }

    public void set_username(String username) {
        _username = username;
    }

    public void set_password(String password){
        _password = password;
    }

    public void set_status(String status) {
        _status = status;
    }

    public void set_bRemember(boolean bRemember) {
        _bRemember = bRemember;
    }

    public void restoreUserData(){
        SharedPreferences prefs = getSharedPreferences("com.example.ecce_219_pc12_user01.qwitter", Context.MODE_PRIVATE);

        _username = prefs.getString("USERNAME", "");
        _password = prefs.getString("PASSWORD", "");
        _status = prefs.getString("STATUS", "");

//        if (_username.equals("") == false) {
//            _bRemember = true;
//        } else {
//            _bRemember = false;
//        }
//
//        return;
    }

    public void saveUserData() {
        SharedPreferences prefs = getSharedPreferences("com.example.ecce_219_pc12_user01.qwitter", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEdit = prefs.edit();

        prefsEdit.putString("USERNAME", _username);
        prefsEdit.putString("PASSWORD", _password);
        prefsEdit.putString("STATUS", _status);

        prefsEdit.commit();
    }

    public void clearStoredUserData() {
        SharedPreferences prefs = getSharedPreferences("com.example.ecce_219_pc12_user01.qwitter", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEdit = prefs.edit();

        prefsEdit.putString("USERNAME", "");
        prefsEdit.putString("PASSWORD", "");
        prefsEdit.putString("STATUS", "");

        prefsEdit.commit();
    }

    public String getMessageData() {
        if (_username == null) {
            return "";
        }

        if (_username.equals("")) {
            return "";
        }

        try {
            if (FSUtil.isStorageReady()) {
                Log.i("INFO", "Reading file...");
                BufferedInputStream is = new BufferedInputStream(FSUtil.getFileInputStream(_username));

                String dataStr = "";
                int cInp = 0;
                while (is.available() > 0) {
                    cInp = is.read();
                    dataStr += (char)(cInp);
                }
                is.close();
                Log.i("INFO", "Reading done: " + dataStr);
                return dataStr;
            }
        } catch (Exception e) {
            Log.e("ERROR", "Exception occurred" + e.getMessage());
        }
        return "";
    }

    public void saveMessageData(String messages){
        if (_username == null) {
            return;
        }

        if (_username.equals("")) {
            return;
        }

        if (FSUtil.isStorageReady()) {
            Log.i("INFO", "Writing file...");
            FSUtil.write(_username, messages.getBytes());
            Log.i("INFO", "Writing done.");
        }

        return;
    }

    public HttpClient get_httpClient() {
        return _httpClient;
    }
}
