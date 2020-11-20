package DataLayer.Fidanlik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import DataLayer.Ortak.DataController;
import EntityLayer.Fidanlik.FidanAjaxTur;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on  13.01.2017.
 */
public class FidanAjaxTurData extends DataController<FidanAjaxTur> {
    public FidanAjaxTurData(Context ctx) {
        super(ctx,new FidanAjaxTur());
    }



    public List<FidanAjaxTur> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<FidanAjaxTur> list = new ArrayList<FidanAjaxTur>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);

            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((FidanAjaxTur) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            db.close();
            return list;
        }
    }


    public FidanAjaxTur CursorToObject(Cursor cursor) throws OrbisDefaultException {
        FidanAjaxTur vej = new FidanAjaxTur();
        vej.setId(cursor.getLong(cursor.getColumnIndex("id")));
        vej.setAdi(cursor.getString(cursor.getColumnIndex("adi")));
        vej.setLatinceAdiTur(cursor.getString(cursor.getColumnIndex("latinceAdiTur")));
        vej.setStokId(cursor.getLong(cursor.getColumnIndex("stokId")));
        vej.setDigercinsid(cursor.getLong(cursor.getColumnIndex("digercinsid")));
        vej.setMid(cursor.getLong(cursor.getColumnIndex("mid")));
        vej.setMustid(cursor.getLong(cursor.getColumnIndex("mustid")));

        return vej;
    }




    public Boolean insertFromContent(List<FidanAjaxTur> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (FidanAjaxTur kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow("FID_AJAX_TUR", null, line);
                if (m_id > 0)
                {
                    status = true;
                    kayit.setMid(m_id);
                } else {
                    status = false;
                    throw new OrbisDefaultException("DataController-insert:Kayit Eklenemedi, database tablosu hatalı !" + kayit.toString());
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



    public Boolean updateFromContent(List<FidanAjaxTur> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (FidanAjaxTur kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = "FID_AJAX_TUR";
                String[] whereParameters = {String.valueOf(kayit.getMid())};
                String WhereArgs = "mid=?";
                m_id = db.update(tableName, line, WhereArgs, whereParameters);

                if (m_id > 0) {
                    status = true;
                    Log.v("upda","bitti2");
                    Log.d("DataController", "Kayit guncellendi id:" + m_id + " -" + kayit.toString());
                } else {
                    Log.v("upda","bitti3");
                    Log.d("DataController", "Kayıt guncelleme başarısız !");
                    status = false;
                    throw new OrbisDefaultException("DataController-update:Kayit guncellenemedi !" + kayit.toString());
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




    public ContentValues ObjectToContentValues(FidanAjaxTur vej) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put("id", vej.getId());
            satir.put("adi",vej.getAdi());
            satir.put("latinceAdiTur",vej.getLatinceAdiTur());
            satir.put("stokId",vej.getStokId());
            satir.put("digercinsid",vej.getDigercinsid());
            satir.put("mid",vej.getMid());
            satir.put("mustid",vej.getMustid());


        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }
}
