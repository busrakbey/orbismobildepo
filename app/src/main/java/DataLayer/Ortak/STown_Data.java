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

import ContractLayer.OrtakCo.STown_Co;
import EntityLayer.Ortak.STown;
import EntityLayer.Ortak.Unvan;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 13.10.2016.
 */
public class STown_Data  extends DataController<STown> {

    public STown_Data(Context ctx) {
        super(ctx, new STown());
    }

    public List<STown> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<STown> ilceList = new ArrayList<STown>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    ilceList.add((STown) CursorToObject(cursor));
                }
                cursor.close();
            }

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            db.close();
            return ilceList;
        }
    }

    public STown CursorToObject(Cursor cursor) throws OrbisDefaultException {
        STown o = new STown();
        o.setId(cursor.getLong(cursor.getColumnIndex("id")));
        o.setIlId(cursor.getLong(cursor.getColumnIndex(STown_Co.c1_ilId)));
        o.setAdi(cursor.getString(cursor.getColumnIndex(STown_Co.c2_adi)));
        o.setGorunum(cursor.getString(cursor.getColumnIndex(STown_Co.c3_gorunum)));
        return o;
    }

    public Boolean insertFromContent(List<STown> itms) throws OrbisDefaultException {
        Boolean status = false;
        if (itms != null && itms.size() > 0) {
            Long m_id = 0L;
            db = helper.getWritableDatabase();
            db.beginTransaction();
            for (STown kayit : itms) {
                long id = 0;
                try {
                    ContentValues line = new ContentValues();
                    line = ObjectToContentValues(kayit);
                    m_id = db.insertOrThrow(STown_Co.ORTAK_ILCE, null, line);
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

    public ContentValues ObjectToContentValues(STown o) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try {
            satir.put(STown_Co.c0_id_typ_long, o.getId());
            satir.put(STown_Co.c1_ilId, o.getIlId());
            satir.put(STown_Co.c2_adi, o.getAdi());
            satir.put(STown_Co.c3_gorunum, o.getGorunum());

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            return satir;
        }
    }

}