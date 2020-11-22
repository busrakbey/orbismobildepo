package ComponentLayer;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import ToolLayer.DateUtils;

/**
 * Created by Ömer YILDIRIM on 9.3.2016.
 */
public class OrbisTimePicker extends TextView {
    public OrbisTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OrbisTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private static final String TAG = "OrbDtPicker";

    private static final float GESTURE_STEP_DP = 5.0f;

    private OnDateValueChangeListener mOnValueChangeListener;

    private AlertDialog datePickerDialog;
    private Drawable mbackDraw;
    private int mbackDrawResId;
    private Date mDate;
    private Boolean  mIsShowDatePickerDialog;
    int mhour ;
    int mMinute;
    int mSecond ;


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

        gettimeDialog();

    }


    private void gettimeDialog()
    {
        final Calendar c = Calendar.getInstance();

         mMinute=  c.get(Calendar.MINUTE);
        mhour = c.get(Calendar.HOUR_OF_DAY);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                StringBuilder sb = new StringBuilder();
                sb.append(hourOfDay+":"+minute);
               setText(sb.toString());
            }
        }, mhour,mMinute,true);

        timePickerDialog.show();
    }

}
