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

    public String getAdate() {
        return adate;
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


}