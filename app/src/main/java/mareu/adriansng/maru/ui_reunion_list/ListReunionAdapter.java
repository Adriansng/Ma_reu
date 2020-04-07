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
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

public class ListReunionAdapter extends RecyclerView.Adapter<ListReunionViewHolder> {

    // FOR DATA
    private List<Reunion> mReunions;

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
            Context context= v.getContext();
            Dialog dialog= new Dialog(context);
            dialog.setContentView((R.layout.popup_filter_room));
            Reunion reunion= this.mReunions.get(position);
            //Name
            TextView nameOrganizer=dialog.findViewById(R.id.detail_name_organizer_txt);
            nameOrganizer.setText(reunion.getNameOrganizer() + "is organizing a meeting");
            //Date
            TextView date=dialog.findViewById(R.id.detail_date_txt);
            date.setText(DateUtils.formatDateData(reunion.getDate()));
            //Hour
            TextView hour=dialog.findViewById(R.id.detail_hour_txt);
            hour.setText(reunion.getHour());
            //Person
            TextView personList=dialog.findViewById(R.id.detail_participant_2_txt);
            String namePerson="";
            for(Person person: reunion.getPersonParticipant()){
                namePerson+="-";
                namePerson+=person.getName();
                namePerson+=" (";
                namePerson+=person.getAddressMail();
                namePerson+=")\n";
            }
            personList.setText(namePerson);
            //Exit
            ImageButton button=dialog.findViewById(R.id.detail_exit_btn);
            button.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
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

