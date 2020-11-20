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

import ContractLayer.OrtakCo.SanatYapisi_Co;
import EntityLayer.Ortak.SanatYapisi;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 17.1.2017.
 */



public class SanatYapi_Data extends DataController<SanatYapisi> {
    public SanatYapi_Data(Context ctx) {
        super(ctx,new SanatYapisi());
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

    public List<SanatYapisi> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<SanatYapisi> notListe = new ArrayList<SanatYapisi>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    notListe.add((SanatYapisi) CursorToObject(cursor));
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

    public SanatYapisi CursorToObject(Cursor cursor) throws OrbisDefaultException {
        SanatYapisi not = new SanatYapisi();

        not.setId(cursor.getLong(cursor.getColumnIndex("id")));
        not.setY(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c2_y)));
        not.setX(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c3_x)));
        not.setSanatcins(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c4_sanatcins)));
        not.setSanatcinskod( cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c5_sanatcinskod)));
        not.setDargenis(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c6_dargenis)));
        not.setYaricap(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c7_yaricap)));
        not.setAciklik(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c8_aciklik)));
        not.setEn(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c9_en)));
        not.setBoy(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c10_boy)));
        not.setYukseklik(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c11_yukseklik)));
        not.setCap(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c12_cap)));
        not.setYolId(cursor.getLong(cursor.getColumnIndex(SanatYapisi_Co.c13_yolId)));
        not.setYolKodu(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c14_yolkodu)));
        not.setYolAdi(cursor.getString(cursor.getColumnIndex(SanatYapisi_Co.c15_yolAdi)));
        not.setYolMid(cursor.getLong(cursor.getColumnIndex(SanatYapisi_Co.c16_yolMid)));
        not.setMid(cursor.getLong(cursor.getColumnIndex(SanatYapisi_Co.c17_mid)));
        not.setMustid(cursor.getLong(cursor.getColumnIndex(SanatYapisi_Co.c18_mustid)));



        return not;
    }

    public Boolean insertFromContent(List<SanatYapisi> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (SanatYapisi kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(SanatYapisi_Co.SANAT_YAPI_TABLE, null, line);
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


    public Boolean updateFromContent(List<SanatYapisi> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (SanatYapisi kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = SanatYapisi_Co.SANAT_YAPI_TABLE;
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

                String tableName = SanatYapisi_Co.SANAT_YAPI_TABLE;
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

    public ContentValues ObjectToContentValues(SanatYapisi not) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(SanatYapisi_Co.c0_id_typ_long,not.getId());
            satir.put(SanatYapisi_Co.c2_y,not.getY());
            satir.put(SanatYapisi_Co.c3_x,not.getX());
            satir.put(SanatYapisi_Co.c4_sanatcins,not.getSanatcins());
            satir.put(SanatYapisi_Co.c5_sanatcinskod,not.getSanatcinskod());
            satir.put(SanatYapisi_Co.c6_dargenis,not.getDargenis());
            satir.put(SanatYapisi_Co.c7_yaricap,not.getYaricap());
            satir.put(SanatYapisi_Co.c8_aciklik,not.getAciklik());
            satir.put(SanatYapisi_Co.c9_en,not.getEn());
            satir.put(SanatYapisi_Co.c10_boy,not.getBoy());
            satir.put(SanatYapisi_Co.c11_yukseklik,not.getYukseklik());
            satir.put(SanatYapisi_Co.c12_cap,not.getCap());
            satir.put(SanatYapisi_Co.c13_yolId,not.getYolId());
            satir.put(SanatYapisi_Co.c14_yolkodu,not.getYolKodu());
            satir.put(SanatYapisi_Co.c15_yolAdi,not.getYolAdi());
            satir.put(SanatYapisi_Co.c16_yolMid,not.getYolMid());
            satir.put(SanatYapisi_Co.c17_mid,not.getMid());
            satir.put(SanatYapisi_Co.c18_mustid,not.getMustid());



            Log.v("sanat yapı ins= x >",not.getX()+" y =>"+not.getY());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
