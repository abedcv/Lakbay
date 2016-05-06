package com.example.abevillalobos.lakbayph;

/**
 * Created by AbeVillalobos on 04/05/2016.
 */
public class PlaceList {
    private String _url = "---";
    private String _name = "---";

    public PlaceList(String url, String name){
        _url=url;
        _name=name;
    }

    public String get_url() {
        return _url;
    }

    public String get_name() {
        return _name;
    }

    public String toString() {
        return _name;
    }
}
