package DataLayer.Ortak;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import InterfaceLayer.IEntityView;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Konumsal PC11 on 14.1.2016.
 */
public class
        SqlHelper<T extends IEntityView>
{
    public Cursor cursor=null;
    public T entity=null;
    public Field[] fields=null;
    public ContentValues createContentValuesFromEntity(IEntityView _entity) throws OrbisDefaultException {
        ContentValues satir  = new ContentValues();
        try {

            entity =(T) IEntityView.class.newInstance();
            Constructor[] sot1=   entity.getClass().getDeclaredConstructors();
            Constructor[] sot= entity.getClass().getConstructors();
        } catch (InstantiationException e) {
             e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            for (Field fiel:_entity.getClass().getDeclaredFields())
            {
                Class<?> t = fiel.getType();
                fiel.setAccessible(true);
                String columnName = fiel.getName();
                if (t==String.class)
                {
                    satir.put(columnName,(String)fiel.get(_entity));
                }
                else if (t==Long.class)
                {
                    satir.put(columnName,(Long)fiel.get(_entity));
                }
                else if (t==Double.class)
                {
                    satir.put(columnName,(Double)fiel.get(_entity));
                }
                else if (t==Float.class)
                {
                    satir.put(columnName,(Double)fiel.get(_entity));
                }
                else if (t==BigDecimal.class)
                {
                    satir.put(columnName,(Double)fiel.get(_entity));
                }
                else if (t==Boolean.class)
                {
                    Boolean val = (Boolean)fiel.get(_entity);
                    if (val!=null)
                    {
                        if (val) satir.put(columnName, 1);
                        else satir.put(columnName, 0);
                    }
                }
                else if (t==Date.class)
                {
                    Date dat = (Date)fiel.get(_entity);
                    if (dat!=null)
                    {
                        satir.put(columnName,dat.toString());
                    }
                }
                else if (t==Integer.class)
                {
                    satir.put(columnName,(Integer)fiel.get(_entity));
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:creatContentValuesFromEntity->"+e.getMessage());
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:creatContentValuesFromEntity->"+e.getMessage());
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:creatContentValuesFromEntity->"+e.getMessage());
        }

        finally {
            return satir;
        }
    }




}
