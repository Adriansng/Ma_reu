package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.model.Reunion;

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



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ListReunionViewHolder holder, int position) {
        holder.bind(this.mReunions.get(position));
        Context mContext= holder.itemView.getContext();
        if(position %2==0){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorItem,null));
        }

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

