package mareu.adriansng.maru.ui_reunion_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;

class ListReunionAdapter extends RecyclerView.Adapter<ListReunionViewHolder> {

    // FOR DATA
    private final List<Reunion> mReunions;

    // CONSTRUCTOR
    ListReunionAdapter(List<Reunion> mReunions) {
        this.mReunions = mReunions;
    }

    @NonNull
    @Override
    public ListReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item_reunion, parent, false);
        return new ListReunionViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    public void onBindViewHolder(@NonNull ListReunionViewHolder holder, int position) {
        holder.bind(this.mReunions.get(position));
        Context mContext = holder.itemView.getContext();
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorItem, null));
        }
        // POPUP DETAIL
        Gson gson = new Gson();
        holder.itemView.setOnClickListener(v -> {
            Reunion mReunion= mReunions.get(position);
            Intent intent= new Intent(mContext, DetailReunionActivity.class);
            intent.putExtra("Reunion",gson.toJson(mReunion));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }
}

