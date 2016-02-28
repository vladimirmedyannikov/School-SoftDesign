package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.ActiveUser;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 13.02.2016.
 */
public class RecycleUsersActiveAdapter extends RecyclerView.Adapter<UserViewActiveHolder> {
    List<ActiveUser> mUserList = new ArrayList<ActiveUser>();

    public RecycleUsersActiveAdapter(List<ActiveUser> listUser){
        mUserList = listUser;
    }

    @Override
    public UserViewActiveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_active_list, parent, false);
        return new UserViewActiveHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewActiveHolder holder, int position) {
        ActiveUser user = mUserList.get(position);
        holder.mAvatar.setImageDrawable(user.getmImage());
        holder.mFullName.setText(user.getFullName());
        holder.mTeamName.setText(user.getTeam().getTeamName());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public ActiveUser getUser(int position){
        return mUserList.get(position);
    }
}
