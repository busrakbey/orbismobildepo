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

import ContractLayer.OrtakCo.OrtakKisiHareket_Co;
import EntityLayer.Ortak.OrtakKisiHareket;
import EntityLayer.Ortak.OtherUsers;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 28.02.2017
 */
public class OrtakKisiHareket_Data extends DataController<OrtakKisiHareket> {

    public OrtakKisiHareket_Data(Context ctx) {
        super(ctx, new OrtakKisiHareket());
    }

    public List<OrtakKisiHareket> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakKisiHareket> ortakKisiList = new ArrayList<OrtakKisiHareket>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    ortakKisiList.add((OrtakKisiHareket) CursorToObject(cursor));
                }
                cursor.close();
            }

        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            db.close();
            return ortakKisiList;
        }
    }

    public OrtakKisiHareket CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakKisiHareket o = new OrtakKisiHareket();
        o.setId(cursor.getLong(cursor.getColumnIndex(OrtakKisiHareket_Co.c0_id_typ_long)));
        o.setPersonelId(cursor.getLong(cursor.getColumnIndex(OrtakKisiHareket_Co.c2_ipersonelId)));
        o.setBirimId(cursor.getLong(cursor.getColumnIndex(OrtakKisiHareket_Co.c3_birimId)));
        o.setAdi(cursor.getString(cursor.getColumnIndex(OrtakKisiHareket_Co.c4_adi)));
        o.setSoyadi(cursor.getString(cursor.getColumnIndex(OrtakKisiHareket_Co.c5_soyadi)));
        o.setUnvan(cursor.getString(cursor.getColumnIndex(OrtakKisiHareket_Co.c6_unvan)));
        o.setSonHareketZamani(cursor.getString(cursor.getColumnIndex(OrtakKisiHareket_Co.c7_sonHareketZamani)));
        o.setXkoordinati(cursor.getString(cursor.getColumnIndex(OrtakKisiHareket_Co.c8_xkoordinati)));
        o.setYkoordinati(cursor.getString(cursor.getColumnIndex(OrtakKisiHareket_Co.c9_ykoordinati)));
        o.setMid(cursor.getLong(cursor.getColumnIndex(OrtakKisiHareket_Co.c10_mid)));
        o.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakKisiHareket_Co.c11_mustid)));
        o.setSonHareketMs(cursor.getLong(cursor.getColumnIndex(OrtakKisiHareket_Co.c13_sonHareketMs)));

        boolean aktif_value = cursor.getInt(cursor.getColumnIndex(OrtakKisiHareket_Co.c12_lokasyonAktif)) > 0;
        o.setLokasyonAktif(aktif_value);


        return o;
    }

    public Boolean insertFromContent(List<OrtakKisiHareket> itms) throws OrbisDefaultException {
        Boolean status = false;
        if (itms != null && itms.size() > 0) {
            Long m_id = 0L;
            db = helper.getWritableDatabase();
            for (OrtakKisiHareket kayit : itms) {
                long id = 0;
                try {
                    ContentValues line = new ContentValues();
                    line = ObjectToContentValues(kayit);
                    m_id = db.insertOrThrow(OrtakKisiHareket_Co.ORTAK_KISI_HAREKET_TABLE, null, line);
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
            db.close();
        }
        return status;
    }


    public Boolean updateFromContent(List<OrtakKisiHareket> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakKisiHareket kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = OrtakKisiHareket_Co.ORTAK_KISI_HAREKET_TABLE;
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




    public ContentValues ObjectToContentValues(OrtakKisiHareket o) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try {
            satir.put(OrtakKisiHareket_Co.c0_id_typ_long, o.getId());
            satir.put(OrtakKisiHareket_Co.c2_ipersonelId, o.getPersonelId());
            satir.put(OrtakKisiHareket_Co.c3_birimId, o.getBirimId());
            satir.put(OrtakKisiHareket_Co.c4_adi, o.getAdi());
            satir.put(OrtakKisiHareket_Co.c5_soyadi, o.getSoyadi());
            satir.put(OrtakKisiHareket_Co.c6_unvan, o.getUnvan());
            satir.put(OrtakKisiHareket_Co.c7_sonHareketZamani, o.getSonHareketZamani());
            satir.put(OrtakKisiHareket_Co.c8_xkoordinati, o.getXkoordinati());
            satir.put(OrtakKisiHareket_Co.c9_ykoordinati, o.getYkoordinati());
            satir.put(OrtakKisiHareket_Co.c10_mid, o.getMid());
            satir.put(OrtakKisiHareket_Co.c11_mustid, o.getMustid());
            satir.put(OrtakKisiHareket_Co.c12_lokasyonAktif,o.getLokasyonAktif());
            satir.put(OrtakKisiHareket_Co.c13_sonHareketMs,o.getSonHareketMs());



        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            return satir;
        }
    }
}