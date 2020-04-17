package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

@SuppressLint("Registered")
class DetailReunionActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reunion);
        ReunionApiService apiService = DI.getReunionApiService();
        Gson gson=new Gson();
        String strObj= getIntent().getStringExtra("Reunion");
        Reunion mReunion = gson.fromJson(strObj, Reunion.class);
        // DESIGN
        TextView nameOrganizerDetail = findViewById(R.id.detail_name_organizer_txt);
        TextView dateDetail = findViewById(R.id.detail_date_txt);
        TextView hourDetail = findViewById(R.id.detail_hour_txt);
        TextView meetingRoomDetail = findViewById(R.id.detail_meeting_room);
        TextView personListDetail = findViewById(R.id.detail_participant_txt);
        TextView subjectReunion= findViewById(R.id.detail_description_txt);
        ImageButton buttonDetail = findViewById(R.id.detail_exit_btn);

        // CONSTRUCTOR
        if (mReunion != null) {
            nameOrganizerDetail.setText(mReunion.getNameOrganizer() + " is organizing a meeting");
            meetingRoomDetail.setText(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom()));
            dateDetail.setText(DateUtils.formatDateData(mReunion.getDate()));
            hourDetail.setText(mReunion.getHour());
            StringBuilder personName= new StringBuilder();
            for (Person person : mReunion.getPersonParticipant()) {
                personName.append("-");
                personName.append(person.getName());
                personName.append(" (");
                personName.append(person.getAddressMail());
                personName.append(")\n");
            }
            personListDetail.setText(personName.toString());
            subjectReunion.setText(mReunion.getSubjectReunion());
        }
        //Exit
        buttonDetail.setOnClickListener((View v1) -> finish());
    }
}
