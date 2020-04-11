package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

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
    private DetailReunion detailReunion;
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
        this.mAvatar.setBackgroundResource(apiService.getColorAvatarMeetingRoom(mReunion.getIdMeetingRoom()));
        this.mReunionMeetingRoom.setText(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom()) + " - " + mReunion.getHour() + " - " + mReunion.getNameOrganizer());
        this.mReunionListMail.setText(address.toString());
        this.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
        // POPUP DETAIL
        Gson gson = new Gson();
        this.itemView.setOnClickListener(v -> {
            Context mContext=v.getContext();
            Intent intent= new Intent(mContext, DetailReunion.class);
            intent.putExtra("Reunion",gson.toJson(mReunion));
            mContext.startActivity(intent);
        });
    }
}
