package DataLayer.Ortak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ContractLayer.OrtakCo.OrtakKamera_Co;
import EntityLayer.Ortak.OrtakKamera;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 17.1.2017.
 */

public class OrtakKamera_Data extends DataController<OrtakKamera> {
    public OrtakKamera_Data(Context ctx) {
        super(ctx,new OrtakKamera());
    }

    public void execSQL(String slqQuery) throws OrbisDefaultException
    {
        try {
            db = helper.getWritableDatabase();
            db.execSQL(slqQuery);
            db.close();

        }catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }catch (Throwable e){
            throw new OrbisDefaultException(e.toString());
        }
    }

    public List<OrtakKamera> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakKamera> kalemListe = new ArrayList<OrtakKamera>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    kalemListe.add((OrtakKamera) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }catch (Throwable e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            db.close();
            return kalemListe;
        }
    }

    public OrtakKamera CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakKamera kalem = new OrtakKamera();

        kalem.setId(cursor.getLong(cursor.getColumnIndex("id")));
        kalem.setBolgeMudurlukId(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c2_bolgeMudurlukId)));
        kalem.setIsletmeMudurlukId(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c3_isletmeMudurlukId)));
        kalem.setIsletmeSeflikId(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c4_isletmeSeflikId)));
        kalem.setIlId( cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c5_ilId)));
        kalem.setIlceId(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c6_ilceId)));
        kalem.setKameraTuru(cursor.getInt(cursor.getColumnIndex(OrtakKamera_Co.c7_kameraTuru)));
        kalem.setKameraMarka(cursor.getString(cursor.getColumnIndex(OrtakKamera_Co.c8_kameraMarka)));
        kalem.setKameraModel(cursor.getString(cursor.getColumnIndex(OrtakKamera_Co.c9_kameraModel)));
        kalem.setKameraAdi(cursor.getString(cursor.getColumnIndex(OrtakKamera_Co.c10_kameraAdi)));
        kalem.setKameraUrl(cursor.getString(cursor.getColumnIndex(OrtakKamera_Co.c11_kameraUrl)));
        kalem.setKullaniciAdi(cursor.getString(cursor.getColumnIndex(OrtakKamera_Co.c12_kullaniciAdi)));
        kalem.setSifre(cursor.getString(cursor.getColumnIndex(OrtakKamera_Co.c13_sifre)));
        kalem.setTasinmazId(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c14_tasinmazId)));
        kalem.setModulId(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c15_modulId)));
        kalem.setMid(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c16_mid)));
        kalem.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakKamera_Co.c17_mustid)));


        return kalem;
    }

    public Boolean insertFromContent(List<OrtakKamera> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakKamera kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(OrtakKamera_Co.ORTAK_KAMERA_TABLE, null, line);
                if (m_id > 0) {
                    status = true;
                    kayit.setMid(m_id);
                } else {
                    status = false;
                    throw new OrbisDefaultException("oragacdata-insert:Kayit Eklenemedi, database tablosu hatalı !" + kayit.toString());
                }

            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new OrbisDefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new OrbisDefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new OrbisDefaultException("DataController(insert)Hata:" + e.getMessage());
            }
            catch (Throwable e)
            {
                Log.d("DataController:insert\n", e.getMessage());
                status = false;
                throw new OrbisDefaultException("DataController:insert\n:" + e.toString());
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }


    public ContentValues ObjectToContentValues(OrtakKamera not) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(OrtakKamera_Co.c0_id_typ_long,not.getId());
            satir.put(OrtakKamera_Co.c2_bolgeMudurlukId,not.getBolgeMudurlukId());
            satir.put(OrtakKamera_Co.c3_isletmeMudurlukId,not.getIsletmeMudurlukId());
            satir.put(OrtakKamera_Co.c4_isletmeSeflikId,not.getIsletmeSeflikId());
            satir.put(OrtakKamera_Co.c5_ilId,not.getIlId());
            satir.put(OrtakKamera_Co.c6_ilceId,not.getIlceId());
            satir.put(OrtakKamera_Co.c7_kameraTuru,not.getKameraTuru());
            satir.put(OrtakKamera_Co.c8_kameraMarka,not.getKameraMarka());
            satir.put(OrtakKamera_Co.c9_kameraModel,not.getKameraModel());
            satir.put(OrtakKamera_Co.c10_kameraAdi,not.getKameraAdi());
            satir.put(OrtakKamera_Co.c11_kameraUrl,not.getKameraUrl());
            satir.put(OrtakKamera_Co.c12_kullaniciAdi,not.getKullaniciAdi());
            satir.put(OrtakKamera_Co.c13_sifre,not.getSifre());
            satir.put(OrtakKamera_Co.c14_tasinmazId,not.getTasinmazId());
            satir.put(OrtakKamera_Co.c15_modulId,not.getModulId());
            satir.put(OrtakKamera_Co.c16_mid,not.getMid());
            satir.put(OrtakKamera_Co.c17_mustid,not.getMustid());
            Log.v("ortak kamera eklendi=>",not.getKameraAdi());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
