package EntityLayer.GeoPortal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by isahin on 20.1.2017.
 */

public class GfeatureWithList
{
    public GfeatureWithList()
    {
        attributes = new HashMap<String,Object>();
        geometry = new HashMap<String,List<List<Double[]>>>();
    }
    public Map<String, Object> attributes;
    public Map<String, List< List<Double[]>>> geometry;
   // public
}
