package EntityLayer.GeoPortal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Konumsal PC11 on 28.4.2016.
 */
public class Error
{
    public int code;
    public String description;
    public String message;
    public List<Object> details;
    public Error()
    {
        details = new ArrayList<Object>();
    }
}
