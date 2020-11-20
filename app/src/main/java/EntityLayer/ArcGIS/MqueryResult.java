package EntityLayer.ArcGIS;

import java.util.List;

import EntityLayer.GeoPortal.Error;
import EntityLayer.GeoPortal.Field;
import EntityLayer.GeoPortal.Mfeature;

/**
 * Created by Ömer YILDIRIM on 16.5.2016.
 */
public class MqueryResult
{

    public String objectIdFieldName;
    public String globalIdFieldName;
    public String			displayFieldName;
    public List<Field> fields;
    public List<Mfeature>	features;
    public Error error;
    public Boolean exceededTransferLimit;
}
