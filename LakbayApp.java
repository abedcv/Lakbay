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

    private String piloc = "";
    private String picont = "";

    private HttpClient _httpClient = new DefaultHttpClient();

    public HttpClient get_httpClient() {
        return _httpClient;
    }

    public String getPiloc() {
        return piloc;
    }

    public void setPiloc(String piloc) {
        this.piloc = piloc;
    }

    public String getPicont() {
        return picont;
    }

    public void setPicont(String picont) {
        this.picont = picont;
    }
}