package AdapterLayer.Ortak;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.konumsal.orbisozetmobil.R;

import java.util.List;

import EntityLayer.Ortak.OrtakKamera;

/**
 * Created by isahin on 7.3.2017.
 */
public class KameraListAdapter  extends ArrayAdapter<OrtakKamera> {

    Context context;

    public List<OrtakKamera> faaliyet_detay_tablo_list;
    int layoutResID;
    int NameID;
    private int[] colors = new int[] { 0x23755383, 0x22369620};
    private int[] colors2 = new int[] { 0x93755383, 0x10369620};
    View view;
    int mes_or_surutme_or_sevk;

    public KameraListAdapter(Context context, int layoutResourceID, List<OrtakKamera> listItems)
    {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.faaliyet_detay_tablo_list = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final OrtakKamera dItem = (OrtakKamera) this.faaliyet_detay_tablo_list.get(position);
        final DetayBilgiOzetItemHolder drawerHolder;
        view = convertView;


        if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DetayBilgiOzetItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.kamera_adi_item_text = (TextView) view.findViewById(R.id.kamera_adi_item_text);
            drawerHolder.kamera_url_item_text = (TextView) view.findViewById(R.id.kamera_url_item_text);

            view.setTag(drawerHolder);

        } else
        {
            drawerHolder = (DetayBilgiOzetItemHolder) view.getTag();
        }



        if(dItem.getKameraAdi() != null)
            drawerHolder.kamera_adi_item_text.setText(dItem.getKameraAdi());
        else
            drawerHolder.kamera_adi_item_text.setText("");


        if(dItem.getKameraUrl() != null)
            drawerHolder.kamera_url_item_text.setText(dItem.getKameraUrl());
        else
            drawerHolder.kamera_url_item_text.setText("");


        return view;
    }



    private static class DetayBilgiOzetItemHolder
    {
        TextView kamera_adi_item_text;
        TextView kamera_url_item_text;




    }
}

