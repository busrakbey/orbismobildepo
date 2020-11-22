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

import ContractLayer.OrtakCo.OrtakOdunTuru_Co;

import EntityLayer.Ortak.OrtakOdunTuru;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin
 */
public class OrtakOdunTuru_Data extends DataController<OrtakOdunTuru> {
    public OrtakOdunTuru_Data(Context ctx) {
        super(ctx,new OrtakOdunTuru());
    }


    public List<OrtakOdunTuru> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakOdunTuru> agacListe = new ArrayList<OrtakOdunTuru>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    agacListe.add((OrtakOdunTuru) CursorToObject(cursor));
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
            return agacListe;
        }
    }

    public OrtakOdunTuru CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakOdunTuru agac = new OrtakOdunTuru();

        agac.setId(cursor.getLong(cursor.getColumnIndex("id")));
        agac.setSablonTur(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c1_sablonTur)));
        agac.setAdi(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c2_adi)));
        agac.setOlcuBirimId(cursor.getLong(cursor.getColumnIndex(OrtakOdunTuru_Co.c3_olcuBirimId)));
        agac.setKategori(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c4_kategori)));

        boolean aktif_value = cursor.getInt(cursor.getColumnIndex(OrtakOdunTuru_Co.c5_aktif)) > 0;
        agac.setAktif(aktif_value);

        agac.setMaxBoy( BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(OrtakOdunTuru_Co.c6_maxBoy))));
        agac.setMinBoy(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(OrtakOdunTuru_Co.c7_minBoy))));
        agac.setKisaBoyCm(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(OrtakOdunTuru_Co.c8_kisaBoyCm))));
        agac.setOrtaBoyCm(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(OrtakOdunTuru_Co.c9_ortaBoyCm))));
        agac.setVahidiFiyatRaporSira(cursor.getInt(cursor.getColumnIndex(OrtakOdunTuru_Co.c10_vahidiFiyatRaporSira)));
        agac.setAciklama(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c11_aciklama)));
        agac.setMaktaHesapNo(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c12_maktaHesapNo)));
        agac.setOrmanIciHesapNo(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c13_ormanIciHesapNo)));
        agac.setOrmanDisiHesapNo(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c14_ormanDisiHesapNo)));
        agac.setTarifeBedeli(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(OrtakOdunTuru_Co.c15_tarifeBedeli))));
        agac.setKod(cursor.getString(cursor.getColumnIndex(OrtakOdunTuru_Co.c16_kod)));
        agac.setMid(cursor.getLong(cursor.getColumnIndex(OrtakOdunTuru_Co.c17_mid_long)));
        agac.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakOdunTuru_Co.c18_mustid_long)));
        agac.setEnabled(cursor.getInt(cursor.getColumnIndex(OrtakOdunTuru_Co.c19_enabled)));
       // Log.v("ortak odun id/ad=>",String.valueOf(agac.getId())+"/"+agac.getAdi()+"/"+agac.getKisaBoyCm()+"/"+agac.getOrtaBoyCm());
        return agac;
    }

    public Boolean insertFromContent(List<OrtakOdunTuru> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakOdunTuru kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(OrtakOdunTuru_Co.ORTAK_ODUN_TURU_TABLE, null, line);
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
    public ContentValues ObjectToContentValues(OrtakOdunTuru agac) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{
          //  Log.v("ortak odun eklenecek=>","**");
            satir.put(OrtakOdunTuru_Co.c0_id_typ_long,agac.getId());
            satir.put(OrtakOdunTuru_Co.c1_sablonTur,agac.getSablonTur());
            satir.put(OrtakOdunTuru_Co.c2_adi,agac.getAdi());
            satir.put(OrtakOdunTuru_Co.c3_olcuBirimId,agac.getOlcuBirimId());
            satir.put(OrtakOdunTuru_Co.c4_kategori,agac.getKategori());
            satir.put(OrtakOdunTuru_Co.c5_aktif,agac.getAktif());
            satir.put(OrtakOdunTuru_Co.c6_maxBoy,String.valueOf(agac.getMaxBoy()));
            satir.put(OrtakOdunTuru_Co.c7_minBoy,String.valueOf(agac.getMinBoy()));
            satir.put(OrtakOdunTuru_Co.c8_kisaBoyCm,String.valueOf(agac.getKisaBoyCm()));
            satir.put(OrtakOdunTuru_Co.c9_ortaBoyCm,String.valueOf(agac.getOrtaBoyCm()));
            satir.put(OrtakOdunTuru_Co.c10_vahidiFiyatRaporSira,agac.getVahidiFiyatRaporSira());
            satir.put(OrtakOdunTuru_Co.c11_aciklama,agac.getAciklama());
            satir.put(OrtakOdunTuru_Co.c12_maktaHesapNo,agac.getMaktaHesapNo());
            satir.put(OrtakOdunTuru_Co.c13_ormanIciHesapNo,agac.getOrmanIciHesapNo());
            satir.put(OrtakOdunTuru_Co.c14_ormanDisiHesapNo,agac.getOrmanDisiHesapNo());
            satir.put(OrtakOdunTuru_Co.c15_tarifeBedeli,String.valueOf(agac.getTarifeBedeli()));
            satir.put(OrtakOdunTuru_Co.c16_kod,agac.getKod());
            satir.put(OrtakOdunTuru_Co.c17_mid_long,agac.getMid());
            satir.put(OrtakOdunTuru_Co.c18_mustid_long,agac.getMustid());
            satir.put(OrtakOdunTuru_Co.c19_enabled,String.valueOf(agac.getEnabled()));


           // Log.v("ortak odun eklendi=>",agac.getAdi()+"/"+agac.getId()+"/"+agac.getKisaBoyCm()+"/"+agac.getOrtaBoyCm());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
