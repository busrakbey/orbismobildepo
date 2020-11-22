package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



import com.konumsal.orbisozetmobil.R;
import com.konumsal.orbisozetmobil.com.google.zxing.integration.android.IntentIntegrator;
import com.konumsal.orbisozetmobil.com.google.zxing.integration.android.IntentResult;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

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
import EnumsLayer.HttpRequestType;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;
import ToolLayer.Utils;

public class Home2Activity extends AppCompatActivity {

    Toolbar toolbar = null;
    ImageView iv;
    private static final int CONTENT_REQUEST = 1337;
    String upLoadServerUri;



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            MessageBox.showAlert(Home2Activity.this,scanContent,false);
            if(scanContent.startsWith("http") || scanContent.startsWith("www"))
            {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(scanContent));
                    startActivity(i);
                }
                catch (Exception ex)
                {
                    MessageBox.showAlert(Home2Activity.this,"url hatalı...",false);
                }
            }
            Toast toast = Toast.makeText(getApplicationContext(), scanContent + "--" + scanFormat, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

        Log.v("oakl", "activity result");
        if (resultCode == 0) {
            // TODO Extract the data returned from the child Activity.
            // loadDataTaskYeni();
        }

        if (requestCode == CONTENT_REQUEST) {
            Log.v("onay ", "resim show");
            //byte[ ] buffer = new byte[256];
            // String imageDataString = Base64.encodeToString(buffer,Base64.DEFAULT);
            // setPic();




         /*   ConfigData cd = new ConfigData(Home2Activity.this);
            upLoadServerUri = cd.getSERVICURL()+Home2Activity.this.getResources().getString(R.string.OrtakRS_upload_pictures);
            String fileName = Environment.getExternalStorageDirectory().toString()+"/5.jpg";
            String fileName2 = file.getName();
            Log.v("uri","=>"+upLoadServerUri);
            Log.v("lolo","xxccc");
            Log.v("file name ","=>"+fileName);
            Log.v("file name2 ","=>"+file.getAbsolutePath());
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = new File(fileName);

            if (!sourceFile.isFile()) {

                Log.v("file ","empty");
            }
            else
            {
                try {
                    Log.v("file ","full");
                    // open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(upLoadServerUri);

                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    //conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                   // conn.setRequestProperty("uploaded_file", fileName);

                    dos = new DataOutputStream( conn.getOutputStream() );
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + file.getAbsolutePath() +"\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // Read file
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0)
                    {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Responses from the server (code and message)

                    int serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();

                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (MalformedURLException ex) {
                    Log.v("malm","exc");
                    Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                } catch (Exception e) {

                    Log.v("coomon","exc");
                }


            }*/
        }
    }

   /* private void setPic() {

        // Get the dimensions of the View
        int targetW = iv.getWidth();
        int targetH = iv.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
        iv.setImageBitmap(bitmap);
    }*/


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
                oa.showAyarlarPage(Home2Activity.this);

                return true;

            case R.id.menu3:
                Log.v("home4", "1");

                    new AlertDialog.Builder(Home2Activity.this)
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

    private void verileri_sil() throws OrbisDefaultException {


        SModulKodDeger_Data sModulKodDegerData = new SModulKodDeger_Data(Home2Activity.this);
        SCalisan_Data sCalisanData = new SCalisan_Data(Home2Activity.this);
        SOrgBirim_Data sOrgBirimData = new SOrgBirim_Data(Home2Activity.this);
        SKullanici_Data sKullanici_data = new SKullanici_Data(Home2Activity.this);
        OrtakAgacTuru_Data ortakAgacTuruData = new OrtakAgacTuru_Data(Home2Activity.this);
        OrtakOdunTuru_Data ortakOdunTuruData = new OrtakOdunTuru_Data(Home2Activity.this);
        Unvan_Data unvanData = new Unvan_Data(Home2Activity.this);
        OtherUsers_Data otherUsersData = new OtherUsers_Data(Home2Activity.this);
        GpBolme_Data gpBolmeData = new GpBolme_Data(Home2Activity.this);
        MuhStokLokasyon_Data muhStokLokasyonData = new MuhStokLokasyon_Data(Home2Activity.this);
        OrtakKisi_Data ortakKisiData = new OrtakKisi_Data(Home2Activity.this);;
        User_Data userData = new User_Data(Home2Activity.this,new User());


        OrtakNot_Data ortakNotData = new OrtakNot_Data(Home2Activity.this);
        OrtakNotKonu_Data ortakNotKonuData = new OrtakNotKonu_Data(Home2Activity.this);
        OrtakIsKalemleri_Data ortakIsKalemleriData = new OrtakIsKalemleri_Data(Home2Activity.this);
        MobilGuzergah_Data mobilGuzergahData = new MobilGuzergah_Data(Home2Activity.this);
        OrtakKisiHareket_Data ortakKisiHareketData = new OrtakKisiHareket_Data(Home2Activity.this);
        STown_Data sTownData = new STown_Data(Home2Activity.this);
        OrtakKamera_Data ortakKameraData = new OrtakKamera_Data(Home2Activity.this);
        FidanAjaxTurData fidanAjaxTurData  = new FidanAjaxTurData(Home2Activity.this);
        SanatYapi_Data sanatYapiData = new SanatYapi_Data(Home2Activity.this);
        YolBilgi_Data yolBilgiData = new YolBilgi_Data(Home2Activity.this);


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


    public int kontrol = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ortak_home2_layout);
        //   iv = (ImageView)findViewById(R.id.imageView);
        Utils.HideKeyboard(getWindow());
        initToolBar();
        OrtakFunction.setModul_deger_keys();
        //Log.v("foto gonder","on create");
        // foto_gonder();
        if(OrtakFunction.mContext == null)
            OrtakFunction.mContext = getBaseContext();


        if (kontrol == 0 && OrtakFunction.s_org_birim_path == null) {

            kontrol = 1;
            Log.v("home2", "2");
            ortakVeriIndir("1");
        }
        //connectWebSocket();
    }


    class imageTask extends AsyncTask {
        @Override
        protected void onPostExecute(Object o) {

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Object doInBackground(Object[] params) {


            try {

                String s1 = "\"inputByteArray\":";
                String s2 = "{";
                String s3 = "}";
                String s5 = "\"";
                String s4 = imageDataString;

                String s1_1 = "\"path\":";
                String s4_1 = target_path;


                String s1_2 = "\"fileName\":";
                String s4_2 = target_fileName;

                String input1 = s2 + s1 + s5 + s4 + s5 + "," + s1_1 + s5 + s4_1 + s5 + "," + s1_2 + s5 + s4_2 + s5 + s3;
                Log.v("input", "=>" + input1);

             /*  int maxLogSize = 1000;
               for(int i = 0; i <= input1.length() / maxLogSize; i++) {
                   int start = i * maxLogSize;
                   int end = (i+1) * maxLogSize;
                   end = end > input1.length() ? input1.length() : end;
                   Log.v("str1", input1.substring(start, end));
               }*/


                RSOperator rsOperator = new RSOperator();
                ConfigData cd = new ConfigData(Home2Activity.this);
                String url = cd.getSERVICURL() + Home2Activity.this.getResources().getString(R.string.OrtakRS_upload_pictures);
                Log.v("path/file", "=>" + target_path + "-" + target_fileName);

                String s = rsOperator.CreateToRSUrlConnection(HttpRequestType.POST, null, url, input1);
                //  s.replace("\"id\":0","\"id\":null");
                Log.v("sssssss", "==>" + s);
                if (s != null && s.trim().trim().length() > 3) {
                    Log.v("image", "dönüş değerini falan alırsıın.resim kaydoldu mu falan diye");
                }


            } catch (OrbisDefaultException e) {

            } catch (Exception e) {

            } catch (Throwable e) {

            }

            return null;
        }//end of backround
    }//end of async


    String imageDataString = "";
    String target_path = "";
    String target_fileName = "";
    String imageDataString2 = "";

    private void foto_gonder() {

        Log.v("foto gonder", "definition");
        String fileNameWithPath = Environment.getExternalStorageDirectory().toString() + "/5.jpg";
        target_fileName = "mobildeneme.jpg";
        target_path = "D:\\\\mobilPictures";//string olarak \\ yazılmak zorunda.2.\\ json için eklendi
        File file = new File(fileNameWithPath);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            Log.v("file path", "=>" + fileNameWithPath);
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            Log.v("bytes", "=>" + bytes.toString());
            imageDataString = Base64.encodeToString(bytes, Base64.NO_WRAP);

            // Log.v("byte str","=>"+imageDataString);

          /*  int maxLogSize = 1000;
            for(int i = 0; i <= imageDataString.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > imageDataString.length() ? imageDataString.length() : end;
                Log.v("str1", imageDataString.substring(start, end));
            }*/

            //BURASI FARKLI BİR BYTE ARRAY ÜRETİYOR.ÜSTTEKİ ÇALIŞIYOR

           /* Bitmap bitmap = BitmapFactory.decodeFile(fileName);
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 , blob);
            byte[] bitmapdata = blob.toByteArray();
            imageDataString2 = Base64.encodeToString(bitmapdata,Base64.DEFAULT);


            for(int i = 0; i <= imageDataString2.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > imageDataString2.length() ? imageDataString2.length() : end;
                Log.v("str2", imageDataString2.substring(start, end));
            }*/

            new imageTask().execute();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }





   /*public void home2_yangin_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, OrmanYanginAltMenu.class);
        startActivity(mintent);
    }

    public void home2_bluetooth_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, Deneme.class);
        startActivity(mintent);
    }

    public void home2_orkoy_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, OrkoyAltMenu.class);
        startActivity(mintent);
    }

    public void home2_yonetici_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, YoneticiEkraniActivity.class);
        startActivity(mintent);
    }


    public void home2_notlar_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, OrtakNotGuzergahActivity.class);
        startActivity(mintent);
    }


    //oduh click
    public void oduh_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, OduhAltMenu.class);
        startActivity(mintent);
    }

    //fidanlik_onclick
    public void fidanlik_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, FidanlikAltMenu.class);
        startActivity(mintent);
    }*/


    //OZM CLİCK
   /* public void home2_2_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, OzmAltMenu.class);
        startActivity(mintent);

    }

    //silvikultur
    public void home2_silvikultur_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, SilvikulturAltMenu.class);
        startActivity(mintent);

    }


    public void home2_ip_onclick(View v) {

        Intent mintent = new Intent(Home2Activity.this, IpAltMenu.class);
        startActivity(mintent);

    }

    public void home2_yaninokta_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, PlanSelectorActivity.class);
        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mintent.putExtra("MODE", 1);
        startActivity(mintent);
        Home2Activity.this.finish();

    }*/

  /*  public void home2_plan_onclick(View v) {
        PLAN_Data plan_data = new PLAN_Data(Home2Activity.this);
        String strSql = "SELECT * FROM PLAN";
        try {
            Long countt = plan_data.getRecordCount(strSql);
            if (countt > 0) {
                Log.v("plan var", "devam");
                Intent mintent = new Intent(Home2Activity.this, PlanSelectorActivity.class);
                mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mintent.putExtra("MODE", 0);
                startActivity(mintent);
                //Home2Activity.this.finish();
            } else {
                Intent mintent = new Intent(Home2Activity.this, PlanDataActivity.class);
                mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mintent.putExtra("MODE", 0);
                startActivity(mintent);
                // Home2Activity.this.finish();
            }

        } catch (OrbisDefaultException e) {
            MessageBox.showAlert(Home2Activity.this, e.toString(), false);
        }


    }

    public void home2_plandataactivity_onclick(View v) {
        Intent mintent = new Intent(Home2Activity.this, PlanDataActivity.class);
        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mintent);
        Home2Activity.this.finish();

    }*/







    private void ortakVeriIndir(String ilk_giris) {
        Intent mintent = new Intent(Home2Activity.this, ConfigSettingsActivity.class);
        mintent.putExtra("ilk_giris", ilk_giris);
        startActivity(mintent);
    }

    public void home2_agacturu_onclick(View v)
    {
        Intent mintent = new Intent(Home2Activity.this, OrtakAgacActivity.class);
        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mintent);
        Home2Activity.this.finish();
       // FragmentManager manager = getFragmentManager();
       // FragmentDefaultTreeList fm = new FragmentDefaultTreeList(Home2Activity.this);
       // fm.show(manager, "");
    }

    public void initToolBar()
    {

        try {
            toolbar =(Toolbar)findViewById(R.id.ortak_home2_toolbar);
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
                    Intent intent = new Intent(Home2Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(Home2Activity.this, "Hata:"+e.toString(), false);

        }catch (Throwable e) {
            e.printStackTrace();
            MessageBox.showAlert(Home2Activity.this, "Hata:"+e.toString(), false);
        }
    }



    static Uri capturedImageUri = null;
    File file;
    public void foto_onclick(View v)
    {


        Calendar cal = Calendar.getInstance();
        file = new File(Environment.getExternalStorageDirectory(), (cal.getTimeInMillis() + ".jpg"));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        capturedImageUri = Uri.fromFile(file);
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
        startActivityForResult(i, CONTENT_REQUEST);



    }




    public void home2_barcode_onclick(View v)
    {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }














}
