package ComponentLayer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;


import java.util.Calendar;
import java.util.Date;

import ToolLayer.DateUtils;

/**
 * Created by Ömer YILDIRIM on 9.3.2016.
 */
public class OrbisDatePicker extends TextView
{

    public OrbisDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();

    }

    public OrbisDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();

    }

//////////////////////////////////
   private static final String TAG = "OrbDtPicker";

    private static final float GESTURE_STEP_DP = 5.0f;

    private OnDateValueChangeListener mOnValueChangeListener;

    private AlertDialog datePickerDialog;
    private Drawable mbackDraw;
    private int mbackDrawResId;
    private Date mDate;
    private Boolean  mIsShowDatePickerDialog;
    int mYear ;
     int mMonth;
    int mDay ;
    private Date mvalue;
    private void Init()
    {
        mDate = new DateUtils().dateDate;
        setText(DateUtils.asShortDateString(mDate));
    }
    private void changeValue()
    {
        setText(DateUtils.asShortDateString(mDate));
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

    private void processClick() {

            getdateDialog();

    }
    public void showDatePicker() {
        if (datePickerDialog != null && datePickerDialog.isShowing())
            return;

        datePickerDialog = getdatePickerDialog();
        datePickerDialog.show();
    }
    private void notifyListener(Date newValue)
    {
        if (mOnValueChangeListener != null && mOnValueChangeListener.ondateValueChange(this, mDate, newValue)) {

            mDate = newValue;
        } else {

            //changeValue(mDate);
        }
    }
    private void getdateDialog()
    {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        mDate =newDate.getTime();
                        setTag(mDate);
                        setMvalue(mDate);
                        changeValue();
                    }
                }, mYear, mMonth, mDay);
        dpd.setTitle("Orbis Mobile");
        dpd.show();
    }
    private AlertDialog getdatePickerDialog() {
        final DatePicker dtPicker = new DatePicker(getContext());
        dtPicker.setLayoutParams(new DatePicker.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Tarih değerini seçiniz..");
       // builder.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_addwhite));

        builder.setView(dtPicker).setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int newValue = dtPicker.getDayOfMonth();
                        int newValue2 = dtPicker.getMonth();
                        int newValue3 = dtPicker.getYear();
                        mDate = DateUtils.getformattedDate(newValue3, newValue2, newValue);
                        changeValue();
                        //notifyListener(t);
                    }
                });

        return builder.create();
    }


    public Date getMvalue() {
        return mDate;
    }

    public void setMvalue(Date mvalue) {
        this.mvalue = mvalue;
        this.mDate =mvalue;
    }
}
