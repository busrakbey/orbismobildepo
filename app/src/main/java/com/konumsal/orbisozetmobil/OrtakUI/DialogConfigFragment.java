package com.konumsal.orbisozetmobil.OrtakUI;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.konumsal.orbisozetmobil.R;

import ToolLayer.MessageBox;

/**
 * Created by Konumsal PC11 on 9.12.2015.
 */
@SuppressLint("ValidFragment")
public class DialogConfigFragment extends DialogFragment
{
    View m_view;
    EditText url;
    Button exitBtn,btnKAydet;
    serviceUrlListener listener;
    Context context;
    String uls=null;
    public DialogConfigFragment(Context ctx, String urls) {
        context=ctx;
        uls = urls;
    }
    String urlStr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_view=inflater.inflate(R.layout.layout_dialog_config,container);
        setCancelable(true);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getDialog().getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        lp.height = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getDialog().getWindow().setAttributes(lp);
        urlStr="";
        init();
        return m_view;
    }

    public void init()
    {
        url =(EditText)m_view.findViewById(R.id.dialog_config_SERVICEURL);
        url.setText("http://");
        if (uls!=null)
            url.setText(uls);

        btnKAydet =(Button)m_view.findViewById(R.id.dialog_config_kaydetButton);
        btnKAydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url.getText().toString().trim().length()>10)
                {
                    urlStr =url.getText().toString();
                    listener.getServiceUrl(urlStr,0);

                }
                else
                {
                    MessageBox.showAlert(context, "Url bilgisi girilmeli !", false);
                }
                dismiss();
                dismiss();

            }
        });
        exitBtn =(Button)m_view.findViewById(R.id.dialog_config_ExitButton);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                dismiss();
            }
        });

    }
    public  interface serviceUrlListener
    {
        public void getServiceUrl(String url, int mod);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener=(serviceUrlListener)activity;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dialog.cancel();
    }
}
