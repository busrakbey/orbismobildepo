package com.konumsal.orbisozetmobil.OrtakUI;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.konumsal.orbisozetmobil.R;

import java.util.ArrayList;
import java.util.List;

import DataLayer.Ortak.OrtakAgacTuru_Data;
import EntityLayer.Ortak.OrtakAgacTuru;
import ToolLayer.OrbisDefaultException;

/**
* Created by Ömer YILDIRIM on 5.5.2016.
*/
@SuppressLint("ValidFragment")
public class FragmentDefaultTreeList extends DialogFragment {
   View m_view;

   Context context;
   ListView lstView;
    List<OrtakAgacTuru> agacTuruList;
   public FragmentDefaultTreeList(Context ctx) {
       context=ctx;

   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       m_view=inflater.inflate(R.layout.mr_dialog_default_treelist,container);
       lstView = (ListView)m_view.findViewById(R.id.mr_dia_def_treelist_listview);
       OrtakAgacTuru_Data ortakAgacTuru_data = new OrtakAgacTuru_Data(getActivity());
       agacTuruList = new ArrayList<OrtakAgacTuru>();
       try {
          // agacTuruList = ortakAgacTuru_data.list();
           agacTuruList= ortakAgacTuru_data.listAll();
          // treeListAdapter = new ortakagacturadp(getActivity(),agacTuruList);
           //lstView.setAdapter(treeListAdapter);
       } catch (OrbisDefaultException e) {
           e.printStackTrace();
           Log.e("OrtakAgacList",e.toString());
       }


       return m_view;
   }

   public void init()
   {

   }





}
