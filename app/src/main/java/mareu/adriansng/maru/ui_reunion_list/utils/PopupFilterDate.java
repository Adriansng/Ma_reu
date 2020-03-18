package mareu.adriansng.maru.ui_reunion_list.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

import mareu.adriansng.maru.R;

/**
 * Created by Adrian SENEGAS 18/03/2020.
 */
public class PopupFilterDate extends Dialog implements DatePickerDialog.OnDateSetListener {

    private String date;
    private Button buttonDate;
    private DialogFragment datePicker;


    public PopupFilterDate(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_filter_date);
        this.buttonDate=  findViewById(R.id.date);
        buttonDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
            }
        });
    }

    public DialogFragment getDatePicker() {
        return datePicker;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textViewDate=findViewById(R.id.view_hour_date_filter);
        textViewDate.setText(currentDateString);
        String date= textViewDate.getText().toString();
    }

    public String getDateSelection(){
        return date;
    }

    public void build(){
        show();
    }

}

