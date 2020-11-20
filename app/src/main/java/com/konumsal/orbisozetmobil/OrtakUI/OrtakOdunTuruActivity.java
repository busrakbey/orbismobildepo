package com.konumsal.orbisozetmobil.OrtakUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.konumsal.orbisozetmobil.R;

import java.util.List;

import AdapterLayer.Ortak.TreeListOrtakOdunAdapter;
import DataLayer.Ortak.OrtakOdunTuru_Data;
import EntityLayer.Ortak.OrtakOdunTuru;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;

public class OrtakOdunTuruActivity extends AppCompatActivity {

    GridView gridView;
    TreeListOrtakOdunAdapter adapter;
    List<OrtakOdunTuru> odunTuruList;
    Toolbar toolbar=null;
    Integer Mode =1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ortak_odun_act_layout);
        gridView =(GridView)findViewById(R.id.ortakodun_act_gridodunlar);
        Mode =getIntent().getIntExtra("MODE",1);
        initToolBar();
        reload(1);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OrtakOdunTuru agac = (OrtakOdunTuru) parent.getItemAtPosition(position);
                gridView.setClickable(false);


                if (agac.getEnabled() ==1)
                {
                    agac.setEnabled(0);


                }else
                {
                    agac.setEnabled(1);
                    long time= System.currentTimeMillis();
                    Log.v("secim date","=>"+String.valueOf(time));

                }
                updateItem(agac,position);

            }
        });

    }

    private void updateItem(OrtakOdunTuru oa,int posss)  {
        OrtakOdunTuru_Data ortakAgacTuru_data = new OrtakOdunTuru_Data(OrtakOdunTuruActivity.this);
        ortakAgacTuru_data.getDataList().add(oa);
        try {
            Boolean svn= ortakAgacTuru_data.update();
            if (svn)
            {
                String mess = "eklendi";
            }
        } catch (OrbisDefaultException e) {
            MessageBox.showAlert(OrtakOdunTuruActivity.this,"Hata !\n"+e.toString(),false);
        }
        reload(posss);
    }

    public void reload(int posss)
    {
        OrtakOdunTuru_Data ortakAgacTuru_data = new OrtakOdunTuru_Data(OrtakOdunTuruActivity.this);
        try {
            odunTuruList = ortakAgacTuru_data.loadFromQuery("SELECT * FROM ORTAK_ODUN_TURU ");
        } catch (OrbisDefaultException e) {
            e.printStackTrace();
        }
        adapter = new TreeListOrtakOdunAdapter(OrtakOdunTuruActivity.this, R.layout.ortak_odun_act_layout,odunTuruList);
        gridView.setAdapter(adapter);
        gridView.requestFocusFromTouch();
        gridView.setClickable(true);
        gridView.setSelection(posss);
    }



    public void initToolBar()
    {

        try {
            toolbar =(Toolbar)findViewById(R.id.ortak_agacact_toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
            toolbar.setTitle("ORBIS");
            setSupportActionBar(toolbar);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                   /* if (Mode==1)
                    {
                        Intent intent = new Intent(OrtakAgacActivity.this, OrnekAlanKarneListUI.class);
                        intent.putExtra("GLOBALID",PLAN_GLOBALID);
                        intent.putExtra("OBJECTID",PLAN_OBJECTID);
                        startActivity(intent);
                        finish();
                    }if (Mode==2)
                    {
                        Intent intent = new Intent(OrtakAgacActivity.this, DikiliDamgaListUI.class);
                        intent.putExtra("GLOBALID",PLAN_GLOBALID);
                        intent.putExtra("OBJECTID",PLAN_OBJECTID);
                        startActivity(intent);
                        finish();

                    }*/
                    finish();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(OrtakOdunTuruActivity.this, "Hata:"+e.toString(), false);

        }catch (Throwable e) {
            e.printStackTrace();
            MessageBox.showAlert(OrtakOdunTuruActivity.this, "Hata:"+e.toString(), false);
        }
    }


    //sonradan kapatıldı
   /* @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrtakAgacActivity.this, Home2Activity.class);
        startActivity(intent);
        finish();
    }*/
}

