package mareu.adriansng.maru.ui_reunion_list;

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
import mareu.adriansng.maru.service_api.DummyReunionList;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DatePickerFragment;
import mareu.adriansng.maru.ui_reunion_list.utils.SpinnerMeetingRoomAdapter;
import mareu.adriansng.maru.ui_reunion_list.utils.TimerPickerFragment;

public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    //Data
    private ReunionApiService mApiService;
    private DummyReunionList mDummyReunionList;

    private ArrayList<MeetingRoom> mMeetingRoom;
    //Design
    private Spinner mRoomReunion;
    private SpinnerMeetingRoomAdapter mAdapter;
    private Button finishButton;
    //Parameter Reunion
    private int idReunion;
    private int idMeetingRoom;
    private String nameOrganizer;
    private String date;
    private String hour;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion_activity);
        mApiService = DI.getReunionApiService();
        mDummyReunionList = new DummyReunionList();
        //Name Organizer
        EditText editNameOrganizer= findViewById(R.id.name_organizer);
        String nameOrganizer= editNameOrganizer.getText().toString();

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
        Button buttonHour= findViewById(R.id.hour);
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
                int idMeetingRoom= selectionRoom.getId();
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
                mApiService.addReunion(Reunion.addNewReunion(idReunion+1,idMeetingRoom,nameOrganizer,hour,mApiService.getAddressMailList()));
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
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textViewDate=findViewById(R.id.view_hour_date);
        textViewDate.setText(currentDateString);
        String date= textViewDate.getText().toString();

    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textViewTimes=findViewById(R.id.view_hour);
        textViewTimes.setText(hourOfDay+"H"+minute);
        String hour=textViewTimes.getText().toString();
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
