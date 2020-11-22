package AdapterLayer.Ortak;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.konumsal.orbisozetmobil.R;

import java.util.List;

import EntityLayer.Ortak.OrtakOdunTuru;


/**
 * Created by busrakbey 29.01.2020.
 */
public class TreeListOrtakOdunAdapter extends ArrayAdapter<OrtakOdunTuru> {
    public TreeListOrtakOdunAdapter(Context context, int resource, List<OrtakOdunTuru> objects) {
        super(context, resource, objects);
        mcontext =context;
    }
    Context mcontext;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrtakOdunTuru item = getItem(position);
        //Random rnd = new Random();
        //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.treelist_item_layout,parent,false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.treelist_item_txtName);

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //  convertView.setBackgroundColor(color);

        if (item.getEnabled() ==1)
        {
            viewHolder.txtName.setTextColor(Color.GREEN);
            viewHolder.txtName.setBackgroundColor(Color.GRAY);
            convertView.setBackgroundColor(Color.GRAY);
            viewHolder.txtName.setText(item.toStringName()+ "\n Kaldır");

        }else
        {
            viewHolder.txtName.setTextColor(Color.BLACK);
            viewHolder.txtName.setBackgroundColor(Color.TRANSPARENT);
            convertView.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.txtName.setText(item.toStringName()+ "\n Ekle");
        }
        return convertView;
    }

    private static class ViewHolder
    {
        public TextView txtName;
        public TextView txtTitle;
        public int capdeger;

    }


}