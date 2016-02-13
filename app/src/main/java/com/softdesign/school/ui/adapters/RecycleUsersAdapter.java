package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;

/**
 * Created by Vladimir on 13.02.2016.
 */
public class RecycleUsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> mUserList = new ArrayList<User>();

    public RecycleUsersAdapter(ArrayList<User> listUser){
        mUserList = listUser;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.mAvatar.setImageDrawable(user.getmImage());
        holder.mFullName.setText(user.getFullName());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
