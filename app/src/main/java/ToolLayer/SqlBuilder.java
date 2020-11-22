package ToolLayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import AnnotationLayer.Column;
import AnnotationLayer.OneToMany;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;


/**
 * Created by OmerYildirim @KonumsalBilgi on 2.12.2015.
 */
public class SqlBuilder<T extends BaseEntity>
{
    public Cursor cursor=null;
    public T entity=null;
    public List<Field> fields=null;

    public SqlBuilder(T entity_)
    {
        entity=entity_;
        fields=getColumnFields(entity_);
    }
    public List<T> readFromCursor(Cursor crs) throws OrbisDefaultException {

        List<T> datalist = null;
       try {
            cursor =crs;
            datalist = new ArrayList<T>();
            while (cursor.moveToNext())
            {
                T mEntity = createNewEntity();
                fields = getColumnFields(mEntity);
                for (Field fd : fields)
                {
                    String fdName = fd.getName();
                    Type fdTYPE=  fd.getType();
                    Class tClass = fd.getType().getClass();
                    //kolon adı cursorda olmaz ise ?
                    //
                    setField(fd,cursor,mEntity);
                }
                boolean add;
                if (datalist.add(mEntity))
                {
                    add = true;
                }
                else add = false;
            }
        }
        catch (CursorIndexOutOfBoundsException e)
        {
            Log.d("SqlBldr_readcrs",e.getMessage());
            e.printStackTrace();
            throw  new OrbisDefaultException("SqlBuilder:readCursor->"+e.getMessage());
        }
        catch (OrbisDefaultException e) {
            Log.d("SqlBldr_readcrs",e.getMessage());
            e.printStackTrace();
            throw  new OrbisDefaultException("SqlBuilder:readCursor->"+e.getMessage());
        }
        catch (Throwable e)
        {
            Log.d("SqlBldr_readcrs",e.getMessage());
            e.printStackTrace();
            throw  new OrbisDefaultException("SqlBuilder:readCursor->"+e.getMessage());
        }

        return datalist;
    }

    public void setField(Field f,Cursor cur,T entity) throws OrbisDefaultException {
        try {
            f.setAccessible(true);

            Class<?> t = f.getType();
            String typeNAME= t.getName();
            String fieldNAME = f.getName();
            Boolean hasNullable = cur.isNull(cur.getColumnIndex(fieldNAME));
            if (!hasNullable)
            {
                if (t==Object.class)
                {
                    f.set(entity,cur.getString(cur.getColumnIndex(fieldNAME)));
                }
                else if(t == Double.class)
                {

                    f.set(entity,cur.getDouble(cur.getColumnIndexOrThrow(fieldNAME)));

                }else
                if(t == Float.class)
                {

                    f.set(entity,cur.getFloat(cur.getColumnIndexOrThrow(fieldNAME)));

                }
                else  if (t==String.class)
                {

                    f.set(entity,cur.getString(cur.getColumnIndex(fieldNAME)));
                }
                else if (t==Long.class)
                {
                    Long val =cur.getLong(cur.getColumnIndex(fieldNAME));
                    if (val!=null)
                    {
                        f.set(entity, cur.getLong(cur.getColumnIndex(fieldNAME)));
                    }
                }
                else if (t== Date.class)
                {
                    String dateStr = cur.getString(cur.getColumnIndexOrThrow(fieldNAME));
                    if (dateStr!=null)
                    {
                        Long dLong = Long.parseLong(dateStr);
                        if (dLong!=null)
                        {
                            Date dtL = new Date(dLong);
                            String dtLSTR = dtL.toString();
                            if (dtL!=null)
                                f.set(entity,dtL);
                        }
                    }

                }
                else if (t== Integer.class)
                {
                    f.set(entity,cur.getInt(cur.getColumnIndexOrThrow(fieldNAME)));
                }
                else if (t== Boolean.class)
                {
                    Integer _boolean = cur.getInt(cur.getColumnIndexOrThrow(fieldNAME));
                    Boolean boolVal = false;
                    if (_boolean > 0) {
                        boolVal = true;
                    }
                    f.set(entity, boolVal);

                }
                else if (t== BigDecimal.class)
                {
                    Double objVal =cur.getDouble(cur.getColumnIndexOrThrow(fieldNAME));
                    if (objVal!=null)
                    {
                        BigDecimal bd = BigDecimal.valueOf(objVal);
                        f.set(entity,bd);
                    }

                }
            }


        }

        catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:setField->"+e.getMessage());
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:setField->"+e.getMessage());
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:setField->"+e.getMessage());

        }


    }

    public T createNewEntity() throws OrbisDefaultException {
        Constructor[] ctors = new Constructor[0];
        T ent =null;
        try {
            ctors = entity.getClass().getDeclaredConstructors();
            Constructor ctor = null;
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }
            ctor.setAccessible(true);
             ent = (T)ctor.newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqblBuilder:createNewEntity->"+e.getMessage());
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
        }
        catch (Throwable e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
        }

        return ent;
    }

    public ContentValues createContentValuesFromEntity(T _entity) throws OrbisDefaultException {
        ContentValues satir  = new ContentValues();
        try {
            fields =getColumnFields(_entity);
            for (Field fiel:fields)
            {
                Class<?> t = fiel.getType();
                fiel.setAccessible(true);
                String columnName = fiel.getName();
                if (t==Object.class)
                {
                    satir.put(columnName,(String)fiel.get(_entity).toString());
                    //Log.v("bu bir object","_>"+_entity.toString());
                }
                if (t==String.class)
                {
                    satir.put(columnName,(String)fiel.get(_entity));
                   // Log.v("bu bir str","_>"+_entity.toString());
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
                    Object objVal= fiel.get(_entity);
                    if (objVal!=null)
                    {
                        Double dVal= Double.parseDouble(objVal.toString());
                        if (dVal!=null)
                        satir.put(columnName,dVal);
                    }

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
                        Long dLong =dat.getTime();
                        if (dLong!=null)
                        {
                            satir.put(columnName,dLong.toString());
                        }
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
        } catch (IllegalArgumentException e) {
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

    public String createTable(Boolean isAutoIncrement) throws OrbisDefaultException
    {
        String TableName="";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Table table = entity.getClass().getAnnotation(Table.class);
            stringBuilder.append("CREATE TABLE IF NOT EXISTS  ");
            if (table!=null)
            {
                TableName =table.name();
                stringBuilder.append(TableName);
                stringBuilder.append("(");
            }
            int count =0;
            for (Field m_field:fields)
            {
                Class<?> tip = m_field.getType();
                Column column = m_field.getAnnotation(Column.class);
                if (column!=null)
                {
                    String fieldName = m_field.getName();
                    stringBuilder.append(" "+fieldName);
                    stringBuilder.append(" ");
                    if (tip == String.class)
                    {
                        stringBuilder.append(" TEXT");
                    }
                    else
                        if (tip==Object.class)
                        {
                            stringBuilder.append(" TEXT");
                        }
                    else

                    if (tip==Long.class)
                    {
                        stringBuilder.append(" INTEGER");
                    }
                    else
                    if (tip==Integer.class)
                    {
                        stringBuilder.append(" INTEGER");
                    }
                    else
                    if (tip==Date.class)
                    {
                        stringBuilder.append(" TEXT");
                    }
                    else
                    if (tip==Boolean.class)
                    {
                        stringBuilder.append(" INTEGER");
                    }
                    else
                    if (tip==Double.class)
                    {
                        stringBuilder.append(" REAL");
                    }
                    else
                    if (tip==Float.class)
                    {
                        stringBuilder.append(" REAL");
                    }
                    else
                    if (tip==BigDecimal.class)
                    {
                        stringBuilder.append(" REAL");
                    }

                    if (m_field.getName().equals("mid"))
                    {
                        if (isAutoIncrement)
                        {
                            stringBuilder.append(" PRIMARY KEY AUTOINCREMENT");
                        }else
                        {
                            stringBuilder.append(" PRIMARY KEY");
                        }

                    }
                    stringBuilder.append(" ,");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(")");

        } catch (Exception e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:creatContentValuesFromEntity->"+e.getMessage());
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:creatContentValuesFromEntity->"+e.getMessage());
        }
        return stringBuilder.toString();
    }
    public String dropTable() throws OrbisDefaultException
    {
        StringBuilder builder = null;
        try {
            builder = new StringBuilder();
            builder.append("DROP  TABLE  IF EXISTS ");
            Table tablName = entity.getClass().getAnnotation(Table.class);
            if (tablName != null) {
                builder.append(tablName.name());
            }
            else
            {
                throw new OrbisDefaultException("Hata:Tablo adı alınamadı !");
            }
        } catch (OrbisDefaultException e) {
            e.printStackTrace();

        }
        catch (Exception e)
        {
            throw new OrbisDefaultException("Hata:Tablo adı alınamadı !"+e.getMessage());
        }
        catch (Throwable e)
        {
            throw new OrbisDefaultException("Hata:Tablo adı alınamadı !"+e.getMessage());
        }
        return builder.toString();
    }
    public String getTableName() throws OrbisDefaultException
    {
        String name= null;
        try {
            name = "";
            name = entity.getClass().getAnnotation(Table.class).name();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }
        return name;
    }
    public String[] getColumNames() throws OrbisDefaultException
    {

        String[] nameArray= new String[0];
        try {
            List<String> nameList = new ArrayList<String>();
            for (Field m_field:fields)
            {
                Class<?> tip = m_field.getType();
                Column column = m_field.getAnnotation(Column.class);
                if (column!=null)
                {
                  nameList.add(column.name());
                }
            }
            nameArray = new String[nameList.size()];
            for (int i=0;i<nameList.size();i++)
            {
                nameArray[i]=nameList.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:getColumnNames->"+e.getMessage());
        }
        return nameArray;
    }
    public String getSelectQueryViewColumns() throws OrbisDefaultException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        for (Field m_field:fields)
        {
            Class<?> tip = m_field.getType();
            Column column = m_field.getAnnotation(Column.class);
            if (column!=null)
            {
                    sb.append(m_field.getName());
                    sb.append(",");
            }
        }
        String name="";
        Table anno =entity.getClass().getAnnotation(Table.class);
        if (anno!=null)
        {
            name = anno.name();
            sb.deleteCharAt(sb.length()-1);
            sb.append("  FROM ");
            sb.append(name);
            return sb.toString();
        }
        else
        {
            return "";
        }


    }
    public ArrayList<String> getTableHeadersName()
    {
        ArrayList<String> nameList = new ArrayList<String>();
        for (Field m_field:fields)
        {
            Class<?> tip = m_field.getType();
            Column column = m_field.getAnnotation(Column.class);
            if (column!=null)
            {
                String definition ="";
                definition =column.columnDefinition();
                if (definition.trim().length()>1)
                {
                    nameList.add(column.columnDefinition());
                }

            }
        }
        return nameList;
    }

    public String selectQueryFindById(Long id) throws OrbisDefaultException
    {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("SELECT * FROM ");
        sbuilder.append(getTableName());
        sbuilder.append("  WHERE  id =");
        sbuilder.append(id);
        return sbuilder.toString();
    }
    public String selectQueryFindByMId(Long mid) throws OrbisDefaultException
    {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("SELECT * FROM ");
        sbuilder.append(getTableName());
        sbuilder.append("  WHERE  mid =");
        sbuilder.append(mid);
        return sbuilder.toString();
    }
    public String selectQueryFindByOrgId(Long orgid) throws OrbisDefaultException
    {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("SELECT * FROM ");
        sbuilder.append(getTableName());
        sbuilder.append("  WHERE  orgid =");
        sbuilder.append(orgid);
        return sbuilder.toString();
    }

    public <A extends BaseEntity> String getReleatedEntity() throws OrbisDefaultException {
       Field[] df =  entity.getClass().getDeclaredFields();
        for (Field fa: df)
        {
            OneToMany om = fa.getAnnotation(OneToMany.class);
            if (om!=null)
            {
                Class a = om.targetEntity();
                Constructor[] ctors = new Constructor[0];
                A ent =null;
                try {
                    Object asd=   a.newInstance();
                    Constructor[] ctooo= a.getDeclaredConstructors();
                    ctors =  a.getDeclaredConstructors();
                    Constructor ctor = null;
                    for (int i = 0; i < ctors.length; i++) {
                        ctor = ctors[i];
                        if (ctor.getGenericParameterTypes().length == 0)
                            break;
                    }
                    ctor.setAccessible(true);
                    ent = (A)ctor.newInstance();
                    SqlBuilder<A> bl = new SqlBuilder<A>(ent);
                    String name = bl.getTableName();
                    String cmd= bl.getTableName();
                }
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                    throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
                }
                catch (InvocationTargetException e)
                {
                    e.printStackTrace();
                    throw new OrbisDefaultException("SqblBuilder:createNewEntity->"+e.getMessage());
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                    throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
                }
                catch (Throwable e) {
                    e.printStackTrace();
                    throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
                }

            }
        }

        return "";

    }

    public List<Field> getColumnFields(T m_ent)
    {
        List<Field> lst = new ArrayList<Field>();
        if (m_ent!=null)
        {
            Field[] dec_f = m_ent.getClass().getDeclaredFields();
//            Field[] f_f =  m_ent.getClass().getFields();

            for (Field sd: dec_f)
            {
                    if (!lst.contains(sd))
                    {
                        lst.add(sd);
                    }
            }
//            for (Field sd: f_f)
//            {
//                if (!lst.contains(sd))
//                {
//                    lst.add(sd);
//                }
//            }
        }
        return lst;
    }
}
