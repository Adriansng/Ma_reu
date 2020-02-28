package mareu.adriansng.maru.utils;


import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import mareu.adriansng.maru.model.Reunion;

public class UserDiffCallBack extends DiffUtil.Callback {

    private final List<Reunion> oldUsers;
    private final List<Reunion> newUsers;

    public UserDiffCallBack(List<Reunion> newUsers, List<Reunion> oldUsers) {
        this.newUsers = newUsers;
        this.oldUsers = oldUsers;
    }

    @Override
    public int getOldListSize() {
        return oldUsers.size();
    }

    @Override
    public int getNewListSize() {
        return newUsers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).getId() == newUsers.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
    }
}