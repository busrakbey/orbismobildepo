package EntityLayer.ArcGIS;

import java.util.List;

import EntityLayer.GeoPortal.Error;
import EntityLayer.GeoPortal.Field;

/**
 * Created by Konumsal PC11 on 17.5.2016.
 */
public class ENKARAGACAGS {

    public String objectIdFieldName;
    public String globalIdFieldName;
    public String			displayFieldName;
    public List<Field> fields=null;
    public List<attributesEnkarAgac>	features=null;
    public Error error=null;
    public Boolean exceededTransferLimit=null;
}
