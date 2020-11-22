package AdapterLayer.Ortak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.konumsal.orbisozetmobil.R;

import java.util.ArrayList;
import java.util.List;

import EntityLayer.Ortak.BaseEntity;


/**
 * Created by Ömer YILDIRIM on 10.3.2016.
 */
public class DynamicPopupAdapter<T extends BaseEntity> extends ArrayAdapter<T> {

    Context context;
    int resource, textViewResourceId;
    List<T> items, tempItems, suggestions;

    public DynamicPopupAdapter(Context context, int resource, int textViewResourceId, List<T> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<T>(items); //
        suggestions = new ArrayList<T>();
    }

    @Override
    public int getPosition(T item) {
        return items.indexOf(item);
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mr_data_selector_list_iem, parent, false);
        }
        T _item = items.get(position);
        if (_item != null) {
            TextView lblName = (TextView) view.findViewById(R.id.mr_dataselectoritem_txt);
            if (lblName != null)
                lblName.setText(_item.toStringName());

        }
        return view;
    }
    protected void refreshResult(List<T> filteredList)
    {
        items.clear();
        if (filteredList.size()>0)
        {

            items.addAll(filteredList);
        }else {
            items.addAll(tempItems);
        }
        notifyDataSetChanged();
    }
    public void getFilteredList(CharSequence constraint)
    {
        if (constraint != null && constraint.toString().length()>0)
        {
            suggestions.clear();
            for (T _item : tempItems)
            {

                if (_item.toStringName().toLowerCase().startsWith(constraint.toString().toLowerCase()))
                {
                    suggestions.add(_item);
                }
            }
            if (suggestions!=null  && suggestions.size()>0)
            {
                refreshResult(suggestions);
            }
        }else
        {
            refreshResult(tempItems);
        }
    }
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     *
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((T) resultValue).toStringName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (T _item : tempItems) {

                    if (_item.toStringName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(_item);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<T> filterList = (ArrayList<T>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (T _item : filterList) {
                    add(_item);
                    notifyDataSetChanged();
                }
            }
        }
    };

}
