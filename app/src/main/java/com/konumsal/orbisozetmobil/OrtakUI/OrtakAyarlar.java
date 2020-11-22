package com.konumsal.orbisozetmobil.OrtakUI;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.konumsal.orbisozetmobil.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DataLayer.Ortak.ConfigData;
import DataLayer.Ortak.OrtakKisiHareket_Data;
import EntityLayer.GeoPortal.Geometry;
import EntityLayer.Ortak.OrtakKisiHareket;
import EnumsLayer.HttpRequestType;
import ToolLayer.DateUtils;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;
import ToolLayer.StateReceiver;

/**
 * Created by isahin on 1.3.2017.
 */
public class OrtakAyarlar {

    public static LocationManager locationManager;
    private String provider;
    private static MyLocationListener mylistener;
    private Criteria criteria;
    int TIME_INTERVAL = 5000;

    Context context;
    boolean koordinat_gonderme_sonlandir = false;
    public static boolean switch_button_aktif = false;

    public void showAyarlarPage(Context context_) {

        context = context_;
        // Create custom dialog object
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Ayarlar");


        dialog.setContentView(R.layout.ayarlar_popup);
        final Switch switch_lokasyon = (Switch) dialog.findViewById(R.id.lokasyon_izin_ver_switch);
        switch_lokasyon.setChecked(switch_button_aktif);
        dialog.show();


        Button declineButton = (Button) dialog.findViewById(R.id.ayarlar_kapat);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        Button kaydetButton = (Button) dialog.findViewById(R.id.ayarlar_kaydet);
        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrtakFunction.lokasyon_izin_ver = switch_lokasyon.isChecked();
                if (OrtakFunction.lokasyon_izin_ver) {
                    switch_button_aktif = true;
                    get_mevcut_kisi_hareket();
                    init_koordinat();
                } else {
                    koordinat_gonderme_sonlandir = true;
                    switch_button_aktif = false;
                    Toast toast = Toast.makeText(context, "konum gönderme durduruldu", Toast.LENGTH_SHORT);
                    toast.show();
                }
                dialog.dismiss();
            }
        });
    }

    private void removeLocationManager() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(mylistener);
        }
        else
        {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.removeUpdates(mylistener);
        }
            Log.v("remove","location update");
    }


    private void init_koordinat() {



        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        mylistener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, mylistener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, mylistener);



      /*  locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);   //default
        criteria.setCostAllowed(false);
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission( context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }*/

      /*  try {
            final Location location = locationManager.getLastKnownLocation(provider);
            if (mylistener == null)
                mylistener = new MyLocationListener();
            if (location != null) {
                mylistener.onLocationChanged(location);

            } else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
            locationManager.requestLocationUpdates(provider, TIME_INTERVAL, 0, mylistener);
        }
        catch (Exception ex)
        {
            Log.v("LOCATION ","EXCEPTION");
        }*/

    }



    List<OrtakKisiHareket> mevcut_kisi_hareket_list = new ArrayList<OrtakKisiHareket>();
    OrtakKisiHareket mevcut_kisi_hareket;
    private void get_mevcut_kisi_hareket()
    {
        OrtakKisiHareket_Data data = new OrtakKisiHareket_Data(context);
        StringBuilder sqlStr = new StringBuilder();

        String k_id = "";
        if(OrtakFunction.vip_user_list.contains(OrtakFunction.kullanici_adi))
            k_id = OrtakFunction.gecici_personel_id;
        else
            k_id = String.valueOf(OrtakFunction.kullanici_id);

        sqlStr.append("SELECT * from  ORTAK_KISI_HAREKET okh  WHERE okh.personelId = "+ k_id);

        try {
            mevcut_kisi_hareket_list = data.loadFromQuery(sqlStr.toString());
            if(mevcut_kisi_hareket_list.size() > 0) {
                mevcut_kisi_hareket = new OrtakKisiHareket();
                mevcut_kisi_hareket = mevcut_kisi_hareket_list.get(0);
            }

        } catch (OrbisDefaultException e) {
            e.printStackTrace();
        }
    }

    private void ortak_kisi_hareket_sunucuya_gonder(double blx , double bly) {
        if (StateReceiver.isNetworkAvailable(context))
        {

            Geometry geo = new Geometry();
            geo.x = 0.0;
            geo.y = 0.0;
            new itemSyncKisiKoordinat().execute(geo);

            String dateString = "";
            try {
                dateString = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss.SSS").format(new Date(Long.valueOf(mevcut_kisi_hareket.getSonHareketMs())));

            }
            catch (Exception ex)
            {

            }
            Toast toast = Toast.makeText(context, "konumunuz sunucuya gönderiliyor!\n"+String.valueOf(blx)+"  "+String.valueOf(bly)+"\n"+dateString+"\n"+mevcut_kisi_hareket.getSonHareketMs(), Toast.LENGTH_SHORT);
            toast.show();

        }else
        {
            Toast toast = Toast.makeText(context, "internet bağlantısı olmadığından konum gönderilemiyor!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    private class MyLocationListener implements LocationListener {

        @Override

        public void onLocationChanged(Location location) {

        /*    OrtakKisiHareket_Data data = new OrtakKisiHareket_Data(Home2Activity.this);
            try {
                data.clearDatabaseTable();
            } catch (OrbisDefaultException e) {
                e.printStackTrace();
            }
*/


            Log.v("Location changed!","MyLocationListener Home activity");



            Double blx= location.getLongitude();
            Double bly=location.getLatitude();

            long time= System.currentTimeMillis();

            if(mevcut_kisi_hareket == null)
            {
                Log.v("mevcut id ","null");
                String k_id = "";
                String birim_id = "";
                if(OrtakFunction.vip_user_list.contains(OrtakFunction.kullanici_adi))
                {k_id = OrtakFunction.gecici_personel_id;

                    birim_id = OrtakFunction.admine_ozel_birim_id;
                }
                else {
                    k_id = String.valueOf(OrtakFunction.kullanici_id);
                    birim_id = OrtakFunction.birim_id;
                }


                Log.v("unvan",OrtakFunction.kullanici_unvan_adi);
                mevcut_kisi_hareket = new OrtakKisiHareket();
                mevcut_kisi_hareket.setPersonelId(Long.valueOf(k_id));
                mevcut_kisi_hareket.setBirimId(Long.valueOf(birim_id));
                Log.v("date","=>"+new DateUtils().dateNowLong);
                Log.v("date","=>"+new DateUtils().dateNowDateString);

                mevcut_kisi_hareket.setSonHareketZamani(new DateUtils().dateNowLong);

                mevcut_kisi_hareket.setAdi(OrtakFunction.kullanici_gercek_adi);
                mevcut_kisi_hareket.setSoyadi(OrtakFunction.kullanici_soyadi);
                mevcut_kisi_hareket.setUnvan(OrtakFunction.kullanici_unvan_adi);
                mevcut_kisi_hareket.setXkoordinati(String.valueOf(blx));
                mevcut_kisi_hareket.setYkoordinati(String.valueOf(bly));
                mevcut_kisi_hareket.setSonHareketMs(time);
                mevcut_kisi_hareket.setLokasyonAktif(true);

                if(koordinat_gonderme_sonlandir)
                    mevcut_kisi_hareket.setLokasyonAktif(false);


                OrtakKisiHareket_Data detay_data = new OrtakKisiHareket_Data(context);
                List<OrtakKisiHareket> detay_list = new ArrayList<OrtakKisiHareket>();
                detay_list.add(mevcut_kisi_hareket);

                try {
                    boolean control =   detay_data.insertFromContent(detay_list);
                    if(control)
                    {
                        if(String.valueOf(mevcut_kisi_hareket.getId()).equals(("0")))
                            mevcut_kisi_hareket.setId(null);

                        ortak_kisi_hareket_sunucuya_gonder(blx,bly);
                    }
                } catch (OrbisDefaultException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                mevcut_kisi_hareket.setLokasyonAktif(true);

                if(koordinat_gonderme_sonlandir)
                    mevcut_kisi_hareket.setLokasyonAktif(false);

                Log.v("unvan",OrtakFunction.kullanici_unvan_adi);
                mevcut_kisi_hareket.setSonHareketMs(time);
                Log.v("date2","=>"+new DateUtils().dateNowLong);
                Log.v("date2","=>"+new DateUtils().dateNowDateString);

                mevcut_kisi_hareket.setSonHareketZamani(new DateUtils().dateNowLong);

                mevcut_kisi_hareket.setXkoordinati(String.valueOf(blx));
                mevcut_kisi_hareket.setYkoordinati(String.valueOf(bly));

                if(String.valueOf(mevcut_kisi_hareket.getId()).equals(("0")))
                    mevcut_kisi_hareket.setId(null);

                ortak_kisi_hareket_sunucuya_gonder(blx,bly);
            }


            if(koordinat_gonderme_sonlandir)
                removeLocationManager();

        }

        @Override

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.v("On status change!",String.valueOf(status));
        }


        @Override

        public void onProviderEnabled(String provider) {
            Log.v("provider","enabled");
        }

        @Override

        public void onProviderDisabled(String provider) {
            Log.v("provider","disabled");
        }
    }



    class itemSyncKisiKoordinat extends AsyncTask<Geometry,String,String>
    {
        Boolean status=true;
        StringBuilder strMess= new StringBuilder();

        @Override
        protected void onPreExecute() {
            try {

            } catch (Exception e) {
                e.printStackTrace();
                MessageBox.showAlert(context,"İşlem gerçekleştirilemedi !\n"+e.toString(),false);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {


        }

        @Override
        protected void onPostExecute(String a) {

        }


        @Override
        protected String doInBackground(Geometry... params) {
            //  Geometry geo = null;

            try {
                RSOperator rsOperator = new RSOperator();
                // geo =params[0];
                ConfigData cd = new ConfigData(context);
                String url = cd.getSERVICURL()+context.getResources().getString(R.string.OrtakRS_upload_kisi_location);
                Log.v("senkron url","=>"+url);
                //Log.v("geo ","=>"+geo.x+"-"+geo.y);



                String kullaniciId = String.valueOf(OrtakFunction.kullanici_id);//String.valueOf(ul.get(0).getId());

                String  s= rsOperator.CreateToRSUrlConnection(HttpRequestType.POST, mevcut_kisi_hareket, url ,"");
                //  s.replace("\"id\":0","\"id\":null");
                Log.v("sssssss","==>"+s);


                if (s!=null && s.trim().trim().length()>3)
                {
                    OrtakKisiHareket nhareket = new OrtakKisiHareket();
                    nhareket = rsOperator.convertJSONToEntity(s,OrtakKisiHareket.class,nhareket);
                    if(nhareket !=null && nhareket.getId()>0)
                    {
                        if (status)
                        {

                            boolean ana_hareket_bilgileri = false;
                            List<OrtakKisiHareket> temp_hareket_list = new ArrayList<OrtakKisiHareket>();
                            List<OrtakKisiHareket> temp_hareket_list2 = new ArrayList<OrtakKisiHareket>();
                            temp_hareket_list.add(mevcut_kisi_hareket);
                            temp_hareket_list2.add(nhareket);

                            if(mevcut_kisi_hareket.getId() == null || String.valueOf(mevcut_kisi_hareket.getId()).equals("0"))
                            {

                                OrtakKisiHareket_Data data = new OrtakKisiHareket_Data(context);
                                StringBuilder sqlStr = new StringBuilder();
                                sqlStr.append("SELECT * FROM ORTAK_KISI_HAREKET ohk WHERE ohk.mid = "+mevcut_kisi_hareket.getMid());
                                List<OrtakKisiHareket> update_list = new ArrayList<OrtakKisiHareket>();
                                try {
                                    update_list = data.loadFromQuery(sqlStr.toString());
                                    for (OrtakKisiHareket item:update_list) {
                                        item.setId(nhareket.getId());
                                        mevcut_kisi_hareket.setId(nhareket.getId());
                                        Log.v("hareket id guncellendi","ok");
                                    }
                                    if(update_list.size()>0)
                                        data.updateFromContent(update_list);

                                } catch (OrbisDefaultException e) {
                                    e.printStackTrace();
                                }


                            }

                        }
                        else
                        {
                            // strMess.append("Vejetasyon güncellenemedi !\n Cihazınızdaki kayıt güncellemesi başarısız..");
                            status=false;
                            return null;
                        }
                    }
                    else
                    {
                        // strMess.append(" Hata :Vejetasyon güncellenemedi !\n Gönderilen kayıt geri alınamadı..");
                        status=false;
                        return null;

                    }

                } else
                {
                    //   strMess.append("Vejetasyon güncellenemedi !\n Server ile senkronizasyon gerçekleştirilemedi..\nResponse string is null");
                    status=false;
                    return null;
                }

            }catch (OrbisDefaultException e)
            {
                e.printStackTrace();
                status =false;
                // strMess.append("VejetasyonController->Senkronizasyon gerçekleştirilemedi\n"+e.toString());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                status =false;
                // strMess.append("VejetasyonController->Senkronizasyon gerçekleştirilemedi\n"+e.toString());
            }catch (Throwable e)
            {
                e.printStackTrace();
                status =false;
                // strMess.append("VejetasyonController->Senkronizasyon gerçekleştirilemedi\n"+e.toString());
            }
            return "a";
        }
    }
}
