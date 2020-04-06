package mareu.adriansng.maru.ui_reunion_list.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.model.MeetingRoom;

public class SpinnerMeetingRoomAdapter extends ArrayAdapter<MeetingRoom> {




    public SpinnerMeetingRoomAdapter(@NonNull Context context, ArrayList<MeetingRoom> meetingRooms) {
        super(context, 0, meetingRooms);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_spinner_row, parent, false);

        }

        TextView textViewName = convertView.findViewById(R.id.text_spinner_name);

        MeetingRoom mMeetingRoom = getItem(position);
        if(mMeetingRoom != null) {
            Context mContext= textViewName.getContext();
            textViewName.setText(mMeetingRoom.getNameRoom());
            if(position %2==0){
                textViewName.setBackgroundColor(mContext.getResources().getColor(R.color.colorItem));
            }
        }
        return convertView;
    }
}
