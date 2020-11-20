package com.konumsal.orbisozetmobil.OrtakUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.konumsal.orbisozetmobil.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


import AdapterLayer.Ortak.DynamicPopupAdapter;
import ComponentLayer.OrbisAutoComplete;

import DataLayer.Ortak.DataController;
import EntityLayer.Ortak.BaseEntity;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Ömer YILDIRIM on 10.3.2016.
 */
@SuppressLint("ValidFragment")
public class DataSelectorPopup<T extends DataController,K extends BaseEntity> extends DialogFragment {
    View m_view;
    EditText url;
    Context context;
    ListView lstView;
    String dataName;
    String entNAme;
    T datasClass;
    K selectedItem;
    List<K> enttyList;
    DynamicPopupAdapter<K> adapter;
    K enttty;
    OrbisAutoComplete mcomponent;
    Button btnvazcay,mrdataselectoryeniagacturubutton;//btnClear

    EditText edtfilter;
    public DataSelectorPopup(Context ctx, String entyName, String dataNamee, OrbisAutoComplete componentm) {
        context=ctx;
        entNAme =entyName;
        dataName=dataNamee;
        mcomponent=componentm;
    }
    String urlStr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_view=inflater.inflate(R.layout.mr_data_selector_popup_layout,container);
        lstView = (ListView)m_view.findViewById(R.id.mr_dataselectorpopup_listview);

        btnvazcay =(Button)m_view.findViewById(R.id.mrdataselectorvazgecbutton);
       // btnClear =(Button)m_view.findViewById(R.id.mrdataselectortemizlebutton);
        edtfilter =(EditText)m_view.findViewById(R.id.mr_dataselectoritem_filter);
        mrdataselectoryeniagacturubutton =(Button)m_view.findViewById(R.id.mrdataselectoryeniagacturubutton);

        edtfilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (adapter!=null)
                {
                    adapter.getFilteredList(s);
                }
            }
        });
     /*  btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcomponent.setText("");
                selectedItem =null;
                mcomponent.setSelectedValue(null);
                mcomponent.setTag(null);
                //dismiss();
            }
        });*/
        btnvazcay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
            try {
                createNewData();
                lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        K selected = enttyList.get(position);

                        mcomponent.setTag(selected);
                        mcomponent.setSelectedValue(selected);
                        mcomponent.setText(selected.toStringName());
                        dismiss();
                    }
                });
            } catch (OrbisDefaultException e) {
                e.printStackTrace();
            }
        mrdataselectoryeniagacturubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent mint = new Intent(context,OrtakAgacActivity.class);
                mint.putExtra("MODE",2);
                mint.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mint);
                dismiss();
            }
        });


        return m_view;
    }

    public void init()
    {

    }
    public  interface onSelectedValueListener
    {
        public void getServiceUrl(String url, int mod);

    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dialog.cancel();
    }
    public T createNewData() throws OrbisDefaultException {

        try {
            Constructor ctor = null;
            Class[] ctorArgs1 = new Class[1];
            ctorArgs1[0] = Context.class;
            Class<?> dtClass=Class.forName(dataName);
            ctor = dtClass.getDeclaredConstructor(ctorArgs1);
            ctor.setAccessible(true);
            datasClass = (T)ctor.newInstance(context);
            enttyList= datasClass.list();

            Log.v("ortak ağaç sayısı","=>"+enttyList.size());
            if(enttyList.size() == 0)
            {
                mrdataselectoryeniagacturubutton.setText("Ağaç Türü Tanımları(Tıkla)");
                Animation shake = AnimationUtils.loadAnimation(context, R.anim.shakeanim);
                mrdataselectoryeniagacturubutton.startAnimation(shake);
            }

            adapter = new DynamicPopupAdapter<K>(context,R.layout.mr_data_selector_popup_layout,R.layout.mr_simple_spinner_dropdown_item,enttyList);
            lstView.setAdapter(adapter);
        }
        catch (java.lang.InstantiationException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqblBuilder:createNewEntity->"+e.getMessage());
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
        }
        catch (Throwable e) {
            e.printStackTrace();
            throw new OrbisDefaultException("SqlBuilder:createNewEntity->"+e.getMessage());
        }

        return datasClass;
    }

}
