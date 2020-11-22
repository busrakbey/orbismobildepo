package com.konumsal.orbisozetmobil;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteMisuseException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.database.sqlite.SQLiteTableLockedException;
import android.util.Log;

import java.util.ArrayList;


import EntityLayer.Fidanlik.FidanAjaxTur;

import EntityLayer.GeoPortal.AGAC_HACIM_ARTIM_TABLOSU;
import EntityLayer.GeoPortal.EN_KAR_AGAC;
import EntityLayer.GeoPortal.ORNEK_ALAN;
import EntityLayer.GeoPortal.PLAN;
import EntityLayer.IsletmePazarlama.MuhStokLokasyon;
import EntityLayer.Ortak.GpBolme;
import EntityLayer.Ortak.MobilGuzergah;
import EntityLayer.Ortak.OrtakAgacTuru;
import EntityLayer.Ortak.OrtakIsKalemleri;
import EntityLayer.Ortak.OrtakKamera;
import EntityLayer.Ortak.OrtakKisi;
import EntityLayer.Ortak.OrtakKisiHareket;
import EntityLayer.Ortak.OrtakNot;
import EntityLayer.Ortak.OrtakNotKonu;
import EntityLayer.Ortak.OrtakOdunTuru;
import EntityLayer.Ortak.OtherUsers;
import EntityLayer.Ortak.STown;
import EntityLayer.Ortak.SanatYapisi;
import EntityLayer.Ortak.Unvan;
import EntityLayer.Ortak.User;
import EntityLayer.Ortak.YolBilgi;
import EntityLayer.Sistem.SCalisan;
import EntityLayer.Sistem.SKullanici;
import EntityLayer.Sistem.SModulKodDeger;
import EntityLayer.Sistem.SOrgBirim;
import ToolLayer.OrbisDefaultException;
import ToolLayer.SqlBuilder;

public class DbHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "ORBISOZET_MOBILE";
    public static final int DATABASE_VERSION = 1;
    //85 SURUMUNDE YOL_BILGI TABLOSU YOK.cursor38

    public static DbHelper helper;

    public static DbHelper getInstance(Context ctx) {//db helper singleton yapıldı.16.03.2017

        helper = new DbHelper(ctx.getApplicationContext());
        return helper;
    }


    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public  void ClearDataTables(SQLiteDatabase db)
    {
        onUpgrade(db,1,2);

    }
    public  void  ClearUsers(SQLiteDatabase db)
    {

    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        try {
            String tblsPLAN = new SqlBuilder<PLAN>(new PLAN()).createTable(true);
            db.execSQL(new SqlBuilder<PLAN>(new PLAN()).createTable(true));

            String tblsORNEKALAN = new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).createTable(true);
            db.execSQL(new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).createTable(true));

            db.execSQL(new SqlBuilder<EN_KAR_AGAC>(new EN_KAR_AGAC()).createTable(true));

            String tblsModul = new SqlBuilder<SModulKodDeger>(new SModulKodDeger()).createTable(true);
            db.execSQL(new SqlBuilder<SModulKodDeger>(new SModulKodDeger()).createTable(true));

            db.execSQL(new SqlBuilder<SCalisan>(new SCalisan()).createTable(true));

            String tblBirim = new SqlBuilder<SOrgBirim>(new SOrgBirim()).createTable(true);
            db.execSQL(new SqlBuilder<SOrgBirim>(new SOrgBirim()).createTable(true));

            String tblkullanici = new SqlBuilder<SKullanici>(new SKullanici()).createTable(true);
            db.execSQL(new SqlBuilder<SKullanici>(new SKullanici()).createTable(true));

            String tbluser = new SqlBuilder<User>(new User()).createTable(true);
            db.execSQL(new SqlBuilder<User>(new User()).createTable(true));

            String tblAgac = new SqlBuilder<OrtakAgacTuru>(new OrtakAgacTuru()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakAgacTuru>(new OrtakAgacTuru()).createTable(true));

            String tblsAgacHacimArtim = new SqlBuilder<AGAC_HACIM_ARTIM_TABLOSU>(new AGAC_HACIM_ARTIM_TABLOSU()).createTable(true);
            db.execSQL(new SqlBuilder<AGAC_HACIM_ARTIM_TABLOSU>(new AGAC_HACIM_ARTIM_TABLOSU()).createTable(true));





            String tblsOrtakOdunTuru = new SqlBuilder<OrtakOdunTuru>(new OrtakOdunTuru()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakOdunTuru>(new OrtakOdunTuru()).createTable(true));

            String tblUnvan = new SqlBuilder<Unvan>(new Unvan()).createTable(true);
            db.execSQL(new SqlBuilder<Unvan>(new Unvan()).createTable(true));



            String tblPerSicil = new SqlBuilder<OtherUsers>(new OtherUsers()).createTable(true);
            db.execSQL(new SqlBuilder<OtherUsers>(new OtherUsers()).createTable(true));

            String gp_bolme = new SqlBuilder<GpBolme>(new GpBolme()).createTable(true);
            db.execSQL(new SqlBuilder<GpBolme>(new GpBolme()).createTable(true));

            String muh_stok_lokasyon = new SqlBuilder<MuhStokLokasyon>(new MuhStokLokasyon()).createTable(true);
            db.execSQL(new SqlBuilder<MuhStokLokasyon>(new MuhStokLokasyon()).createTable(true));

            String ortak_kisi = new SqlBuilder<OrtakKisi>(new OrtakKisi()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakKisi>(new OrtakKisi()).createTable(true));


            String ortak_not = new SqlBuilder<OrtakNot>(new OrtakNot()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakNot>(new OrtakNot()).createTable(true));


            String ortak_not_konu = new SqlBuilder<OrtakNotKonu>(new OrtakNotKonu()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakNotKonu>(new OrtakNotKonu()).createTable(true));


            String ortak_is_kalemleri = new SqlBuilder<OrtakIsKalemleri>(new OrtakIsKalemleri()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakIsKalemleri>(new OrtakIsKalemleri()).createTable(true));

            String mobil_guzergah = new SqlBuilder<MobilGuzergah>(new MobilGuzergah()).createTable(true);
            db.execSQL(new SqlBuilder<MobilGuzergah>(new MobilGuzergah()).createTable(true));

            String kisi_hareket = new SqlBuilder<OrtakKisiHareket>(new OrtakKisiHareket()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakKisiHareket>(new OrtakKisiHareket()).createTable(true));


            String s_town = new SqlBuilder<STown>(new STown()).createTable(true);
            db.execSQL(new SqlBuilder<STown>(new STown()).createTable(true));

            String ortak_kamera = new SqlBuilder<OrtakKamera>(new OrtakKamera()).createTable(true);
            db.execSQL(new SqlBuilder<OrtakKamera>(new OrtakKamera()).createTable(true));


            String fidan_tur = new SqlBuilder<FidanAjaxTur>(new FidanAjaxTur()).createTable(true);
            db.execSQL(new SqlBuilder<FidanAjaxTur>(new FidanAjaxTur()).createTable(true));


            String sanat_yapisi = new SqlBuilder<SanatYapisi>(new SanatYapisi()).createTable(true);
            db.execSQL(new SqlBuilder<SanatYapisi>(new SanatYapisi()).createTable(true));


            String yol_bilgi = new SqlBuilder<YolBilgi>(new YolBilgi()).createTable(true);
            db.execSQL(new SqlBuilder<YolBilgi>(new YolBilgi()).createTable(true));

            Log.v("db","created");

        } catch (SQLiteDatatypeMismatchException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteFullException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteAbortException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteAccessPermException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteBindOrColumnIndexOutOfRangeException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteBlobTooBigException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteCantOpenDatabaseException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteConstraintException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteDatabaseCorruptException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteDatabaseLockedException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteDiskIOException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteDoneException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteMisuseException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteOutOfMemoryException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteReadOnlyDatabaseException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteTableLockedException exp)
        {
            String expmessage = exp.getMessage();
        }


        catch (SQLiteException exp)
        {
            String expmessage = exp.getMessage();
        }
//////////////////////////////////////////////////////////////////////////////////////

        catch (ClassCastException e) {
            e.printStackTrace();
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NegativeArraySizeException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (OrbisDefaultException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(newVersion>oldVersion) {
            try {

            /*    Log.v("db", "upgraded");
                db.execSQL(new SqlBuilder<PLAN>(new PLAN()).dropTable());
                db.execSQL(new SqlBuilder<EN_KAR_AGAC>(new EN_KAR_AGAC()).dropTable());
                db.execSQL(new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).dropTable());
                db.execSQL(new SqlBuilder<User>(new User()).dropTable());
                db.execSQL(new SqlBuilder<SModulKodDeger>(new SModulKodDeger()).dropTable());
                db.execSQL(new SqlBuilder<SOrgBirim>(new SOrgBirim()).dropTable());
                db.execSQL(new SqlBuilder<SKullanici>(new SKullanici()).dropTable());
                db.execSQL(new SqlBuilder<SCalisan>(new SCalisan()).dropTable());
                db.execSQL(new SqlBuilder<User>(new User()).dropTable());

                db.execSQL(new SqlBuilder<PazDikiliDamga>(new PazDikiliDamga()).dropTable());
                db.execSQL(new SqlBuilder<PazDikiliDamgaDetay>(new PazDikiliDamgaDetay()).dropTable());
                db.execSQL(new SqlBuilder<PazDikiliDamgaDetay>(new PazDikiliDamgaDetay()).dropTable());

                db.execSQL(new SqlBuilder<AGAC_HACIM_ARTIM_TABLOSU>(new AGAC_HACIM_ARTIM_TABLOSU()).dropTable());
            */
                Log.v("DB VERSİYONU","yenilendi.db no=>"+newVersion);

















               /* String tableName = "PAZ_FAALIYET";
                Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
                if(cursor!=null) {
                    if (cursor.getCount() > 0)
                        cursor.close();
                }
                else
                {
                    String tblsPazFaaliyet = new SqlBuilder<PazFaaliyet>(new PazFaaliyet()).createTable(true);
                    db.execSQL(new SqlBuilder<PazFaaliyet>(new PazFaaliyet()).createTable(true));

                    String tblsPazFaaliyetDetay = new SqlBuilder<PazFaaliyetDetay>(new PazFaaliyetDetay()).createTable(true);
                    db.execSQL(new SqlBuilder<PazFaaliyetDetay>(new PazFaaliyetDetay()).createTable(true));

                }*/





            } catch (Exception Exp) {

            }
            onCreate(db);
        }

    }
    public void clearAllDataTables( SQLiteDatabase db) throws OrbisDefaultException {


        Log.v("db","clear all");
        Integer plansv= db.delete(new SqlBuilder<PLAN>(new PLAN()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<PLAN>(new PLAN()).createTable(true));

        Integer ukk1= db.delete(new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).createTable(true));

        Integer ukk2= db.delete(new SqlBuilder<EN_KAR_AGAC>(new EN_KAR_AGAC()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<EN_KAR_AGAC>(new EN_KAR_AGAC()).createTable(true));

        Integer ukk= db.delete(new SqlBuilder<User>(new User()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<User>(new User()).createTable(true));


        Integer oat = db.delete(new SqlBuilder<OrtakAgacTuru>(new OrtakAgacTuru()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<OrtakAgacTuru>(new OrtakAgacTuru()).createTable(true));


        Integer sc = db.delete(new SqlBuilder<SCalisan>(new SCalisan()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<SCalisan>(new SCalisan()).createTable(true));
        Integer sk = db.delete(new SqlBuilder<SKullanici>(new SKullanici()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<SKullanici>(new SKullanici()).createTable(true));

        Integer smkd = db.delete(new SqlBuilder<SModulKodDeger>(new SModulKodDeger()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<SModulKodDeger>(new SModulKodDeger()).createTable(true));

        Integer sob= db.delete(new SqlBuilder<SOrgBirim>(new SOrgBirim()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<SOrgBirim>(new SOrgBirim()).createTable(true));

        Integer ahat = db.delete(new SqlBuilder<AGAC_HACIM_ARTIM_TABLOSU>(new AGAC_HACIM_ARTIM_TABLOSU()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<AGAC_HACIM_ARTIM_TABLOSU>(new AGAC_HACIM_ARTIM_TABLOSU()).createTable(true));

    }

    public ArrayList<Cursor> getData(String Query){

        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("Db exception", sqlEx.getMessage());
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("Db exception", ex.getMessage());
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}

