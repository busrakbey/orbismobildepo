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

import EntityLayer.Ortak.OtherUsers;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 24.10.2016.
 */
public class OtherUsers_Data extends DataController<OtherUsers> {

    public OtherUsers_Data(Context ctx) {
        super(ctx, new OtherUsers());
    }

    public List<OtherUsers> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OtherUsers> otherUsersList = new ArrayList<OtherUsers>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    otherUsersList.add((OtherUsers) CursorToObject(cursor));
                }
                cursor.close();
            }

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            db.close();
            return otherUsersList;
        }
    }

    public OtherUsers CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OtherUsers o = new OtherUsers();
        o.setId(cursor.getLong(cursor.getColumnIndex("id")));
        o.setAd(cursor.getString(cursor.getColumnIndex("ad")));
        o.setSoyad(cursor.getString(cursor.getColumnIndex("soyad")));
        o.setGorevBirimId(cursor.getLong(cursor.getColumnIndex("gorevBirimId")));
        o.setGorevUnvanId(cursor.getLong(cursor.getColumnIndex("gorevUnvanId")));
        o.setSuperId(cursor.getLong(cursor.getColumnIndex("superId")));
        return o;
    }

    public Boolean insertFromContent(List<OtherUsers> itms) throws OrbisDefaultException {
        Boolean status = false;
        if (itms != null && itms.size() > 0) {
            Long m_id = 0L;
            db = helper.getWritableDatabase();
            db.beginTransaction();
            for (OtherUsers kayit : itms) {
                long id = 0;
                try {
                    ContentValues line = new ContentValues();
                    line = ObjectToContentValues(kayit);
                    m_id = db.insertOrThrow("PER_SICIL", null, line);
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

    public ContentValues ObjectToContentValues(OtherUsers o) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try {
            satir.put("id", o.getId());
            satir.put("ad", o.getAd());
            satir.put("soyad", o.getSoyad());
            satir.put("gorevBirimId", o.getGorevBirimId());
            satir.put("gorevUnvanId", o.getGorevUnvanId());
            satir.put("superId",o.getSuperId());

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            return satir;
        }
    }
}