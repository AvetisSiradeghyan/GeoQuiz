package com.example.myapplication;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

public class Map_Model {
    String Town;
    Double Lat;
    Double Lng;

    public Map_Model(String town, Double lat, Double lng) {
        Town = town;
        Lat = lat;
        Lng = lng;
    }

    public String getTown() {
        return Town;
    }

    public Double getLat() {
        return Lat;
    }

    public Double getLng() {
        return Lng;
    }
}
