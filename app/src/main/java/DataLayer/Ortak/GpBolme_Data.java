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

import EntityLayer.Ortak.GpBolme;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 24.10.2016.
 */
public class GpBolme_Data extends DataController<GpBolme> {
    public GpBolme_Data(Context ctx) {
        super(ctx,new GpBolme());
    }



    public List<GpBolme> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<GpBolme> bolme_list = new ArrayList<GpBolme>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    bolme_list.add((GpBolme) CursorToObject(cursor));
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
            return bolme_list;
        }
    }


    public List<GpBolme> loadFromQueryForOzmUygulamaKontrolTurChange(String queryStr) throws OrbisDefaultException {
        List<GpBolme> bolme_list = new ArrayList<GpBolme>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    bolme_list.add((GpBolme) CursorToObjectForOzmUygulamaKontrolTurChange(cursor));
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
            return bolme_list;
        }
    }


    public GpBolme CursorToObjectForOzmUygulamaKontrolTurChange(Cursor cursor) throws OrbisDefaultException {
        GpBolme agac = new GpBolme();
        agac.setId(cursor.getLong(cursor.getColumnIndex("id")));
        agac.setBolmeNo(cursor.getString(cursor.getColumnIndex("bolmeNo")));
        agac.setSeflikBirimId(cursor.getLong(cursor.getColumnIndex("seflikBirimId")));
        agac.setOzmKorumaUygulamaId(cursor.getLong(cursor.getColumnIndex("ozmKorumaUygulamaId")));
        Log.v("ozmKorumaUygulamaId","=>"+agac.getOzmKorumaUygulamaId());
        return agac;
    }



    public GpBolme CursorToObject(Cursor cursor) throws OrbisDefaultException {
        GpBolme agac = new GpBolme();
        agac.setId(cursor.getLong(cursor.getColumnIndex("id")));
        agac.setBolmeNo(cursor.getString(cursor.getColumnIndex("bolmeNo")));
        agac.setSeflikBirimId(cursor.getLong(cursor.getColumnIndex("seflikBirimId")));
        return agac;
    }
    public Boolean insertFromContent(List<GpBolme> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (GpBolme kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow("GP_BOLME", null, line);
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
    public ContentValues ObjectToContentValues(GpBolme bolme) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put("id",bolme.getId());
            satir.put("bolmeNo",bolme.getBolmeNo());
            satir.put("seflikBirimId",bolme.getSeflikBirimId());
            Log.v("bolme eklendi=>",bolme.getId()+"-"+bolme.getBolmeNo());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
