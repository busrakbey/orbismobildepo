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

import ContractLayer.OrtakCo.OrtakAgacTuru_Co;
import EntityLayer.GeoPortal.EN_KAR_AGAC;
import EntityLayer.Ortak.OrtakAgacTuru;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Ömer YILDIRIM on 21.01.2016.
 */
public class OrtakAgacTuru_Data extends DataController<OrtakAgacTuru> {
    public OrtakAgacTuru_Data(Context ctx) {
        super(ctx,new OrtakAgacTuru());
    }

    @Override
    public ArrayList<OrtakAgacTuru> list() throws OrbisDefaultException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ORTAK_AGAC_TURU where enabled=1 order by secimDate");
        return (ArrayList)loadFromQuery(sb.toString());
    }

    public ArrayList<OrtakAgacTuru> listAll() throws OrbisDefaultException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ORTAK_AGAC_TURU");
        return (ArrayList)loadFromQuery(sb.toString());
    }
    public List<OrtakAgacTuru> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakAgacTuru> agacListe = new ArrayList<OrtakAgacTuru>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    agacListe.add((OrtakAgacTuru) CursorToObject(cursor));
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
    public OrtakAgacTuru CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakAgacTuru agac = new OrtakAgacTuru();
        agac.setId(cursor.getLong(cursor.getColumnIndex("id")));
        agac.setUstId(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c1_ust_id_typ_long)));
        agac.setAgacCinsiId(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c2_agac_cinsi_id_typ_long)));
        agac.setTur(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c3_tur_typ_string)));
        agac.setYol(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c4_yol_typ_string)));
        agac.setRumuz(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c5_rumuz_typ_long)));
        agac.setAgacAdi(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c6_agac_adi_typ_string)));
        agac.setKategori(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c7_kategori_typ_string)));
        agac.setLatinAdi(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c8_latin_adi_typ_string)));
        agac.setIngilizceAdi(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c9_ingilizce_adi_typ_string)));
        agac.setDigerDilAdi(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c10_diger_dil_adi_typ_string)));
        //agac.setAktif(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c11_aktif_typ_int)));
       // agac.setZehirliSiviGazSalgilar(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c12_zehirli_sivi_gaz_salgilar_typ_int)));
        //agac.setKururkenCeker(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c13_kururken_ceker_typ_int)));
        agac.setBozulmaSuresi(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c14_bozulma_suresi_typ_int)));
        agac.setKisaKod(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c15_kisa_kod_typ_string)));
        agac.setFizikiEtkilereDayanimi(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c16_fiziki_etkilere_dayanimi_typ_string)));
        agac.setKesimZorlugu(cursor.getString(cursor.getColumnIndex(OrtakAgacTuru_Co.c17_kesim_zorlugu_typ_string)));
        agac.setOrtalamaBoy(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c18_ortalama_boy_typ_int)));
       // agac.setKabuguSoyulacak(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c19_kabugu_soyulacak_typ_int)));
        agac.setAmenajmanSira(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c20_amenajmanSira_typ_long)));
        //agac.setG(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c21_gunleme_zamani_typ_string)));
        agac.setGunleyenId(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c22_gunleyen_id_typ_long)));
        agac.setOrgId(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c23_org_id_typ_long)));
        agac.setGonderildi(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c24_gonderildi_typ_int)));
        agac.setEnabled(cursor.getInt(cursor.getColumnIndex(OrtakAgacTuru_Co.c25_enabled_typ_int)));
        agac.setMid(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c26_mid_long)));
        agac.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakAgacTuru_Co.c27_mustid_long)));
      //  Log.v("ortak agac id/kod=>",String.valueOf(agac.getId())+"/"+agac.getAgacAdi()+"/"+agac.getTur());
        return agac;
    }
    public Boolean insertFromContent(List<OrtakAgacTuru> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakAgacTuru kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(OrtakAgacTuru_Co.ORTAK_AGAC_TURU_TABLE, null, line);
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
    public ContentValues ObjectToContentValues(OrtakAgacTuru agac) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(OrtakAgacTuru_Co.c0_id_typ_long,agac.getId());
            satir.put(OrtakAgacTuru_Co.c2_agac_cinsi_id_typ_long,agac.getAgacCinsiId());
            satir.put(OrtakAgacTuru_Co.c3_tur_typ_string,agac.getTur());
            satir.put(OrtakAgacTuru_Co.c4_yol_typ_string,agac.getYol());
            satir.put(OrtakAgacTuru_Co.c5_rumuz_typ_long,agac.getRumuz());
            satir.put(OrtakAgacTuru_Co.c6_agac_adi_typ_string,agac.getAgacAdi());
            satir.put(OrtakAgacTuru_Co.c7_kategori_typ_string,agac.getKategori());
            satir.put(OrtakAgacTuru_Co.c8_latin_adi_typ_string,agac.getLatinAdi());
            satir.put(OrtakAgacTuru_Co.c9_ingilizce_adi_typ_string,agac.getIngilizceAdi());
            satir.put(OrtakAgacTuru_Co.c10_diger_dil_adi_typ_string,agac.getDigerDilAdi());
            satir.put(OrtakAgacTuru_Co.c11_aktif_typ_int,agac.getAktif());
            satir.put(OrtakAgacTuru_Co.c12_zehirli_sivi_gaz_salgilar_typ_int,agac.getZehirliSiviGazSalgilar());
            satir.put(OrtakAgacTuru_Co.c13_kururken_ceker_typ_int,agac.getKururkenCeker());
            satir.put(OrtakAgacTuru_Co.c14_bozulma_suresi_typ_int,agac.getBozulmaSuresi());
            satir.put(OrtakAgacTuru_Co.c15_kisa_kod_typ_string,agac.getKisaKod());
            satir.put(OrtakAgacTuru_Co.c16_fiziki_etkilere_dayanimi_typ_string,agac.getFizikiEtkilereDayanimi());
            satir.put(OrtakAgacTuru_Co.c17_kesim_zorlugu_typ_string,agac.getKesimZorlugu());
            satir.put(OrtakAgacTuru_Co.c18_ortalama_boy_typ_int,agac.getOrtalamaBoy());
            satir.put(OrtakAgacTuru_Co.c19_kabugu_soyulacak_typ_int,agac.getKabuguSoyulacak());
            satir.put(OrtakAgacTuru_Co.c20_amenajmanSira_typ_long,agac.getAmenajmanSira());
           // satir.put(OrtakAgacTuru_Co.c21_gunleme_zamani_typ_string,agac.getGunlemeZamani());
            satir.put(OrtakAgacTuru_Co.c22_gunleyen_id_typ_long,agac.getGunleyenId());
            satir.put(OrtakAgacTuru_Co.c23_org_id_typ_long,agac.getOrgId());
            satir.put(OrtakAgacTuru_Co.c24_gonderildi_typ_int,agac.getGonderildi());
            satir.put(OrtakAgacTuru_Co.c25_enabled_typ_int,agac.getEnabled());
            satir.put(OrtakAgacTuru_Co.c27_mustid_long,agac.getMustid());
            Log.v("ortak ağaç eklendi=>",agac.getAmenajmanSira()+"/"+agac.getTur()+"/"+agac.getAgacCinsiId()+"/"+agac.getAgacAdi()+"/"+agac.getKategori()+"/"+agac.getKisaKod()+"/"+agac.getId());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
