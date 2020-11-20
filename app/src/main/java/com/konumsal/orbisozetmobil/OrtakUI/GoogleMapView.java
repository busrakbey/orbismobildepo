package com.konumsal.orbisozetmobil.OrtakUI;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.konumsal.orbisozetmobil.R;


/**
 * Created by isahin on 28.5.2017.
 */
public class GoogleMapView extends AppCompatActivity implements OnMapReadyCallback {

    View rootView;
    private GoogleMap map;
    MapView mapView;
    int otel = 0;
    Long dir_id = 0L;



    double longitude;
    double latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map_view_ly);
        otel = getIntent().getIntExtra("otel", 0);
        dir_id = getIntent().getLongExtra("dir_id", 0L);
        Log.v("google param", String.valueOf(otel));

        //loadPlacesFromSqlite();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


   /* private void loadPlacesFromSqlite() {
        Ziyaret_Data data = new Ziyaret_Data(this);
        StringBuilder sqlStr = new StringBuilder();
        if (otel == 1)
            sqlStr.append("SELECT * FROM ZIYARET d where d.Deleted = 0 and d.Otel = 1");
        else if (otel == 0)
            sqlStr.append("SELECT * FROM ZIYARET d where d.Deleted = 0 and d.Otel is null");

        try {
            ziyaret_list = new ArrayList<Ziyaret>();
            ziyaret_list = data.loadFromQuery(sqlStr.toString());
            Log.v("*****ziyaret list", "size=>" + ziyaret_list.size());
            for (Ziyaret ziyaret : ziyaret_list) {
                Log.v("ziyaret properties", "==>" + ziyaret.getId() + "-" + ziyaret.getMid() + "-" + ziyaret.getZiyaret_yer_adi());
            }

            if (ziyaret_list.size() == 0) {
                Log.v("hic place", "girilmemis");
            }

        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }*/

    private GoogleMap mMap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*for (Ziyaret place : ziyaret_list) {


            if (!String.valueOf(place.getDirectory_id()).equals(String.valueOf(dir_id))) {
                LatLng coor = new LatLng(Double.valueOf(place.getX_koor()), Double.valueOf(place.getY_koor()));
                map.addMarker(new MarkerOptions().position(coor).title(place.getZiyaret_yer_adi()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                map.moveCamera(CameraUpdateFactory.newLatLng(coor));
            } else {
                LatLng coor = new LatLng(Double.valueOf(place.getX_koor()), Double.valueOf(place.getY_koor()));
                Marker marker = map.addMarker(new MarkerOptions().position(coor).title(place.getZiyaret_yer_adi()));
                map.moveCamera(CameraUpdateFactory.newLatLng(coor));
                marker.showInfoWindow();
            }
        }*/



        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(39, 39);
        mMap.addMarker(new MarkerOptions().position(sydney).title(String.valueOf("1"))).showInfoWindow();

        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        mMap.setMyLocationEnabled(true);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



}
