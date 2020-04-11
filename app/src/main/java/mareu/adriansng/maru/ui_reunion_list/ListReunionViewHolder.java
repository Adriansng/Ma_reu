package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

class ListReunionViewHolder extends RecyclerView.ViewHolder {

    private final TextView mReunionMeetingRoom;
    private final TextView mReunionListMail;
    private final ImageButton mDeleteButton;
    private final View mAvatar;
    private DetailReunionPopup detailReunionPopup;
    private List<Reunion> mReunions;

    ListReunionViewHolder(View itemView) {
        super(itemView);
        mAvatar = itemView.findViewById(R.id.item_list_reunion_avatar);
        mReunionMeetingRoom = itemView.findViewById(R.id.item_list_reunion_number);
        mReunionListMail = itemView.findViewById(R.id.item_list_reunion_mail);
        mDeleteButton = itemView.findViewById(R.id.item_list_user_delete_button);

    }

    @SuppressLint("SetTextI18n")
    void bind(Reunion mReunion) {
        // DATA
        ReunionApiService apiService = DI.getReunionApiService();
        // FIELD
        StringBuilder address = new StringBuilder();
        for (Person person : mReunion.getPersonParticipant()) {
            address.append(person.getAddressMail());
            address.append(" - ");
        }
        this.mAvatar.setBackgroundColor(apiService.getColorAvatarMeetingRoom(mReunion.getIdMeetingRoom()));
        this.mReunionMeetingRoom.setText(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom()) + " - " + mReunion.getHour() + " - " + mReunion.getNameOrganizer());
        this.mReunionListMail.setText(address.toString());
        this.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
        // POPUP DETAIL
        this.itemView.setOnClickListener(v -> {
            Context mContext=v.getContext();
            detailReunionPopup = new DetailReunionPopup(mContext);
            detailReunionPopup.setDetailNameOrganizer(mReunion.getNameOrganizer() + "is organizing a meeting");
            detailReunionPopup.setDetailMeetingRoom(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom()));
            detailReunionPopup.setDetailDate(DateUtils.formatDateData(mReunion.getDate()));
            detailReunionPopup.setDetailHour(mReunion.getHour());
            StringBuilder personListDetail = new StringBuilder();
            for (Person person : mReunion.getPersonParticipant()) {
                personListDetail.append("-");
                personListDetail.append(person.getName());
                personListDetail.append(" (");
                personListDetail.append(person.getAddressMail());
                personListDetail.append(")\n");
            }
            detailReunionPopup.setDetailPersonList(personListDetail.toString());
            //Exit
            detailReunionPopup.getButtonDetail().setOnClickListener((View v1) -> detailReunionPopup.dismiss());
            detailReunionPopup.build();
        });
    }
}
