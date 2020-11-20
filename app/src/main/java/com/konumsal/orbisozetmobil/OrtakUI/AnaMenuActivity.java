package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.reflect.TypeToken;
import com.konumsal.orbisozetmobil.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import AdapterLayer.Ortak.AnaMenuAdapter;
import DataLayer.Fidanlik.FidanAjaxTurData;
import DataLayer.IsletmePazarlama.MuhStokLokasyon_Data;
import DataLayer.Ortak.ConfigData;
import DataLayer.Ortak.GpBolme_Data;
import DataLayer.Ortak.MobilGuzergah_Data;
import DataLayer.Ortak.OrtakAgacTuru_Data;
import DataLayer.Ortak.OrtakIsKalemleri_Data;
import DataLayer.Ortak.OrtakKamera_Data;
import DataLayer.Ortak.OrtakKisiHareket_Data;
import DataLayer.Ortak.OrtakKisi_Data;
import DataLayer.Ortak.OrtakNotKonu_Data;
import DataLayer.Ortak.OrtakNot_Data;
import DataLayer.Ortak.OrtakOdunTuru_Data;
import DataLayer.Ortak.OtherUsers_Data;
import DataLayer.Ortak.STown_Data;
import DataLayer.Ortak.SanatYapi_Data;
import DataLayer.Ortak.Unvan_Data;
import DataLayer.Ortak.User_Data;
import DataLayer.Ortak.YolBilgi_Data;
import DataLayer.Sistem.SCalisan_Data;
import DataLayer.Sistem.SKullanici_Data;
import DataLayer.Sistem.SModulKodDeger_Data;
import DataLayer.Sistem.SOrgBirim_Data;
import EntityLayer.Ortak.User;
import EntityLayer.Sistem.SOrgBirim;
import EnumsLayer.HttpRequestType;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;

public class AnaMenuActivity extends AppCompatActivity {

    GridView lst_alt_menu_listview;
    Toolbar toolbar;
    List<String> menuList;
    AnaMenuAdapter menuItemsAdapter;
    public int kontrol = 0;

    ArrayList<SOrgBirim> all_list_SOrgBirim;
    Type typeOf_SOrgBirim=null;
    String all_jsonSOrgBirim="";
    List<SOrgBirim> org_birim_list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ana_menu_activity);

        Init();
        initToolBar();

        if(OrtakFunction.mContext == null)
            OrtakFunction.mContext = getBaseContext();

        getBolgeMudurlukSeflik();

        if (kontrol == 0 && OrtakFunction.s_org_birim_path == null) {

            kontrol = 1;
            Log.v("home2", "2");
            ortakVeriIndir("1");
        }
        else{
           // new getBaseDataServiceForSorgulamalar().execute();
        }


    }

    private void initToolBar() {
        try {

            toolbar = (Toolbar) findViewById(R.id.ip_alt_menu_list_ui_toolbar);
            setSupportActionBar(toolbar);
            //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnaMenuActivity.this.finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(AnaMenuActivity.this, "Hata:"+e.toString(), false);

        }catch (Throwable e) {
            e.printStackTrace();
            MessageBox.showAlert(AnaMenuActivity.this, "Hata:"+e.toString(), false);
        }
    }


    private void menuEkle() {
        menuList.add("Ağaçlandırma ve Toprak Muhafaza");
        menuList.add("Amenajman");
        menuList.add("Bilgi Sistemleri");
        menuList.add("Destek Hizmetleri");
        menuList.add("Dış İlişkiler");
        menuList.add("Fidanlık");
        menuList.add("Hukuk");
        menuList.add("İşletme ve Pazarlama");
        menuList.add("İnşaat ve İkmal");
        menuList.add("İzin İrtifak");
        menuList.add("Kadastro ve Mülkiyet");
        menuList.add("Odun Dışı");
        menuList.add("Orköy");
        menuList.add("Orman Zararlarıyla Mücadele");
        menuList.add("Orman İdaresi");
        menuList.add("Orman Yangınları");
        menuList.add("Personel");
        menuList.add("Silvikültür");
        menuList.add("Strateji");
        menuList.add("Teftiş");

    }


    private void Init() {


        lst_alt_menu_listview= (GridView) findViewById(R.id.ip_alt_menu_list_listView);
        menuList = new ArrayList<String>();
        menuEkle();
        //pd2 = new ProgressDialog(AnaMenuActivity.this);
        all_list_SOrgBirim = new ArrayList<SOrgBirim>();
        typeOf_SOrgBirim = new TypeToken<List<SOrgBirim>>(){}.getType();
        //menuItemsAdapter = new ArrayAdapter<String>(this, android.R.layout. , menuList);

        menuItemsAdapter = new AnaMenuAdapter(AnaMenuActivity.this,R.layout.ana_menu_activity, menuList , 0);
        lst_alt_menu_listview.setAdapter(menuItemsAdapter);

        lst_alt_menu_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, FiltreActivity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("DDMES","1");
                    startActivity(mintent);

                }
                else if(position == 1)
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("DDMES","2");
                    startActivity(mintent);
                }

                else if(position == 2)
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("DDMES","3");
                    startActivity(mintent);
                }

                else if(position == 3)
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("DDMES","4");
                    startActivity(mintent);
                }

                else if(position == 4)
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mintent);
                }

                else if(position == 5){
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("DDMES","5");
                    startActivity(mintent);
                }


                else if(position == 6){
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("PAGEID","0");
                    startActivity(mintent);
                }

                else if(position == 7)
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("PAGEID","0");
                    startActivity(mintent);
                }


                else if(position ==8 )
                {
                    Intent mintent = new Intent(AnaMenuActivity.this, Home2Activity.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mintent.putExtra("MODE",1);
                    mintent.putExtra("DDMES","8");
                    startActivity(mintent);
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu1, menu);
        Log.v("main", "options menu");
        return true;
    }

    OrtakAyarlar oa;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Log.v("home1", "1");
                ortakVeriIndir("0");
                return true;

            case R.id.menu2:
                Log.v("home2", "1");
                if(oa == null)
                    oa = new OrtakAyarlar();
                oa.showAyarlarPage(AnaMenuActivity.this);

                return true;

            case R.id.menu3:
                Log.v("home4", "1");

                new AlertDialog.Builder(AnaMenuActivity.this)
                        .setTitle("Orbis Mobile Sistem Bilgisi")
                        .setMessage("Cihazınızdaki kullanıcı verileri kalıcı olarak silinecektir.Onaylıyor musunuz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int which) {
                                try {
                                    verileri_sil();
                                } catch (OrbisDefaultException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void ortakVeriIndir(String ilk_giris) {
        Intent mintent = new Intent(AnaMenuActivity.this, ConfigSettingsActivity.class);
        mintent.putExtra("ilk_giris", ilk_giris);
        startActivity(mintent);
    }


    private void verileri_sil() throws OrbisDefaultException {


        SModulKodDeger_Data sModulKodDegerData = new SModulKodDeger_Data(AnaMenuActivity.this);
        SCalisan_Data sCalisanData = new SCalisan_Data(AnaMenuActivity.this);
        SOrgBirim_Data sOrgBirimData = new SOrgBirim_Data(AnaMenuActivity.this);
        SKullanici_Data sKullanici_data = new SKullanici_Data(AnaMenuActivity.this);
        OrtakAgacTuru_Data ortakAgacTuruData = new OrtakAgacTuru_Data(AnaMenuActivity.this);

        OrtakOdunTuru_Data ortakOdunTuruData = new OrtakOdunTuru_Data(AnaMenuActivity.this);
        Unvan_Data unvanData = new Unvan_Data(AnaMenuActivity.this);
   
        OtherUsers_Data otherUsersData = new OtherUsers_Data(AnaMenuActivity.this);
        GpBolme_Data gpBolmeData = new GpBolme_Data(AnaMenuActivity.this);
        MuhStokLokasyon_Data muhStokLokasyonData = new MuhStokLokasyon_Data(AnaMenuActivity.this);
        OrtakKisi_Data ortakKisiData = new OrtakKisi_Data(AnaMenuActivity.this);
        User_Data userData = new User_Data(AnaMenuActivity.this,new User());

        OrtakNot_Data ortakNotData = new OrtakNot_Data(AnaMenuActivity.this);
        OrtakNotKonu_Data ortakNotKonuData = new OrtakNotKonu_Data(AnaMenuActivity.this);
        OrtakIsKalemleri_Data ortakIsKalemleriData = new OrtakIsKalemleri_Data(AnaMenuActivity.this);
        MobilGuzergah_Data mobilGuzergahData = new MobilGuzergah_Data(AnaMenuActivity.this);
        OrtakKisiHareket_Data ortakKisiHareketData = new OrtakKisiHareket_Data(AnaMenuActivity.this);


        STown_Data sTownData = new STown_Data(AnaMenuActivity.this);

        OrtakKamera_Data ortakKameraData = new OrtakKamera_Data(AnaMenuActivity.this);
        FidanAjaxTurData fidanAjaxTurData  = new FidanAjaxTurData(AnaMenuActivity.this);
        SanatYapi_Data sanatYapiData = new SanatYapi_Data(AnaMenuActivity.this);
        YolBilgi_Data yolBilgiData = new YolBilgi_Data(AnaMenuActivity.this);


        userData.clearDatabaseTable();
        sModulKodDegerData.clearDatabaseTable();
        sCalisanData.clearDatabaseTable();
        sOrgBirimData.clearDatabaseTable();
        sKullanici_data.clearDatabaseTable();
        ortakAgacTuruData.clearDatabaseTable();
        ortakOdunTuruData.clearDatabaseTable();
        unvanData.clearDatabaseTable();
        otherUsersData.clearDatabaseTable();
        gpBolmeData.clearDatabaseTable();
        muhStokLokasyonData.clearDatabaseTable();
        ortakKisiData.clearDatabaseTable();
        ortakNotData.clearDatabaseTable();
        ortakNotKonuData.clearDatabaseTable();
        ortakIsKalemleriData.clearDatabaseTable();
        mobilGuzergahData.clearDatabaseTable();
        ortakKisiHareketData.clearDatabaseTable();
        sTownData.clearDatabaseTable();
        ortakKameraData.clearDatabaseTable();
        fidanAjaxTurData.clearDatabaseTable();
        sanatYapiData.clearDatabaseTable();
        yolBilgiData.clearDatabaseTable();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        System.exit(0);

    }



    void getBolgeMudurlukSeflik(){

        SOrgBirim_Data data = new SOrgBirim_Data(AnaMenuActivity.this);
        List<SOrgBirim> milli_park_bolge_mud = new ArrayList<SOrgBirim>();


        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("SELECT * FROM S_ORG_BIRIM");
        try {
            org_birim_list = new ArrayList<SOrgBirim>();
            org_birim_list = data.loadFromQuery(sqlStr.toString());
        } catch (OrbisDefaultException e) {
            e.printStackTrace();
        }
        for(SOrgBirim item:org_birim_list) {
            if(item.getKategori() != null) {
                if (String.valueOf(item.getKategori()).equals("5")) {

                    if(!item.getAdi().contains("DOĞA")) {
                        OrtakFunction.bolge_list.add(item);
                        OrtakFunction.bolge_list_string.add(item.getAdi());
                    }
                    else
                    {
                        milli_park_bolge_mud.add(item);
                    }

                } else if (String.valueOf(item.getKategori()).equals("6")) {
                    OrtakFunction.mudurluk_list.add(item);
                    OrtakFunction.mudurluk_list_string.add(item.getAdi());
                } else if (String.valueOf(item.getKategori()).equals("7")) {
                    OrtakFunction.seflik_list.add(item);
                    OrtakFunction.seflik_list_string.add(item.getAdi());
                }
            }
            Log.v("BIRIM", "=>" + item.getAdi());
        }


        Collections.sort(OrtakFunction.bolge_list_string);
        Collections.sort(OrtakFunction.bolge_list, new Comparator<SOrgBirim>() {
            @Override
            public int compare(final SOrgBirim object1, final SOrgBirim object2) {
                return object1.getAdi().compareTo(object2.getAdi());
            }
        });

        for(int i = 0;i<milli_park_bolge_mud.size();i++) {
            OrtakFunction.bolge_list.add(milli_park_bolge_mud.get(i));
            OrtakFunction.bolge_list_string.add(milli_park_bolge_mud.get(i).getAdi());
        }

        OrtakFunction.bolge_list.add(0,null);
        OrtakFunction.bolge_list_string.add(0,"");

    }


}
