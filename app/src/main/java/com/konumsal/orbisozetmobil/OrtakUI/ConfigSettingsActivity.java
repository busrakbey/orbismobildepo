package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.konumsal.orbisozetmobil.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DataLayer.Fidanlik.FidanAjaxTurData;
import DataLayer.IsletmePazarlama.MuhStokLokasyon_Data;
import DataLayer.Ortak.ConfigData;
import DataLayer.Ortak.GpBolme_Data;
import DataLayer.Ortak.OrtakAgacTuru_Data;
import DataLayer.Ortak.OrtakIsKalemleri_Data;
import DataLayer.Ortak.OrtakNotKonu_Data;
import DataLayer.Ortak.OrtakOdunTuru_Data;
import DataLayer.Ortak.OtherUsers_Data;
import DataLayer.Ortak.STown_Data;
import DataLayer.Ortak.Unvan_Data;
import DataLayer.Sistem.SModulKodDeger_Data;
import DataLayer.Sistem.SOrgBirim_Data;

import EntityLayer.Fidanlik.FidanAjaxTur;
import EntityLayer.IsletmePazarlama.MuhStokLokasyon;
import EntityLayer.Ortak.GpBolme;
import EntityLayer.Ortak.Messages;
import EntityLayer.Ortak.OrtakAgacTuru;
import EntityLayer.Ortak.OrtakIsKalemleri;
import EntityLayer.Ortak.OrtakNotKonu;
import EntityLayer.Ortak.OrtakOdunTuru;
import EntityLayer.Ortak.OtherUsers;
import EntityLayer.Ortak.STown;
import EntityLayer.Ortak.Unvan;
import EntityLayer.Sistem.SCalisan;
import EntityLayer.Sistem.SKullanici;
import EntityLayer.Sistem.SModulKodDeger;
import EntityLayer.Sistem.SOrgBirim;
import EnumsLayer.HttpRequestType;

import ToolLayer.DateUtils;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;
import ToolLayer.StateReceiver;
import ToolLayer.Utils;

/**
 * Created by Konumsal PC11 on 25.10.2015.
 */
public class ConfigSettingsActivity extends AppCompatActivity {
    AutoCompleteTextView in_url;
    ListView in_ListView;
    Button in_btnGuncelle;

    ArrayList<SOrgBirim> list_SOrgBirim;
    ArrayList<SOrgBirim> all_list_SOrgBirim;

    List<SKullanici> list_SKullanici;
    List<SCalisan> list_SCalisan;
    List<SModulKodDeger> list_SModulKodDeger;
    List<OrtakAgacTuru> list_ortakAgacTuru;
    List<OrtakOdunTuru> list_ortakOdunTuru;
    List<Unvan> list_unvan;
    List<OtherUsers> list_persoonel;
    List<GpBolme> list_bolme;
    List<OrtakIsKalemleri> list_ortak_is_kalemleri;
    List<OrtakNotKonu> list_ortak_konu;
    List<STown> list_STown;
    // List<OzmKorumaEkip> list_korumaEkip;
    List<FidanAjaxTur> list_fidanTur;

    Type typeOf_SOrgBirim = null;
    Type typeOf_OduhTurKayitAile = null;
    Type typeOf_SModulKodDeger = null;
    Type typeOf_SKullanici = null;
    Type typeOf_SCalisan = null;
    Type typeof_OrtakAgacTuru = null;
    Type typeof_OrtakOdunTuru = null;
    Type typeof_Unvan = null;
    Type typeof_Personel = null;
    Type typeof_Bolme = null;
    Type typeof_OrtakIsKalemleri = null;
    Type typeof_OrtakKonu = null;
    Type typeof_Town = null;
    Type typeof_korumaEkip = null;
    Type typeof_fidanTur = null;

    HorizontalScrollView hsv;


    String servisURL = null;

    ProgressDialog progressDialog;
    String jsonSOrgBirim = "";
    String all_jsonSOrgBirim = "";
    String jsonOduhTurKayitAile = "";
    String jsonSKullanici = "";
    String jsonSCalisan = "";
    String jsonSModulKodDeger = "";
    String jsonOrtakAgacTuru = "";
    String jsonOrtakOdunTuru = "";
    String jsonUnvan = "";
    String jsonPersonel = "";
    String jsonBolme = "";
    String jsonOrtakIsKalemleri = "";
    String jsonOrtakKonu = "";
    String jsonTown = "";
    String jsonKorumaEkipleri = "";
    String jsonFidanTurleri = "";

    ConfigData configData;
    ArrayAdapter<String> messageAdapter;
    List<String> messageList;
    Toolbar toolbar = null;
    String ilk_giris = "0";

    ProgressDialog mprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Log.v("config", "settings");
            setContentView(R.layout.activity_config_settings);
            initToolBar();

            mprogress = new ProgressDialog(this);
            mprogress.setTitle("Orbis Mobile");
            mprogress.setMessage("Dosya kopyalanıyor.\n Lütfen Bekleyiniz ...");
            mprogress.setCancelable(false);


            Utils.HideKeyboard(getWindow());
            in_url = (AutoCompleteTextView) findViewById(R.id.configset_act_urlActx);
            in_ListView = (ListView) findViewById(R.id.configset_act_listView);
            ilk_giris = getIntent().getStringExtra("ilk_giris");
            setFields();
            configData = new ConfigData(ConfigSettingsActivity.this);
            String srvURL = configData.getSERVICURL();
            if (srvURL != null) {
                servisURL = srvURL;
                in_url.setText(srvURL);
            }
            if (ilk_giris.equals("1")) {
                if (StateReceiver.isNetworkAvailable(ConfigSettingsActivity.this)) {
                    new serviceConnectorTask().execute();
                } else {
                    MessageBox.showAlert(ConfigSettingsActivity.this, "Ağ bağlantısını kontrol ediniz ! \nİnternet bağlantınızı kurduktan sonra tekrar deneyiniz..", false);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(ConfigSettingsActivity.this, "Hata\n" + e.toString(), false);
        } catch (Throwable e) {
            e.printStackTrace();
            MessageBox.showAlert(ConfigSettingsActivity.this, "Hata\n" + e.toString(), false);

        }

    }

    public void setFields() {
        messageList = new ArrayList<String>();
        messageAdapter = new ArrayAdapter<String>(ConfigSettingsActivity.this, android.R.layout.simple_list_item_1, messageList);
        in_ListView.setAdapter(messageAdapter);

        progressDialog = new ProgressDialog(ConfigSettingsActivity.this);
        pd2 = new ProgressDialog(ConfigSettingsActivity.this);
        progressDialog.setTitle("Orbis Mobile");
        progressDialog.setMessage("İşlem gerçekleştiriliyor..\nVeriler hazırlanıyor..\nLütfen Bekleyiniz...");

        list_SOrgBirim = new ArrayList<SOrgBirim>();

        list_SKullanici = new ArrayList<SKullanici>();
        list_SCalisan = new ArrayList<SCalisan>();
        list_SModulKodDeger = new ArrayList<SModulKodDeger>();
        list_ortakAgacTuru = new ArrayList<OrtakAgacTuru>();
        list_ortakOdunTuru = new ArrayList<OrtakOdunTuru>();
        list_unvan = new ArrayList<Unvan>();
        list_bolme = new ArrayList<GpBolme>();
        list_persoonel = new ArrayList<OtherUsers>();
        list_ortak_is_kalemleri = new ArrayList<OrtakIsKalemleri>();
        list_ortak_konu = new ArrayList<OrtakNotKonu>();
        all_list_SOrgBirim = new ArrayList<SOrgBirim>();
        list_STown = new ArrayList<STown>();
        // list_korumaEkip = new ArrayList<OzmKorumaEkip>();
        list_fidanTur = new ArrayList<FidanAjaxTur>();

        typeOf_SOrgBirim = new TypeToken<List<SOrgBirim>>() {
        }.getType();
        typeOf_SModulKodDeger = new TypeToken<List<SModulKodDeger>>() {
        }.getType();
        typeOf_SKullanici = new TypeToken<List<SKullanici>>() {
        }.getType();
        typeOf_SCalisan = new TypeToken<List<SCalisan>>() {
        }.getType();
        typeof_OrtakAgacTuru = new TypeToken<List<OrtakAgacTuru>>() {
        }.getType();
        typeof_OrtakOdunTuru = new TypeToken<List<OrtakOdunTuru>>() {
        }.getType();
        typeof_Unvan = new TypeToken<List<Unvan>>() {
        }.getType();
        typeof_Bolme = new TypeToken<List<GpBolme>>() {
        }.getType();
        typeof_Personel = new TypeToken<List<OtherUsers>>() {
        }.getType();
        typeof_OrtakIsKalemleri = new TypeToken<List<OrtakIsKalemleri>>() {
        }.getType();
        typeof_OrtakKonu = new TypeToken<List<OrtakNotKonu>>() {
        }.getType();
        typeof_Town = new TypeToken<List<STown>>() {
        }.getType();
       /* typeof_korumaEkip = new TypeToken<List<OzmKorumaEkip>>() {
        }.getType();*/
        typeof_fidanTur = new TypeToken<List<FidanAjaxTur>>() {
        }.getType();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void mf_actionbargreenSaveOnclick(View v) {
        new AlertDialog.Builder(ConfigSettingsActivity.this)
                .setTitle("Orbis Mobile Sistem Bilgisi")
                .setMessage("Ayarlar kaydedilsin mi?")
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }

    public void cfgact_actionbargreenVazgecOnclick(View v) {
        Intent i = new Intent(ConfigSettingsActivity.this, Home2Activity.class);
        startActivity(i);
        ConfigSettingsActivity.this.finish();

    }

    public void cfgact_actionbargreenExitOnclick(View v) {
        Intent i = new Intent(ConfigSettingsActivity.this, LoginActivity.class);
        startActivity(i);
        ConfigSettingsActivity.this.finish();
    }


    class copyFileSyncStart extends AsyncTask<Void, Void, Void> {
        Boolean status = true;
        StringBuilder strMess = new StringBuilder();

        @Override
        protected void onPreExecute() {
            try {
                mprogress.show();
            } catch (Exception e) {
                e.printStackTrace();
                MessageBox.showAlert(ConfigSettingsActivity.this, "İşlem gerçekleştirilemedi!\nTekrar indirmeyi deneyiniz" + e.toString(), false);
            }
        }


        @Override
        protected void onPostExecute(Void v) {

            try {
                if (mprogress.isShowing())
                    mprogress.dismiss();


                long sizeInBytes = src.length();
                long sizeInMb = sizeInBytes / (1024 * 1024);

                Log.v("FİLE MB=", "=>" + sizeInMb);

                MessageBox.showAlert(ConfigSettingsActivity.this, "Dosya yolu ayarlandı\n" + sizeInMb + " MB başarıyla kopyalandı", false);
                Log.v("file sizes", "=>" + src.getTotalSpace() + "-" + src.getFreeSpace() + "-" + src.getUsableSpace());


            } catch (Exception e) {
                e.printStackTrace();
                MessageBox.showAlert(ConfigSettingsActivity.this, "Harita dosya kopyalama esnasında hata oluştu !\n" + e.toString() + "\n" + strMess.toString(), false);
            }

        }


        @Override
        protected Void doInBackground(Void... params) {

            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(src);
                out = new FileOutputStream(dst);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            try {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    File src;
    File dst;
   /* public void copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        mprogress.dismiss();
        MessageBox.showAlert(ConfigSettingsActivity.this,"Haritanın yolu ayarlandı",false);


        long sizeInBytes = src.length();
        long sizeInMb = sizeInBytes / (1024 * 1024);

        Log.v("FİLE MB=","=>"+sizeInMb);
    }
*/


    public void import_click(View v) {
        File mPath = new File(Environment.getExternalStorageDirectory().getPath());
        fileDialog = new FileDialog(this, mPath, ".db");
        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
            public void fileSelected(File file) {
                Log.d(getClass().getName(), "selected file " + file.toString() + " name=>" + file.getName());
                String output[] = file.getName().split("\\.");

                File directory = getApplicationContext().getDatabasePath("ORBISMOBILEE").getParentFile();
                Log.v("parent file ", "=>" + directory);
                if (!directory.isDirectory()) {
                    directory.mkdirs();
                }

                File destinationFile = new File(directory, "ORBISMOBILEE");//seçilen dosyanın ismiyle aynı kopyalıyor.yani kullancı kopyalamadan önce haritanın ismini değiştirmeli
                src = file;
                dst = destinationFile;

                new copyFileSyncStart().execute();

            }
        });

        fileDialog.showDialog();
    }

    public void yedekle_click(View v) {
        Log.v("on create", "copy db");
        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        File backupDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), timeStamp + ".db"); // for example "my_data_backup.db"
        Log.v("on create", "db path=>" + backupDB.getAbsolutePath());
        File currentDB = getApplicationContext().getDatabasePath("ORBISMOBILEE");
        Log.v("on create", "current db path=>" + currentDB.getAbsolutePath());
        if (currentDB.exists()) {
            Log.v("on create", "copy db2");
            FileChannel src = null;
            try {
                src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                Log.v("on create", "copy db transfered");
                src.close();
                dst.close();
                MessageBox.showAlert(ConfigSettingsActivity.this, timeStamp + " adlı veri tabanı yedeği , indirilenler klasörüne kopyalandı", false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                MessageBox.showAlert(ConfigSettingsActivity.this, "Hata oluştu", false);
            } catch (IOException e) {
                e.printStackTrace();
                MessageBox.showAlert(ConfigSettingsActivity.this, "Hata oluştu", false);
            }
        }
    }

    FileDialog fileDialog;

    public void gp_map_browse_click(View v) {
        File mPath = new File(Environment.getExternalStorageDirectory().getPath());
        fileDialog = new FileDialog(this, mPath, ".tpk");
        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
            public void fileSelected(File file) {
                Log.d(getClass().getName(), "selected file " + file.toString() + " name=>" + file.getName());


                File directory = new File(Environment.getExternalStorageDirectory().getPath() + "//orbisdata//basemapdata");
                if (!directory.isDirectory()) {
                    directory.mkdirs();
                }

                File destinationFile = new File(directory, file.getName());//seçilen dosyanın ismiyle aynı kopyalıyor.yani kullancı kopyalamadan önce haritanın ismini değiştirmeli
                src = file;
                dst = destinationFile;

                //try {
                new copyFileSyncStart().execute();
                //copyFile(file , destinationFile);//file browserdan seçilen dosyadır
                // } catch (IOException e) {
                //     e.printStackTrace();
                //    mprogress.dismiss();
                // }
            }
        });
        //fileDialog.addDirectoryListener(new FileDialog.DirectorySelectedListener() {
        //  public void directorySelected(File directory) {
        //      Log.d(getClass().getName(), "selected dir " + directory.toString());
        //  }
        //});
        //fileDialog.setSelectDirectoryOption(false);
        fileDialog.showDialog();
    }

    public void confg_set_GuncelleOnClick(View v) {
        new AlertDialog.Builder(ConfigSettingsActivity.this)
                .setTitle("Orbis Mobile Sistem Mesajı")
                .setMessage("Temel veriler cihazınıza indirilecek.\nVeri eşitleme işlemi gerçekleştirilsin mi?")
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        new serviceConnectorTask().execute();

//                                senkronize(item);
                    }
                })
                .setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onDestroy() {
        progressDialog.dismiss();
        super.onDestroy();
    }


    public class serviceConnectorTask extends AsyncTask {
        boolean urlDurum;

        Messages durum = new Messages();

        Messages messages_SModulKodDeger = new Messages();
        Messages messages_SOrgBirim = new Messages();

        Messages messages_SKullanici = new Messages();

        Messages messages_SCalisan = new Messages();

        Messages messages_OduhTurKayitAile = new Messages();
        Messages messages_ortakAgacTuru = new Messages();
        Messages messages_ortakOdunTuru = new Messages();
        Messages messages_unvan = new Messages();
        Messages messages_personel = new Messages();
        Messages messages_bolme = new Messages();
        Messages messages_ortak_is_kalemleri = new Messages();
        Messages messages_ortak_konu = new Messages();
        Messages messages_korumaEkip = new Messages();
        Messages messages_fidanTur = new Messages();
        Messages all_messages_SOrgBirim = new Messages();
        Messages messages_ilce = new Messages();


        private void ust_birimleri_yukle(String birim_parameter) {
            try {
                RSOperator operator = new RSOperator();
                System.gc();
                jsonSOrgBirim = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.sorgbirimGet_methodPost_tumkayitgetirir), birim_parameter);

                if (jsonSOrgBirim.trim().length() > 1) {

                    SOrgBirim_Data dasorg2 = new SOrgBirim_Data(ConfigSettingsActivity.this);

                    list_SOrgBirim = operator.convertJSONToEntity(jsonSOrgBirim, typeOf_SOrgBirim, list_SOrgBirim);
                    jsonSOrgBirim = null;
                    publishProgress("SOrgBirim inen veri adedi:" + list_SOrgBirim.size());

                    if (list_SOrgBirim.size() > 0) {
                        //dasorg2.clearDatabaseTable();
                        publishProgress("Birim türü  cihaz verileri temizleniyor");
                        // dasorg2.setDataList(list_SOrgBirim);
                        Log.v("eklenen ust birim=>", list_SOrgBirim.get(0).getAdi() + "-" + list_SOrgBirim.get(0).getId());
                        messages_SOrgBirim.setStatus(dasorg2.insertFromContent(list_SOrgBirim));

                        dasorg2 = null;
                        //                                ArrayList<SOrgBirim> ndas = dasorg2.list();
                        //                                messages_SOrgBirim = sOrgBirim_data.SaveChange(En_SqlOperationType.EKLE);
                        //                               List<SOrgBirim> birimlers = dasorg2.getDataList(); //sOrgBirim_data.Load();
                        if (messages_SOrgBirim.isStatus()) {

                            publishProgress("Üst SOrgBirim cihaza kaydedilen veri:" + list_SOrgBirim.size());

                        } else {
                            publishProgress("Hata !\nSOrgBirim cihaza kaydedilemedi");
                        }


                    }

                } else publishProgress("Hata !\nBirim türü verisi alınamadı !\n");
                //                        data2.getAllDataFromService();
            } catch (OrbisDefaultException e) {
                e.printStackTrace();
                publishProgress("HATA:" + e.getMessage());
                durum.setStatus(false);
                durum.getMessageList().add(e.getMessage());

            }


        }


        @Override
        protected void onProgressUpdate(Object[] values) {
            Toast.makeText(ConfigSettingsActivity.this, values[0].toString(), Toast.LENGTH_LONG).show();
            messageList.add(values[0].toString());
            messageAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            urlDurum = false;
            durum.setStatus(true);
            servisURL = in_url.getText().toString();
            messageList.add("Veriler indirilirken lütfen bekleyiniz...\n");
            if (servisURL != null && servisURL.trim().length() > 8) {
                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Veriler indirilirken lütfen bekleyiniz...");
                messageList.add(servisURL + " adresine bağlanılıyor..\n");
                urlDurum = true;
            } else {
                Toast.makeText(ConfigSettingsActivity.this, "Hata:Servis url bilgisi bulunamadı", Toast.LENGTH_LONG).show();
                MessageBox.showAlert(ConfigSettingsActivity.this, "Servis url bilgisi bulunamadı ! \n Tekrar deneyiniz...", true);
                messageList.add("Hata:Servis url bilgisi bulunamadı !");
            }
            messageAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Object o) {

            if (urlDurum && durum.isStatus()) {
                new getBaseDataServiceForSorgulamalar().execute();
                try {
                    configData.setBaseConfigData();
                } catch (OrbisDefaultException e) {
                    Log.v("cathch", "retval");
                    e.printStackTrace();

                }
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
               /* new AlertDialog.Builder(ConfigSettingsActivity.this)
                        .setTitle("Orbis Mobile Sistem Bilgisi")
                        .setMessage("İşlem Tamamlandı ")
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                if (ilk_giris.equals("1"))
                                    ConfigSettingsActivity.this.finish();


                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/
            } else {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                new AlertDialog.Builder(ConfigSettingsActivity.this)
                        .setTitle("Orbis Mobile Sistem Bilgisi")
                        .setMessage("İşlem Gerçekleştirilemedi !")
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();


                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }


        }

        @Override
        protected Object doInBackground(Object[] params) {
            do {
                messages_SOrgBirim.setStatus(false);
                if (urlDurum) {
                    try {
                        try {
                            DateUtils dateUtils = new DateUtils();
                            //DbHelper helper = new DbHelper(ConfigSettingsActivity.this);
                            // SQLiteDatabase db = helper.getWritableDatabase();
                            //                        helper.ClearDataTables(db);
                            RSOperator operator = new RSOperator();
                            jsonOrtakAgacTuru = operator.CreateToRSUrlConnection(HttpRequestType.GET, null, servisURL + getResources().getString(R.string.ortak_agac_tur_tumkayitlargetir_ortakAgacTurGet_methodPost), null);

                            if (jsonOrtakAgacTuru.trim().length() > 1) {
                                publishProgress("Servis ile bağlantı başarılı. indiriliiyor..");
                                OrtakAgacTuru_Data ortakAgacTuru_data = new OrtakAgacTuru_Data(ConfigSettingsActivity.this);
                                list_ortakAgacTuru = operator.convertJSONToEntity(jsonOrtakAgacTuru, typeof_OrtakAgacTuru, list_ortakAgacTuru);
                                jsonOrtakAgacTuru = null;
                                publishProgress("OrtakAgacTuru indirilen veri adedi:" + list_ortakAgacTuru.size());

                                if (list_ortakAgacTuru.size() > 0) {

                                    ortakAgacTuru_data.clearDatabaseTable();
                                    publishProgress("Ağaç türü  cihaz verileri temizleniyor");
                                    //ortakAgacTuru_data.setDataList((ArrayList)list_ortakAgacTuru);
                                    messages_ortakAgacTuru.setStatus(ortakAgacTuru_data.insertFromContent(list_ortakAgacTuru));
                                    ortakAgacTuru_data = null;

                                    if (messages_ortakAgacTuru.isStatus()) {

                                        publishProgress("OrtakAgacTuru cihaza kaydedilen veri adedi:" + list_ortakAgacTuru.size());
                                        publishProgress("Veri indirme bilgisi: %10 tamamlandı. Lütfen bekleyiniz..");
                                        list_ortakAgacTuru = null;


                                    } else {
                                        publishProgress("Hata !\nOrtakAgacTuru cihaza kaydedilemedi");
                                        list_ortakAgacTuru = null;
                                    }
                                    //                                list_ortakAgacTuru.clear();
                                    //                                list_ortakAgacTuru = ortakAgacTuru_data.list();
                                    //                                int kayitC = list_ortakAgacTuru.size();
                                } else publishProgress("Hata !\nAğaç türü verisi alınamadı !");
                            } else
                                publishProgress("Hata !\nAğaç türü verisi alınamadı !\nServis ile iletişim başarısız..");
                            list_ortakAgacTuru = null;


                            operator = new RSOperator();
                            System.gc();
                           /* String birim_id = OrtakFunction.birim_id;
                            if (OrtakFunction.vip_user_list.contains(OrtakFunction.kullanici_adi))
                                birim_id = OrtakFunction.admine_ozel_birim_id;

                            jsonSOrgBirim = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.sorgbirimGet_methodPost_tumkayitgetirir), birim_id);

                            if (jsonSOrgBirim.trim().length() > 1) {

                                SOrgBirim_Data dasorg2 = new SOrgBirim_Data(ConfigSettingsActivity.this);

                                list_SOrgBirim = operator.convertJSONToEntity(jsonSOrgBirim, typeOf_SOrgBirim, list_SOrgBirim);
                                jsonSOrgBirim = null;
                                publishProgress("SOrgBirim inen veri adedi:" + list_SOrgBirim.size());


                                if (list_SOrgBirim.size() > 0) {
                                    dasorg2.clearDatabaseTable();
                                    publishProgress("Birim türü  cihaz verileri temizleniyor");
                                    // dasorg2.setDataList(list_SOrgBirim);

                                    messages_SOrgBirim.setStatus(dasorg2.insertFromContent(list_SOrgBirim));

                                    dasorg2 = null;
                                    //                                ArrayList<SOrgBirim> ndas = dasorg2.list();
                                    //                                messages_SOrgBirim = sOrgBirim_data.SaveChange(En_SqlOperationType.EKLE);
                                    //                               List<SOrgBirim> birimlers = dasorg2.getDataList(); //sOrgBirim_data.Load();
                                    if (messages_SOrgBirim.isStatus()) {

                                        publishProgress("SOrgBirim cihaza kaydedilen veri:" + list_SOrgBirim.size());
                                        publishProgress("Veri indirme bilgisi: %20 tamamlandı. Lütfen bekleyiniz..");

                                        try {
                                            configData.setBaseConfigData();
                                        } catch (OrbisDefaultException e) {
                                            Log.v("cathch", "retval");
                                            e.printStackTrace();
                                        }

                                        if (configData.getIlgiliBirim() != null && configData.getIlgiliBirim().getYol() != null) {
                                            String path = configData.getIlgiliBirim().getYol();
                                            String output[] = path.split("\\.");
                                            OrtakFunction.s_org_birim_path = new ArrayList<Long>();
                                            for (String retval : output) {
                                                Log.v("retval config", "=>" + retval);
                                                OrtakFunction.s_org_birim_path.add(Long.valueOf(retval));

                                                if (!retval.equals(birim_id)) {
                                                    //ust_birimleri_yukle(retval);
                                                }
                                            }
                                        }
                                    } else {
                                        publishProgress("Hata !\nSOrgBirim cihaza kaydedilemedi");
                                    }


                                }

                            } else publishProgress("Hata !\nBirim türü verisi alınamadı !\n");
                            //                        data2.getAllDataFromService();


                            operator = new RSOperator();
                            System.gc();*/////////////  GET BIRIMLER
                            ////////////////////////////////////////////////////////////////////////////////////////

                            /*jsonKorumaEkipleri = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.SaglikDurumuRS_koruma_ekip_get), String.valueOf(configData.getIlgiliBirim().getId()));

                            if (jsonKorumaEkipleri != null && jsonKorumaEkipleri.trim().length() > 1) {

                                OzmKorumaEkip_Data data = new OzmKorumaEkip_Data(ConfigSettingsActivity.this);
                                Log.v("json ekip =>", "-" + jsonKorumaEkipleri);
                                list_korumaEkip = operator.convertJSONToEntity(jsonKorumaEkipleri, typeof_korumaEkip, list_korumaEkip);
                                publishProgress("Koruma Ekip inen veri adedi:" + list_korumaEkip.size());

                                if (list_korumaEkip.size() > 0) {
                                    data.clearDatabaseTable();
                                    publishProgress("Koruma ekip cihaz verileri temizleniyor");

                                    messages_korumaEkip.setStatus(data.insertFromContent(list_korumaEkip));
                                    data = null;

                                    if (messages_korumaEkip.isStatus()) {

                                        publishProgress("Koruma ekip cihaza kaydedilen veri:" + list_korumaEkip.size());
                                        publishProgress("Veri indirme bilgisi: %25 tamamlandı. Lütfen bekleyiniz..");
                                    } else {
                                        publishProgress("Hata !\nKoruma ekip cihaza kaydedilemedi");
                                    }

                                }

                            } else publishProgress("Hata !\nKoruma ekip verisi alınamadı !\n");*/


                           /* operator = new RSOperator();
                            System.gc();

                            jsonFidanTurleri = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.OrtakRS_get_fidan_tur), null);

                            if (jsonFidanTurleri != null && jsonFidanTurleri.trim().length() > 1) {

                                FidanAjaxTurData data = new FidanAjaxTurData(ConfigSettingsActivity.this);
                                Log.v("json fidan =>", "-" + jsonFidanTurleri);
                                list_fidanTur = operator.convertJSONToEntity(jsonFidanTurleri, typeof_fidanTur, list_fidanTur);
                                publishProgress("Fidan tur inen veri adedi:" + list_fidanTur.size());

                                if (list_fidanTur.size() > 0) {
                                    data.clearDatabaseTable();
                                    publishProgress("Fidan tur cihaz verileri temizleniyor");

                                    messages_fidanTur.setStatus(data.insertFromContent(list_fidanTur));
                                    data = null;

                                    if (messages_fidanTur.isStatus()) {

                                        publishProgress("Fidan tur cihaza kaydedilen veri:" + list_fidanTur.size());
                                        publishProgress("Veri indirme bilgisi: %30 tamamlandı. Lütfen bekleyiniz..");
                                    } else {
                                        publishProgress("Hata !\nFidan turleri cihaza kaydedilemedi");
                                    }

                                }

                            } else publishProgress("Hata !\nFidan turleri verisi alınamadı !\n");*/


                            operator = new RSOperator();
                            System.gc();

                            jsonOrtakOdunTuru = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.ortak_odun_turu_get), null);

                            if (jsonOrtakOdunTuru != null && jsonOrtakOdunTuru.trim().length() > 1) {

                                OrtakOdunTuru_Data data_odun = new OrtakOdunTuru_Data(ConfigSettingsActivity.this);
                                Log.v("json odun turu=>", "-" + jsonOrtakOdunTuru);
                                list_ortakOdunTuru = operator.convertJSONToEntity(jsonOrtakOdunTuru, typeof_OrtakOdunTuru, list_ortakOdunTuru);
                                publishProgress("Odun Turu inen veri adedi:" + list_ortakOdunTuru.size());

                                if (list_ortakOdunTuru.size() > 0) {
                                    data_odun.clearDatabaseTable();
                                    publishProgress("Odun türü cihaz verileri temizleniyor");

                                    messages_ortakOdunTuru.setStatus(data_odun.insertFromContent(list_ortakOdunTuru));
                                    data_odun = null;

                                    if (messages_ortakOdunTuru.isStatus()) {

                                        publishProgress("Ortak odun türü cihaza kaydedilen veri:" + list_ortakOdunTuru.size());
                                        publishProgress("Veri indirme bilgisi: %40 tamamlandı." + "\n" + "Yükleme devam ediyor lütfen bekleyiniz...");
                                    } else {
                                        publishProgress("Hata !\nOrtak odun türü cihaza kaydedilemedi");
                                    }


                                }

                            } else publishProgress("Hata !\nOrtak odun türü verisi alınamadı !\n");


                            operator = new RSOperator();
                            System.gc();

                            jsonTown = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.OrtakRS_ilce_get), null);

                            if (jsonTown != null && jsonTown.trim().length() > 1) {

                                STown_Data data_ilce = new STown_Data(ConfigSettingsActivity.this);
                                Log.v("json ilce=>", "-" + jsonTown);
                                list_STown = operator.convertJSONToEntity(jsonTown, typeof_Town, list_STown);
                                publishProgress("İlçe inen veri adedi:" + list_STown.size());

                                if (list_STown.size() > 0) {
                                    data_ilce.clearDatabaseTable();
                                    publishProgress("İlçe cihaz verileri temizleniyor");

                                    messages_ilce.setStatus(data_ilce.insertFromContent(list_STown));
                                    data_ilce = null;

                                    if (messages_ilce.isStatus()) {

                                        publishProgress("İlçe cihaza kaydedilen veri:" + list_STown.size());
                                        publishProgress("Veri indirme bilgisi: %50 tamamlandı." + "\n" + "Yükleme devam ediyor lütfen bekleyiniz...");
                                    } else {
                                        publishProgress("Hata !\nİlçeler cihaza kaydedilemedi");
                                    }


                                }

                            } else publishProgress("Hata !\nİlçe verisi alınamadı !\n");


                           /*operator = new RSOperator();
                            System.gc();
                           jsonUnvan = operator.CreateToRSUrlConnection(HttpRequestType.GET, null, servisURL + getResources().getString(R.string.PersonelRS_unvan_get), null);

                            if (jsonUnvan.trim().length() > 1) {
                                Unvan_Data unvan_data = new Unvan_Data(ConfigSettingsActivity.this);
                                list_unvan = operator.convertJSONToEntity(jsonUnvan, typeof_Unvan, list_unvan);
                                jsonUnvan = null;
                                publishProgress("Ünvan indirilen veri adedi:" + list_unvan.size());

                                if (list_unvan.size() > 0) {
                                    unvan_data.clearDatabaseTable();
                                    publishProgress("Ünvan cihaz verileri temizleniyor");
                                    //ortakAgacTuru_data.setDataList((ArrayList)list_ortakAgacTuru);
                                    messages_unvan.setStatus(unvan_data.insertFromContent(list_unvan));
                                    unvan_data = null;

                                    if (messages_unvan.isStatus()) {

                                        publishProgress("Ünvan cihaza kaydedilen veri adedi:" + list_unvan.size());
                                        list_unvan = null;
                                        publishProgress("Veri indirme bilgisi: %55 tamamlandı. Lütfen bekleyiniz...");
                                    } else {
                                        publishProgress("Hata !\nÜnvan cihaza kaydedilemedi");
                                        list_unvan = null;
                                    }
                                } /*else
                                    publishProgress("Hata !\nÜnvan verisi alınamadı !");
                            } else
                                publishProgress("Hata !\nÜnvan verisi alınamadı !\nServis ile iletişim başarısız..");
                            list_unvan = null


                            operator = new RSOperator();
                            System.gc();*/
                            String prm = "";
                            if (OrtakFunction.get_vip_user_list().contains(OrtakFunction.kullanici_adi))
                                prm = OrtakFunction.admine_ozel_birim_id;
                            else
                                prm = OrtakFunction.birim_id;

                            Log.v("ortak func.birim id", "=>" + prm);

                            /*jsonBolme = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.SaglikDurumuRS_get_gp_bolme), prm);

                            if (jsonBolme != null && jsonBolme.trim().length() > 1) {

                                GpBolme_Data bolme_data = new GpBolme_Data(ConfigSettingsActivity.this);
                                Log.v("json bolme=>", "-" + jsonBolme);
                                list_bolme = operator.convertJSONToEntity(jsonBolme, typeof_Bolme, list_bolme);
                                publishProgress("Bölme inen veri adedi:" + list_bolme.size());

                                if (list_bolme.size() > 0) {
                                    bolme_data.clearDatabaseTable();
                                    publishProgress("Bölme cihaz verileri temizleniyor");

                                    messages_bolme.setStatus(bolme_data.insertFromContent(list_bolme));
                                    bolme_data = null;

                                    if (messages_bolme.isStatus()) {

                                        publishProgress("Bölme cihaza kaydedilen veri:" + list_bolme.size());
                                        publishProgress("Veri indirme bilgisi: %65 tamamlandı. Lütfen bekleyiniz..");
                                    } else {
                                        publishProgress("Hata !\n Bölme cihaza kaydedilemedi");
                                    }


                                }

                            } *//*else publishProgress("Hata !\nBölme verisi alınamadı !\n");
                             */


                           /* SModulKodDeger_Data s_modul_deger_data = new SModulKodDeger_Data(ConfigSettingsActivity.this);
                            s_modul_deger_data.clearDatabaseTable();
                            publishProgress("S Modül cihaz verileri temizleniyor");

                            for (String item_prm : OrtakFunction.getOzm_s_modul_kod_deger_keys()) {
                                operator = new RSOperator();
                                System.gc();

                                jsonSModulKodDeger = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.SaglikDurumuRS_get_s_modul_kod), item_prm);

                                if (jsonSModulKodDeger != null && jsonSModulKodDeger.trim().length() > 1) {


                                    Log.v("json s modul=>", "-" + jsonSModulKodDeger);
                                    list_SModulKodDeger = operator.convertJSONToEntity(jsonSModulKodDeger, typeOf_SModulKodDeger, list_SModulKodDeger);
                                    // publishProgress(item_prm+" inen veri adedi:" + list_SModulKodDeger.size());

                                    if (list_SModulKodDeger.size() > 0) {

                                        messages_SModulKodDeger.setStatus(s_modul_deger_data.insertFromContent(list_SModulKodDeger));
                                        s_modul_deger_data = new SModulKodDeger_Data(ConfigSettingsActivity.this);

                                        if (messages_SModulKodDeger.isStatus()) {

                                            //publishProgress(item_prm+" cihaza kaydedilen veri:" + list_SModulKodDeger.size());
                                            //Log.v(item_prm," cihaza kaydedilen veri:" + list_SModulKodDeger.size());
                                            list_SModulKodDeger = new ArrayList<SModulKodDeger>();
                                            //publishProgress("Veri indirme bilgisi: %70 tamamlandı. Lütfen bekleyiniz..");

                                        } else {
                                            publishProgress("Hata !\n " + item_prm + " cihaza kaydedilemedi");
                                        }

                                    }

                                } else
                                    publishProgress("Hata !\n" + item_prm + " verisi alınamadı !\n");

                            }*/
//////////////////////////////////////BİRİMLER


                            ///////////////////////////

//////////////////////////////////////////////////////ORTAK NOT///////////////////////////////////
                         /*   operator = new RSOperator();
                            System.gc();

                            jsonOrtakIsKalemleri = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.OrtakRS_get_is_kalemleri), null);

                            if (jsonOrtakIsKalemleri != null && jsonOrtakIsKalemleri.trim().length() > 1) {

                                OrtakIsKalemleri_Data data_is_kalemleri = new OrtakIsKalemleri_Data(ConfigSettingsActivity.this);
                                Log.v("json iş kalemi turu=>", "-" + jsonOrtakIsKalemleri);
                                list_ortak_is_kalemleri = operator.convertJSONToEntity(jsonOrtakIsKalemleri, typeof_OrtakIsKalemleri, list_ortak_is_kalemleri);
                                publishProgress("İş kalemleri inen veri adedi:" + list_ortak_is_kalemleri.size());

                                if (list_ortak_is_kalemleri.size() > 0) {
                                    data_is_kalemleri.clearDatabaseTable();
                                    publishProgress("İş kalemleri cihaz verileri temizleniyor");

                                    messages_ortak_is_kalemleri.setStatus(data_is_kalemleri.insertFromContent(list_ortak_is_kalemleri));
                                    data_is_kalemleri = null;

                                    if (messages_ortak_is_kalemleri.isStatus()) {

                                        publishProgress("İş kalemleri cihaza kaydedilen veri:" + list_ortak_is_kalemleri.size());
                                        publishProgress("Veri indirme bilgisi: %75 tamamlandı. Lütfen bekleyiniz..");
                                    } else {
                                        publishProgress("Hata !\n İş kalemleri cihaza kaydedilemedi");
                                    }


                                }

                            } else publishProgress("Hata !\nİş kalemleri verisi alınamadı !\n");


                            operator = new RSOperator();
                            System.gc();*/

                           /* jsonOrtakKonu = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.OrtakRS_get_not_konu), null);

                            if (jsonOrtakKonu != null && jsonOrtakKonu.trim().length() > 1) {

                                OrtakNotKonu_Data data_konu = new OrtakNotKonu_Data(ConfigSettingsActivity.this);
                                Log.v("json konu turu=>", "-" + jsonOrtakKonu);
                                list_ortak_konu = operator.convertJSONToEntity(jsonOrtakKonu, typeof_OrtakKonu, list_ortak_konu);
                                publishProgress("Ortak not konu inen veri adedi:" + list_ortak_konu.size());

                                if (list_ortak_konu.size() > 0) {
                                    data_konu.clearDatabaseTable();
                                    publishProgress("Ortak not konu cihaz verileri temizleniyor");

                                    messages_ortak_konu.setStatus(data_konu.insertFromContent(list_ortak_konu));
                                    data_konu = null;

                                    if (messages_ortak_konu.isStatus()) {

                                        publishProgress("Ortak not konu cihaza kaydedilen veri:" + list_ortak_konu.size());
                                        publishProgress("Veri indirme bilgisi: %85 tamamlandı. Lütfen bekleyiniz..");
                                    } else {
                                        publishProgress("Hata !\n Ortak not konu cihaza kaydedilemedi");
                                    }


                                }

                            } else publishProgress("Hata !\nOrtak not konu verisi alınamadı !\n");*/


//////////////////////////////////////////7/////////////////////////////ORTAK NOT///////////////////////////////////


                            operator = new RSOperator();
                            System.gc();


                           /* jsonPersonel = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, servisURL + getResources().getString(R.string.PersonelRS_get_personel), prm);

                            if (jsonPersonel != null && jsonPersonel.trim().length() > 1) {

                                OtherUsers_Data data_personel = new OtherUsers_Data(ConfigSettingsActivity.this);
                                Log.v("json personel turu=>", "-" + jsonPersonel);
                                list_persoonel = operator.convertJSONToEntity(jsonPersonel, typeof_Personel, list_persoonel);
                                publishProgress("Personel inen veri adedi:" + list_persoonel.size());

                                if (list_persoonel.size() > 0) {
                                    data_personel.clearDatabaseTable();
                                    publishProgress("Personel cihaz verileri temizleniyor");

                                    messages_personel.setStatus(data_personel.insertFromContent(list_persoonel));
                                    data_personel = null;

                                    if (messages_personel.isStatus()) {

                                        publishProgress("Personel cihaza kaydedilen veri:" + list_persoonel.size());
                                        publishProgress("Veri indirme bilgisi: %95 tamamlandı. Lütfen bekleyiniz..");
                                    } else {
                                        publishProgress("Hata !\n Personel cihaza kaydedilemedi");
                                    }


                                }

                            } else publishProgress("Hata !\nPersonel verisi alınamadı !\n");*/


                         /*   Log.v("muh lokasyon servis", "start");
                            String url = servisURL + getResources().getString(R.string.IsletmePazarlamaRS_get_lokasyon_for_surutme);
                            List<MuhStokLokasyon> data = new ArrayList<MuhStokLokasyon>();
                            Type tip = new TypeToken<List<MuhStokLokasyon>>() {
                            }.getType();

                            if (OrtakFunction.get_vip_user_list().contains(OrtakFunction.kullanici_adi))
                                prm = OrtakFunction.admine_ozel_mudurluk_id;
                            else {
                                if (OrtakFunction.s_org_birim_path.size() == 4) {
                                    prm = String.valueOf(configData.getIlgiliBirim().getUstId());
                                } else if (OrtakFunction.s_org_birim_path.size() == 3) {
                                    prm = String.valueOf(configData.getIlgiliBirim().getId());
                                } else if (OrtakFunction.s_org_birim_path.size() == 2) {
                                    prm = String.valueOf(configData.getIlgiliBirim().getId());
                                }
                            }

                            Log.v("prm olarak gidiyor", "->" + prm);

                            String sonuc = null;

                            sonuc = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, url, prm);

                            if (sonuc != null && sonuc.trim().length() > 2) {
                                Log.v("lokasyon sonuc", "alındı");
                                data = operator.convertJSONToEntity(sonuc, tip, data);
                                Log.v("lokasyon sonuc", "jsona çevrildi");
                                //   detay_data =  operator.convertJSONToEntity(sonuc,tip_detay,detay_data);
                                if (data != null && data.size() > 0) {
                                    MuhStokLokasyon_Data lokasyon_data = new MuhStokLokasyon_Data(ConfigSettingsActivity.this);
                                    Boolean sa = lokasyon_data.clearDatabaseTable();

                                    Boolean durum = lokasyon_data.insertFromContent(data);

                                    if (durum) {
                                        for (MuhStokLokasyon lokasyon : data) {
                                            Log.v("gelen lokasyon id =>", "__>" + String.valueOf(lokasyon.getId()) + "-" + lokasyon.getAdi());
                                        }

                                        Log.v("lokasyonlar kaydedildi", "bla blamessagebox");
                                        publishProgress("Veri indirme bilgisi: %100 tamamlandı.");
                                    } else {
                                        Log.v("veriler kaydolmadı", "hata bla ala");
                                    }

                                } else {
                                    Log.v("log", "Serviste stok lokasyon verisi bulunamadı !\n");
                                }

                            } else {
                                Log.v("log", "Servisten veri alınamadı !\nStok lokasyona ait veri mevcut değil ..");
                            }*/

                            //publishProgress("Veri indirme bilgisi: %100 tamamlandı.");
                            jsonSOrgBirim = null;
                            list_SOrgBirim = null;
                            list_ortakOdunTuru = null;
                            jsonOrtakOdunTuru = null;
                            jsonUnvan = null;
                            jsonBolme = null;
                            jsonPersonel = null;
                            operator = new RSOperator();

                            jsonSOrgBirim = null;
                            list_SOrgBirim = null;
                            operator = new RSOperator();


                        } catch (JsonSyntaxException e) {

                            e.printStackTrace();
                            publishProgress("HATA:" + e.getMessage());
                            durum.setStatus(false);
                            durum.getMessageList().add("Hata:" + e.getMessage());
                            throw new OrbisDefaultException(e.getMessage());

                        }

                    } catch (OrbisDefaultException e) {
                        e.printStackTrace();
                        publishProgress("HATA:" + e.getMessage());
                        durum.setStatus(false);
                        durum.getMessageList().add(e.getMessage());

                    }
                }
            } while (false);

            return null;
        }
    }


    public void changeservisUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ConfigSettingsActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SERVICEURL", servisURL);
        editor.apply();
    }

    public void userDelete() {
        ConfigData data = new ConfigData(ConfigSettingsActivity.this);
        data.deleteConfig();
    }

    public void initToolBar() {

        try {
            toolbar = (Toolbar) findViewById(R.id.config_settingact_toolbar);
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
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(ConfigSettingsActivity.this, "Hata:" + e.toString(), false);

        } catch (Throwable e) {
            e.printStackTrace();
            MessageBox.showAlert(ConfigSettingsActivity.this, "Hata:" + e.toString(), false);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    ProgressDialog pd2;

    public class getBaseDataServiceForSorgulamalar extends AsyncTask {
        StringBuilder sb = new StringBuilder();
        Boolean result = true;


        @Override
        protected void onPreExecute() {
            pd2.setMessage("Veriler indirilirken lütfen bekleyiniz..");
            pd2.setTitle("Orbis Mobile");
            pd2.show();
        }

        @Override
        protected void onPostExecute(Object o) {
            pd2.dismiss();
            if (!result) {
                //  MessageBox.showAlert(OzmSorgulamaMainActivity.this,"Sorgulama için gerekli temel veriler indirilemedi!",false);

                new AlertDialog.Builder(ConfigSettingsActivity.this)
                        .setTitle("Orbis Mobile Sistem Bilgisi")
                        .setMessage("İşlem Tamamlandı ")
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                if (ilk_giris.equals("1"))
                                    ConfigSettingsActivity.this.finish();


                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                // finish();
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.v("birim sorgulama", "base");
            ConfigData configData = new ConfigData(ConfigSettingsActivity.this);
            String surl = configData.getSERVICURL();
            String servisURL = surl + getResources().getString(R.string.sorgbirimGet_methodPost_tumkayitgetirir);
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
                        for (SOrgBirim item : all_list_SOrgBirim) {
                            if (item.getKategori() != null) {
                                if (String.valueOf(item.getKategori()).equals("5")) {

                                    if (!item.getAdi().contains("DOĞA")) {
                                        OrtakFunction.bolge_list.add(item);
                                        OrtakFunction.bolge_list_string.add(item.getAdi());
                                    } else {
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
                                if (object2.getAdi() != null && object1.getAdi() != null)
                                    return object1.getAdi().compareTo(object2.getAdi());
                                return 0;
                            }
                        });

                        for (int i = 0; i < milli_park_bolge_mud.size(); i++) {
                            OrtakFunction.bolge_list.add(milli_park_bolge_mud.get(i));
                            OrtakFunction.bolge_list_string.add(milli_park_bolge_mud.get(i).getAdi());
                        }

                        OrtakFunction.bolge_list.add(0, null);
                        OrtakFunction.bolge_list_string.add(0, "");
                        publishProgress("Birim inen veri adedi:" + all_list_SOrgBirim.size());
                        publishProgress("Veri indirme bilgisi: %100 tamamlandı.");


                    }

                } else
                    MessageBox.showAlert(ConfigSettingsActivity.this, "Hata !\nBirim  verisi alinamadi !\n", false);
            } catch (
                    OrbisDefaultException e) {
                e.printStackTrace();
                result = false;
            } catch (Throwable e) {
                e.printStackTrace();
                result = false;
            }

            return null;
        }
    }

}
