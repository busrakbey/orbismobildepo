package DataLayer.Ortak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;


import com.konumsal.orbisozetmobil.DbHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import EntityLayer.Ortak.BaseEntity;
import InterfaceLayer.IData;
import ToolLayer.OrbisDefaultException;
import ToolLayer.SqlBuilder;


/**
 * Created by Ömer YILDIRIM on 2.12.2015.
 */
public abstract class DataController<T extends BaseEntity> implements IData<T> {

    public DataController(Context ctx, T _entity) {
        this.setDataList(new ArrayList<T>());
        helper = DbHelper.getInstance(ctx);
        entity = _entity;
    }

    private ArrayList<T> dataList;
    public Context context;
    public SQLiteDatabase db;
    public DbHelper helper;
    public SqlBuilder<T> builder;
    public T entity;

    @Override
    public List<T> saveChange(List<T> data) throws OrbisDefaultException {
        for (T item : data)
        {
            if (item.getMid()!=null &&  item.getMid()>0)
            {
                //UPDATE
                dataList = new ArrayList<T>();
                dataList.add(item);
                Boolean sta= update();
                if (!sta)
                    throw new OrbisDefaultException("Hata:DataController->SaveChange to Update\nişlem gerçekleştirilemedi !");
                else
                    item = dataList.get(0);
            }
            else
            {
                // INSERT
                dataList = new ArrayList<T>();
                dataList.add(item);
               Boolean sta=  insert();
                if (!sta)
                    throw new OrbisDefaultException("Hata:DataController->SaveChange\nişlem gerçekleştirilemedi !");
                else
                    item = dataList.get(0);

            }
        }
        return data;
    }

    public ArrayList<T> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public T findById(Long id) throws OrbisDefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();

            String query = builder.selectQueryFindById(id);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            } else {
                Log.d("DataController:findById", "Hata:sorgu başarısız kayıt bulunamadı!");

            }
        } catch (OrbisDefaultException e) {
            Log.d("DataController:findById", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } finally {
            db.close();

        }
        if (dlist.size()>0)
        return dlist.get(0);
        else
            return null;
    }

    @Override
    public T findByMId(Long mid) throws OrbisDefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String query = builder.selectQueryFindByMId(mid);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            } else {
                Log.d("DataController:findById", "Hata:sorgu başarısız kayıt bulunamadı!");

            }
        } catch (OrbisDefaultException e) {
            Log.d("DataController:findById", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } finally {
            db.close();

        }
        if (dlist.size()>0)
            return dlist.get(0);
        else
            return null;
    }

    @Override
    public T findByOrgId(Long orgid) throws OrbisDefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String query = builder.selectQueryFindByOrgId(orgid);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            } else {
                Log.d("DataController:findById", "Hata:sorgu başarısız kayıt bulunamadı!");

            }
        } catch (OrbisDefaultException e) {
            Log.d("DataController:findById", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:findById->" + e.toString());
        } finally {
            db.close();

        }
        if (dlist.size()>0)
            return dlist.get(0);
        else
            return null;
    }

    @Override
    public Boolean insert() throws OrbisDefaultException {
        Long m_id = null;
        Boolean status = true;
        if (getDataList().size() > 0) {
            try {
                builder = new SqlBuilder<T>(createNewEntity(getDataList().get(0)));
                db = helper.getWritableDatabase();
                db.beginTransaction();
                for (T kayit : getDataList())
                {
                    long id = 0;
                    try {
                        ContentValues line = new ContentValues();
                        line = builder.createContentValuesFromEntity(kayit);
                        line.remove("mid");
                        String tableName = builder.getTableName();
                        m_id = db.insertOrThrow(tableName, null, line);
                        if (m_id > 0) {
                            status = true;
                            kayit.setMid(m_id);
                            Log.d("DataController", "Kayit eklendi mid:" + m_id + " -" + kayit.toString());
                        } else {
                            Log.d("DataController", "Kayıt ekleme başarısız !");
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
            } catch (OrbisDefaultException e) {
                e.printStackTrace();
                Log.d("DataController", e.getMessage());
                status = false;
                throw e;
            }

        } else {

            status = false;
            throw new OrbisDefaultException("DataController(insert)-Eklenecek kayıt bulunamadı ! Liste boş...");
        }

        return status;
    }

    @Override
    public ArrayList<T> list() throws OrbisDefaultException {
        ArrayList<T> dlist = new ArrayList<T>();
        try {
            getDataList().clear();
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String tableNAME = builder.getTableName();
            Cursor cursor = db.rawQuery("SELECT * FROM  " + builder.getTableName(), null);
            if (cursor.getCount() > 0) {
                dlist = (ArrayList<T>) builder.readFromCursor(cursor);
                cursor.close();
                getDataList().addAll(dlist);
            }
        } catch (OrbisDefaultException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());

        } finally {
            db.close();
            return getDataList();
        }
    }

    @Override
    public Boolean update() throws OrbisDefaultException {
        int m_id = 0;
        Boolean status = true;
        if (getDataList().size() > 0) {
            try {
                builder = new SqlBuilder<T>(createNewEntity(getDataList().get(0)));
                db = helper.getWritableDatabase();
                db.beginTransaction();
                for (T kayit : getDataList()) {
                    long id = 0;
                    try {
                        ContentValues line = new ContentValues();

                        line = builder.createContentValuesFromEntity(kayit);

                        String tableName = builder.getTableName();
                        Log.v("tabe name","_>"+tableName);
                        String[] whereParameters = {String.valueOf(kayit.getMid())};
                        String WhereArgs = "mid=?";
                        m_id = db.update(tableName, line, WhereArgs, whereParameters);

                        if (m_id > 0) {
                            status = true;
                            Log.d("DataController", "Kayit guncellendi id:" + m_id + " -" + kayit.toString());
                        } else {
                            Log.d("DataController", "Kayıt guncelleme başarısız !");
                            status = false;
                            throw new OrbisDefaultException("DataController-update:Kayit guncellenemedi !" + kayit.toString());
                        }


                    } catch (SQLiteConstraintException e) {
                        status = false;
                        Log.d("DataController", e.getMessage());
                        throw new OrbisDefaultException("DataController(update)Hata:" + e.getMessage());
                    } catch (SQLiteDatatypeMismatchException e) {
                        Log.d("DataController", e.getMessage());
                        status = false;
                        throw new OrbisDefaultException("DataController(update)Hata:" + e.getMessage());
                    } catch (SQLiteException e) {
                        Log.d("DataController", e.getMessage());
                        status = false;
                        throw new OrbisDefaultException("DataController(update)Hata:" + e.getMessage());
                    }


                }

            } catch (OrbisDefaultException e) {
                e.printStackTrace();
                Log.d("DataController", e.getMessage());
                status = false;
                throw e;
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            if(db.isOpen())
                db.close();

        } else {

            status = false;
            throw new OrbisDefaultException("DataController(update)-Guncellenecek kayıt bulunamadı ! Liste boş...");
        }


        return status;
    }

    @Override
    public Boolean delete() throws OrbisDefaultException {
        int m_id = 0;
        Boolean status = true;
        if (getDataList().size() > 0) {
            try {
                builder = new SqlBuilder<T>(createNewEntity(getDataList().get(0)));
                db = helper.getWritableDatabase();
                db.beginTransaction();
                for (T kayit : getDataList()) {
                    long id = 0;
                    try {
                        String[] whereParameters = {String.valueOf(kayit.getMid())};
                        String WhereArgs = "mid=?";
                        String tableName = builder.getTableName();
                        m_id = db.delete(tableName, WhereArgs, whereParameters);

                        if (m_id > 0) {
                            status = true;
                            Log.d("DataController", "Kayit silindi id:" + m_id + " -" + kayit.toString());
                        } else {
                            Log.d("DataController", "Kayıt silme başarısız !");
                            status = false;
                            throw new OrbisDefaultException("DataController-delete:Kayit silinemedi, database tablosu hatalı !" + kayit.toString()+kayit.getMid());
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


                }
                db.setTransactionSuccessful();
                db.endTransaction();
                db.close();
            } catch (OrbisDefaultException e) {
                e.printStackTrace();
                Log.d("DataController", "DataController(delete):" + e.getMessage());
                status = false;
                throw e;
            }

        } else {

            status = false;
            throw new OrbisDefaultException("DataController(delete)-Silinecek kayıt bulunamadı ! Liste boş...");
        }

        return status;
    }

    @Override
    public Boolean clearDatabaseTable() throws OrbisDefaultException {
        Boolean status = true;
        try {
            builder = new SqlBuilder<T>(entity);
            String tableName = builder.getTableName();
            db = helper.getWritableDatabase();
            db.beginTransaction();
            int deletedcount = 0;
//            deletedcount = db.delete(tableName, "1", null);
            db.execSQL("DELETE FROM " + builder.getTableName());
            Log.d(tableName, builder.getTableName()+" tablosunda  kayıtlar silindi.ID=>"+entity.getId());
        } catch (OrbisDefaultException e) {
            status = false;
            e.printStackTrace();
            throw e;
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
            throw new OrbisDefaultException("DataController:clearDataTable->" + e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            status = false;
            throw new OrbisDefaultException("DataController:clearDataTable->" + e.getMessage());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }







    public T createNewEntity(T entity) throws OrbisDefaultException {
        T ent = null;
        try {
            Constructor[] ctors = entity.getClass().getDeclaredConstructors();
            Constructor ctor = null;
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }
            ctor.setAccessible(true);
            ent = (T) ctor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("DataController:createNewEntity->" + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("DataController:createNewEntity->" + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("DataController:createNewEntity->" + e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("DataController:createNewEntity->" + e.getMessage());
        }
        return ent;
    }

    @Override
    public List<T> loadPassiveDataList() throws OrbisDefaultException {

        return null;
    }

    @Override
    public List<T> loadActiveDataList() throws OrbisDefaultException {
        return null;
    }

    @Override
    public List<T> getAllDataFromService(String url) throws OrbisDefaultException {

        return null;
    }

    @Override
    public List<T> listFromQuery(StringBuilder sb) throws OrbisDefaultException {
        dataList = new ArrayList<>();
        if (sb != null && sb.toString().trim().length() > 0) {

            try {
                getDataList().clear();
                db = helper.getReadableDatabase();
                Cursor crsr = db.rawQuery(sb.toString(), null);
                builder = new SqlBuilder<T>(entity);
                dataList.clear();
                if (crsr.getCount() > 0) {
                    dataList = (ArrayList<T>) builder.readFromCursor(crsr);
                    crsr.close();
                }
            }
            catch (OrbisDefaultException e) {
                Log.d("DataController:list", e.getMessage());
                throw new OrbisDefaultException("DataController:list->" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController:list", e.getMessage());
                throw new OrbisDefaultException("DataController:list->" + e.getMessage());
            } catch (Exception e) {
                Log.d("DataController:list", e.getMessage());
                throw new OrbisDefaultException("DataController:list->" + e.getMessage());
            } catch (Throwable e) {
                Log.d("DataController:list", e.getMessage());
                throw new OrbisDefaultException("DataController:list->" + e.getMessage());
            }
            db.close();
        }
        return getDataList();
    }

    @Override
    public Boolean reduce() throws OrbisDefaultException {
        int m_id = 0;
        Boolean status = true;
            try {
                builder = new SqlBuilder<T>(entity);
                db = helper.getWritableDatabase();
                db.beginTransaction();
                    long id = 0;
                    try {
                        String[] whereParameters = {String.valueOf(100)};
                        String WhereArgs = "id>=?";
                        String tableName = builder.getTableName();
                        m_id = db.delete(tableName, WhereArgs, whereParameters);

                        if (m_id > 0) {
                            status = true;
                            Log.d("DataController", "Kayit silindi id:" + m_id  );
                        } else {
                            Log.d("DataController", "Kayıt silme başarısız !");
                            status = false;
                            throw new OrbisDefaultException("DataController-insert:Kayit silinemedi, database tablosu hatalı !" );
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
                db.setTransactionSuccessful();
                db.endTransaction();
                db.close();
            } catch (OrbisDefaultException e) {
                e.printStackTrace();
                Log.d("DataController", "DataController(delete):" + e.getMessage());
                status = false;
                throw e;
            }

        return status;
    }

    @Override
    public Long getRecordCount(String strSql) throws OrbisDefaultException {
        Long count_=0L;
        try {
            builder = new SqlBuilder<T>(entity);
            db = helper.getReadableDatabase();
            String tableNAME = builder.getTableName();
            Cursor cursor = db.rawQuery(strSql, null);
            if (cursor.getCount() > 0) {
               count_=(long)cursor.getCount();
            }
        } catch (OrbisDefaultException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());
        } catch (SQLiteException e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());
        } catch (Exception e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());
        } catch (Throwable e) {
            Log.d("DataController:list", e.getMessage());
            throw new OrbisDefaultException("DataController:list->" + e.getMessage());

        } finally {
            db.close();
            return count_;
        }
    }


    @Override
    public Long getMaxIdOfProDetay(String strSql) throws OrbisDefaultException {

        Long max_= -1L;
        try {

            if(db == null)
                Log.v("db","null");
            else {
                if (db.isOpen())
                    Log.v("db", "opened");
                if (db.isDbLockedByCurrentThread())
                    Log.v("db", "lockedbycureetnthread");
            }


            db = helper.getReadableDatabase();

            Cursor cursor = db.rawQuery(strSql, null);
            if(cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                max_= cursor.getLong(cursor.getColumnIndex("max_"));
                cursor.close();
                Log.v("getmaxFunction","if");

            }
            else
            {
                Log.v("getmaxFunction","else");
            }

        }catch (Exception e){
            Log.v("getMaxId","throw exception");
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            db.close();
            return max_;
        }
    }


}


