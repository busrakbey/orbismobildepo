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

import ContractLayer.OrtakCo.OrtakNot_Co;
import EntityLayer.Ortak.OrtakNot;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 17.1.2017.
 */



public class OrtakNot_Data extends DataController<OrtakNot> {
    public OrtakNot_Data(Context ctx) {
        super(ctx,new OrtakNot());
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

    public List<OrtakNot> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakNot> notListe = new ArrayList<OrtakNot>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    notListe.add((OrtakNot) CursorToObject(cursor));
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
            return notListe;
        }
    }

    public OrtakNot CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakNot not = new OrtakNot();

        not.setId(cursor.getLong(cursor.getColumnIndex("id")));
        not.setIsKalemId(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c2_isKalemId)));
        not.setPersonelId(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c3_personelId)));
        not.setNotKonuId(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c4_notKonuId)));

        boolean aktif_value = cursor.getInt(cursor.getColumnIndex(OrtakNot_Co.c5_aktif)) > 0;
        not.setAktif(aktif_value);
        Log.v("not aktif","=>"+aktif_value);

        not.setNotAlinmaTarihi( cursor.getString(cursor.getColumnIndex(OrtakNot_Co.c6_notAlinmaTarihi)));
        not.setNotAciklama(cursor.getString(cursor.getColumnIndex(OrtakNot_Co.c7_notAciklama)));
        not.setGunlemeZamani(cursor.getString(cursor.getColumnIndex(OrtakNot_Co.c8_gunlemeZamani)));
        not.setGunleyenId(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c9_gunleyenId)));
        not.setMid(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c10_mid)));
        not.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c11_mustid)));
        not.setModulId(cursor.getInt(cursor.getColumnIndex(OrtakNot_Co.c12_modulId)));
        not.setGuzergahId(cursor.getInt(cursor.getColumnIndex(OrtakNot_Co.c13_guzergahId)));
        not.setBirimId(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c14_birimId)));
        not.setOBJECTID(cursor.getLong(cursor.getColumnIndex(OrtakNot_Co.c15_objectId)));
        not.setYolKod(cursor.getString(cursor.getColumnIndex(OrtakNot_Co.c16_yolkodu)));
        not.setYolAdi(cursor.getString(cursor.getColumnIndex(OrtakNot_Co.c17_yolAdi)));
        not.setYolKategori(cursor.getInt(cursor.getColumnIndex(OrtakNot_Co.c18_yolKategori)));



        return not;
    }

    public Boolean insertFromContent(List<OrtakNot> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakNot kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(OrtakNot_Co.ORTAK_NOT_TABLE, null, line);
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


    public Boolean updateFromContent(List<OrtakNot> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakNot kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = OrtakNot_Co.ORTAK_NOT_TABLE;
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


    public Boolean deleteFromContent(List<Long> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Long kayit : itms)
        {
            long id = 0;
            try {

                String tableName = OrtakNot_Co.ORTAK_NOT_TABLE;
                String[] whereParameters = {String.valueOf(kayit)};
                String WhereArgs = "mid=?";
                m_id = db.delete(tableName , WhereArgs, whereParameters);

                if (m_id > 0) {
                    status = true;
                    Log.v("delete","bitti2");
                    Log.d("DataController", "Kayit silindi id:" + m_id + " -" + kayit.toString());
                } else {
                    Log.v("delete","bitti3");
                    Log.d("DataController", "Kayıt silme başarısız !");
                    status = false;
                    throw new OrbisDefaultException("DataController-update:Kayit silinemedi !" + kayit.toString());
                }

            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new OrbisDefaultException("DataController(delete)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new OrbisDefaultException("DataController(delete)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new OrbisDefaultException("DataController(delete)Hata:" + e.getMessage());
            }
            catch (Throwable e)
            {
                Log.d("DataController:delete\n", e.getMessage());
                status = false;
                throw new OrbisDefaultException("DataController:delete\n:" + e.toString());
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }

    public ContentValues ObjectToContentValues(OrtakNot not) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(OrtakNot_Co.c0_id_typ_long,not.getId());
            satir.put(OrtakNot_Co.c2_isKalemId,not.getIsKalemId());
            satir.put(OrtakNot_Co.c3_personelId,not.getPersonelId());
            satir.put(OrtakNot_Co.c4_notKonuId,not.getNotKonuId());
            satir.put(OrtakNot_Co.c5_aktif,String.valueOf(not.getAktif()));
            satir.put(OrtakNot_Co.c6_notAlinmaTarihi,not.getNotAlinmaTarihi());
            satir.put(OrtakNot_Co.c7_notAciklama,not.getNotAciklama());
            satir.put(OrtakNot_Co.c8_gunlemeZamani,not.getGunlemeZamani());
            satir.put(OrtakNot_Co.c9_gunleyenId,not.getGunleyenId());
            satir.put(OrtakNot_Co.c10_mid,not.getMid());
            satir.put(OrtakNot_Co.c11_mustid,not.getMustid());
            satir.put(OrtakNot_Co.c12_modulId,not.getModulId());
            satir.put(OrtakNot_Co.c13_guzergahId,not.getGuzergahId());
            satir.put(OrtakNot_Co.c14_birimId,not.getBirimId());
            satir.put(OrtakNot_Co.c15_objectId,not.getOBJECTID());
            satir.put(OrtakNot_Co.c16_yolkodu,not.getYolKod());
            satir.put(OrtakNot_Co.c17_yolAdi,not.getYolAdi());
            satir.put(OrtakNot_Co.c18_yolKategori,not.getYolKategori());



            Log.v("ortaknotdata ins=>",not.getNotAciklama()+" not guzergah id =>"+not.getGuzergahId());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
