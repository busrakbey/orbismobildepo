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

import ContractLayer.OrtakCo.MobilGuzergah_Co;
import EntityLayer.Ortak.MobilGuzergah;
import ToolLayer.OrbisDefaultException;

/**
 * Created by isahin on 19.1.2017.
 */

public class MobilGuzergah_Data extends DataController<MobilGuzergah> {
    public MobilGuzergah_Data(Context ctx) {
        super(ctx,new MobilGuzergah());
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
    public List<MobilGuzergah> loadFromQuery(String queryStr) throws OrbisDefaultException {
        List<MobilGuzergah> guzergah_List = new ArrayList<MobilGuzergah>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);

            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    guzergah_List.add((MobilGuzergah) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            db.close();
            return guzergah_List;
        }
    }
    public MobilGuzergah CursorToObject(Cursor cursor) throws OrbisDefaultException {
        MobilGuzergah nokta = new MobilGuzergah();
        nokta.setId(cursor.getLong(cursor.getColumnIndex("id")));
        nokta.setMid(cursor.getLong(cursor.getColumnIndex(MobilGuzergah_Co.c1_mid)));
        nokta.setMustid(cursor.getLong(cursor.getColumnIndex(MobilGuzergah_Co.c2_mustid)));
        nokta.setNot_mid(cursor.getLong(cursor.getColumnIndex(MobilGuzergah_Co.c3_not_mid)));
        nokta.setNOT_ID(cursor.getLong(cursor.getColumnIndex(MobilGuzergah_Co.c4_NOT_ID)));
        nokta.setX_KOOR(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c5_X_KOOR)));
        nokta.setY_KOOR(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c6_Y_KOOR)));
        nokta.setOBJECTID(cursor.getLong(cursor.getColumnIndex(MobilGuzergah_Co.c7_OBJECTID)));
       // nokta.setBIRIM_ID(cursor.getLong(cursor.getColumnIndex(MobilGuzergah_Co.c8_BIRIM_ID)));
      //  nokta.setACIKLAMA(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c9_ACIKLAMA)));
        nokta.setGLOBALID(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c10_GLOBALID)));
        nokta.setCREATED_USER(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c11_CREATED_USER)));
        nokta.setCREATED_DATE(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c12_CREATED_DATE)));
        nokta.setLAST_EDITED_USER(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c13_LAST_EDITED_USER)));
        nokta.setLAST_EDITED_DATE(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c14_LAST_EDITED_DATE)));
        nokta.setGonderildi(cursor.getInt(cursor.getColumnIndex(MobilGuzergah_Co.c15_gonderildi)));
        nokta.setGeotype(cursor.getInt(cursor.getColumnIndex(MobilGuzergah_Co.c16_geotype)));
        nokta.setUTM_X_KOOR(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c17_UTM_X_KOOR)));
        nokta.setUTM_Y_KOOR(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c18_UTM_Y_KOOR)));
        nokta.setSIRA_NO(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c20_SIRA_NO)));
        nokta.setTUL(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c21_TUL)));
        nokta.setYOL_DURUM_KOD(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c19_YOL_DURUM_KOD)));
        nokta.setYolBilgiId(cursor.getString(cursor.getColumnIndex(MobilGuzergah_Co.c22_yolBilgiId)));


        return nokta;
    }
    public Boolean insertFromContent(List<MobilGuzergah> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (MobilGuzergah kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(MobilGuzergah_Co.MOBIL_GUZERGAH_TABLE, null, line);
                if (m_id > 0)
                {
                    status = true;
                    kayit.setMid(m_id);
                } else {
                    status = false;
                    throw new OrbisDefaultException("DataController-insert:Kayit Eklenemedi, database tablosu hatalı !" + kayit.toString());
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



    public Boolean updateFromContent(List<MobilGuzergah> itms) throws OrbisDefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (MobilGuzergah kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                Log.v("1","1");
                line = ObjectToContentValues(kayit);
                Log.v("1","2");
                String tableName = MobilGuzergah_Co.MOBIL_GUZERGAH_TABLE;
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


    public ContentValues ObjectToContentValues(MobilGuzergah nokta) throws OrbisDefaultException {
        ContentValues satir = new ContentValues();
        try{

            satir.put(MobilGuzergah_Co.c0_id_typ_long, nokta.getMid());
            satir.put(MobilGuzergah_Co.c1_mid, nokta.getMid());
            satir.put(MobilGuzergah_Co.c2_mustid,nokta.getMustid());
            satir.put(MobilGuzergah_Co.c3_not_mid,nokta.getNot_mid());
            satir.put(MobilGuzergah_Co.c4_NOT_ID,nokta.getNOT_ID());
            satir.put(MobilGuzergah_Co.c5_X_KOOR,nokta.getX_KOOR());
            satir.put(MobilGuzergah_Co.c6_Y_KOOR,nokta.getY_KOOR());
            satir.put(MobilGuzergah_Co.c7_OBJECTID,nokta.getOBJECTID());
          //  satir.put(MobilGuzergah_Co.c8_BIRIM_ID,nokta.getBIRIM_ID());
          //  satir.put(MobilGuzergah_Co.c9_ACIKLAMA,nokta.getACIKLAMA());
            satir.put(MobilGuzergah_Co.c10_GLOBALID,nokta.getGLOBALID());
            satir.put(MobilGuzergah_Co.c11_CREATED_USER,nokta.getCREATED_USER());
            satir.put(MobilGuzergah_Co.c12_CREATED_DATE,nokta.getCREATED_DATE());
            satir.put(MobilGuzergah_Co.c13_LAST_EDITED_USER,nokta.getLAST_EDITED_USER());
            satir.put(MobilGuzergah_Co.c14_LAST_EDITED_DATE,nokta.getLAST_EDITED_DATE());
            satir.put(MobilGuzergah_Co.c15_gonderildi,nokta.getGonderildi());
            satir.put(MobilGuzergah_Co.c16_geotype,nokta.getGeotype());

            satir.put(MobilGuzergah_Co.c17_UTM_X_KOOR,nokta.getUTM_X_KOOR());
            satir.put(MobilGuzergah_Co.c18_UTM_Y_KOOR,nokta.getUTM_Y_KOOR());
            satir.put(MobilGuzergah_Co.c19_YOL_DURUM_KOD,nokta.getYOL_DURUM_KOD());
            satir.put(MobilGuzergah_Co.c20_SIRA_NO,nokta.getSIRA_NO());
            satir.put(MobilGuzergah_Co.c21_TUL,nokta.getTUL());
            satir.put(MobilGuzergah_Co.c22_yolBilgiId,nokta.getYolBilgiId());

            Log.v("guzergah verisi",nokta.getX_KOOR()+"-"+nokta.getY_KOOR());
        }
        catch (Exception e){
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }
}

