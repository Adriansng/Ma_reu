package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DatePickerFragment;
import mareu.adriansng.maru.ui_reunion_list.utils.SpinnerMeetingRoomAdapter;
import mareu.adriansng.maru.ui_reunion_list.utils.TimerPickerFragment;

public class AddReunionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    //Data
    private ReunionApiService mApiService;
    private ArrayList<MeetingRoom> mMeetingRoom;
    //Parameter Reunion
    private MeetingRoom selectionRoom;
    private String date;
    private String hour;
    private TextView textViewDate;
    private TextView textViewTimes;
    private String minuteString;
    private Spinner mRoomReunion;
    private int intHour;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion_activity);
        mApiService = DI.getReunionApiService();
        //Name Organizer
        EditText editNameOrganizer= findViewById(R.id.name_organizer);
        String nameOrganizer= editNameOrganizer.getText().toString();

        // Date
        Button buttonDate=  findViewById(R.id.date_add);
        textViewDate=findViewById(R.id.view_date_add);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetList();
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        // Hour
        Button buttonHour= findViewById(R.id.hour_add);
        textViewTimes=findViewById(R.id.view_hour_add);
        buttonHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetList();
                DialogFragment hourPicker= new TimerPickerFragment();
                hourPicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        //Spinner
        mRoomReunion = findViewById(R.id.roomReunion);
        mRoomReunion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionRoom = mApiService.getMeetingRoom().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Validate reunion
        ImageButton finishButton= findViewById(R.id.validate_btn);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reunion reunion= new Reunion(mApiService.getReunionSize()+1,selectionRoom.getId(), nameOrganizer, hour, date , mApiService.getPersonParticipant());
                mApiService.addReunion(reunion);
                finish();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date= DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());
        textViewDate.setText(date);
        configSpinner();

    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(minute<10){
            minuteString="0"+minute;
        }else{
           minuteString= String.valueOf(minute);

        }
        intHour = hourOfDay;
        textViewTimes.setText(hourOfDay+"H"+minuteString);
        hour=textViewTimes.getText().toString();
        configSpinner();
    }

    private void configSpinner(){
        if (textViewDate.getText().toString().equals(date) && textViewTimes.getText().toString().equals(hour)) {
            mRoomReunion.setVisibility(View.VISIBLE);
            initList();
            SpinnerMeetingRoomAdapter mAdapter = new SpinnerMeetingRoomAdapter(this, mMeetingRoom);
            mRoomReunion.setAdapter(mAdapter);
        } else {
            mRoomReunion.setVisibility(View.GONE);
        }
    }

    private void initList() {
        mMeetingRoom = new ArrayList<>();
        for(int minute = Integer.parseInt(minuteString), hourOfDay=intHour; minute>=+45;minute++){
            if (minute == 60 && hourOfDay!=23) {
                hourOfDay = intHour++;
                minute=0;
            }
            hour=hourOfDay+"H"+minute;
            mApiService.getAvailabilityMeetingRoom(date,hour);
        }
        for(int minute = Integer.parseInt(minuteString), hourOfDay= intHour; minute>=+45; minute--){
            if (minute < 0 && hourOfDay !=0) {
                hourOfDay = intHour--;
                minute=59;
            }
            hour = hourOfDay + "H" + minute;
            mApiService.getAvailabilityMeetingRoom(date, hour);
        }
        mApiService.getAvailabilityMeetingRoom(date,hour);
        mMeetingRoom.addAll(mApiService.getInitListSpinnerRoomAvailability());
    }

    private void resetList(){
        mMeetingRoom= new ArrayList<>();
        mApiService.getResetAvailabilityMeetingRoom();
        mMeetingRoom.addAll(mApiService.getInitListSpinnerRoomAvailability());
    }
}
