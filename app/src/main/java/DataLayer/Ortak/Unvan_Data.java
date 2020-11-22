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

import EntityLayer.Ortak.Unvan;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 13.10.2016.
 */
public class Unvan_Data  extends DataController<Unvan> {

    public Unvan_Data(Context ctx) {
        super(ctx, new Unvan());
    }

    public List<Unvan> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<Unvan> unvanList = new ArrayList<Unvan>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    unvanList.add((Unvan) CursorToObject(cursor));
                }
                cursor.close();
            }

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            db.close();
            return unvanList;
        }
    }

    public Unvan CursorToObject(Cursor cursor) throws OrbisDefaultException {
        Unvan o = new Unvan();
        o.setId(cursor.getLong(cursor.getColumnIndex("id")));
        o.setPersonelTipi(cursor.getString(cursor.getColumnIndex("personelTipi")));
        o.setHizmetSinifi(cursor.getString(cursor.getColumnIndex("hizmetSinifi")));
        o.setKodu(cursor.getString(cursor.getColumnIndex("kodu")));
        o.setUnvan(cursor.getString(cursor.getColumnIndex("unvan")));
        o.setKisaUnvan(cursor.getString(cursor.getColumnIndex("kisaUnvan")));
        return o;
    }

    public Boolean insertFromContent(List<Unvan> itms) throws OrbisDefaultException {
        Boolean status = false;
        if (itms != null && itms.size() > 0) {
            Long m_id = 0L;
            db = helper.getWritableDatabase();
            db.beginTransaction();
            for (Unvan kayit : itms) {
                long id = 0;
                try {
                    ContentValues line = new ContentValues();
                    line = ObjectToContentValues(kayit);
                    m_id = db.insertOrThrow("PER_UNVAN", null, line);
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
                } catch (Throwable e) {
                    Log.d("DataController:insert\n", e.getMessage());
                    status = false;
                    throw new OrbisDefaultException("DataController:insert\n:" + e.toString());
                }

            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        }
        return status;
    }

    public ContentValues ObjectToContentValues(Unvan o) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try {
            satir.put("id", o.getId());
            satir.put("personelTipi", o.getPersonelTipi());
            satir.put("hizmetSinifi", o.getHizmetSinifi());
            satir.put("kodu", o.getKodu());
            satir.put("unvan", o.getUnvan());
            satir.put("kisaUnvan", o.getKisaUnvan());

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            return satir;
        }
    }

}