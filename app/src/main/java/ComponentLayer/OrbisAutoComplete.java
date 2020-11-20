package ComponentLayer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;


import com.konumsal.orbisozetmobil.OrtakUI.DataSelectorPopup;
import com.konumsal.orbisozetmobil.R;

import java.util.List;

import DataLayer.Ortak.DataController;
import EntityLayer.Ortak.BaseEntity;


/**
 * Created by Ömer YILDIRIM on 10.3.2016.
 */
public  class OrbisAutoComplete<T extends DataController,K extends BaseEntity> extends TextView {
    public OrbisAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public OrbisAutoComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    private String entityName=null;
    private String dataName=null;
    private T dataclazz=null;
    private List<K> entityList=null;
    DataSelectorPopup dtselectorPop;
    OrbisAutoComplete aoc;
    private K selectedValue=null;
    private void init(Context context, AttributeSet attributeSet) {
        initAttributes(context, attributeSet);
        aoc=this;
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.OrbisAutoComplete, 0, 0);
        if (attrs != null) {
            try {
                String ads=attrs.getString(R.styleable.OrbisAutoComplete_entity_name);
                setEntityName(attrs.getString(R.styleable.OrbisAutoComplete_entity_name));
                setDataName(attrs.getString(R.styleable.OrbisAutoComplete_data_name));

            } finally {
                attrs.recycle();
            }
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled())
            return false;



        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                return true;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_CANCEL:

            case MotionEvent.ACTION_UP:

                processClick();
                return false;

            default:
                //setNormalBackground();
                return false;
        }
        return true;
    }
    private void processClick()
    {
        getdataDialog();
    }
    public void getdataDialog() {
        FragmentManager manager =((Activity)getContext()).getFragmentManager();
        dtselectorPop = new DataSelectorPopup<T,K>(getContext(),entityName,dataName,this);
        dtselectorPop.show(manager,"");
    }
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public T getDataclazz() {
        return dataclazz;
    }

    public void setDataclazz(T dataclazz) {
        this.dataclazz = dataclazz;
    }

    public List<K> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<K> entityList) {
        this.entityList = entityList;
    }

    public K getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(K selectedValue) {
        this.selectedValue = selectedValue;
    }
}
