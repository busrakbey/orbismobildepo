package ToolLayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.konumsal.orbisozetmobil.OrtakUI.Home2Activity;
import com.konumsal.orbisozetmobil.R;

import ComponentLayer.OrbisCustomDialog;

/**
 * Created by Ömer YILDIRIM on 9.09.2015.
 */
public final class MessageBox
{
     static AlertDialog adia=null;
    public static   void showAlert(final Context context,String message, final boolean close)
    {

        try {
            OrbisCustomDialog builder = new OrbisCustomDialog(context, message);
            AlertDialog aldia = builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (close)
                    {
                        Intent imtt= new Intent(context,Home2Activity.class);
                        context.startActivity(imtt);

                    }
                    dialog.dismiss();
                }
            }).create();
            aldia.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {

                    Button positiveButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);


                    final Drawable positiveButtonDrawable = context.getResources().getDrawable(R.drawable.mr_custd_button_selector);
                    if (Build.VERSION.SDK_INT >= 16) {
                        positiveButton.setBackground(positiveButtonDrawable);
                    } else {
                        positiveButton.setBackgroundDrawable(positiveButtonDrawable);
                    }

                    positiveButton.invalidate();

                    positiveButton.setTextSize(30);
                    positiveButton.setTextColor(Color.WHITE);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    positiveButton.setLayoutParams(params);
                }
            });

            aldia.show();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public static   void showToast(Context context,String toastMessage)
    {
        Toast.makeText(context,"Orbis Sistem Mesaj: \n"+toastMessage,Toast.LENGTH_LONG).show();
    }
}
