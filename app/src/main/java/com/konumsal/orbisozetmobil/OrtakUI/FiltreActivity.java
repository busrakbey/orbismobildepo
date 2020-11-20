package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;
import com.konumsal.orbisozetmobil.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import DataLayer.Ortak.ConfigData;
import EntityLayer.Sistem.SOrgBirim;
import EnumsLayer.HttpRequestType;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;

public class FiltreActivity  extends AppCompatActivity {
    Toolbar toolbar;
    Spinner bolge_spinner, mudurluk_spinner, seflik_spinner, yil_bas_spinner, yil_bit_spinner;
    Button ozm_hb_sorgula_button;
    ProgressDialog pd_main;
    int selected_bolge_index = 0, selected_mudurluk_index = 0, selected_seflik_index = 0;
    Long secili_bolge_id = -1L, secili_mudurluk_id = -1L, secili_seflik_id = -1L;
    List<String> item_source_str_mudurluk;
    List<String> item_source_str_seflik;
    List<SOrgBirim> item_souce_mudurluk;
    List<SOrgBirim> item_source_seflik;
    ArrayList<SOrgBirim> all_list_SOrgBirim;
    Type typeOf_SOrgBirim=null;
    String all_jsonSOrgBirim="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.filtre_activity);

        Init();
        initToolBar();


    }

    private void initToolBar() {
        try {

            toolbar = (Toolbar) findViewById(R.id.ozm_hb_sorgula_list_ui_toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FiltreActivity.this.finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(FiltreActivity.this, "Hata:" + e.toString(), false);

        } catch (Throwable e) {
            e.printStackTrace();
            MessageBox.showAlert(FiltreActivity.this, "Hata:" + e.toString(), false);
        }
    }

    void Init(){
        bolge_spinner= (Spinner) findViewById(R.id.bolge_spinner);
        mudurluk_spinner= (Spinner) findViewById(R.id.mudurluk_spinner);
        seflik_spinner= (Spinner) findViewById(R.id.seflik_spinner);
        yil_bas_spinner= (Spinner) findViewById(R.id.yil_bas_spinner);
        yil_bit_spinner= (Spinner) findViewById(R.id.yil_bit_spinner);

        item_source_str_mudurluk = new ArrayList<String>();
        item_source_str_seflik = new ArrayList<String>();
        item_souce_mudurluk = new ArrayList<SOrgBirim>();
        item_source_seflik = new ArrayList<SOrgBirim>();

        pd2 = new ProgressDialog(FiltreActivity.this);
        all_list_SOrgBirim = new ArrayList<SOrgBirim>();
        typeOf_SOrgBirim = new TypeToken<List<SOrgBirim>>(){}.getType();

      //  new getBaseDataServiceForSorgulamalar().execute();



        ArrayAdapter<String> dataAdapter_bolge = new ArrayAdapter<String>(FiltreActivity.this , android.R.layout.simple_spinner_item,OrtakFunction.bolge_list_string);
        dataAdapter_bolge.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);
        bolge_spinner.setAdapter(dataAdapter_bolge);
        bolge_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    String valInfo=OrtakFunction.bolge_list_string.get(position);
                    if (valInfo!=null) {
                        selected_bolge_index = position;

                        Long bolge_mud_id = OrtakFunction.bolge_list.get(selected_bolge_index).getId();
                        secili_bolge_id = bolge_mud_id;
                        secili_mudurluk_id = -1L;
                        secili_seflik_id = -1L;

                        item_source_str_mudurluk.clear();
                        item_souce_mudurluk.clear();
                        item_source_str_seflik.clear();
                        item_source_seflik.clear();

                        item_souce_mudurluk.add(null);
                        item_source_str_mudurluk.add("");

                        for(SOrgBirim item:OrtakFunction.mudurluk_list)
                        {
                            if(item == null)
                                continue;
                            if(String.valueOf(item.getUstId()).equals(String.valueOf(bolge_mud_id)))
                            {
                                item_source_str_mudurluk.add(item.getAdi());
                                item_souce_mudurluk.add(item);
                            }
                        }


                        ArrayAdapter<String> dataAdapter_mudurluk = new ArrayAdapter<String>(FiltreActivity.this , android.R.layout.simple_spinner_item,item_source_str_mudurluk);
                        dataAdapter_mudurluk.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);
                        mudurluk_spinner.setAdapter(dataAdapter_mudurluk);


                        ArrayAdapter<String> dataAdapter_seflik = new ArrayAdapter<String>(FiltreActivity.this , android.R.layout.simple_spinner_item,item_source_str_seflik);
                        dataAdapter_seflik.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);
                        seflik_spinner.setAdapter(dataAdapter_seflik);
                        seflik_spinner.setSelection(0);


                        if(OrtakFunction.s_org_birim_path.size() == 4) {
                            for(int i = 1;i<item_souce_mudurluk.size();i++)
                            {
                                if(String.valueOf(item_souce_mudurluk.get(i).getId()).equals(String.valueOf(OrtakFunction.s_org_birim_path.get(2))))
                                {
                                    mudurluk_spinner.setSelection(i);
                                    // mudurluk_spinner.setEnabled(false);
                                    break;
                                }
                            }
                        }
                        else
                        {
                            mudurluk_spinner.setSelection(0);
                        }



                    }
                }
                else {
                    secili_bolge_id = -1L;
                    secili_mudurluk_id = -1L;
                    secili_seflik_id = -1L;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        for(Long item : OrtakFunction.s_org_birim_path)
            Log.v("birim","=>"+item);

        if(OrtakFunction.s_org_birim_path.size() == 4) {
            for(int i = 1;i<OrtakFunction.bolge_list.size();i++)
            {
                if(String.valueOf(OrtakFunction.bolge_list.get(i).getId()).equals(String.valueOf(OrtakFunction.s_org_birim_path.get(1))))
                {
                    bolge_spinner.setSelection(i);
                    //bolge_spinner.setEnabled(false);
                    break;
                }
            }
        }
        else
        {
            bolge_spinner.setSelection(0);
        }


        ArrayAdapter<String> dataAdapter_mudurluk = new ArrayAdapter<String>(FiltreActivity.this , android.R.layout.simple_spinner_item,item_source_str_mudurluk);
        dataAdapter_mudurluk.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);

        mudurluk_spinner.setAdapter(dataAdapter_mudurluk);
        mudurluk_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    String valInfo=item_source_str_mudurluk.get(position);
                    if (valInfo!=null) {
                        selected_mudurluk_index = position;

                        Long mud_id = item_souce_mudurluk.get(selected_mudurluk_index).getId();
                        secili_mudurluk_id = mud_id;
                        secili_seflik_id = -1L;

                        item_source_str_seflik.clear();
                        item_source_seflik.clear();

                        item_source_seflik.add(null);
                        item_source_str_seflik.add("");


                        for(SOrgBirim item:OrtakFunction.seflik_list)
                        {
                            if(item == null)
                                continue;
                            if(String.valueOf(item.getUstId()).equals(String.valueOf(mud_id)))
                            {
                                item_source_str_seflik.add(item.getAdi());
                                item_source_seflik.add(item);
                            }
                        }


                        ArrayAdapter<String> dataAdapter_seflik = new ArrayAdapter<String>(FiltreActivity.this , android.R.layout.simple_spinner_item,item_source_str_seflik);
                        dataAdapter_seflik.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);
                        seflik_spinner.setAdapter(dataAdapter_seflik);




                        if(OrtakFunction.s_org_birim_path.size() == 4) {
                            for(int i = 1;i<item_source_seflik.size();i++)
                            {
                                if(String.valueOf(item_source_seflik.get(i).getId()).equals(String.valueOf(OrtakFunction.s_org_birim_path.get(3))))
                                {
                                    seflik_spinner.setSelection(i);
                                    //seflik_spinner.setEnabled(false);
                                    break;
                                }
                            }
                        }
                        else
                        {
                            seflik_spinner.setSelection(0);
                        }


                    }
                    else {
                        secili_mudurluk_id = -1L;
                        secili_seflik_id = -1L;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        ArrayAdapter<String> dataAdapter_seflik = new ArrayAdapter<String>(FiltreActivity.this , android.R.layout.simple_spinner_item,item_source_str_seflik);
        dataAdapter_seflik.setDropDownViewResource(R.layout.mr_simple_spinner_dropdown_item);

        seflik_spinner.setAdapter(dataAdapter_seflik);
        seflik_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    String valInfo=item_source_str_seflik.get(position);
                    if (valInfo!=null) {
                        selected_seflik_index = position;
                        secili_seflik_id = item_source_seflik.get(selected_seflik_index).getId();
                    }
                }
                else {
                    secili_seflik_id = -1L;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        ozm_hb_sorgula_button = (Button)findViewById(R.id.ozm_hb_sorgula_button);
        ozm_hb_sorgula_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new getHastalikMainQuery().execute();
            }
        });





    }



    ProgressDialog pd2;
    public class getBaseDataServiceForSorgulamalar extends AsyncTask
    {
        StringBuilder sb = new StringBuilder();
        Boolean result=true;


        @Override
        protected void onPreExecute()
        {
            pd2.setMessage("Lütfen bekleyiniz..");
            pd2.setTitle("Orbis Mobile");
            pd2.show();
        }

        @Override
        protected void onPostExecute(Object o)
        {
            pd2.dismiss();
            if(!result)
            {
                //  MessageBox.showAlert(OzmSorgulamaMainActivity.this,"Sorgulama için gerekli temel veriler indirilemedi!",false);
                finish();
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.v("birim sorgulama","base");
            ConfigData configData = new ConfigData(FiltreActivity.this);
            String surl=  configData.getSERVICURL();
            String servisURL = surl +getResources().getString(R.string.sorgbirimGet_methodPost_tumkayitgetirir);
            RSOperator operator = new RSOperator();
            try {
                List<SOrgBirim> milli_park_bolge_mud = new ArrayList<SOrgBirim>();

                all_jsonSOrgBirim = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL, null);
                if (all_jsonSOrgBirim.trim().length() > 1) {
                    all_list_SOrgBirim = operator.convertJSONToEntity(all_jsonSOrgBirim, typeOf_SOrgBirim, all_list_SOrgBirim);
                    all_jsonSOrgBirim = null;

                    if (all_list_SOrgBirim.size() > 0) {
                        //  OrtakFunction.bolge_list.add(null);
                        // OrtakFunction.bolge_list_string.add("");
                        OrtakFunction.mudurluk_list.add(null);
                        OrtakFunction.mudurluk_list_string.add("");
                        OrtakFunction.seflik_list.add(null);
                        OrtakFunction.seflik_list_string.add("");
                        for(SOrgBirim item:all_list_SOrgBirim) {
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

                } else
                    MessageBox.showAlert(FiltreActivity.this, "Hata !\nBirim  verisi alinamadi !\n", false);
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

            return null;
        }
    }



    void birim_spinner(){



    }






}