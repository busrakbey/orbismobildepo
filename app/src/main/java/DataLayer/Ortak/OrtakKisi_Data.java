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

import ContractLayer.SistemCo.OrtakKisi_Co;
import EntityLayer.Ortak.OrtakKisi;
import EntityLayer.Ortak.OtherUsers;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 24.10.2016.
 */
public class OrtakKisi_Data extends DataController<OrtakKisi> {

    public OrtakKisi_Data(Context ctx) {
        super(ctx, new OrtakKisi());
    }

    public List<OrtakKisi> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakKisi> ortakKisiList = new ArrayList<OrtakKisi>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    ortakKisiList.add((OrtakKisi) CursorToObject(cursor));
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

    public OrtakKisi CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakKisi o = new OrtakKisi();
        o.setId(cursor.getLong(cursor.getColumnIndex(OrtakKisi_Co.c0_id_typ_long)));
        o.setAdi(cursor.getString(cursor.getColumnIndex(OrtakKisi_Co.c1_ad)));
        o.setSoyadi(cursor.getString(cursor.getColumnIndex(OrtakKisi_Co.c2_soyad)));
        o.setTcKimlikNo(cursor.getString(cursor.getColumnIndex(OrtakKisi_Co.c3_tc)));
        o.setMid(cursor.getLong(cursor.getColumnIndex(OrtakKisi_Co.c4_mid)));
        o.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakKisi_Co.c5_must)));
        Log.v("cursor kisi","=>"+o.getMid()+"-"+o.getTcKimlikNo()+"-"+o.getAdi()+"-"+o.getSoyadi());
        return o;
    }

    public Boolean insertFromContent(List<OrtakKisi> itms) throws OrbisDefaultException {
        Boolean status = false;
        if (itms != null && itms.size() > 0) {
            Long m_id = 0L;
            db = helper.getWritableDatabase();
            for (OrtakKisi kayit : itms) {
                long id = 0;
                try {
                    ContentValues line = new ContentValues();
                    line = ObjectToContentValues(kayit);
                    m_id = db.insertOrThrow(OrtakKisi_Co.ORTAK_KISI_TABLE, null, line);
                    if (m_id > 0) {

                        Log.v("o.kisi tc","=>"+kayit.getTcKimlikNo());
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


    public Boolean updateFromContent(List<OrtakKisi> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakKisi kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = OrtakKisi_Co.ORTAK_KISI_TABLE;
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




    public ContentValues ObjectToContentValues(OrtakKisi o) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try {
            satir.put(OrtakKisi_Co.c0_id_typ_long, o.getId());
            satir.put(OrtakKisi_Co.c1_ad, o.getAdi());
            satir.put(OrtakKisi_Co.c2_soyad, o.getSoyadi());
            satir.put(OrtakKisi_Co.c3_tc, o.getTcKimlikNo());
            satir.put(OrtakKisi_Co.c4_mid, o.getMid());
            satir.put(OrtakKisi_Co.c5_must, o.getMustid());
            Log.v("ccccc","ortak kisi data=>"+o.getTcKimlikNo());

      /*      satir.put(OrtakKisi_Co.c6_dogumYeri, o.getDogumYer());
            satir.put(OrtakKisi_Co.c7_uyrugu, o.getUyrugu());
            satir.put(OrtakKisi_Co.c8_kisiTur, o.getKisiTur());
            satir.put(OrtakKisi_Co.c9_kimlikNumarasi, o.getKimlikNumarasi());
            satir.put(OrtakKisi_Co.c10_cinsiyet, o.getCinsiyet());

            satir.put(OrtakKisi_Co.c11_babaAdi, o.getBabaAdi());
            satir.put(OrtakKisi_Co.c12_anaAdi, o.getAnaAdi());
            satir.put(OrtakKisi_Co.c13_dogumTarihi, o.getDogumTarihi());
            satir.put(OrtakKisi_Co.c14_meslekTuru, o.getMeslekTuru());
            satir.put(OrtakKisi_Co.c15_gorevUnvan, o.getGorevUnvan());


            satir.put(OrtakKisi_Co.c16_kurumSirket, o.getKurumSirket());
            satir.put(OrtakKisi_Co.c17_adiSoyadi, o.getAdiSoyadi());
            satir.put(OrtakKisi_Co.c18_gunlemeZamani, o.getGunlemeZamani());
            satir.put(OrtakKisi_Co.c19_gunleyenId, o.getGunleyenId());
            satir.put(OrtakKisi_Co.c20_personelYakini, o.getPersonelYakini());


            satir.put(OrtakKisi_Co.c21_yakinlikDerecesi, o.getYakinlikDerecesi());
            satir.put(OrtakKisi_Co.c22_dogumYer, o.getDogumYer());
            satir.put(OrtakKisi_Co.c23_seriNo, o.getSeriNo());
            satir.put(OrtakKisi_Co.c24_cuzdanNo, o.getCuzdanNo());
            satir.put(OrtakKisi_Co.c25_personelId, o.getPersonelId());


            satir.put(OrtakKisi_Co.c26_pasaportTur, o.getPasaportTur());
            satir.put(OrtakKisi_Co.c27_pasaportNo, o.getPasaportNo());
            satir.put(OrtakKisi_Co.c28_duzTarihi, o.getDuzTarihi());
            satir.put(OrtakKisi_Co.c29_gecerlilikTarihi, o.getGecerlilikTarihi());
            satir.put(OrtakKisi_Co.c30_sistemKullaniciId, o.getSistemKullaniciId());*/


        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } finally {
            return satir;
        }
    }
}