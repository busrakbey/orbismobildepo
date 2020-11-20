package AdapterLayer.Ortak;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.konumsal.orbisozetmobil.R;

import java.util.List;

import DataLayer.Ortak.OrtakAgacTuru_Data;
import EntityLayer.Ortak.OrtakAgacTuru;
import ToolLayer.MessageBox;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Ömer YILDIRIM on 5.5.2016.
 */
public class DefaultTreeListAdapter extends ArrayAdapter<OrtakAgacTuru> {
    public DefaultTreeListAdapter(Context context, int resource, List<OrtakAgacTuru> objects) {
        super(context, resource, objects);
        mcontext = context;
    }

    Context mcontext;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final OrtakAgacTuru item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mr_dia_def_treelist_item, parent, false);

            viewHolder.txt_Title = (TextView) convertView.findViewById(R.id.mr_diadeftree_item_Title_txt);
            viewHolder.btnImage=(ImageView)convertView.findViewById(R.id.mr_diadeftree_item_imgRight);
            viewHolder.btn_addRemove=(Button)convertView.findViewById(R.id.mr_diadeftree_item_addRemoveBtn);

            if (item.getEnabled()==1)
            {
                viewHolder.btn_addRemove.setText("Kaldır");
                //viewHolder.btn_addRemove.setBackground(mcontext.getResources().getDrawable(R.drawable.mf_greenborder_backtrans_drw));
              //  viewHolder.btn_addRemove.setTextColor(mcontext.getResources().getColor(R.color.redtrans));
            }else
            {
                //viewHolder.btn_addRemove.setTextColor(mcontext.getResources().getColor(R.color.mr_haki));
                viewHolder.btn_addRemove.setText("Ekle");
            }
            viewHolder.btn_addRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (item.getEnabled()==0) {
                        item.setEnabled(1);
                        ((Button)v).setText("Kaldır");
                        //((Button)v).setTextColor(mcontext.getResources().getColor(R.color.redtrans));
                    }
                    else
                    {
                        item.setEnabled(0);
                        ((Button)v).setText("Ekle");
                    }
                    updateDate(item);
                    notifyDataSetChanged();

                }
            });
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StringBuilder sbTitle= new StringBuilder();
        if (item.getAgacAdi()!=null)
        {
            sbTitle.append(item.getAgacAdi());
        }
        viewHolder.txt_Title.setText(sbTitle.toString());
        return convertView;
    }

    private class ViewHolder {
        TextView txt_Title;
        Button btn_addRemove;
        ImageView btnImage;
    }

    public void updateDate(OrtakAgacTuru item)
    {
        OrtakAgacTuru_Data ortakAgacTuru_data = new OrtakAgacTuru_Data(mcontext);
        ortakAgacTuru_data.getDataList().add(item);
        try {
            Boolean son=   ortakAgacTuru_data.update();

        } catch (OrbisDefaultException e) {
            e.printStackTrace();
            Log.e("TreeAdpt", e.toString());
            MessageBox.showToast(getContext(), e.toString()+"\nişlem  başarısız, bir terslik oluştu ..");
        }
    }
}
