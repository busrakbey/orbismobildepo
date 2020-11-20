package DataLayer.Sistem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;
;import java.util.ArrayList;
import java.util.List;

import ContractLayer.OrtakCo.OrtakAgacTuru_Co;
import ContractLayer.SistemCo.SOrgBirim_Co;
import DataLayer.Ortak.DataController;
import EntityLayer.Ortak.OrtakAgacTuru;
import EntityLayer.Sistem.SOrgBirim;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Ömer YILDIRIM on 21.01.2016.
 */
public class SOrgBirim_Data extends DataController<SOrgBirim> {

    public SOrgBirim_Data(Context ctx) {
        super(ctx, new SOrgBirim());
    }

    public List<SOrgBirim> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<SOrgBirim> birimList = new ArrayList<SOrgBirim>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    birimList.add((SOrgBirim) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            db.close();
            return birimList;
        }
    }
    public SOrgBirim CursorToObject(Cursor cursor) throws OrbisDefaultException {
        SOrgBirim o = new SOrgBirim();
        o.setId(cursor.getLong(cursor.getColumnIndex("id")));
        o.setUstId(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c1_ust_id_typ_long)));
        o.setYol(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c2_yol_typ_string)));
        o.setKurumId(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c3_kurum_id_typ_long)));
        o.setAdi(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c4_adi_typ_string)));
        //o.setAktif(cursor.getInt(cursor.getColumnIndex(SOrgBirim_Co.c5_aktif_typ_int)));
        o.setKodu(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c6_kodu_typ_string)));
        o.setDesimalKodu(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c7_desimal_kodu_typ_string)));
        o.setDahiliKod(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c8_dahili_kod_typ_string)));
        o.setKategori(cursor.getInt(cursor.getColumnIndex(SOrgBirim_Co.c9_kategori_typ_int)));
        o.setTipi(cursor.getInt(cursor.getColumnIndex(SOrgBirim_Co.c10_tipi_typ_int)));
        o.setSinifi(cursor.getInt(cursor.getColumnIndex(SOrgBirim_Co.c11_sinifi_typ_int)));
        o.setOzelKod1(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c12_ozel_kod_1_typ_string)));
        o.setOzelKod2(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c13_ozel_kod_2_typ_string)));
        o.setAdres(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c14_adres_typ_string)));
        o.setKatNo(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c15_kat_no_typ_string)));
        o.setOdaNo(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c16_oda_no_typ_string)));
        o.setTelefon1(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c17_telefon_1_typ_string)));
        o.setTelefon2(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c18_telefon_2_typ_string)));
        o.setDahiliTelefon1(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c19_dahili_telefon_1_typ_string)));
        o.setDahiliTelefon2(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c20_dahili_telefon_2_typ_string)));
        o.setEposta(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c21_e_posta_typ_string)));
        o.setYetkiliId(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c22_yetkili_id_typ_long)));
      //  o.setGunlemeZamani(cursor.getString(cursor.getColumnIndex(SOrgBirim_Co.c23_gunleme_zamani_typ_string)));
        o.setGunleyenId(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c24_gunleyen_id_typ_long)));
        o.setOrgId(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c25_org_id_typ_long)));
        o.setMid(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c26_mid)));
        o.setMustid(cursor.getLong(cursor.getColumnIndex(SOrgBirim_Co.c27_mustid)));
        o.setGonderildi(cursor.getInt(cursor.getColumnIndex(SOrgBirim_Co.c28_gonderildi)));
        return o;
    }
    public Boolean insertFromContent(List<SOrgBirim> itms) throws OrbisDefaultException
    {
        Boolean status =false;
         if (itms!=null&& itms.size()>0)
         {
             Long m_id=0L;
             db = helper.getWritableDatabase();
             db.beginTransaction();
             for (SOrgBirim kayit : itms)
             {
                 long id = 0;
                 try {
                     ContentValues line = new ContentValues();
                     line = ObjectToContentValues(kayit);
                     m_id = db.insertOrThrow(SOrgBirim_Co.S_ORG_BIRIM_TABLE, null, line);
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
         }
        return status;
    }
    public ContentValues ObjectToContentValues(SOrgBirim o) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(SOrgBirim_Co.c0_id_typ_long,o.getId());
            satir.put(SOrgBirim_Co.c1_ust_id_typ_long,o.getUstId());
            satir.put(SOrgBirim_Co.c2_yol_typ_string,o.getYol());
            satir.put(SOrgBirim_Co.c3_kurum_id_typ_long,o.getKurumId());
            satir.put(SOrgBirim_Co.c4_adi_typ_string,o.getAdi());
            satir.put(SOrgBirim_Co.c5_aktif_typ_int,o.getAktif());
            satir.put(SOrgBirim_Co.c6_kodu_typ_string,o.getKodu());
            satir.put(SOrgBirim_Co.c7_desimal_kodu_typ_string,o.getDesimalKodu());
            satir.put(SOrgBirim_Co.c8_dahili_kod_typ_string,o.getDahiliKod());
            satir.put(SOrgBirim_Co.c9_kategori_typ_int,o.getKategori());
            satir.put(SOrgBirim_Co.c10_tipi_typ_int,o.getTipi());
            satir.put(SOrgBirim_Co.c11_sinifi_typ_int,o.getSinifi());
            satir.put(SOrgBirim_Co.c12_ozel_kod_1_typ_string,o.getOzelKod1());
            satir.put(SOrgBirim_Co.c13_ozel_kod_2_typ_string,o.getOzelKod2());
            satir.put(SOrgBirim_Co.c14_adres_typ_string,o.getAdres());
            satir.put(SOrgBirim_Co.c15_kat_no_typ_string,o.getKatNo());
            satir.put(SOrgBirim_Co.c16_oda_no_typ_string,o.getOdaNo());
            satir.put(SOrgBirim_Co.c17_telefon_1_typ_string,o.getTelefon1());
            satir.put(SOrgBirim_Co.c18_telefon_2_typ_string,o.getTelefon2());
            satir.put(SOrgBirim_Co.c19_dahili_telefon_1_typ_string,o.getDahiliTelefon1());
            satir.put(SOrgBirim_Co.c20_dahili_telefon_2_typ_string,o.getDahiliTelefon2());
            satir.put(SOrgBirim_Co.c21_e_posta_typ_string,o.getEposta());
            satir.put(SOrgBirim_Co.c22_yetkili_id_typ_long,o.getYetkiliId());
           // satir.put(SOrgBirim_Co.c23_gunleme_zamani_typ_string,o.getGunlemeZamani());
            satir.put(SOrgBirim_Co.c24_gunleyen_id_typ_long,o.getGunleyenId());
            satir.put(SOrgBirim_Co.c25_org_id_typ_long,o.getOrgId());
            satir.put(SOrgBirim_Co.c26_mid,o.getMid());
            satir.put(SOrgBirim_Co.c27_mustid,o.getMustid());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}