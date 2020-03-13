package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;

class ListReunionViewHolder extends RecyclerView.ViewHolder{

    private TextView mReunionMeetingRoom;
    private TextView mReunionListMail;
    private ImageButton mDeleteButton;
    private ReunionApiService apiService;

    public ListReunionViewHolder(View itemView) {
        super(itemView);
        mReunionMeetingRoom= itemView.findViewById(R.id.item_list_reunion_number);
        mReunionListMail=itemView.findViewById(R.id.item_list_reunion_mail);
        mDeleteButton=itemView.findViewById(R.id.item_list_user_delete_button);
    }

    @SuppressLint("SetTextI18n")
    public void bind(Reunion mReunion) {
        //this.mReunionMeetingRoom.setText(apiService.getNameMeetingRome()+ " - "+ mReunion.getHourDate()+" - "+mReunion.getNameOrganizer());
        //this.mReunionListMail.setText(mReunion.getAddressMailList().toString());
        mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
    }
}
