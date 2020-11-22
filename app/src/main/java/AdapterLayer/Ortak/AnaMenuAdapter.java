package AdapterLayer.Ortak;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.konumsal.orbisozetmobil.R;

import java.util.List;


public class AnaMenuAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> drawerItemList;
    int layoutResID;
    int NameID;
    int fromMenu = 0;


    public AnaMenuAdapter(Context context, int layoutResourceID, List<String> listItems, int fromMenu) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
        this.fromMenu = fromMenu;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final String item = drawerItemList.get(position);
        final altMenuItemHolder drawerHolder;
        View view = convertView;
        TextView txtTitle;
        ImageView menu_img;
        Button btn_Sync;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        drawerHolder = new altMenuItemHolder();


        view = inflater.inflate(R.layout.ana_menu_list_item, parent, false);
        txtTitle = (TextView) view.findViewById(R.id.fire_alt_menu_list_item_text_title);
        menu_img = (ImageView) view.findViewById(R.id.fire_image);

        StringBuilder sbTitle = new StringBuilder();


        sbTitle.append(item + "\n");

        if (item != null)
            txtTitle.setText(sbTitle.toString());


        if (fromMenu == 0) {
            if (position == 0) {
                menu_img.setImageResource(R.drawable.agac_icon);
            } else if (position == 1) {
                menu_img.setImageResource(R.drawable.amenajman_64_ana_menu);
            } else if (position == 2) {
                menu_img.setImageResource(R.drawable.bilgi_sistem_icon);
            } else if (position == 3) {
                menu_img.setImageResource(R.drawable.destek_icon);
            } else if (position == 4) {
                menu_img.setImageResource(R.drawable.dis_iliskiler_icon);
            } else if (position == 5) {
                menu_img.setImageResource(R.drawable.icon_fidanlik_64);
            } else if (position == 6) {
                menu_img.setImageResource(R.drawable.ozm_64);
            } else if (position == 7) {
                menu_img.setImageResource(R.drawable.ip_ana_menu_64);
            } else if (position == 8) {
                menu_img.setImageResource(R.drawable.guzergah_64);
            } else if (position == 9) {
                menu_img.setImageResource(R.drawable.admin_64);
            } else if (position == 10) {
                menu_img.setImageResource(R.drawable.guzergah_64);
            } else if (position == 11) {
                menu_img.setImageResource(R.drawable.wood);
            } else if (position == 12) {
                menu_img.setImageResource(R.drawable.ip_ana_menu_64);
            } else if (position == 13) {
                menu_img.setImageResource(R.drawable.ozm_icon);
            } else if (position == 14) {
                menu_img.setImageResource(R.drawable.agac_icon);
            } else if (position == 15) {
                menu_img.setImageResource(R.drawable.fire);
            } else if (position == 16) {
                menu_img.setImageResource(R.drawable.admin_64);
            } else if (position == 17) {
                menu_img.setImageResource(R.drawable.agac_icon);
            } else if (position == 18) {
                menu_img.setImageResource(R.drawable.strateji_icon);
            } else if (position == 19) {
                menu_img.setImageResource(R.drawable.ip_ana_menu_64);
            }
        }
        return view;

    }


    private static class altMenuItemHolder {

        TextView txt_Title;
        TextView txt_bottomDef;
        TextView txt_RightDef;
        ImageView img_damga;
    }

}
