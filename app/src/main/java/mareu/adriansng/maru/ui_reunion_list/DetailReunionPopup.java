package mareu.adriansng.maru.ui_reunion_list;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ImageButton;
import android.widget.TextView;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

public class DetailReunionPopup extends Dialog {

    // DESIGN
    private TextView nameOrganizerDetail;
    private TextView dateDetail, hourDetail;
    private TextView meetingRoomDetail, personListDetail;
    private String detailNameOrganizer;
    private String detailDate,detailHour;
    private String detailMeetingRoom,detailPersonList;
    private ImageButton buttonDetail;

    // CONSTRUCTOR
    public DetailReunionPopup(Activity activity)
    {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_detail_reunion);
        this.nameOrganizerDetail =findViewById(R.id.detail_name_organizer_txt);
        this.dateDetail =findViewById(R.id.detail_date_txt);
        this.hourDetail =findViewById(R.id.detail_hour_txt);
        this.meetingRoomDetail =findViewById(R.id.detail_meeting_room);
        this.personListDetail =findViewById(R.id.detail_participant_2_txt);
        this.buttonDetail =findViewById(R.id.detail_exit_btn);
    }

    public void setDetailNameOrganizer(String detailNameOrganizer) {
        this.detailNameOrganizer = detailNameOrganizer;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public void setDetailHour(String detailHour) {
        this.detailHour = detailHour;
    }

    public void setDetailMeetingRoom(String detailMeetingRoom) {
        this.detailMeetingRoom = detailMeetingRoom;
    }

    public void setDetailPersonList(String detailPersonList) {
        this.detailPersonList = detailPersonList;
    }

    public ImageButton getButtonDetail(){return buttonDetail;}

    public void build(){
        show();
        nameOrganizerDetail.setText(detailNameOrganizer);
        dateDetail.setText(detailDate);
        hourDetail.setText(detailHour);
        meetingRoomDetail.setText(detailMeetingRoom);
        personListDetail.setText(detailPersonList);
    }
}
