package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DatePickerFragment;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;
import mareu.adriansng.maru.ui_reunion_list.utils.SpinnerMeetingRoomAdapter;
import mareu.adriansng.maru.ui_reunion_list.utils.TimerPickerFragment;

public class AddReunionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // FOR DATA
    private ReunionApiService mApiService;
    private ArrayList<MeetingRoom> mMeetingRoom;

    // FOR PARAMETER ADD REUNION
    //Name organizer
    @BindView(R.id.add_name_organizer_edit_txt)
    EditText editNameOrganizer;

    //Subject Reunion
    @BindView(R.id.add_subject_reunion_edit)
    EditText editSubjectReunion;

    //Meeting room
    @BindView(R.id.add_roomReunion_spinner)
    private Spinner mRoomReunion;
    private MeetingRoom selectionRoom;

    //Date
    @BindView(R.id.add_date_txt)
    private TextView textViewDate;
    @BindView(R.id.add_date_btn)
    ImageButton buttonDate;
    private String date;
    private String dateUtils = "";

    //Hour
    @BindView(R.id.add_hour_txt)
    private TextView textViewTimes;
    @BindView(R.id.add_hour_btn)
    ImageButton buttonHour;
    private String hour = "";

    //Validate
    @BindView(R.id.add_validate_btn)
    ImageButton finishButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion_activity);
        ButterKnife.bind(this);
        mApiService = DI.getReunionApiService();

        // DATE
        buttonDate.setOnClickListener(this::datePickerClick);

        // HOUR
        buttonHour.setOnClickListener(this::timePickerClick);

        // MEETING ROOM
        setSelectionRoom(mApiService);

        // VALIDATE REUNION
        finishButton.setOnClickListener(this::onClick);
    }

    // DATE
    private void datePickerClick(View v){
        resetList(); /*Reset the list spinner meeting room*/
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "popup_filter_date_btn picker");
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH).format(c.getTime());
        dateUtils = DateUtils.formatDateData(date);
        textViewDate.setText(dateUtils);
        configSpinner(); /*Meeting available for this popup_filter_date_btn*/
    }

    // HOUR
    private void timePickerClick(View v){
        resetList();
        DialogFragment hourPicker = new TimerPickerFragment();
        hourPicker.show(getSupportFragmentManager(), "time picker");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String minuteString;
        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = String.valueOf(minute);
        }
        textViewTimes.setText(hourOfDay + "H" + minuteString);
        hour = textViewTimes.getText().toString();
        configSpinner(); /*Meeting available for this hour*/
    }

    // MEETING ROOM
    private void setSelectionRoom(ReunionApiService mApiService){
        selectionRoom = mApiService.getMeetingRoom().get(0);
        mRoomReunion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionRoom = mApiService.getListMeetingRoomAvailability().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configSpinner() {
        //Meeting available for this popup_filter_date_btn and hour
        if (dateUtils.equals(textViewDate.getText().toString()) && textViewTimes.getText().toString().equals(hour)) {
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
        mApiService.getAvailabilityMeetingRoom(date, hour, 45);
        mMeetingRoom.addAll(mApiService.getListMeetingRoomAvailability());
    }

    private void resetList() {
        mMeetingRoom = new ArrayList<>();
        mApiService.getResetAvailabilityMeetingRoom();
        mMeetingRoom.addAll(mApiService.getListMeetingRoomAvailability());
    }

    // VALIDATE
    private void validate(View v, ReunionApiService mApiService){
        String nameOrganizer = editNameOrganizer.getText().toString(); /*Get the name organizer of edit text */
        String subjectReunion = editSubjectReunion.getText().toString();/*Get the subject of edit text*/
        if (selectionRoom.getId() != 0 && selectionRoom != null && !nameOrganizer.equals("") && !subjectReunion.equals("")) {
            // Send new reunion
            Reunion reunion = new Reunion(mApiService.getReunionSize() + 1, selectionRoom.getId(), nameOrganizer, hour, date, mApiService.getPersonParticipant(), subjectReunion);
            mApiService.addReunion(reunion);
            // Finish activity
            finish();
        } else {
            Toast.makeText(AddReunionActivity.this, "Select all information", Toast.LENGTH_LONG).show();
        }
    }

    private void onClick(View v) {
        validate(v, mApiService);
    }
}
