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

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String toString() {
        return _name;
    }
}
