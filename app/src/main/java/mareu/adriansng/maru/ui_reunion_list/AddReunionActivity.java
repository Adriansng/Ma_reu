package mareu.adriansng.maru.ui_reunion_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
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
import mareu.adriansng.maru.service_api.DummyReunionList;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DatePickerFragment;
import mareu.adriansng.maru.ui_reunion_list.utils.SpinnerMeetingRoomAdapter;
import mareu.adriansng.maru.ui_reunion_list.utils.TimerPickerFragment;

public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ReunionApiService mApiService;
    private Spinner mRoomReunion;
    private DummyReunionList mDummyReunionList;
    private SpinnerMeetingRoomAdapter mAdapter;
    private ArrayList<MeetingRoom> mMeetingRoom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion_activity);
        mApiService = DI.getReunionApiService();
        mDummyReunionList = new DummyReunionList();

        // Date
        Button buttonDate=  findViewById(R.id.date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        // Hour
        Button buttonHour= (Button)findViewById(R.id.hour);
        buttonHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment hourPicker= new TimerPickerFragment();
                hourPicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        // Room Reunion
        initList();
        mRoomReunion = findViewById(R.id.roomReunion);
        mAdapter = new SpinnerMeetingRoomAdapter(this, mMeetingRoom);
        mRoomReunion.setAdapter(mAdapter);

        mRoomReunion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MeetingRoom selectionRoom= mApiService.getMeetingRoom().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textViewDate=findViewById(R.id.view_hour_date);
        textViewDate.setText(currentDateString);

    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textViewTimes=findViewById(R.id.view_hour);
        textViewTimes.setText(hourOfDay+"H"+minute);
    }

    private void initList() {
        mMeetingRoom = new ArrayList<>();
        mMeetingRoom.addAll(mApiService.getMeetingRoom());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
