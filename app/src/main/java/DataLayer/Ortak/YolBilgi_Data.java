package DataLayer.Ortak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import ContractLayer.OrtakCo.YolBilgi_Co;
import EntityLayer.Ortak.YolBilgi;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 17.1.2017.
 */



public class YolBilgi_Data extends DataController<YolBilgi> {
    public YolBilgi_Data(Context ctx) {
        super(ctx,new YolBilgi());
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

    public List<YolBilgi> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<YolBilgi> notListe = new ArrayList<YolBilgi>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    notListe.add((YolBilgi) CursorToObject(cursor));
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

    public YolBilgi CursorToObject(Cursor cursor) throws OrbisDefaultException {
        YolBilgi not = new YolBilgi();

        not.setId(cursor.getLong(cursor.getColumnIndex("id")));
        not.setYolId(cursor.getLong(cursor.getColumnIndex(YolBilgi_Co.c2_yolId)));
        not.setTUL(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c3_TUL)));
        not.setSIRA_NO(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c4_SIRA_NO)));
        not.setYOL_BASLANGIC_X( cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c5_YOL_BASLANGIC_X)));
        not.setYOL_BASLANGIC_Y(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c6_YOL_BASLANGIC_Y)));
        not.setYOL_BITIS_X(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c7_YOL_BITIS_X)));
        not.setYOL_BITIS_Y(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c8_YOL_BITIS_Y)));
        not.setYOL_BASLANGIC_UTM_X(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c9_YOL_BASLANGIC_UTM_X)));
        not.setYOL_BASLANGIC_UTM_Y(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c10_YOL_BASLANGIC_UTM_Y)));
        not.setYOL_BITIS_UTM_X(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c11_YOL_BITIS_UTM_X)));
        not.setYOL_BITIS_UTM_Y(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c12_YOL_BITIS_UTM_Y)));
        not.setYOL_TIPI(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c13_YOL_TIPI)));
        not.setIslemDurumu(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c14_islemDurumu)));
        not.setBaslangici(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(YolBilgi_Co.c15_baslangici))));
        not.setBitisi(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(YolBilgi_Co.c16_bitisi))));
        not.setOffset(cursor.getLong(cursor.getColumnIndex(YolBilgi_Co.c17_offset)));
        not.setAciklama(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c19_aciklama)));
        not.setTarih(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c20_tarih)));
        not.setYolBirimKodu(cursor.getString(cursor.getColumnIndex(YolBilgi_Co.c21_yolBirimKodu)));
        not.setEklenecekTul(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(YolBilgi_Co.c22_eklenecekTul))));
        not.setOffsetbaslangic(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(YolBilgi_Co.c23_offsetbaslangic))));
        not.setOffsetbitis(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(YolBilgi_Co.c24_offsetbitis))));
        not.setYapimSekli(cursor.getInt(cursor.getColumnIndex(YolBilgi_Co.c28_yapimSekli)));
        not.setDurum(cursor.getInt(cursor.getColumnIndex(YolBilgi_Co.c30_durum)));
        not.setMid(cursor.getLong(cursor.getColumnIndex(YolBilgi_Co.c31_mid_long)));
        not.setMustid(cursor.getLong(cursor.getColumnIndex(YolBilgi_Co.c32_mustid_long)));
        not.setOrgId(cursor.getLong(cursor.getColumnIndex(YolBilgi_Co.c33_org_id_typ_long)));
        not.setGonderildi(cursor.getInt(cursor.getColumnIndex(YolBilgi_Co.c34_gonderildi_typ_int)));


        return not;
    }

    public Boolean insertFromContent(List<YolBilgi> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (YolBilgi kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(YolBilgi_Co.YOL_BILGI_TABLE, null, line);
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


    public Boolean updateFromContent(List<YolBilgi> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (YolBilgi kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = YolBilgi_Co.YOL_BILGI_TABLE;
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

                String tableName = YolBilgi_Co.YOL_BILGI_TABLE;
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

    public ContentValues ObjectToContentValues(YolBilgi not) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(YolBilgi_Co.c0_id_typ_long,not.getId());
            satir.put(YolBilgi_Co.c2_yolId,not.getYolId());
            satir.put(YolBilgi_Co.c3_TUL,not.getTUL());
            satir.put(YolBilgi_Co.c4_SIRA_NO,not.getSIRA_NO());
            satir.put(YolBilgi_Co.c5_YOL_BASLANGIC_X,not.getYOL_BASLANGIC_X());
            satir.put(YolBilgi_Co.c6_YOL_BASLANGIC_Y,not.getYOL_BASLANGIC_Y());
            satir.put(YolBilgi_Co.c7_YOL_BITIS_X,not.getYOL_BITIS_X());
            satir.put(YolBilgi_Co.c8_YOL_BITIS_Y,not.getYOL_BITIS_Y());
            satir.put(YolBilgi_Co.c9_YOL_BASLANGIC_UTM_X,not.getYOL_BASLANGIC_UTM_X());
            satir.put(YolBilgi_Co.c10_YOL_BASLANGIC_UTM_Y,not.getYOL_BASLANGIC_UTM_Y());
            satir.put(YolBilgi_Co.c11_YOL_BITIS_UTM_X,not.getYOL_BITIS_UTM_X());
            satir.put(YolBilgi_Co.c12_YOL_BITIS_UTM_Y,not.getYOL_BITIS_UTM_Y());
            satir.put(YolBilgi_Co.c13_YOL_TIPI,not.getYOL_TIPI());
            satir.put(YolBilgi_Co.c14_islemDurumu,not.getIslemDurumu());
            satir.put(YolBilgi_Co.c15_baslangici,String.valueOf(not.getBaslangici()));
            satir.put(YolBilgi_Co.c16_bitisi,String.valueOf(not.getBitisi()));
            satir.put(YolBilgi_Co.c17_offset,not.getOffset());
            satir.put(YolBilgi_Co.c19_aciklama,not.getAciklama());
            satir.put(YolBilgi_Co.c20_tarih,not.getTarih());
            satir.put(YolBilgi_Co.c21_yolBirimKodu,not.getYolBirimKodu());
            satir.put(YolBilgi_Co.c22_eklenecekTul,String.valueOf(not.getEklenecekTul()));
            satir.put(YolBilgi_Co.c23_offsetbaslangic,String.valueOf(not.getOffsetbaslangic()));
            satir.put(YolBilgi_Co.c24_offsetbitis,String.valueOf(not.getOffsetbitis()));
            satir.put(YolBilgi_Co.c28_yapimSekli,not.getYapimSekli());
            satir.put(YolBilgi_Co.c30_durum,not.getDurum());
            satir.put(YolBilgi_Co.c31_mid_long,not.getMid());
            satir.put(YolBilgi_Co.c32_mustid_long,not.getMustid());
            satir.put(YolBilgi_Co.c33_org_id_typ_long,not.getOrgId());
            satir.put(YolBilgi_Co.c34_gonderildi_typ_int,not.getGonderildi());



        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
