package mareu.adriansng.maru.ui_reunion_list;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;

class ListReunionViewHolder extends RecyclerView.ViewHolder{

    private TextView mReunionMeetingRoom;
    private TextView mReunionListMail;
    private ImageButton mDeleteButton;
    private ImageView mAvatar;
    private ReunionApiService apiService;

    public ListReunionViewHolder(View itemView) {
        super(itemView);
        mAvatar=itemView.findViewById(R.id.item_list_reunion_avatar);
        mReunionMeetingRoom= itemView.findViewById(R.id.item_list_reunion_number);
        mReunionListMail=itemView.findViewById(R.id.item_list_reunion_mail);
        mDeleteButton=itemView.findViewById(R.id.item_list_user_delete_button);

    }


    public void bind(Reunion mReunion) {
        //data
        apiService= DI.getReunionApiService();
        Context mContext = itemView.getContext();
        //List mail
        String address = "";
        for(Person person: mReunion.getPersonParticipant()) {
            address += person.getAddressMail();
            address += " - ";
        }
        this.mAvatar.setColorFilter(mContext.getResources().getColor(apiService.getColorAvatarMeetingRoom()));
        this.mReunionMeetingRoom.setText(apiService.getNameMeetingRome(mReunion.getIdMeetingRoom())+ " - "+ mReunion.getHour()+" - "+ mReunion.getNameOrganizer());
        this.mReunionListMail.setText(address);
        this.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
    }
}
