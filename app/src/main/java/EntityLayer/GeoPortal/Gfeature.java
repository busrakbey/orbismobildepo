package EntityLayer.GeoPortal;

import java.util.HashMap;
import java.util.Map;

public class Gfeature
{
    public Gfeature()
    {
        attributes = new HashMap<String,Object>();
    }
    public Map<String, Object> attributes;
    public Geometry geometry;
}
