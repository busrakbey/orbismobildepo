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

import ContractLayer.OrtakCo.OrtakIsKalemleri_Co;
import EntityLayer.Ortak.OrtakIsKalemleri;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 17.1.2017.
 */

public class OrtakIsKalemleri_Data extends DataController<OrtakIsKalemleri> {
    public OrtakIsKalemleri_Data(Context ctx) {
        super(ctx,new OrtakIsKalemleri());
    }


    public List<OrtakIsKalemleri> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<OrtakIsKalemleri> kalemListe = new ArrayList<OrtakIsKalemleri>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    kalemListe.add((OrtakIsKalemleri) CursorToObject(cursor));
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
            return kalemListe;
        }
    }

    public OrtakIsKalemleri CursorToObject(Cursor cursor) throws OrbisDefaultException {
        OrtakIsKalemleri kalem = new OrtakIsKalemleri();

        kalem.setId(cursor.getLong(cursor.getColumnIndex("id")));
        kalem.setIsKalemTanim(cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c2_isKalemTanim)));
        kalem.setIsKalemKategori(cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c3_isKalemKategori)));
        kalem.setIlgiliBirimId(cursor.getLong(cursor.getColumnIndex(OrtakIsKalemleri_Co.c4_ilgiliBirimId)));

        boolean aktif_value = cursor.getInt(cursor.getColumnIndex(OrtakIsKalemleri_Co.c8_aktif)) > 0;
        kalem.setAktif(aktif_value);

        kalem.setKodu( cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c5_kodu)));
        kalem.setOlusturulmaTarihi(cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c6_olusturulmaTarihi)));
        kalem.setBitisTarihi(cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c7_bitisTarihi)));
        kalem.setAciklama(cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c9_aciklama)));
        kalem.setGunlemeZamani(cursor.getString(cursor.getColumnIndex(OrtakIsKalemleri_Co.c10_gunlemeZamani)));
        kalem.setGunleyenId(cursor.getLong(cursor.getColumnIndex(OrtakIsKalemleri_Co.c11_gunleyenId)));
        kalem.setModulId(cursor.getInt(cursor.getColumnIndex(OrtakIsKalemleri_Co.c12_modulId)));
        kalem.setMid(cursor.getLong(cursor.getColumnIndex(OrtakIsKalemleri_Co.c13_mid)));
        kalem.setMustid(cursor.getLong(cursor.getColumnIndex(OrtakIsKalemleri_Co.c14_mustid)));


        return kalem;
    }

    public Boolean insertFromContent(List<OrtakIsKalemleri> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (OrtakIsKalemleri kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(OrtakIsKalemleri_Co.ORTAK_IS_KALEMLERI_TABLE, null, line);
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
    public ContentValues ObjectToContentValues(OrtakIsKalemleri not) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(OrtakIsKalemleri_Co.c0_id_typ_long,not.getId());
            satir.put(OrtakIsKalemleri_Co.c2_isKalemTanim,not.getIsKalemTanim());
            satir.put(OrtakIsKalemleri_Co.c3_isKalemKategori,not.getIsKalemKategori());
            satir.put(OrtakIsKalemleri_Co.c4_ilgiliBirimId,not.getIlgiliBirimId());
            satir.put(OrtakIsKalemleri_Co.c5_kodu,not.getKodu());
            satir.put(OrtakIsKalemleri_Co.c6_olusturulmaTarihi,not.getOlusturulmaTarihi());
            satir.put(OrtakIsKalemleri_Co.c7_bitisTarihi,not.getBitisTarihi());
            satir.put(OrtakIsKalemleri_Co.c8_aktif,String.valueOf(not.getAktif()));
            satir.put(OrtakIsKalemleri_Co.c9_aciklama,not.getAciklama());
            satir.put(OrtakIsKalemleri_Co.c10_gunlemeZamani,not.getGunlemeZamani());
            satir.put(OrtakIsKalemleri_Co.c11_gunleyenId,not.getGunleyenId());
            satir.put(OrtakIsKalemleri_Co.c12_modulId,not.getModulId());
            satir.put(OrtakIsKalemleri_Co.c13_mid,not.getMid());
            satir.put(OrtakIsKalemleri_Co.c14_mustid,not.getMustid());



            Log.v("ortak kalem eklendi=>",not.getIsKalemTanim());

        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}
