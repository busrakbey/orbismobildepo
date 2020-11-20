package EntityLayer.GeoPortal;

import java.util.ArrayList;
import java.util.List;

public class UpdateOperationResult
{
	public UpdateOperationResult()
	{
		updateResults = new ArrayList<OperationResult>();
	}

	public List<OperationResult>	updateResults;
	public Error error;

}
