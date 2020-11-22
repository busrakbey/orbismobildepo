package EntityLayer.GeoPortal;

import java.util.ArrayList;
import java.util.List;

import EntityLayer.Ortak.BaseEntity;

public class QueryResult<T extends BaseEntity>
{
	//public List<T>		features;
	public String objectIdFieldName;
	public String globalIdFieldName;
	public String			displayFieldName;
	public List<Field>		fields;
	public List<Feature<T>>	features;
	public Error error;
	public List<T> getList()
	{
		List<T> dtl = new ArrayList<T>();
		for (Feature ftr: features)
		{
			dtl.add((T)ftr.attributes);
			dtl.add((T)ftr.geometry);
		}
		return dtl;
	}

}
