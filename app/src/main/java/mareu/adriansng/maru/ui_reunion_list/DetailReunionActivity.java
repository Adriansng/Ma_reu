package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;


public class DetailReunionActivity extends AppCompatActivity {

    // FOR DESIGN
    @BindView(R.id.detail_name_organizer_txt)
    TextView nameOrganizerDetail;
    @BindView(R.id.detail_description_txt)
    TextView subjectReunion;
    @BindView(R.id.detail_date_txt)
    TextView dateDetail;
    @BindView(R.id.detail_hour_txt)
    TextView hourDetail;
    @BindView(R.id.detail_meeting_room)
    TextView meetingRoomDetail;
    @BindView(R.id.detail_participant_txt)
    TextView personListDetail;
    @BindView(R.id.detail_exit_btn)
    ImageButton buttonDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reunion);
        ButterKnife.bind(this);

        // DATA
        ReunionApiService apiService = DI.getReunionApiService();

        // REUNION
        Gson gson=new Gson();
        String strObj= getIntent().getStringExtra("Reunion");
        Reunion mReunion = gson.fromJson(strObj, Reunion.class);

        // CONSTRUCTOR
        constructorDetail(mReunion,apiService);

        // EXIT
        buttonDetail.setOnClickListener((View v1) -> finish());
    }

    @SuppressLint("SetTextI18n")
    private void constructorDetail(Reunion mReunion, ReunionApiService apiService){
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
    }
}
