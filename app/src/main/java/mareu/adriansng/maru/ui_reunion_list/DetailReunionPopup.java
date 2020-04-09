package mareu.adriansng.maru.ui_reunion_list;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.TextView;

import mareu.adriansng.maru.R;

class DetailReunionPopup extends Dialog {

    // DESIGN
    private final TextView nameOrganizerDetail;
    private final TextView dateDetail;
    private final TextView hourDetail;
    private final TextView meetingRoomDetail;
    private final TextView personListDetail;
    private String detailNameOrganizer;
    private String detailDate, detailHour;
    private String detailMeetingRoom, detailPersonList;
    private final ImageButton buttonDetail;

    // CONSTRUCTOR
    DetailReunionPopup(Context activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_detail_reunion);
        this.nameOrganizerDetail = findViewById(R.id.detail_name_organizer_txt);
        this.dateDetail = findViewById(R.id.detail_date_txt);
        this.hourDetail = findViewById(R.id.detail_hour_txt);
        this.meetingRoomDetail = findViewById(R.id.detail_meeting_room);
        this.personListDetail = findViewById(R.id.detail_participant_2_txt);
        this.buttonDetail = findViewById(R.id.detail_exit_btn);
    }

    void setDetailNameOrganizer(String detailNameOrganizer) {
        this.detailNameOrganizer = detailNameOrganizer;
    }

    void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    void setDetailHour(String detailHour) {
        this.detailHour = detailHour;
    }

    void setDetailMeetingRoom(String detailMeetingRoom) {
        this.detailMeetingRoom = detailMeetingRoom;
    }

    void setDetailPersonList(String detailPersonList) {
        this.detailPersonList = detailPersonList;
    }

    ImageButton getButtonDetail() {
        return buttonDetail;
    }

    void build() {
        show();
        nameOrganizerDetail.setText(detailNameOrganizer);
        dateDetail.setText(detailDate);
        hourDetail.setText(detailHour);
        meetingRoomDetail.setText(detailMeetingRoom);
        personListDetail.setText(detailPersonList);
    }
}
