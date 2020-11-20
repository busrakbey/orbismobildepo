package DataLayer.Ortak;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.konumsal.orbisozetmobil.OrtakUI.OrtakFunction;

import java.util.List;

import DataLayer.Sistem.SCalisan_Data;
import DataLayer.Sistem.SKullanici_Data;
import DataLayer.Sistem.SOrgBirim_Data;
import EntityLayer.Ortak.Unvan;
import EntityLayer.Ortak.User;
import EntityLayer.Sistem.SCalisan;
import EntityLayer.Sistem.SKullanici;
import EntityLayer.Sistem.SOrgBirim;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;


public class ConfigData
{
    public String getSERVICURL() {
        return SERVICURL;
    }

    public void setSERVICURL(String SERVICURL) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SERVICEURL",SERVICURL);
        editor.apply();
        this.SERVICURL = SERVICURL;
    }


    public Context getActivity() {
        return activity;
    }

    public void setActivity(Context activity) {
        this.activity = activity;
    }

    private User user;
    private String SERVICURL ;
    Context activity;
    private SOrgBirim ilgiliBirim;
    private SCalisan ilgiliCalisan;
    private Boolean firsttime;
    private Unvan ilgiliUnvan;


    public String DamgaNo;

    public String getBluetoothDeviceName() {
        return BLUETOOTHDEVICENAME;
    }

    public void setBluetoothDeviceName(String BLUETOOTHDEVICENAME) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("BLUETOOTHDEVICENAME",BLUETOOTHDEVICENAME);
        editor.apply();
        this.BLUETOOTHDEVICENAME = BLUETOOTHDEVICENAME;
    }

    private String BLUETOOTHDEVICENAME ;

    public String getBLUETOOTHDEVICETYPE() {
        return BLUETOOTHDEVICETYPE;
    }

    public void setBLUETOOTHDEVICETYPE(String BLUETOOTHDEVICETYPE) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("BLUETOOTHDEVICETYPE",BLUETOOTHDEVICETYPE);
        editor.apply();
        this.BLUETOOTHDEVICETYPE = BLUETOOTHDEVICETYPE;
    }

    private String BLUETOOTHDEVICETYPE ;

    public ConfigData(Context activity_)
    {
        activity = activity_;
        ilgiliBirim = new SOrgBirim();
        ilgiliCalisan = new SCalisan();
        ilgiliUnvan = new Unvan();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        this.SERVICURL=preferences.getString("SERVICEURL", "");
        this.DamgaNo = preferences.getString("DAMGANO", "1");
        this.firsttime=preferences.getBoolean("firsttime",true);
        this.BLUETOOTHDEVICENAME=preferences.getString("BLUETOOTHDEVICENAME", "");
        this.BLUETOOTHDEVICETYPE=preferences.getString("BLUETOOTHDEVICETYPE", "");


    }
    public void setBaseConfigData() throws OrbisDefaultException {
        User_Data  user_data = new User_Data(activity,new User());
        List<User> ul = user_data.list();
        if (ul!=null && ul.size()>0)
        {
            setUser(ul.get(0));

            if(OrtakFunction.vip_user_list.contains(OrtakFunction.kullanici_adi))
            {
                Log.v("vip setbase config","start");
                SOrgBirim_Data sOrgBirim_data = new SOrgBirim_Data(activity);
                ilgiliBirim = sOrgBirim_data.findById(Long.valueOf(OrtakFunction.admine_ozel_birim_id));

                Unvan_Data unvan_data = new Unvan_Data(activity);
                ilgiliUnvan = unvan_data.findById(getUser().getUnvanId());
                if(ilgiliUnvan != null)
                    OrtakFunction.kullanici_unvan_adi = ilgiliUnvan.getUnvan();

                SCalisan_Data sCalisan_data = new SCalisan_Data(activity);
            }
            else
            {
                SOrgBirim_Data sOrgBirim_data = new SOrgBirim_Data(activity);
                ilgiliBirim = sOrgBirim_data.findById(getUser().getOrgBirimId());

                Unvan_Data unvan_data = new Unvan_Data(activity);
                ilgiliUnvan = unvan_data.findById(getUser().getUnvanId());
                if(ilgiliUnvan != null)
                    OrtakFunction.kullanici_unvan_adi = ilgiliUnvan.getUnvan();

                SCalisan_Data sCalisan_data = new SCalisan_Data(activity);
            }
        }

    }

    public void kullaniciSetNull()
    {
        setUser(null);
    }


    public SOrgBirim getSeflikConfigDataForDamga(Long damga_birim_id) throws OrbisDefaultException {

        SOrgBirim_Data sOrgBirim_data = new SOrgBirim_Data(activity);
        return sOrgBirim_data.findById(damga_birim_id);

    }

    public SOrgBirim getIsletmeConfigDataForDamga(Long ust_id) throws OrbisDefaultException {

        SOrgBirim_Data sOrgBirim_data = new SOrgBirim_Data(activity);
        return sOrgBirim_data.findById(ust_id);

    }




    public SOrgBirim getIlgiliBirim() {
        return ilgiliBirim;
    }

    public Unvan getUnvan() {
        return ilgiliUnvan;
    }

    public void setIlgiliBirim(SOrgBirim ilgiliBirim) {
        this.ilgiliBirim = ilgiliBirim;
    }

    public SCalisan getIlgiliCalisan() {
        return ilgiliCalisan;
    }

    public void setIlgiliCalisan(SCalisan ilgiliCalisan) {
        this.ilgiliCalisan = ilgiliCalisan;
    }

    public Boolean saveUserInfo(SKullanici kullanici, String damgaNo)
    {
        Boolean d=true;
        try
        {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = preferences.edit();
            SKullanici_Data sKullanici_data = new SKullanici_Data(getActivity());
            SKullanici m_Kullanici= sKullanici_data.findByOrgId(kullanici.getId());
            SOrgBirim_Data sOrgBirim_data = new SOrgBirim_Data(getActivity());
            SOrgBirim m_SorgBirim = sOrgBirim_data.findByOrgId(kullanici.getOrgBirimId());
            if (damgaNo!=null)
                editor.putString("DAMGANO",damgaNo);
            if (kullanici.getOrgBirimId()!=null)
            {
                editor.putString("ilgilibirimOrgId", kullanici.getOrgBirimId().toString());
            }
            int int_scal;
            if (kullanici.getId()>0)
            {
                editor.putString("ilgilicalisanOrgId", kullanici.getId().toString());
            }
            if (m_SorgBirim!=null)
            {
                editor.putString("ilgilibirim", m_SorgBirim.getId().toString());
            }
            if (m_Kullanici!=null)
            {
                editor.putString("ilgilicalisan", m_Kullanici.getId().toString());
            }
            editor.apply();
        }
        catch (OrbisDefaultException e)
        {
            MessageBox.showAlert(getActivity(),"CongigData\nHata:\n"+e.toString(),false);
            d=false;
        }
        catch (Throwable th)
        {
            MessageBox.showAlert(getActivity(),"CongigData\nHata:\n"+th.toString(),false);
            d=false;

        }
       return  d;
    }

    public Boolean hasUserInSystemData(Long orgId)
    {

        SKullanici_Data data = new SKullanici_Data(getActivity());
        SKullanici kullanici=null;
        try {
            kullanici = data.findByOrgId(orgId);
        } catch (OrbisDefaultException e) {
            e.printStackTrace();
            return false;
        }
        return kullanici!=null;
    }

    public void saveDamgaNo(String damgaNo)
    {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = preferences.edit();
            if (damgaNo!=null)
                editor.putString("DAMGANO",damgaNo);
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(getActivity(), "CongigData:SaveDamgaNo-KayıtBaşarısız\nHata:\n" + e.toString(), false);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            MessageBox.showAlert(getActivity(), "CongigData:SaveDamgaNo-KayıtBaşarısız\nHata:\n" + e.toString(), false);
        }
    }
    public void deleteConfig()
    {
       try {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
            editor.putString("DAMGANO","");
            editor.putString("ilgilibirimOrgId", "0");
            editor.putString("ilgilicalisanOrgId", "0");
            editor.putString("ilgilibirim", "0");
            editor.putString("ilgilicalisan", "0");
            editor.putString("ilgiliUnvan","0");

        editor.apply();
    }
    catch (Exception e)
    {
        MessageBox.showAlert(getActivity(),"CongigData:deleteConfig\nHata:\n"+e.toString(),false);

    }
    catch (Throwable th)
    {
        MessageBox.showAlert(getActivity(),"CongigData:deleteConfig\nHata:\n"+th.toString(),false);

    }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Boolean firsttime) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firsttime",firsttime);
        editor.apply();
        this.firsttime = firsttime;
    }
}
