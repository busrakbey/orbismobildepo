package com.konumsal.orbisozetmobil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapOptions;
import com.esri.android.map.MapView;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Point;


public class ActivitEsri extends AppCompatActivity {
    MapView mMapView;
    Integer NOKTANO;
    double XKOOR,YKOOR;
    GraphicsLayer graphicsLayer = new GraphicsLayer();
    Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activit_esri);
        NOKTANO=  getIntent().getIntExtra("NOKTANO",0);
        XKOOR=getIntent().getDoubleExtra("XKOOR",0.0);
        YKOOR = getIntent().getDoubleExtra("YKOOR",0.0);

        if(NOKTANO != null)
            Log.v("map NOKTA NO->",String.valueOf(NOKTANO));
        else
            Log.v("nokta is null","asda");


        Log.v("esri mape geldi", "asd");
      //  mMapView = (MapView) findViewById(R.id.map);
        MapOptions options = new MapOptions(MapOptions.MapType.TOPO , XKOOR , YKOOR , 25);
        mMapView = new MapView(this,options);


        Log.v("harita bulundu", "asd");


     //  final Layer oneMapLayer = new ArcGISTiledMapServiceLayer("http://e1.onemap.sg/arcgis/rest/services/BASEMAP/MapServer");
        mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {

            @Override
            public void onStatusChanged(Object source, STATUS status) {

                if(status == STATUS.INITIALIZED && source == mMapView)
                {
                  //  mMapView.centerAt(36.3799775,40.84877000005,false);
                 //   mMapView.setScale(1500,false);
                 //   mMapView.addLayer(graphicsLayer);
                  //  Point point = GeometryEngine.project(36.3,39.4,mMapView.getSpatialReference());
                   // graphicsLayer.addGraphic(new Graphic(point,new SimpleMarkerSymbol(Color.RED,10, SimpleMarkerSymbol.STYLE.CIRCLE)));
                    Log.v("test", "res "+String.valueOf(mMapView.getResolution()));
                }
            }
        });
      //  mMapView.addLayer(oneMapLayer);





     //   MapOptions options = new MapOptions(MapOptions.MapType.TOPO , XKOOR , YKOOR , 25);
      //  mMapView.setMapOptions(options);


        //  mMapView.centerAndZoom(XKOOR , YKOOR , 1);



       // LocationDisplayManager ldm = mMapView.getLocationDisplayManager();
        // ldm.setShowLocation(true);




    }

    @Override
    protected void onPause() {
        super.onPause();

        // Call MapView.pause to suspend map rendering while the activity is paused, which can save battery usage.
        if (mMapView != null)
        {
            mMapView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Call MapView.unpause to resume map rendering when the activity returns to the foreground.
        if (mMapView != null)
        {
            mMapView.unpause();
        }
    }

}
