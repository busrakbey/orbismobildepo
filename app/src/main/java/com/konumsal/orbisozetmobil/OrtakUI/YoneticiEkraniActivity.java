package com.konumsal.orbisozetmobil.OrtakUI;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.konumsal.orbisozetmobil.R;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import DataLayer.Ortak.ConfigData;
import EntityLayer.Ortak.OzetEkranEntity;
import EnumsLayer.HttpRequestType;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;
import ToolLayer.StateReceiver;

/**
 * Created by isahin on 25.1.2017.
 */
public class YoneticiEkraniActivity extends AppCompatActivity {


    String secili_yil = "2017";
    String secili_il = "";
    String secili_bolge = "";
    GridLayout gridLayout;
    Integer lin_lay_original_width = 0;

    LinearLayout  ll_ds_butce;
    LinearLayout  ll_ob_butce;
    LinearLayout  ll_muh;
    LinearLayout  ll_uretim;
    LinearLayout  ll_sat_mik;
    LinearLayout  ll_sat_tut;
    LinearLayout  ll_oduh;
    LinearLayout  ll_yangin;
    LinearLayout  ll_tasinmaz;
    LinearLayout  ll_per;
    LinearLayout  ll_orman_varlik;
    LinearLayout  ll_fidanlik;
    LinearLayout  ll_hukuk;


    List<OzetEkranEntity> all_list_ozet_bilgi;
    public static HashMap<Integer,BigDecimal> ozetEkranValueMap;
    Button yonetici_ekrani_grafik_button;

    String pattern = "###,###.#";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yonetici_ekran_acitivity);

        initToolBar();
        create_bolge_list();
        create_il_list();
        init();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    Spinner spinner_bolge;
    Spinner spinner_il;
    Spinner spinner_yil;
    Button yonetici_ekrani_refresh_button;


    void init()
    {
       // pd_main = new ProgressDialog(YoneticiEkraniActivity.this);
        typeOf_Ozet =  new TypeToken<List<OzetEkranEntity>>(){}.getType();
        all_list_ozet_bilgi = new ArrayList<OzetEkranEntity>();
        ozetEkranValueMap = new HashMap<Integer,BigDecimal>();
        gridLayout = (GridLayout)findViewById(R.id.yonetici_gridview);

        spinner_bolge = (Spinner) findViewById(R.id.spinner_bolge);
        ArrayAdapter<String> dataAdapter_bolge = new ArrayAdapter<String>(YoneticiEkraniActivity.this , android.R.layout.simple_spinner_item,create_bolge_list());
        dataAdapter_bolge.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);


        spinner_bolge.setAdapter(dataAdapter_bolge);
        spinner_bolge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secili_bolge = bolge_list_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_bolge.setSelection(0);



        spinner_il = (Spinner) findViewById(R.id.spinner_il);
        ArrayAdapter<String> dataAdapter_il = new ArrayAdapter<String>(YoneticiEkraniActivity.this , android.R.layout.simple_spinner_item,create_il_list());
        dataAdapter_il.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);
        spinner_il.setAdapter(dataAdapter_il);
        spinner_il.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valInfo=il_list.get(position);
                if(position == 0)
                    secili_il = "";
                else
                    secili_il = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_il.setSelection(0);


        spinner_yil = (Spinner) findViewById(R.id.spinner_yil);
        ArrayAdapter<String> dataAdapter_yil = new ArrayAdapter<String>(YoneticiEkraniActivity.this , android.R.layout.simple_spinner_item,create_yil_list());
        dataAdapter_yil.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);
        spinner_yil.setAdapter(dataAdapter_yil);
        spinner_yil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valInfo=yil_list.get(position);
                secili_yil = valInfo;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 0;i<yil_list.size();i++)
        {
            if(String.valueOf(year).equals(yil_list.get(i)))
            {
                spinner_yil.setSelection(i);
                secili_yil = yil_list.get(i);
                break;
            }
        }

        yonetici_ekrani_refresh_button = (Button) findViewById(R.id.yonetici_ekrani_refresh_button);
        yonetici_ekrani_refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (StateReceiver.isNetworkAvailable(YoneticiEkraniActivity.this))
                {
                }else
                {
                    MessageBox.showAlert(YoneticiEkraniActivity.this, "Sorgulama için ağ bağlantısı gerekmektedir!",false);
                    return;
                }

                ekran_temizle();
                ozetEkranValueMap = new HashMap<Integer,BigDecimal>();
                for(int i = 0;i< OrtakFunction.getOzetEkranModulList().size();i++) {

                    if(i == 0)
                    {
                        Toast.makeText(YoneticiEkraniActivity.this, "Bağlantı kuruluyor..." , Toast.LENGTH_LONG).show();
                        Toast.makeText(YoneticiEkraniActivity.this, "Lütfen Bekleyiniz...", Toast.LENGTH_LONG).show();
                        Toast.makeText(YoneticiEkraniActivity.this, "İşlem Devam Ediyor...", Toast.LENGTH_LONG).show();
                        Toast.makeText(YoneticiEkraniActivity.this, "Muhasebe verileri getiriliyor...", Toast.LENGTH_LONG).show();
                        Toast.makeText(YoneticiEkraniActivity.this, "Lütfen Bekleyiniz...", Toast.LENGTH_LONG).show();
                        Toast.makeText(YoneticiEkraniActivity.this, "Lütfen Bekleyiniz...", Toast.LENGTH_LONG).show();
                        Toast.makeText(YoneticiEkraniActivity.this, "Lütfen Bekleyiniz...", Toast.LENGTH_LONG).show();
                    }
                    new getOzetEkranMainQuery().execute(String.valueOf(OrtakFunction.getOzetEkranModulList().get(i)) , String.valueOf(i));
                }
            }
        });

        ekran_deger_tanimla();
        ekran_temizle();

        /*yonetici_ekrani_grafik_button = (Button)findViewById(R.id.yonetici_ekrani_grafik_button);
        yonetici_ekrani_grafik_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(YoneticiEkraniActivity.this, GrafikAnaEkran.class);
                mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mintent);
            }
        });*/
    }

    private void initToolBar() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.yonetici_toolbar_);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }



    List<String> yil_list = new ArrayList<String >();
    List<String > create_yil_list()
    {
        yil_list = new ArrayList<String>();
        yil_list.add("2016");
        yil_list.add("2017");
        yil_list.add("2018");
        yil_list.add("2019");
        yil_list.add("2020");
        yil_list.add("2021");
        yil_list.add("2022");
        yil_list.add("2023");
        yil_list.add("2024");
        yil_list.add("2025");
        yil_list.add("2026");
        yil_list.add("2027");
        yil_list.add("2028");
        yil_list.add("2029");
        yil_list.add("2030");

        return yil_list;
    }

    List<String> il_list = new ArrayList<String >();
    List<String > create_il_list() {
        il_list = new ArrayList<String>();
        il_list.add("İl");
        il_list.add("Adana");
        il_list.add("Adıyaman");
        il_list.add("Afyon");
        il_list.add("Ağrı");
        il_list.add("Amasya");
        il_list.add("Ankara");
        il_list.add("Antalya");
        il_list.add("Artvin");
        il_list.add("Aydın");
        il_list.add("Balıkesir");
        il_list.add("Bilecik");
        il_list.add("Bingöl");
        il_list.add("Bitlis");
        il_list.add("Bolu");
        il_list.add("Burdur");
        il_list.add("Bursa");
        il_list.add("Çanakkale");
        il_list.add("Çankırı");
        il_list.add("Çorum");
        il_list.add("Denizli");
        il_list.add("Diyarbakır");
        il_list.add("Edirne");
        il_list.add("Elazığ");
        il_list.add("Erzincan");
        il_list.add("Erzurum");
        il_list.add("Eskişehir");
        il_list.add("Gaziantep");
        il_list.add("Giresun");
        il_list.add("Gümüşhane");
        il_list.add("Hakkari");
        il_list.add("Hatay");
        il_list.add("Isparta");
        il_list.add("Mersin");
        il_list.add("İstanbul");
        il_list.add("İzmir");
        il_list.add("Kars");
        il_list.add("Kastamonu");
        il_list.add("Kayseri");
        il_list.add("Kırklareli");
        il_list.add("Kırşehir");
        il_list.add("Kocaeli");
        il_list.add("Konya");
        il_list.add("Kütahya");
        il_list.add("Malatya");
        il_list.add("Manisa");
        il_list.add("Kahramanmaraş");
        il_list.add("Mardin");
        il_list.add("Muğla");
        il_list.add("Muş");
        il_list.add("Nevşehir");
        il_list.add("Niğde");
        il_list.add("Ordu");
        il_list.add("Rize");
        il_list.add("Sakarya");
        il_list.add("Samsun");
        il_list.add("Siirt");
        il_list.add("Sinop");
        il_list.add("Sivas");
        il_list.add("Tekirdağ");
        il_list.add("Tokat");
        il_list.add("Trabzon");
        il_list.add("Tunceli");
        il_list.add("Şanlıurfa");
        il_list.add("Uşak");
        il_list.add("Van");
        il_list.add("Yozgat");
        il_list.add("Zonguldak");
        il_list.add("Aksaray");
        il_list.add("Bayburt");
        il_list.add("Karaman");
        il_list.add("Kırıkkale");
        il_list.add("Batman");
        il_list.add("Şırnak");
        il_list.add("Bartın");
        il_list.add("Ardahan");
        il_list.add("Iğdır");
        il_list.add("Yalova");
        il_list.add("Karabük");
        il_list.add("Kilis");
        il_list.add("Osmaniye");
        il_list.add("Düzce");



        return il_list;
    }

    List<String> bolge_list = new ArrayList<String >();
    List<String> bolge_list_id = new ArrayList<String >();
    List<String > create_bolge_list()
    {
        bolge_list = new ArrayList<String>();
        bolge_list.add("Orman Genel Müdürlüğü");
        bolge_list.add("Adana Orman Bölge Müdürlüğü");
        bolge_list.add("Amasya Orman Bölge Müdürlüğü");
        bolge_list.add("Ankara Orman Bölge Müdürlüğü");
        bolge_list.add("Antalya Orman Bölge Müdürlüğü");
        bolge_list.add("Artvin Orman Bölge Müdürlüğü");
        bolge_list.add("Balıkesir Orman Bölge Müdürlüğü");
        bolge_list.add("Bolu Orman Bölge Müdürlüğü");
        bolge_list.add("Bursa Orman Bölge Müdürlüğü");
        bolge_list.add("Çanakkale Orman Bölge Müdürlüğü");
        bolge_list.add("Denizli Orman Bölge Müdürlüğü");
        bolge_list.add("Elazığ Orman Bölge Müdürlüğü");
        bolge_list.add("Erzurum Orman Bölge Müdürlüğü");
        bolge_list.add("Eskişehir Orman Bölge Müdürlüğü");
        bolge_list.add("Giresun Orman Bölge Müdürlüğü");
        bolge_list.add("Isparta Orman Bölge Müdürlüğü");
        bolge_list.add("İstanbul Orman Bölge Müdürlüğü");
        bolge_list.add("İzmir Orman Bölge Müdürlüğü");
        bolge_list.add("Kahramanmaraş Orman Bölge Müdürlüğü");
        bolge_list.add("Kastamonu Orman Bölge Müdürlüğü");
        bolge_list.add("Kayseri Orman Bölge Müdürlüğü");
        bolge_list.add("Konya Orman Bölge Müdürlüğü");
        bolge_list.add("Kütahya Orman Bölge Müdürlüğü");
        bolge_list.add("Mersin Orman Bölge Müdürlüğü");
        bolge_list.add("Muğla Orman Bölge Müdürlüğü");
        bolge_list.add("Sakarya Orman Bölge Müdürlüğü");
        bolge_list.add("Şanlıurfa Orman Bölge Müdürlüğü");
        bolge_list.add("Trabzon Orman Bölge Müdürlüğü");
        bolge_list.add("Zonguldak Orman Bölge Müdürlüğü");
        bolge_list.add("Orman Genel Müdürlüğü");
        bolge_list.add("Adana Orman Bölge Müdürlüğü");
        bolge_list.add("Amasya Orman Bölge Müdürlüğü");
        bolge_list.add("Ankara Orman Bölge Müdürlüğü");
        bolge_list.add("Antalya Orman Bölge Müdürlüğü");
        bolge_list.add("Artvin Orman Bölge Müdürlüğü");
        bolge_list.add("Balıkesir Orman Bölge Müdürlüğü");
        bolge_list.add("Bolu Orman Bölge Müdürlüğü");
        bolge_list.add("Bursa Orman Bölge Müdürlüğü");
        bolge_list.add("Çanakkale Orman Bölge Müdürlüğü");
        bolge_list.add("Denizli Orman Bölge Müdürlüğü");
        bolge_list.add("Elazığ Orman Bölge Müdürlüğü");
        bolge_list.add("Erzurum Orman Bölge Müdürlüğü");
        bolge_list.add("Eskişehir Orman Bölge Müdürlüğü");
        bolge_list.add("Giresun Orman Bölge Müdürlüğü");
        bolge_list.add("Isparta Orman Bölge Müdürlüğü");
        bolge_list.add("İstanbul Orman Bölge Müdürlüğü");
        bolge_list.add("İzmir Orman Bölge Müdürlüğü");
        bolge_list.add("Kahramanmaraş Orman Bölge Müdürlüğü");
        bolge_list.add("Kastamonu Orman Bölge Müdürlüğü");
        bolge_list.add("Kayseri Orman Bölge Müdürlüğü");
        bolge_list.add("Konya Orman Bölge Müdürlüğü");
        bolge_list.add("Kütahya Orman Bölge Müdürlüğü");
        bolge_list.add("Mersin Orman Bölge Müdürlüğü");
        bolge_list.add("Muğla Orman Bölge Müdürlüğü");
        bolge_list.add("Sakarya Orman Bölge Müdürlüğü");
        bolge_list.add("Şanlıurfa Orman Bölge Müdürlüğü");
        bolge_list.add("Trabzon Orman Bölge Müdürlüğü");
        bolge_list.add("Zonguldak Orman Bölge Müdürlüğü");

        bolge_list_id = new ArrayList<String>();
        bolge_list_id.add("");
        bolge_list_id.add("1773");
        bolge_list_id.add("2002");
        bolge_list_id.add("2162");
        bolge_list_id.add("2322");
        bolge_list_id.add("2469");
        bolge_list_id.add("2538");
        bolge_list_id.add("2641");
        bolge_list_id.add("2770");
        bolge_list_id.add("2884");
        bolge_list_id.add("2962");
        bolge_list_id.add("3054");
        bolge_list_id.add("3146");
        bolge_list_id.add("3240");
        bolge_list_id.add("3308");
        bolge_list_id.add("3426");
        bolge_list_id.add("3513");
        bolge_list_id.add("3653");
        bolge_list_id.add("3802");
        bolge_list_id.add("3900");
        bolge_list_id.add("4765");
        bolge_list_id.add("4676");
        bolge_list_id.add("4600");
        bolge_list_id.add("4116");
        bolge_list_id.add("4219");
        bolge_list_id.add("1896");
        bolge_list_id.add("4854");
        bolge_list_id.add("4367");
        bolge_list_id.add("4479");

        return  bolge_list;


    }


    Type typeOf_Ozet=null;
    String jsonYoneticiData="";

    //ProgressDialog pd_main;
    public class getOzetEkranMainQuery extends AsyncTask<String ,String,String>
    {
        StringBuilder sb = new StringBuilder();
        Boolean result=true;

        @Override
        protected void onPreExecute()
        {
            //pd_main.setMessage("Lütfen bekleyiniz..");
           // pd_main.setTitle("Orbis Mobile");
            //pd_main.show();
        }

        @Override
        protected void onPostExecute(String o)
        {
           // pd_main.dismiss();
            if(!result)
            {
                MessageBox.showAlert(YoneticiEkraniActivity.this,"Sorgu sırasında hata oluştu!",false);
            }
            else
            {
                if(Integer.valueOf(o) < 7)
                Toast.makeText(YoneticiEkraniActivity.this, OrtakFunction.ozetEkranModulAdlari.get(Integer.valueOf(o)+1)+" verileri getiriliyor\n" + "Lütfen Bekleyiniz...", Toast.LENGTH_LONG).show();

                if(Integer.valueOf(o).equals(7))
                    Toast.makeText(YoneticiEkraniActivity.this, "Sorgulama işlemi tamamlandı", Toast.LENGTH_LONG).show();

                ekran_verilerini_yazdir();
            }
        }

        @Override
        protected String doInBackground(String...params) {

            String input1 = set_input_parameter(params[0]);
            Log.v("INPUT", "=>" + input1);
            Log.v("yonetici sorgulama","base");
            ConfigData configData = new ConfigData(YoneticiEkraniActivity.this);
            String surl=  configData.getSERVICURL();
            String servisURL = surl +getResources().getString(R.string.RaporlamaRS_get_ozet_ekran);
            RSOperator operator = new RSOperator();
            try {
                jsonYoneticiData = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL, input1);

                if (jsonYoneticiData.trim().length() > 1) {

                    LogLarge.v("gelen yonetici data json", jsonYoneticiData);
                    JSONObject jObject = new JSONObject(jsonYoneticiData);
                    Iterator<?> keys = jObject.keys();

                    while( keys.hasNext() ){
                        String key = (String)keys.next();
                        try {
                            Double value = jObject.getDouble(key);
                            Log.v("key,value=>", key + "-" + value);
                            ozetEkranValueMap.put(Integer.valueOf(key), BigDecimal.valueOf(value));
                        }
                        catch (Exception ex)
                        {
                            ozetEkranValueMap.put(Integer.valueOf(key), BigDecimal.valueOf(0));
                        }
                    }

                } else
                    MessageBox.showAlert(YoneticiEkraniActivity.this, "Hata !\nVeri alinamadi !\n", false);
            }

            catch (
                    OrbisDefaultException e) {
                e.printStackTrace();
                result = false;
            }
            catch (Throwable e)
            {
                e.printStackTrace();
                result = false;
            }

            return params[1];
        }

    }



    private String set_input_parameter(String islem_param) {

        String s1 = "\"prmIslem\":";
        String s2 = "{";
        String s3 = "}";
        String s5 = "\"";
        String s4 =  islem_param;

        String s1_2 = "\"prmBolge\":";
        String s4_2 = secili_bolge;

        String s1_5 = "\"prmIl\":";
        String s4_5 = secili_il;

        String s1_3 = "\"prmYil\":";
        String s4_3 = secili_yil;


        String s1_4 = "\"prmYil2\":";
        String s4_4 = "-1";


        String input1 = s2 + s1 + s5 + s4 + s5 + "," +
                s1_5 + s5 + s4_5 + s5 +"," +
                s1_3 + s5 + s4_3 + s5 + "," +
                s1_4 + s5 + s4_4 + s5 + "," +
                s1_2 + s5 + s4_2 + s5 + s3;

        return input1;
    }



    TextView ds_butce_1;
    TextView ds_butce_2;
    TextView ds_butce_3;
    TextView ds_butce_4;

    TextView ob_butce_1;
    TextView ob_butce_2;
    TextView ob_butce_3;
    TextView ob_butce_4;


    TextView muh_1;
    TextView muh_2;
    TextView muh_3;
    TextView muh_4;

    TextView uretim_1;
    TextView uretim_2;
    TextView uretim_3;
    TextView uretim_4;
    TextView uretim_5;

    TextView sat_1;
    TextView sat_2;
    TextView sat_3;


    TextView sat_tut_1;
    TextView sat_tut_2;
    TextView sat_tut_3;

    TextView butce_1;
    TextView butce_2;
    TextView butce_3;
    TextView butce_4;
    TextView butce_5;

    TextView per_1;
    TextView per_2;
    TextView per_3;

    TextView oduh_1;
    TextView oduh_2;
    TextView oduh_3;
    TextView oduh_4;
    TextView oduh_5;

    TextView fid_1;
    TextView fid_2;
    TextView fid_3;
    TextView fid_4;
    TextView fid_5;

    TextView izin_1;
    TextView izin_2;
    TextView izin_3;
    TextView izin_4;


    TextView orkoy_1;
    TextView orkoy_2;
    TextView orkoy_3;
    TextView orkoy_4;
    TextView orkoy_5;

    TextView tasinmaz_1;
    TextView tasinmaz_2;
    TextView tasinmaz_3;
    TextView tasinmaz_4;

    TextView ozm_1;
    TextView ozm_2;
    TextView ozm_3;

    TextView oym_1;
    TextView oym_2;
    TextView oym_3;
    TextView oym_4;
    TextView oym_5;
    TextView oym_6;

    TextView hukuk_1;
    TextView hukuk_2;
    TextView hukuk_3;
    TextView hukuk_4;
    TextView hukuk_5;



    TextView varlik_1;
    TextView varlik_2;
    TextView varlik_3;
    TextView varlik_4;
    TextView varlik_5;


    void ekran_deger_tanimla()
    {

        ll_ds_butce = (LinearLayout) findViewById(R.id.ll_ds_butce);
        ll_ob_butce = (LinearLayout) findViewById(R.id.ll_ob_butce);
        ll_muh = (LinearLayout) findViewById(R.id.ll_muh);
        ll_uretim = (LinearLayout) findViewById(R.id.ll_uretim);
        ll_sat_mik = (LinearLayout) findViewById(R.id.ll_sat_mik);
        ll_sat_tut = (LinearLayout) findViewById(R.id.ll_sat_tut);
        ll_oduh = (LinearLayout) findViewById(R.id.ll_oduh);
        ll_yangin = (LinearLayout) findViewById(R.id.ll_yangin);
        ll_tasinmaz = (LinearLayout) findViewById(R.id.ll_tasinmaz);
        ll_per = (LinearLayout) findViewById(R.id.ll_per);
        ll_orman_varlik = (LinearLayout) findViewById(R.id.ll_orman_varlik);
        ll_fidanlik = (LinearLayout) findViewById(R.id.ll_fidanlik);
        ll_hukuk = (LinearLayout) findViewById(R.id.ll_hukuk);

        GridLayout.LayoutParams lp1 = (GridLayout.LayoutParams) ll_ds_butce.getLayoutParams();
        lin_lay_original_width = lp1.width;


        ///////////
        //BÜTÇE
        ///////////
        ds_butce_1 = (TextView) findViewById(R.id.ds_butce_1);
        ds_butce_2 = (TextView) findViewById(R.id.ds_butce_2);
        ds_butce_3 = (TextView) findViewById(R.id.ds_butce_3);
        ds_butce_4 = (TextView) findViewById(R.id.ds_butce_4);

        ob_butce_1 = (TextView) findViewById(R.id.ob_butce_1);
        ob_butce_2 = (TextView) findViewById(R.id.ob_butce_2);
        ob_butce_3 = (TextView) findViewById(R.id.ob_butce_3);
        ob_butce_4 = (TextView) findViewById(R.id.ob_butce_4);

        ///////////
        //MUHASEBE
        //////////
        muh_1 = (TextView) findViewById(R.id.muh_1);
        muh_2 = (TextView) findViewById(R.id.muh_2);
        muh_3 = (TextView) findViewById(R.id.muh_3);
        muh_4 = (TextView) findViewById(R.id.muh_4);


        ///////////
        //İŞLETME PAZARLAMA
        ///////////
        uretim_1 = (TextView) findViewById(R.id.uretim_1);
        uretim_2 = (TextView) findViewById(R.id.uretim_2);
        uretim_3 = (TextView) findViewById(R.id.uretim_3);
        uretim_4 = (TextView) findViewById(R.id.uretim_4);
        uretim_5 = (TextView) findViewById(R.id.uretim_5);

        sat_1 = (TextView) findViewById(R.id.sat_1);
        sat_2 = (TextView) findViewById(R.id.sat_2);
        sat_3 = (TextView) findViewById(R.id.sat_3);


        sat_tut_1 = (TextView) findViewById(R.id.sat_tut_1);
        sat_tut_2 = (TextView) findViewById(R.id.sat_tut_2);
        sat_tut_3 = (TextView) findViewById(R.id.sat_tut_3);

        ///////////
        //PERSONEL
        ///////////
        per_1 = (TextView) findViewById(R.id.per_1);
        per_2 = (TextView) findViewById(R.id.per_2);
        per_3 = (TextView) findViewById(R.id.per_3);

        ///////////
        //ODUH
        ///////////
        oduh_1 = (TextView) findViewById(R.id.oduh_1);
        oduh_2 = (TextView) findViewById(R.id.oduh_2);
        oduh_3 = (TextView) findViewById(R.id.oduh_3);
        oduh_4 = (TextView) findViewById(R.id.oduh_4);
        oduh_5 = (TextView) findViewById(R.id.oduh_5);

        ///////////
        //FIDANLIK
        ///////////
        fid_1 = (TextView) findViewById(R.id.fid_1);
        fid_2 = (TextView) findViewById(R.id.fid_2);
        fid_3 = (TextView) findViewById(R.id.fid_3);
        fid_4 = (TextView) findViewById(R.id.fid_4);
        fid_5 = (TextView) findViewById(R.id.fid_5);

        ///////////
        //TASINMAZ
        ///////////
        tasinmaz_1 = (TextView) findViewById(R.id.tasinmaz_1);
        tasinmaz_2 = (TextView) findViewById(R.id.tasinmaz_2);
        tasinmaz_3 = (TextView) findViewById(R.id.tasinmaz_3);
        tasinmaz_4 = (TextView) findViewById(R.id.tasinmaz_4);

        ///////////
        //ORMAN VARLIĞI
        ///////////
        varlik_1 = (TextView) findViewById(R.id.varlik_1);
        varlik_2 = (TextView) findViewById(R.id.varlik_2);
        varlik_3 = (TextView) findViewById(R.id.varlik_3);
        varlik_4 = (TextView) findViewById(R.id.varlik_4);
        varlik_5 = (TextView) findViewById(R.id.varlik_5);

        ///////////
        //OYM
        ///////////
        oym_1 = (TextView) findViewById(R.id.oym_1);
        oym_2 = (TextView) findViewById(R.id.oym_2);
        oym_3 = (TextView) findViewById(R.id.oym_3);
        oym_4 = (TextView) findViewById(R.id.oym_4);
        oym_5 = (TextView) findViewById(R.id.oym_5);
        oym_6 = (TextView) findViewById(R.id.oym_6);
        ///////////
        //HUKUK
        ///////////
        hukuk_1 = (TextView) findViewById(R.id.hukuk_1);
        hukuk_2 = (TextView) findViewById(R.id.hukuk_2);
        hukuk_3 = (TextView) findViewById(R.id.hukuk_3);
        hukuk_4 = (TextView) findViewById(R.id.hukuk_4);
        hukuk_5 = (TextView) findViewById(R.id.hukuk_5);


    }

    void ekran_temizle()
    {
        ds_butce_1.setText("0");
        ds_butce_2.setText("0");
        ds_butce_3.setText("0");

        ob_butce_1.setText("0");
        ob_butce_2.setText("0");
        ob_butce_3.setText("0");

        muh_1.setText("0");
        muh_2.setText("0");
        muh_3.setText("0");

        uretim_1.setText("0");
        uretim_2.setText("0");
        uretim_3.setText("0");
        uretim_4.setText("0");
        uretim_5.setText("0");

        sat_1.setText("0");
        sat_2.setText("0");
        sat_3.setText("0");

        sat_tut_1.setText("0");
        sat_tut_2.setText("0");
        sat_tut_3.setText("0");


        per_1.setText("0");
        per_2.setText("0");
        per_3.setText("0");


        oduh_1.setText("0");
        oduh_2.setText("0");
        oduh_3.setText("0");
        oduh_4.setText("0");
        oduh_5.setText("0");

        fid_1.setText("0");
        fid_2.setText("0");

        tasinmaz_1.setText("0");
        tasinmaz_2.setText("0");
        tasinmaz_3.setText("0");
        tasinmaz_4.setText("0");

        varlik_1.setText("0");
        varlik_2.setText("0");
        varlik_3.setText("0");

        oym_1.setText("0");
        oym_2.setText("0");
        oym_3.setText("0");
        oym_4.setText("0");
        oym_5.setText("0");
        oym_6.setText("0");


        hukuk_1.setText("0");
        hukuk_2.setText("0");
        hukuk_3.setText("0");
        hukuk_4.setText("0");
        hukuk_5.setText("0");
    }

    void ekran_verilerini_yazdir()
    {
        ds_butce_1.setText(ozetEkranValueMap.get(6) == null ? "0" : decimalFormat.format(ozetEkranValueMap.get(6)));
        ds_butce_2.setText(ozetEkranValueMap.get(7) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(7)));
        ds_butce_3.setText(ozetEkranValueMap.get(8) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(8)));
        ds_butce_4.setText("");

        ob_butce_1.setText("3.191.729");
        ob_butce_2.setText("0");
        ob_butce_3.setText("0");
        ob_butce_4.setText("");

        muh_1.setText(ozetEkranValueMap.get(1) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(1)));
        Log.v("ihale tutar=>",String.valueOf(ozetEkranValueMap.get(1)));
        muh_2.setText(ozetEkranValueMap.get(4) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(4)));
        muh_3.setText(ozetEkranValueMap.get(5) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(5)));
        muh_4.setText("");

        uretim_1.setText(ozetEkranValueMap.get(101) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(101)));
        uretim_2.setText(ozetEkranValueMap.get(105) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(105)));
        uretim_3.setText(ozetEkranValueMap.get(106) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(106)));
        uretim_4.setText(ozetEkranValueMap.get(113) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(113)));
        uretim_5.setText(ozetEkranValueMap.get(114) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(114)));

        sat_1.setText(ozetEkranValueMap.get(107) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(107)));
        sat_2.setText(ozetEkranValueMap.get(108) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(108)));
        sat_3.setText(ozetEkranValueMap.get(109) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(109)));

        sat_tut_1.setText(ozetEkranValueMap.get(110) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(110)));
        sat_tut_2.setText(ozetEkranValueMap.get(111) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(111)));
        sat_tut_3.setText(ozetEkranValueMap.get(112) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(112)));

        per_1.setText(ozetEkranValueMap.get(201) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(201)));
        per_2.setText(ozetEkranValueMap.get(202) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(202)));
        per_3.setText(ozetEkranValueMap.get(203) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(203)));

        oduh_1.setText(ozetEkranValueMap.get(401) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(401)));
        oduh_2.setText(ozetEkranValueMap.get(402) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(402)));
        oduh_3.setText(ozetEkranValueMap.get(404) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(404)));
        oduh_4.setText(ozetEkranValueMap.get(403) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(403)));
        oduh_5.setText(ozetEkranValueMap.get(405) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(405)));

        fid_1.setText("131");
        fid_2.setText("330.000.000");
        fid_3.setText("");
        fid_4.setText("");
        fid_5.setText("");

        ///////////
        //IZIN
        ///////////
       /* izin_1 = (TextView) findViewById(R.id.izin_1);
        izin_2 = (TextView) findViewById(R.id.izin_2);
        izin_3 = (TextView) findViewById(R.id.izin_3);
        izin_4 = (TextView) findViewById(R.id.izin_4);

        izin_1.setText(ozetEkranValueMap.get(501));
        izin_2.setText(ozetEkranValueMap.get(502));
        izin_3.setText(ozetEkranValueMap.get(503));
        izin_4.setText(ozetEkranValueMap.get(504));
        */

        ///////////
        //ORKOY
        ///////////
       /* orkoy_1 = (TextView) findViewById(R.id.orkoy_1);
        orkoy_2 = (TextView) findViewById(R.id.orkoy_2);
        orkoy_3 = (TextView) findViewById(R.id.orkoy_3);
        orkoy_4 = (TextView) findViewById(R.id.orkoy_4);
        orkoy_5 = (TextView) findViewById(R.id.orkoy_5);


        orkoy_1.setText(ozetEkranValueMap.get(601));
        orkoy_1.setText(ozetEkranValueMap.get(602));
        orkoy_1.setText(ozetEkranValueMap.get(603));
        orkoy_1.setText(ozetEkranValueMap.get(604));
        orkoy_1.setText(ozetEkranValueMap.get(605));
        */
        tasinmaz_1.setText(ozetEkranValueMap.get(901) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(901)));
        tasinmaz_2.setText(ozetEkranValueMap.get(902) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(902)));
        tasinmaz_3.setText(ozetEkranValueMap.get(903) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(903)));
        tasinmaz_4.setText(ozetEkranValueMap.get(904) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(904)));

        varlik_1.setText(ozetEkranValueMap.get(1001) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(1001)));
        varlik_2.setText(ozetEkranValueMap.get(1002) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(1002)));
        varlik_3.setText(ozetEkranValueMap.get(1003) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(1003)));

        ///////////
        //OZM
        ///////////
      /*  ozm_1 = (TextView) findViewById(R.id.ozm_1);
        ozm_2 = (TextView) findViewById(R.id.ozm_2);
        ozm_3 = (TextView) findViewById(R.id.ozm_3);

        ozm_1.setText(ozetEkranValueMap.get(301));
        ozm_2.setText(ozetEkranValueMap.get(302));
        ozm_3.setText("---");
        */

        oym_1.setText(ozetEkranValueMap.get(705) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(705)));
        oym_2.setText(ozetEkranValueMap.get(706) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(706)));
        oym_3.setText(ozetEkranValueMap.get(707) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(707)));
        oym_4.setText(ozetEkranValueMap.get(708) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(708)));
        oym_5.setText(ozetEkranValueMap.get(702) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(702)));
        oym_6.setText(ozetEkranValueMap.get(703) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(703)));

        hukuk_1.setText(ozetEkranValueMap.get(801) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(801)));
        hukuk_2.setText(ozetEkranValueMap.get(805) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(805)));
        hukuk_3.setText(ozetEkranValueMap.get(806) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(806)));
        hukuk_4.setText(ozetEkranValueMap.get(807) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(807)));
        hukuk_5.setText(ozetEkranValueMap.get(808) == null ? "0" :decimalFormat.format(ozetEkranValueMap.get(808)));

    }




    void set_bigger_layout_width()
    {
        GridLayout.LayoutParams lp1 = (GridLayout.LayoutParams) ll_ds_butce.getLayoutParams();
        lp1.width = lp1.width + (lp1.width / 3);
        ll_ds_butce.setLayoutParams(lp1);

        GridLayout.LayoutParams lp2 = (GridLayout.LayoutParams) ll_ob_butce.getLayoutParams();
        lp2.width = lp2.width + (lp2.width / 3);
        ll_ob_butce.setLayoutParams(lp2);

        GridLayout.LayoutParams lp3 = (GridLayout.LayoutParams) ll_muh.getLayoutParams();
        lp3.width = lp3.width + (lp3.width / 3);
        ll_muh.setLayoutParams(lp3);


        GridLayout.LayoutParams lp4 = (GridLayout.LayoutParams) ll_uretim.getLayoutParams();
        lp4.width = lp4.width + (lp4.width / 3);
        ll_uretim.setLayoutParams(lp4);

        GridLayout.LayoutParams lp5 = (GridLayout.LayoutParams) ll_sat_mik.getLayoutParams();
        lp5.width = lp5.width + (lp5.width / 3);
        ll_sat_mik.setLayoutParams(lp5);


        GridLayout.LayoutParams lp6 = (GridLayout.LayoutParams) ll_sat_tut.getLayoutParams();
        lp6.width = lp6.width + (lp6.width / 3);
        ll_sat_tut.setLayoutParams(lp6);

        GridLayout.LayoutParams lp7 = (GridLayout.LayoutParams) ll_oduh.getLayoutParams();
        lp7.width = lp7.width + (lp7.width / 3);
        ll_oduh.setLayoutParams(lp7);

        GridLayout.LayoutParams lp8 = (GridLayout.LayoutParams) ll_yangin.getLayoutParams();
        lp8.width = lp8.width + (lp8.width / 3);
        ll_yangin.setLayoutParams(lp8);

        GridLayout.LayoutParams lp9 = (GridLayout.LayoutParams) ll_tasinmaz.getLayoutParams();
        lp9.width = lp9.width + (lp9.width / 3);
        ll_tasinmaz.setLayoutParams(lp9);


        GridLayout.LayoutParams lp10 = (GridLayout.LayoutParams) ll_per.getLayoutParams();
        lp10.width = lp10.width + (lp10.width / 3);
        ll_per.setLayoutParams(lp10);


        GridLayout.LayoutParams lp11 = (GridLayout.LayoutParams) ll_orman_varlik.getLayoutParams();
        lp11.width = lp11.width + (lp11.width / 3);
        ll_orman_varlik.setLayoutParams(lp11);

        GridLayout.LayoutParams lp12 = (GridLayout.LayoutParams) ll_fidanlik.getLayoutParams();
        lp12.width = lp12.width + (lp12.width / 3);
        ll_fidanlik.setLayoutParams(lp12);

        GridLayout.LayoutParams lp13 = (GridLayout.LayoutParams) ll_hukuk.getLayoutParams();
        lp13.width = lp13.width + (lp13.width / 3);
        ll_hukuk.setLayoutParams(lp13);
    }

    void set_original_layout_width()
    {
        Log.v("orignalw,td","=>"+lin_lay_original_width);
        GridLayout.LayoutParams lp1 = (GridLayout.LayoutParams) ll_ds_butce.getLayoutParams();
        lp1.width = lin_lay_original_width;
        ll_ds_butce.setLayoutParams(lp1);

        GridLayout.LayoutParams lp2 = (GridLayout.LayoutParams) ll_ob_butce.getLayoutParams();
        lp2.width = lin_lay_original_width;
        ll_ob_butce.setLayoutParams(lp2);

        GridLayout.LayoutParams lp3 = (GridLayout.LayoutParams) ll_muh.getLayoutParams();
        lp3.width = lin_lay_original_width;
        ll_muh.setLayoutParams(lp3);

        GridLayout.LayoutParams lp4 = (GridLayout.LayoutParams) ll_uretim.getLayoutParams();
        lp4.width = lin_lay_original_width;
        ll_uretim.setLayoutParams(lp4);

        GridLayout.LayoutParams lp5 = (GridLayout.LayoutParams) ll_sat_mik.getLayoutParams();
        lp5.width = lin_lay_original_width;
        ll_sat_mik.setLayoutParams(lp5);



        GridLayout.LayoutParams lp6 = (GridLayout.LayoutParams) ll_sat_tut.getLayoutParams();
        lp6.width = lin_lay_original_width;
        ll_sat_tut.setLayoutParams(lp6);

        GridLayout.LayoutParams lp7 = (GridLayout.LayoutParams) ll_oduh.getLayoutParams();
        lp7.width = lin_lay_original_width;
        ll_oduh.setLayoutParams(lp7);


        GridLayout.LayoutParams lp8 = (GridLayout.LayoutParams) ll_yangin.getLayoutParams();
        lp8.width = lin_lay_original_width;
        ll_yangin.setLayoutParams(lp8);


        GridLayout.LayoutParams lp9 = (GridLayout.LayoutParams) ll_tasinmaz.getLayoutParams();
        lp9.width = lin_lay_original_width;
        ll_tasinmaz.setLayoutParams(lp9);


        GridLayout.LayoutParams lp10 = (GridLayout.LayoutParams) ll_per.getLayoutParams();
        lp10.width = lin_lay_original_width;
        ll_per.setLayoutParams(lp10);


        GridLayout.LayoutParams lp11 = (GridLayout.LayoutParams) ll_orman_varlik.getLayoutParams();
        lp11.width = lin_lay_original_width;
        ll_orman_varlik.setLayoutParams(lp11);

        GridLayout.LayoutParams lp12 = (GridLayout.LayoutParams) ll_fidanlik.getLayoutParams();
        lp12.width = lin_lay_original_width;
        ll_fidanlik.setLayoutParams(lp12);

        GridLayout.LayoutParams lp13 = (GridLayout.LayoutParams) ll_hukuk.getLayoutParams();
        lp13.width = lin_lay_original_width;
        ll_hukuk.setLayoutParams(lp13);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Log.v( "large screen", "---");
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.v( "landscape", "---");
                set_bigger_layout_width();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                Log.v( "portrait", "---");
                set_original_layout_width();
            }
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Log.v( "Xlarge screen", "---");
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.v( "landscape", "---");
                set_bigger_layout_width();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                Log.v( "portrait", "---");
                set_original_layout_width();
            }
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Log.v( "normal screen", "---");
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Log.v( "SMALL screen", "---");
        }
    }


}
