package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.konumsal.orbisozetmobil.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Test on 11.9.2015.
 */
public class DateSelectorPopup extends Dialog{
    private DatePicker dpResult;
    public int year,current_year;
    public int month,current_month;
    public int day,current_day;
    public String formattedDate;
    Button git,bugun,kapat;
    public int Result=0;

    public DateSelectorPopup(Context context, final EditText txtDate) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.layout_calendar_popup);

        dpResult = (DatePicker) findViewById(R.id.dpResult);
        git = (Button)findViewById(R.id.layout_calendar_popup_SaveBtn);
        bugun = (Button)findViewById(R.id.layout_calendar_popup_getDateBtn);


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        dpResult.init(year, month, day, null);

        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


        git.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try
                {
                    current_year=dpResult.getYear();
                    current_month=dpResult.getMonth();
                    current_day=dpResult.getDayOfMonth();
                    c.set(current_year, current_month, current_day);

                    formattedDate = df.format(c.getTime());
                    Result = 1;
                    txtDate.setText(formattedDate);

                }catch (Exception ex)
                {
                    Log.d("Hata DateSelector","Tarih secilemedi");
                }

                dismiss();
            }
        });

        bugun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dpResult.init(year, month, day, null);
            }
        });

    }

    public DateSelectorPopup(Context context, final AutoCompleteTextView txtDate) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.layout_calendar_popup);

        dpResult = (DatePicker) findViewById(R.id.dpResult);
        git = (Button)findViewById(R.id.layout_calendar_popup_SaveBtn);
        bugun = (Button)findViewById(R.id.layout_calendar_popup_getDateBtn);


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        dpResult.init(year, month, day, null);

        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


        git.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try
                {
                    current_year=dpResult.getYear();
                    current_month=dpResult.getMonth();
                    current_day=dpResult.getDayOfMonth();
                    c.set(current_year, current_month, current_day);
                    formattedDate = df.format(c.getTime());

                    Result = 1;
                    txtDate.setText(formattedDate);

                }catch (Exception ex)
                {
                    Log.d("Hata DateSelector","Tarih secilemedi");
                }

                dismiss();
            }
        });

        bugun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dpResult.init(year, month, day, null);
            }
        });

    }

    public void setDate(final EditText txtDate)
    {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        current_year=dpResult.getYear();
        current_month=dpResult.getMonth();
        current_day=dpResult.getDayOfMonth();
        c.set(current_year, current_month, current_day);
        formattedDate = df.format(c.getTime());
        txtDate.setText(formattedDate);

    }
    public  Date getDate_ddMMyyyy()throws ParseException
    {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        current_year=dpResult.getYear();
        current_month=dpResult.getMonth();
        current_day=dpResult.getDayOfMonth();
        c.set(current_year, current_month, current_day);
        formattedDate = df.format(c.getTime());

        return df.parse(formattedDate);
    }
    public void setDateForAutoComplete(final AutoCompleteTextView txtDate)
    {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        current_year=dpResult.getYear();
        current_month=dpResult.getMonth();
        current_day=dpResult.getDayOfMonth();
        c.set(current_year, current_month, current_day);
        formattedDate = df.format(c.getTime());
        txtDate.setText(formattedDate);
    }



}
