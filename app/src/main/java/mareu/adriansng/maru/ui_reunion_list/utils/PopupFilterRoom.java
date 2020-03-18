package mareu.adriansng.maru.ui_reunion_list.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.service_api.ReunionApiService;


public class PopupFilterRoom extends Dialog {

    private Spinner spinnerRoom;
    private ReunionApiService mApiService;
    private ArrayList<MeetingRoom> mMeetingRoom;
    private SpinnerMeetingRoomAdapter mAdapter;
    private MeetingRoom selectionRoom;
    private int meetingRoom;

    public PopupFilterRoom(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_filter_room);
        this.spinnerRoom= findViewById(R.id.spinner_room_reunion);
        initList();
        Context context= getContext();
        mAdapter = new SpinnerMeetingRoomAdapter(context, mMeetingRoom);
        spinnerRoom.setAdapter(mAdapter);
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionRoom= mApiService.getMeetingRoom().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public int getSelectionRoom() {
        meetingRoom = selectionRoom.getId();
        return meetingRoom;
    }


    private void initList() {
        mMeetingRoom = new ArrayList<>();
        mMeetingRoom.addAll(mApiService.getMeetingRoom());
    }

    public void build(){
        show();
    }
}
