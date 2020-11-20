package InterfaceLayer;

import java.util.List;

import EntityLayer.Ortak.BaseEntity;
import ToolLayer.OrbisDefaultException;

/**
 * Created by OmerYildirim @KonumsalBilgi on 2.12.2015.
 */
public interface IData<T extends BaseEntity>
{

    public List<T> list() throws OrbisDefaultException;


    public Boolean insert() throws OrbisDefaultException;
    public Boolean update() throws OrbisDefaultException;
    public Boolean delete() throws OrbisDefaultException;
    public T findById(Long id) throws OrbisDefaultException;
    public T findByMId(Long id) throws OrbisDefaultException;
    public T findByOrgId(Long id) throws OrbisDefaultException;
    public List<T> saveChange(List<T> data) throws OrbisDefaultException;
    public List<T> loadActiveDataList() throws OrbisDefaultException;
    public List<T> loadPassiveDataList() throws OrbisDefaultException;
    public List<T> getAllDataFromService(String url) throws OrbisDefaultException;
    public Boolean clearDatabaseTable() throws OrbisDefaultException;
    public List<T> listFromQuery(StringBuilder sb) throws OrbisDefaultException;
    public Boolean reduce() throws OrbisDefaultException;
    public Long getRecordCount(String strSql) throws OrbisDefaultException;
    public Long getMaxIdOfProDetay(String strSql) throws OrbisDefaultException;
}
