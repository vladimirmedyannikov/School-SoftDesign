package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

import java.util.List;

/**
 * Created by Vladimir on 13.02.2016.
 */
public class UsersAdapter extends BaseAdapter {
    Context mContext;
    List<User> mUserList;
    LayoutInflater mInflater;
    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = mInflater.inflate(R.layout.item_view_list, parent, false);
        }

        User user = (User) getItem(position);

        return null;
    }
}
