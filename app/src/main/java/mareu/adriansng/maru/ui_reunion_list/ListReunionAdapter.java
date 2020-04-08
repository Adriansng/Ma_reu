package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

public class ListReunionAdapter extends RecyclerView.Adapter<ListReunionViewHolder> {

    // FOR DATA
    private List<Reunion> mReunions;
    private ListReunionActivity activity;
    private ReunionApiService apiService;

    // CONSTRUCTOR
    public ListReunionAdapter(List<Reunion> mReunions){

        this.mReunions = mReunions;
    }

    @Override
    public ListReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.fragment_item_reunion, parent, false);
        return new ListReunionViewHolder(view);
    }



    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ListReunionViewHolder holder, int position) {
        holder.bind(this.mReunions.get(position));
        Context mContext= holder.itemView.getContext();
        if(position %2==0){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorItem,null));
        }
        // POPUP DETAIL
        holder.itemView.setOnClickListener(v -> {
            final DetailReunionPopup detailReunionPopup= new DetailReunionPopup(this.activity);
            apiService= DI.getReunionApiService();
            Reunion reunion=this.mReunions.get(position);
            detailReunionPopup.setDetailNameOrganizer(reunion.getNameOrganizer() + "is organizing a meeting");
            detailReunionPopup.setDetailMeetingRoom(apiService.getNameMeetingRome(reunion.getIdMeetingRoom()));
            detailReunionPopup.setDetailDate(DateUtils.formatDateData(reunion.getDate()));
            detailReunionPopup.setDetailHour(reunion.getHour());
            StringBuilder personListDetail= new StringBuilder();
            for(Person person: reunion.getPersonParticipant()){
                personListDetail.append("-");
                personListDetail.append(person.getName());
                personListDetail.append(" (");
                personListDetail.append(person.getAddressMail());
                personListDetail.append(")\n");
            }
            detailReunionPopup.setDetailPersonList(personListDetail.toString());
            //Exit
            detailReunionPopup.getButtonDetail().setOnClickListener(v1 ->{
            detailReunionPopup.dismiss();
            });
            detailReunionPopup.build();
        });

    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public void updateList(List<Reunion> newList){
        mReunions=new ArrayList<>();
        mReunions.addAll(newList);
        notifyDataSetChanged();
    }


}

