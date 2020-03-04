package mareu.adriansng.maru.ui_reunion_list;

import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

class ListReunionViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.item_list_reunion_number)
    TextView mReunionMeetingRoom;
    @BindView(R.id.item_list_reunion_hour)
    TextView mReunionHourDate;
    @BindView(R.id.item_list_reunion_name_organizer)
    TextView mReunionOrganizer;
    @BindView(R.id.item_list_reunion_mail)
    TextView mReunionListMail;
    @BindView(R.id.item_list_user_delete_button)
    ImageButton mDeleteButton;

    public ListReunionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind( Reunion mReunion) {
        //this.mReunionMeetingRoom.setId(mReunion.getIdMeetingRoom());
        //this.mReunionHourDate.setText(mReunion.getHourDate());
        this.mReunionOrganizer.setText(mReunion.getNameOrganizer());
        //this.mReunionListMail.set(mReunion.getAddressMailList());
        //this.mDeleteButton.setOnClickListener(v ->
                //EventBus.getDefault().post(new DeleteReunionEvent(mReunion)));
    }
}
