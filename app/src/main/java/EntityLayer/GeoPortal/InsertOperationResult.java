package EntityLayer.GeoPortal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konumsal PC11 on 28.4.2016.
 */
public class InsertOperationResult
{
    public InsertOperationResult()
    {
        addResults = new ArrayList<AddResult>();
    }
    public List<AddResult> addResults;
    public Error error;
}
