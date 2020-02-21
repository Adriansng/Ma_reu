package mareu.adriansng.maru.ui_reunion_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Reunion;

class MyReunionRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;

    MyReunionRecyclerViewAdapter(List<Reunion> items) {
        mReunions = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Reunion reunion = mReunions.get(position);
        holder.mReunionMeetingRoom.setText(reunion.getIdMeetingRoom());
        holder.mReunionHourDate.setText(reunion.getHourDate());
        holder.mReunionOrganizer.setText(reunion.getIdOrganizer());
        holder.mReunionListMail.setText((CharSequence) reunion.getAddressMailList());
        //TODO random image //
        holder.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault().post(new DeleteReunionEvent(reunion)));
    }


    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_reunion_number)
        public TextView mReunionMeetingRoom;
        @BindView(R.id.item_list_reunion_hour)
        public TextView mReunionHourDate;
        @BindView(R.id.item_list_reunion_name_organizer)
        public TextView mReunionOrganizer;
        @BindView(R.id.item_list_reunion_mail)
        public TextView mReunionListMail;
        @BindView(R.id.item_list_user_delete_button)
        public ImageButton mDeleteButton;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
