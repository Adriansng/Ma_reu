package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;

class ListReunionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_reunion_number)
    public TextView mReunionMeetingRoom;
    @BindView(R.id.item_list_reunion_mail)
    public TextView mReunionListMail;
    @BindView(R.id.item_list_user_delete_btn)
    public ImageButton mDeleteButton;
    @BindView(R.id.item_list_reunion_avatar)
    public View mAvatar;

    ListReunionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @SuppressLint("SetTextI18n")
    void bind(Reunion mReunion) {

        // DATA
        ReunionApiService apiService = DI.getReunionApiService();
        Context context= itemView.getContext();

        // FIELD
        StringBuilder address = new StringBuilder();
        for (Person person : mReunion.getPersonParticipant()) {
            address.append(person.getAddressMail());
            address.append(" - ");
        }

        this.mAvatar.setBackgroundResource(R.drawable.ic_avatar_meeting_room_24dp);
        GradientDrawable drawable = (GradientDrawable) mAvatar.getBackground();
        drawable.setColor(ContextCompat.getColor(context, apiService.getColorAvatarMeetingRoom(mReunion.getIdMeetingRoom())));

        this.mReunionMeetingRoom.setText(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom()) + " - " + mReunion.getHour() + " - " + mReunion.getNameOrganizer());
        this.mReunionListMail.setText(address.toString());
        this.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
    }
}
