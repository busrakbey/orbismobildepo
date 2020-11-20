package DataLayer.IsletmePazarlama;

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
import EntityLayer.IsletmePazarlama.MuhStokLokasyon;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 2.11.2016.
 */
public class MuhStokLokasyon_Data extends DataController<MuhStokLokasyon> {

    public MuhStokLokasyon_Data(Context ctx) {
        super(ctx,new MuhStokLokasyon());
    }



    public List<MuhStokLokasyon> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<MuhStokLokasyon> lokasyonList = new ArrayList<MuhStokLokasyon>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);

            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    lokasyonList.add((MuhStokLokasyon) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            db.close();
            return lokasyonList;
        }
    }


    public MuhStokLokasyon CursorToObject(Cursor cursor) throws OrbisDefaultException {
        MuhStokLokasyon lokasyon = new MuhStokLokasyon();

        lokasyon.setId(cursor.getLong(cursor.getColumnIndex("id")));
        lokasyon.setUstId(cursor.getLong(cursor.getColumnIndex("ustId")));
        lokasyon.setAdi(cursor.getString(cursor.getColumnIndex("adi")));
        lokasyon.setBirimId(cursor.getLong(cursor.getColumnIndex("birimId")));
        lokasyon.setParselNo(cursor.getString(cursor.getColumnIndex("parselNo")));
        lokasyon.setLokasyonTipi(cursor.getString(cursor.getColumnIndex("lokasyonTipi")));
        return lokasyon;
    }





    public Boolean insertFromContent(List<MuhStokLokasyon> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (MuhStokLokasyon kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow("MUH_STOK_LOKASYON", null, line);
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



    public Boolean updateFromContent(List<MuhStokLokasyon> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (MuhStokLokasyon kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = "MUH_STOK_LOKASYON";
                String[] whereParameters = {String.valueOf(kayit.getMid())};
                String WhereArgs = "mid=?";
                Log.v("where",tableName +" -" +whereParameters[0]);
                m_id = db.update(tableName, line, WhereArgs, whereParameters);
                Log.v("upda","bitti");

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







    public ContentValues ObjectToContentValues(MuhStokLokasyon lokasyon) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put("id", lokasyon.getId());
            satir.put("adi",lokasyon.getAdi());
            satir.put("ustId",lokasyon.getUstId());
            satir.put("birimId",lokasyon.getBirimId());
            satir.put("parselNo",lokasyon.getParselNo());
            satir.put("lokasyonTipi",lokasyon.getLokasyonTipi());



        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
