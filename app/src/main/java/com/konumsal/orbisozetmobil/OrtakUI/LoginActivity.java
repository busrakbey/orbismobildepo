package com.konumsal.orbisozetmobil.OrtakUI;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.konumsal.orbisozetmobil.DbHelper;
import com.konumsal.orbisozetmobil.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DataLayer.Ortak.ConfigData;
import DataLayer.Ortak.OrtakAgacTuru_Data;
import DataLayer.Ortak.User_Data;
import DataLayer.Sistem.SKullanici_Data;
import DataLayer.Sistem.SModulKodDeger_Data;
import DataLayer.Sistem.SOrgBirim_Data;
import EntityLayer.GeoPortal.ORNEK_ALAN;
import EntityLayer.Ortak.Messages;
import EntityLayer.Ortak.OrtakAgacTuru;
import EntityLayer.Ortak.OrtakKisi;
import EntityLayer.Ortak.Result;
import EntityLayer.Ortak.User;
import EntityLayer.Sistem.SCalisan;
import EntityLayer.Sistem.SKullanici;
import EntityLayer.Sistem.SModulKodDeger;

import EntityLayer.Sistem.SOrgBirim;
import EnumsLayer.HttpRequestType;

import ToolLayer.DateUtils;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;
import ToolLayer.RSOperator;
import ToolLayer.SqlBuilder;
import ToolLayer.StateReceiver;
import ToolLayer.Utils;


public class LoginActivity extends Activity implements DialogConfigFragment.serviceUrlListener{

    Button loginBtn;
    ImageView logoimage;
    List<ORNEK_ALAN> ornek_alanList;
    EditText login_act_txtUserName,login_act_txtPassword;
    //FrameLayout rootView;
    String uname=null;
    String upwd = null;
    ConfigData configData;

    String jsonSOrgBirim="";
    String jsonOduhTurKayitAile="";
    String jsonSKullanici="";
    String jsonSCalisan="";
    String jsonSModulKodDeger="";
    String jsonOrtakAgacTur="";
    String jsonGpPlanHazirlik="";
    String jsonOrnekAlanNokta="";
    String jsonOrtakAgacTuru="";
    public static String serviceUrl = "https://orbis.ogm.gov.tr/orbis";//"https://linkobs.ogm.gov.tr/orbis";
    NotificationManager nm;
    TextView version_txt;

    ArrayList<SOrgBirim> list_SOrgBirim;

    List<SKullanici> list_SKullanici;
    List<SCalisan> list_SCalisan;
    List<SModulKodDeger> list_SModulKodDeger;
    List<OrtakAgacTuru> list_ortakAgacTuru;

    Type typeOf_SOrgBirim=null;
    Type typeOf_SModulKodDeger=null;
    Type typeOf_SKullanici=null;
    Type typeOf_SCalisan=null;
    Type typeof_OrtakAgacTuru =null;
    ProgressDialog progressDialog;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.00F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);
        Log.v("login","activity");
        Utils.HideKeyboard(getWindow());
        configData = new ConfigData(this);
        loginBtn = (Button) findViewById(R.id.login_act_btnLogin);
        //rootView=(FrameLayout)findViewById(R.id.login_act_container);
        login_act_txtUserName = (EditText)findViewById(R.id.login_act_txtUserName);
        login_act_txtPassword =(EditText)findViewById(R.id.login_act_txtPassword);

        animation_count = 0;
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Orbis");
        progressDialog.setMessage("İşlem gerçekleştiriliyor...");

        SqlBuilder<User> sqlBuilder = new SqlBuilder<User>(new User());
        try {
            //DbHelper helper = new DbHelper(this);
            //SQLiteDatabase db = helper.getWritableDatabase();
            String crst = sqlBuilder.createTable(true);
            String ab = sqlBuilder.createTable(true);
        } catch (OrbisDefaultException e) {
            e.printStackTrace();
        }

        list_SOrgBirim = new ArrayList<SOrgBirim>();

        list_SKullanici = new ArrayList<SKullanici>();
        list_SCalisan = new ArrayList<SCalisan>();
        list_SModulKodDeger = new ArrayList<SModulKodDeger>();
        list_ortakAgacTuru = new ArrayList<OrtakAgacTuru>();

        typeOf_SOrgBirim = new TypeToken<List<SOrgBirim>>(){}.getType();

        typeOf_SModulKodDeger = new TypeToken<List<SModulKodDeger>>(){}.getType();
        typeOf_SKullanici = new TypeToken<List<SKullanici>>(){}.getType();
        typeOf_SCalisan = new TypeToken<List<SCalisan>>(){}.getType();
        typeof_OrtakAgacTuru = new TypeToken<List<OrtakAgacTuru>>(){}.getType();
        firstOpenHelper();
        User usr = defUser();
        if (usr!=null)
        {
            login_act_txtUserName.setText(usr.getMadi());
            login_act_txtPassword.setText(usr.getMsifre());
        }

       // version_txt = (TextView) findViewById(R.id.version_txt);

    }
    public void login_Act_ayarOnClick(final View v)
    {
        v.startAnimation(buttonClick);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.m_my2anim);
        v.clearAnimation();
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                showConnectionPopup();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    int animation_count = 0;
    public void LoginOnClick(final View v)
    {
        uname = login_act_txtUserName.getText().toString().trim();
        upwd = login_act_txtPassword.getText().toString().trim();

        Log.v("login click","button click");
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.m_move_anim);
        v.clearAnimation();
        v.startAnimation(animation);
        progressDialog.show();
        checkUser();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.v("login click","start animation");
            }

            @Override
            public void onAnimationEnd(Animation animation) {


                //if(animation_count == 0) {
                //animation_count = 1;
                try {
                    Log.v("login click", "animation end");
                    // progressDialog.show();
                    // checkUser();

                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                v.clearAnimation();
                //  }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    public void getServiceUrl(String url, int mod) {
        configData.setSERVICURL(url);
        configData = new ConfigData(LoginActivity.this);
    }


    public void checkUser()
    {
        if (login_act_txtUserName.getText().toString().trim().length()>1 && login_act_txtPassword.getText().toString().trim().length()>0)
        {
            String name = login_act_txtUserName.getText().toString().trim();
            String pwd = login_act_txtPassword.getText().toString().trim();
            Boolean success = false;
            success =hasUser(name,pwd);
            if (success)
            {
                Log.v("check user","user kayıtlı");
                progressDialog.dismiss();
                goHome();
            }
            else
            {

                if (StateReceiver.isNetworkAvailable(LoginActivity.this))
                {
                    if (configData.getSERVICURL().trim().length()>10)
                    {
                        new userTask().execute();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        MessageBox.showAlert(LoginActivity.this,"Bağlantı için server url adresi bulunamadı  !\n Lütfen ayarlarınızı kontrol ediniz..",false);
                    }

                }
                else
                {
                    progressDialog.dismiss();
                    MessageBox.showAlert(LoginActivity.this,"İnternet  bağlantınız mevcut değil !\n Lütfen bağlantı ayarlarınızı kontrol ediniz..",false);
                }
            }

        }
        else
        {
            MessageBox.showAlert(LoginActivity.this,"Kullanıcı adı ve parola bilgisini eksiksiz giriniz !",false);
            progressDialog.dismiss();
        }



    }
    public class userTask extends AsyncTask<Void,Void,Result<User>>
    {
        Result<SKullanici> resKull=null;
        Boolean sKullanciDurum=true;
        @Override
        protected void onPostExecute(Result<User> result) {
            if (result.getResult()!=null)
            {
                progressDialog.dismiss();
                User kullanici = result.getResult();
                if (kullanici!=null)
                {
                    if (!sKullanciDurum)
                    {
                        MessageBox.showToast(LoginActivity.this,"Kullanıcı çevrim hatası oluştu !");
                    }
                    kullanici.setMadi(uname);
                    kullanici.setMsifre(upwd);
                    kullanici.setOrgId(kullanici.getId());
                    Log.v("kullanici Id = >>",String.valueOf(kullanici.getId()));
                    OrtakFunction.kullanici_id = kullanici.getId();
                    OrtakFunction.kullanici_adi = uname;
                    if(OrtakFunction.get_vip_user_list().contains(OrtakFunction.kullanici_adi))
                        OrtakFunction.birim_id = OrtakFunction.admine_ozel_birim_id;
                    else
                        OrtakFunction.birim_id = String.valueOf(kullanici.getOrgBirimId());
                    OrtakFunction.kullanici_unvan_id = kullanici.getUnvanId();
                    OrtakFunction.kullanici_gercek_adi = kullanici.getAdi();
                    OrtakFunction.kullanici_soyadi = kullanici.getSoyadi();

                    try {
                        configData.setBaseConfigData();
                    } catch (OrbisDefaultException e) {
                        Log.v("cathch","retval");
                        e.printStackTrace();
                    }


                    //Log.v("path","=>"+configData.getIlgiliBirim().getYol());

                    if(configData.getIlgiliBirim() != null && configData.getIlgiliBirim().getYol() != null ) {
                        String path = configData.getIlgiliBirim().getYol();
                        String output[] = path.split("\\.");

                        OrtakFunction.s_org_birim_path = new ArrayList<Long>();

                        for (String retval : output) {
                            Log.v("retval", "=>" + retval);
                            OrtakFunction.s_org_birim_path.add(Long.valueOf(retval));
                        }

                    }

                    new saveUserTask().execute(kullanici);
                }
                else
                {
                    progressDialog.dismiss();
                    MessageBox.showAlert(LoginActivity.this,"Kullanıcı girişi hatalı Hata kodu:3 !\n"+"Kullanıcı bulunamadı",false);
                }

            }
            else
            {
                progressDialog.dismiss();
                MessageBox.showAlert(LoginActivity.this, "Kullanıcı girişi hatalı\nHata kodu:4 !\n" + "Kullanıcı adı veya şifre hatalı!", false);
            }
        }

        @Override
        protected Result<User> doInBackground(Void... params)
        {
            ConfigData cd = new ConfigData(LoginActivity.this);
            resKull=new Result<SKullanici>();
            String input = uname+","+upwd;
            input = set_input_parameter_list(uname , upwd);
            String url = cd.getSERVICURL()+getResources().getString(R.string.loginActivity_kullaniciDogrulamaPost);
            RSOperator operator = new RSOperator();
            String retVal=null;
            Result<User> sr = new Result<User>();
            Type typeOf_val=null;
            List<String> valList= new ArrayList<String>();
            Result<User> res= new Result<User>();

            typeOf_val = new TypeToken<Result<User>>(){}.getType();
            try {
                Log.v("login connection url","=>"+url);
                retVal = operator.CreateToRSUrlConnection(HttpRequestType.POST, null, url,input);
                if (retVal!=null)
                {
                    sr = operator.convertJSONToEntity(retVal,typeOf_val,sr);

                    typeOf_val = new TypeToken<Result<SKullanici>>(){}.getType();
                    resKull =operator.convertJSONToEntity(retVal,typeOf_val,resKull);
                    if (resKull!=null)
                    {
                        SKullanici kullnanc= resKull.getResult();
                        if (kullnanc!=null)
                        {
                            SKullanici_Data sKullanici_data = new SKullanici_Data(LoginActivity.this);
                            sKullanici_data.clearDatabaseTable();
                            sKullanici_data.getDataList().add(kullnanc);
                            sKullanciDurum=   sKullanici_data.insert();
                        }

                    }

                    // res=  operator.convertResultFromJson(retVal, new SKullanici());
                    int sz = valList.size();
                }


            } catch (OrbisDefaultException e)
            {
                sr.addError("Hata1\n"+e.toString());
                e.printStackTrace();
            }
            catch (Throwable e)
            {
                sr.addError("Hata2\n"+e.toString());
                e.printStackTrace();
            }
            return sr;
        }
    }


    private String set_input_parameter_list(String kulAdi , String sifre) {

        String s1 = "\"username\":";
        String s2 = "{";
        String s3 = "}";
        String s5 = "\"";
        String s4 = "-1";
        s4 = kulAdi;

        String s1_1 = "\"password\":";
        String s4_1 = "-1";
        s4_1 = sifre;


        String input1 = s2 + s1 + s5 + s4 + s5 + "," +
                s1_1 + s5 + s4_1 + s5 + s3;
        return input1;
    }

    public Boolean hasUser(String name , String pwd)  {
        User_Data ud= new User_Data(LoginActivity.this,new User());
        User u = null;
        Boolean durum =false;
        try
        {
            List<User> uList = ud.list();
            if (uList !=null && uList.size()>0)
            {
                for (User user:uList) {
                    if(user.getMadi().equals(name))
                    {
                        u = user;
                        break;
                    }
                }
                //u=uList.get(0);
                if (u!=null)
                {

                    if (u.getMadi()!=null && u.getMsifre()!=null)
                    {
                        String  _ad=u.getMadi();
                        String  _pwd =u.getMsifre();
                        String kullanici_id = String.valueOf(u.getId());
                        Log.v("mevcut kullanici ==>>","id="+kullanici_id+" birim="+String.valueOf(u.getOrgBirimId())+" name="+name);
                        OrtakFunction.kullanici_id = u.getId();
                        OrtakFunction.kullanici_adi = name;
                        if(OrtakFunction.get_vip_user_list().contains(OrtakFunction.kullanici_adi))
                            OrtakFunction.birim_id = OrtakFunction.admine_ozel_birim_id;
                        else
                            OrtakFunction.birim_id = String.valueOf(u.getOrgBirimId());
                        OrtakFunction.kullanici_unvan_id = u.getUnvanId();
                        OrtakFunction.kullanici_gercek_adi = u.getAdi();
                        OrtakFunction.kullanici_soyadi = u.getSoyadi();
                        Log.v("ad soyad","=>"+u.getAdi()+"-"+u.getSoyadi()+"-"+u.getAdiSoyadi()+"-"+login_act_txtPassword.getText().toString());

                        try {
                            configData.setBaseConfigData();
                        } catch (OrbisDefaultException e) {
                            Log.v("cathch","retval");
                            e.printStackTrace();
                        }

                        //Log.v("path","=>"+configData.getIlgiliBirim().getYol());

                        if(configData.getIlgiliBirim() != null  && configData.getIlgiliBirim().getYol() != null ) {
                            String path = configData.getIlgiliBirim().getYol();
                            String output[] = path.split("\\.");

                            OrtakFunction.s_org_birim_path = new ArrayList<Long>();

                            for (String retval : output) {
                                Log.v("retval", "=>" + retval);
                                OrtakFunction.s_org_birim_path.add(Long.valueOf(retval));
                            }
                        }



                        if (_ad.equals(name)&&_pwd.equals(pwd))
                        {
                            durum=true;
                        }else
                        {
                            if (!_ad.equals(name))
                            {
                               /* PLAN_Data plan_data = new PLAN_Data(LoginActivity.this);
                                EN_KAR_AGAC_Data en_kar_agac_data = new EN_KAR_AGAC_Data(LoginActivity.this);
                                ORNEK_ALAN_Data ornek_alan_data = new ORNEK_ALAN_Data(LoginActivity.this);
                                en_kar_agac_data.clearDatabaseTable();
                                plan_data.clearDatabaseTable();
                                ornek_alan_data.clearDatabaseTable();*/
                            }

                        }
                    }
                    Boolean dm =durum;
                }
            }


        } catch (OrbisDefaultException e) {
            e.printStackTrace();
            MessageBox.showAlert(LoginActivity.this, "Kullanıcı sorgulama hatalı Hata:1!\n" + "", false);
            durum=false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            MessageBox.showAlert(LoginActivity.this, "Kullanıcı sorgulama hatalı Hata:2!\n" + "", false);
            durum=false;
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            MessageBox.showAlert(LoginActivity.this, "Kullanıcı sorgulama hatalı Hata:3!\n" +"", false);
            durum=false;
        }
        return durum;
    }
    public User defUser()
    {
        User_Data ud= new User_Data(LoginActivity.this,new User());
        User u = null;
        try
        {
            List<User> uList = ud.list();
            if (uList !=null && uList.size()>0)
            {
                u=uList.get(0);
            }


        } catch (OrbisDefaultException e) {
            e.printStackTrace();
            MessageBox.showAlert(LoginActivity.this, "Kullanıcı sorgulama hatalı def1 !\n" + "", false);
            return u;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            MessageBox.showAlert(LoginActivity.this, "Kullanıcı sorgulama hatalı def2!\n" + "", false);
            return u;

        }
        catch (Throwable e)
        {
            e.printStackTrace();
            MessageBox.showAlert(LoginActivity.this, "Kullanıcı sorgulama hatalı def3!\n" + "", false);
            return u;
        }
        return u;
    }
    public void goHome()
    {
        if (StateReceiver.isNetworkAvailable(LoginActivity.this))//SÜRÜM KONTROL EDİLİYOR
            //new getVersionInfo().execute();

            Log.v("go home","yankess");
        Intent inst_ = new Intent(LoginActivity.this,AnaMenuActivity.class);
        startActivity(inst_);

    }
    public class saveUserTask extends AsyncTask<User,Void,Void>
    {
        Boolean status =false;

        @Override
        protected void onPostExecute(Void aVoid) {
            if (status)
            {
                goHome();
            }
            else
            {
                MessageBox.showAlert(LoginActivity.this,"Giriş işlemi başarısız ! \nKullanıcı sistem senkronizasyon başarısız..",false);
            }
        }

        @Override
        protected Void doInBackground(User... params) {
            User u = params[0];
            if (u!=null)
            {
                User_Data data = new User_Data(LoginActivity.this,new User());

                try {
                    status =  data.clearDatabaseTable();
                    if (status)
                    {
                        List<User> ul = data.list();
                        data.getDataList().add(u);
                        status= data.insert();
                        if (status)
                        {
                            status =true;
                        }
                    }

                } catch (OrbisDefaultException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public void showConnectionPopup()
    {
        try
        {

            FragmentManager manager = getFragmentManager();
            int count =0;
            DialogConfigFragment kd = new DialogConfigFragment(LoginActivity.this,configData.getSERVICURL());

            kd.show(manager,"");

        }
        catch (Throwable th)
        {
            MessageBox.showAlert(LoginActivity.this,th.toString(),true);
        }
    }
    public void firstOpenHelper()
    {
        if (configData.getFirsttime())
        {
            configData.setFirsttime(false);
            configData.setSERVICURL(serviceUrl);
        /*  new AlertDialog.Builder(LoginActivity.this)
                  .setTitle("Orbis Mobile Sistem Mesajı")
                  .setMessage(getResources().getString(R.string.firsOpenMess))
                  .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                         showConnectionPopup();
                          dialog.dismiss();

                      }
                  })
                  .setIcon(android.R.drawable.ic_dialog_alert)
                  .show();
                */
        }

    }


    String current_db_verison = "";
    class getVersionInfo extends AsyncTask
    {
        Boolean status=true;
        StringBuilder strMess= new StringBuilder();

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Object o) {

            Log.v("SERVER DB/LOCAL DB","=>"+current_db_verison+"-"+DbHelper.DATABASE_VERSION);

            if(!current_db_verison.equals(""))
            {
                if(Integer.valueOf(current_db_verison) > DbHelper.DATABASE_VERSION)
                {
                    String ticker = "Orbis mobil uygulamasının yeni sürümü mevcut...";
                    String contentTitle =  "Orbis Notification";
                    String contentText =  "Orbis mobil uygulamasının yeni sürümü mevcut...";
                    //bildirimGonder(ticker , contentText , contentTitle);
                    //nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    //OrtakFunction.bildirimGonder(ticker , contentText , contentTitle , getApplicationContext() , nm , MyBroadcastReceiver.class , false , false , null);
                }
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                RSOperator rsOperator = new RSOperator();
                ConfigData cd = new ConfigData(LoginActivity.this);
                String url = cd.getSERVICURL() + LoginActivity.this.getResources().getString(R.string.OrtakRS_version_control);
                current_db_verison = rsOperator.CreateToRSUrlConnection(HttpRequestType.POST, null, url, null);
                Log.v("CURRENT DB VERSION","SERVER RESPONSE="+current_db_verison);

            }
            catch (OrbisDefaultException e) {
                e.printStackTrace();
                publishProgress("HATA:" + e.getMessage());
            }

            return null;
        }
    }



    /*private void bildirimGonder(String ticker , String contentText , String contentTitle)
    {

        Log.v("notification", "run");
        int icon = R.drawable.add_icon;
        long when = System.currentTimeMillis();
        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
        Notification notification = new Notification(icon,"Yeni Bildirim",when);

        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setAutoCancel(true);
        builder.setTicker(ticker);
        builder.setContentTitle(contentTitle);
        builder.setContentText(contentText);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pending);
        builder.setOngoing(true);
        builder.setNumber(100);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.build();
        }

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.flags = Notification.FLAG_ONGOING_EVENT;

        notification = builder.getNotification();
        nm.notify(11, notification);
    }



    //BURDAN AŞAĞISI AKTİF DEĞİL
  /*  public class serviceConnectorTask extends AsyncTask
    {
        boolean urlDurum;

        Messages durum = new Messages();

        Messages messages_SModulKodDeger = new Messages();
        Messages messages_SOrgBirim = new Messages();

        Messages messages_SKullanici = new Messages();

        Messages messages_SCalisan = new Messages();

        Messages messages_OduhTurKayitAile = new Messages();
        Messages messages_ortakAgacTuru = new Messages();


        @Override
        protected void onProgressUpdate(Object[] values) {
            Toast.makeText(LoginActivity.this, values[0].toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
            urlDurum =false;
            durum.setStatus(true);

            if (configData.getSERVICURL().trim().length()>10)
            {
                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Veriler indirilirken lütfen bekleyiniz...");
                urlDurum = true;
            }
            else
            {
                Toast.makeText(LoginActivity.this,"Hata:Servis url bilgisi bulunamadı",Toast.LENGTH_LONG).show();
                MessageBox.showAlert(LoginActivity.this, "Servis url bilgisi bulunamadı ! \n Tekrar deneyiniz...", true);

            }


        }

        @Override
        protected void onPostExecute(Object o)
        {

            if (urlDurum&&durum.isStatus())
            {
                progressDialog.dismiss();
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Orbis Mobile Sistem Bilgisi")
                        .setMessage(" Veri indirme işlemi tamamlandı ")
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                goHome();


                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else
            {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Orbis Mobile Sistem Bilgisi")
                        .setMessage("İşlem Gerçekleştirilemedi !")
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent imte = new Intent(LoginActivity.this, LoginActivity.class);
                                startActivity(imte);
                                LoginActivity.this.finish();


                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }


        }
        @Override
        protected Object doInBackground(Object[] params) {
            messages_SOrgBirim.setStatus(false);
            if (urlDurum)
            {
                try {
                    try {
                        DateUtils dateUtils = new DateUtils();
                        DbHelper helper = new DbHelper(LoginActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
//                        helper.ClearDataTables(db);
                        RSOperator operator = new RSOperator();
                        jsonOrtakAgacTuru = operator.CreateToRSUrlConnection(HttpRequestType.GET,null,configData.getSERVICURL()+getResources().getString(R.string.ortak_agac_tur_tumkayitlargetir_ortakAgacTurGet_methodPost),"");
                        if (jsonOrtakAgacTuru.trim().length()>1)
                        {
                            publishProgress("Servis ile bağlantı başarılı. indiriliiyor..");
                            OrtakAgacTuru_Data ortakAgacTuru_data = new OrtakAgacTuru_Data(LoginActivity.this);
                            list_ortakAgacTuru = operator.convertJSONToEntity(jsonOrtakAgacTuru,typeof_OrtakAgacTuru,list_ortakAgacTuru);
                            publishProgress("OrtakAgacTuru indirilen veri adedi:" + list_ortakAgacTuru.size());

                            if (list_ortakAgacTuru.size()>0)
                            {
                                for (OrtakAgacTuru oat : list_ortakAgacTuru)
                                {
                                    oat.setOrgId(oat.getId());
                                    long tm=  oat.getGunlemeZamani().getTime();

                                }
                                ortakAgacTuru_data.clearDatabaseTable();
                                publishProgress("Ağaç türü  cihaz verileri temizleniyor");
                                ortakAgacTuru_data.getDataList().addAll(list_ortakAgacTuru);
                                messages_ortakAgacTuru.setStatus(ortakAgacTuru_data.insert());
                                if (messages_ortakAgacTuru.isStatus())
                                {

                                    publishProgress("OrtakAgacTuru cihaza kaydedilen veri adedi:" + list_ortakAgacTuru.size());
                                }
                                else
                                {
                                    publishProgress("Hata !\nOrtakAgacTuru cihaza kaydedilemedi" );
                                }

                            }  else  publishProgress("Hata !\nAğaç türü verisi alınamadı !" );
                        }
                        else  publishProgress("Hata !\nAğaç türü verisi alınamadı !\nServis ile iletişim başarısız.." );

                        jsonSOrgBirim =       operator.CreateToRSUrlConnection(HttpRequestType.POST,new Object(),configData.getSERVICURL()+getResources().getString(R.string.sorgbirimGet_methodPost_tumkayitgetirir),"");

                        if (jsonSOrgBirim.trim().length()>1)
                        {

                            SOrgBirim_Data dasorg2 = new SOrgBirim_Data(LoginActivity.this);

                            list_SOrgBirim = operator.convertJSONToEntity(jsonSOrgBirim,typeOf_SOrgBirim,list_SOrgBirim);
                            publishProgress("SOrgBirim inen veri adedi:" + list_SOrgBirim.size());

                            if (list_SOrgBirim.size()>0)
                            {
                                for (SOrgBirim sob : list_SOrgBirim)
                                {
                                    sob.setOrgId(sob.getId());
                                }
                                dasorg2.clearDatabaseTable();
                                publishProgress("Birim türü  cihaz verileri temizleniyor");
                                dasorg2.setDataList(list_SOrgBirim);

                                messages_SOrgBirim.setStatus(dasorg2.insert());

                                if (messages_SOrgBirim.isStatus())
                                {

                                    publishProgress("SOrgBirim cihaza kaydedilen veri:" + list_SOrgBirim.size());
                                }
                                else
                                {
                                    publishProgress("Hata !\nSOrgBirim cihaza kaydedilemedi" );
                                }


                            }

                        }else  publishProgress("Hata !\nBirim türü verisi alınamadı !\n" );


                    } catch (JsonSyntaxException e) {

                        e.printStackTrace();
                        publishProgress("HATA:" + e.getMessage());
                        durum.setStatus(false);
                        durum.getMessageList().add("Hata:"+e.getMessage());
                        throw new OrbisDefaultException(e.getMessage());

                    }

                }
                catch (OrbisDefaultException e) {
                    e.printStackTrace();
                    publishProgress("HATA:" + e.getMessage());
                    durum.setStatus(false);
                    durum.getMessageList().add(e.getMessage());

                }
            }


            return null;
        }
    }
    */

   /* public void   dataSyncQuery()
    {
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Orbis Mobile Sistem Mesajı")
                .setMessage("Temel verileriniz indirilsin mi ?")
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new serviceConnectorTask().execute();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goHome();
                dialog.dismiss();
            }
        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }*/

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("notification","broadcast");
            nm.cancel(11); // Notification ID to cancel
        }
    }



}


