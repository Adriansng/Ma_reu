package mareu.adriansng.maru.ui_reunion_list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Reunion;

class ListReunionViewHolder extends RecyclerView.ViewHolder{

    private TextView mReunionMeetingRoom;
    private TextView mReunionHourDate;
    private TextView mReunionOrganizer;
    private TextView mReunionListMail;
    private ImageButton mDeleteButton;

    public ListReunionViewHolder(View itemView) {
        super(itemView);
        mReunionMeetingRoom= itemView.findViewById(R.id.item_list_reunion_number);
        mReunionHourDate=itemView.findViewById(R.id.item_list_reunion_hour);
        mReunionOrganizer=itemView.findViewById(R.id.item_list_reunion_name_organizer);
        mReunionListMail=itemView.findViewById(R.id.item_list_reunion_mail);
        mDeleteButton=itemView.findViewById(R.id.item_list_user_delete_button);
    }

    public void bind( Reunion mReunion) {
        this.mReunionMeetingRoom.setText(mReunion.getMeetingRoom());
        this.mReunionHourDate.setText(mReunion.getHourDate());
        this.mReunionOrganizer.setText(mReunion.getNameOrganizer());
        this.mReunionListMail.setText(mReunion.getAddressMailList().toString());
        this.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
    }
}
