package EntityLayer.ArcGIS;

import java.util.List;

import EntityLayer.GeoPortal.Error;
import EntityLayer.GeoPortal.Field;
import EntityLayer.GeoPortal.ORNEK_ALAN;

/**
 * Created by Konumsal PC11 on 17.5.2016.
 */
public class ORNEKALANAGS  {
    public String objectIdFieldName;
    public String globalIdFieldName;
    public String			displayFieldName;
    public List<Field> fields=null;
    public List<attributes>	features=null;
    public Error error=null;
    public Boolean exceededTransferLimit=null;
}
