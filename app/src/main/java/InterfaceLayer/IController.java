package InterfaceLayer;

import java.util.List;

import EntityLayer.Ortak.BaseEntity;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Ömer YILDIRIM on 15.2.2016.
 */
public interface IController<T extends BaseEntity> {


    public void sync(T ent)throws OrbisDefaultException;
    public void edit(T ent)throws OrbisDefaultException;
    public Boolean create(T ent)throws OrbisDefaultException;
    public void delete(T ent)throws OrbisDefaultException;
    public T getModelByMid(Long mId)throws OrbisDefaultException;
    public T getModelById(Long id_)throws OrbisDefaultException;
    public void setModel(T ent)throws OrbisDefaultException;
    public List<T> getModelList()throws OrbisDefaultException;
    public void  setModelList(List<T> mdllist)throws OrbisDefaultException;

}
