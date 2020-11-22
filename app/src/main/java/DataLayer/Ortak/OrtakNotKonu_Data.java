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

import ContractLayer.OrtakCo.OrtakNotKonu_Co;
import EntityLayer.Ortak.OrtakAgacTuru;
import EntityLayer.Ortak.OrtakNotKonu;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 17.1.2017.
 */

public class OrtakNotKonu_Data extends DataController<OrtakNotKonu> {
    public OrtakNotKonu_Data(Context ctx) {
        super(ctx,new OrtakNotKonu());
    }


    public List<OrtakNotKonu> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakNotKonu> konuListe = new ArrayList<OrtakNotKonu>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    konuListe.add((OrtakNotKonu) CursorToObject(cursor));
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
            return konuListe;
        }
    }

    public OrtakNotKonu CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakNotKonu konu = new OrtakNotKonu();

        konu.setId(cursor.getLong(cursor.getColumnIndex("id")));
        konu.setUstId(cursor.getLong(cursor.getColumnIndex(OrtakNotKonu_Co.c2_ustId)));
        konu.setKonuBasligi(cursor.getString(cursor.getColumnIndex(OrtakNotKonu_Co.c3_konuBasligi)));
        konu.setAciklama(cursor.getString(cursor.getColumnIndex(OrtakNotKonu_Co.c4_aciklama)));

        boolean aktif_value = cursor.getInt(cursor.getColumnIndex(OrtakNotKonu_Co.c6_aktif)) > 0;
        konu.setAktif(aktif_value);

        konu.setYol( cursor.getString(cursor.getColumnIndex(OrtakNotKonu_Co.c5_yol)));
        konu.setGunlemeZamani(cursor.getString(cursor.getColumnIndex(OrtakNotKonu_Co.c7_gunlemeZamani)));
        konu.setGunleyenId(cursor.getLong(cursor.getColumnIndex(OrtakNotKonu_Co.c8_gunleyenId)));
        konu.setModulId(cursor.getInt(cursor.getColumnIndex(OrtakNotKonu_Co.c9_modulId)));
        konu.setMid(cursor.getLong(cursor.getColumnIndex(OrtakNotKonu_Co.c10_mid)));
        konu.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakNotKonu_Co.c11_mustid)));

        return konu;
    }

    public Boolean insertFromContent(List<OrtakNotKonu> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakNotKonu kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(OrtakNotKonu_Co.ORTAK_NOT_KONU_TABLE, null, line);
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
    public ContentValues ObjectToContentValues(OrtakNotKonu not) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(OrtakNotKonu_Co.c0_id_typ_long,not.getId());
            satir.put(OrtakNotKonu_Co.c2_ustId,not.getUstId());
            satir.put(OrtakNotKonu_Co.c3_konuBasligi,not.getKonuBasligi());
            satir.put(OrtakNotKonu_Co.c4_aciklama,not.getAciklama());
            satir.put(OrtakNotKonu_Co.c5_yol,not.getYol());
            satir.put(OrtakNotKonu_Co.c6_aktif,String.valueOf(not.getAktif()));
            satir.put(OrtakNotKonu_Co.c7_gunlemeZamani,not.getGunlemeZamani());
            satir.put(OrtakNotKonu_Co.c8_gunleyenId,not.getGunleyenId());
            satir.put(OrtakNotKonu_Co.c9_modulId,not.getModulId());
            satir.put(OrtakNotKonu_Co.c10_mid,not.getMid());
            satir.put(OrtakNotKonu_Co.c11_mustid,not.getMustid());




            Log.v("ortak konu eklendi=>",not.getKonuBasligi());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
