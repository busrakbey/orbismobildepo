package EntityLayer.GeoPortal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konumsal PC11 on 14.5.2016.
 */
public class DeleteOperationResult
{
    public List<DeleteResult> deleteResults;
    public DeleteOperationResult ()
    {
        deleteResults = new ArrayList<DeleteResult>();
    }
    public Error error;
}
