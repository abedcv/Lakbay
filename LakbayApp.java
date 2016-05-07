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
public class LakbayApp extends Application{


    private HttpClient _httpClient = new DefaultHttpClient();
    private HttpClient _http2 = new DefaultHttpClient();

    private String aname = "x";
    private String adate = "x";
    private String aloc = "x";
    private String acont = "x";
    private String _username = "";
    private String _hometown = "";
    private boolean _bRemember = false;
    private String _status = "";

    public HttpClient get_httpClient() {
        return _httpClient;
    }

    public HttpClient get_http2() {
        return _http2;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }


    public void setAdate(String adate) {
        this.adate = adate;
    }

    public String getAloc() {
        return aloc;
    }

    public void setAloc(String aloc) {
        this.aloc = aloc;
    }

    public String getAcont() {
        return acont;
    }

    public void setAcont(String acont) {
        this.acont = acont;
    }


    public String get_hometown() {
        return _hometown;
    }

    public void set_hometown(String _hometown) {
        this._hometown = _hometown;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public boolean is_bRemember() {
        return _bRemember;
    }

    public void set_bRemember(boolean _bRemember) {
        this._bRemember = _bRemember;
    }

    public void restoreUserData(){
        SharedPreferences _prefs = getSharedPreferences("com.example.abevillalobos.lakbayph", Context.MODE_PRIVATE);
//        if (_prefs == null) {
//            return;
//        }
        _username = _prefs.getString("USERNAME", "");
        _hometown = _prefs.getString("PASSWORD", "");
        _status = _prefs.getString("STATUS", "");

        if (_username .equals("") == false){
            _bRemember = true;
        }
        else {
            _bRemember = false;
        }
        return;
    }
    public void saveUserData(){
        SharedPreferences _prefs = getSharedPreferences("com.example.abevillalobos.lakbayph", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEdit = _prefs.edit();

        prefsEdit.putString("USERNAME", _username);
        prefsEdit.putString("PASSWORD", _hometown);
        prefsEdit.putString("STATUS", _status);

        prefsEdit.commit();
    }

    public void clearStoredUserData() {
        SharedPreferences prefs = getSharedPreferences("com.example.abevillalobos.lakbayph", Context.MODE_PRIVATE);
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

}