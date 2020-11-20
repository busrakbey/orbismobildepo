package DataLayer.Sistem;

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
import EntityLayer.Sistem.SModulKodDeger;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Konumsal PC11 on 11.5.2016.
 */
public class SModulKodDeger_Data  extends DataController<SModulKodDeger> {
    public SModulKodDeger_Data(Context ctx) {
        super(ctx, new SModulKodDeger());
    }



    public List<SModulKodDeger> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<SModulKodDeger> list = new ArrayList<SModulKodDeger>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);

            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((SModulKodDeger) CursorToObject(cursor));
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


    public SModulKodDeger CursorToObject(Cursor cursor) throws OrbisDefaultException {
        SModulKodDeger kod = new SModulKodDeger();

        kod.setId(cursor.getLong(cursor.getColumnIndex("id")));
        kod.setDeger(cursor.getString(cursor.getColumnIndex("deger")));
        kod.setGrup(cursor.getString(cursor.getColumnIndex("grup")));
        kod.setKod(cursor.getString(cursor.getColumnIndex("kod")));
        kod.setModulId(cursor.getLong(cursor.getColumnIndex("modulId")));

        boolean aktif_value = cursor.getInt(cursor.getColumnIndex("aktif")) > 0;
        kod.setAktif(aktif_value);

        return kod;
    }





    public Boolean insertFromContent(List<SModulKodDeger> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (SModulKodDeger kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow("S_MODUL_KOD_DEGER", null, line);
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



    public Boolean updateFromContent(List<SModulKodDeger> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (SModulKodDeger kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = "S_MODUL_KOD_DEGER";
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




    public ContentValues ObjectToContentValues(SModulKodDeger kod) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put("id", kod.getId());
            satir.put("deger", kod.getDeger());
            satir.put("grup", kod.getGrup());
            satir.put("kod",kod.getKod());
            satir.put("modulId",kod.getModulId());
            satir.put("aktif",kod.getAktif());
            Log.v("modul ıd","insert=>"+kod.getGrup()+"-"+kod.getModulId()+"-"+kod.getDeger()+"-"+kod.getModulId()+"-"+kod.getAktif());
        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }

}
