package com.example.abevillalobos.lakbayph;

/**
 * Created by AbeVillalobos on 05/05/2016.
 */
public class PlaceInfo {

    private String _pname = "---";
    private String _date = "---";
    private String _location = "---";
    private String _content = "---";

    public PlaceInfo(String pname, String date, String location, String content){
        _pname = pname;
        _date = date;
        _location = location;
        _content = content;
    }

    public String get_pname() {
        return _pname;
    }

    public String get_date() {
        return _date;
    }

    public String get_location() {
        return _location;
    }

    public String get_content() {
        return _content;
    }
}
