package mareu.adriansng.maru.ui_reunion_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.utils.UserDiffCallBack;

public class ListReunionAdapter extends RecyclerView.Adapter<ListReunionViewHolder> {

    // FOR DATA
    private List<Reunion> mReunions = new ArrayList<>();

    // FOR CALLBACK

    private final Listener callback;
    public interface Listener{
    }

    public ListReunionAdapter(Listener callback){
        this.callback=callback;
    }

    @Override
    public ListReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.fragment_item_reunion, parent, false);
        return new ListReunionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListReunionViewHolder holder, int position) {
        holder.bind(mReunions.get(position),callback);
    }


    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    // PUBLIC API
    public void updateList(List<Reunion> newList) {
        DiffUtil.DiffResult diffResult= DiffUtil.calculateDiff(new UserDiffCallBack(newList, this.mReunions));
        this.mReunions= new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}
