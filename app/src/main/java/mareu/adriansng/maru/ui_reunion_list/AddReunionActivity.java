package mareu.adriansng.maru.ui_reunion_list;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.service_api.DummyReunionList;
import mareu.adriansng.maru.service_api.ReunionApiService;

public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
