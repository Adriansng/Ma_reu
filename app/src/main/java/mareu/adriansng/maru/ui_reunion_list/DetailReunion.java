package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

@SuppressLint("Registered")
class DetailReunion extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReunionApiService apiService = DI.getReunionApiService();
        Intent intent = getIntent();
        Reunion mReunion = intent.getParcelableExtra("Reunion");
        // DESIGN
        TextView nameOrganizerDetail = findViewById(R.id.detail_name_organizer_txt);
        TextView dateDetail = findViewById(R.id.detail_date_txt);
        TextView hourDetail = findViewById(R.id.detail_hour_txt);
        TextView meetingRoomDetail = findViewById(R.id.detail_meeting_room);
        TextView personListDetail = findViewById(R.id.detail_participant_2_txt);
        ImageButton buttonDetail = findViewById(R.id.detail_exit_btn);

        // CONSTRUCTOR
        if (mReunion != null) {
            nameOrganizerDetail.setText(mReunion.getNameOrganizer() + "is organizing a meeting");
        }
        if (mReunion != null) {
            meetingRoomDetail.setText(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom()));
        }
        if (mReunion != null) {
            dateDetail.setText(DateUtils.formatDateData(mReunion.getDate()));
        }
        if (mReunion != null) {
            hourDetail.setText(mReunion.getHour());
        }
        StringBuilder personName= new StringBuilder();
        if (mReunion != null) {
            for (Person person : mReunion.getPersonParticipant()) {
                personName.append("-");
                personName.append(person.getName());
                personName.append(" (");
                personName.append(person.getAddressMail());
                personName.append(")\n");
            }
        }
        personListDetail.setText(personName.toString());
        //Exit
        buttonDetail.setOnClickListener((View v1) -> finish());
    }
}
