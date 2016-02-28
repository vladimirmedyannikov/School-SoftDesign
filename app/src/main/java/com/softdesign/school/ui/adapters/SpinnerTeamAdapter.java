package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.softdesign.school.data.storage.models.Team;

import java.util.List;

/**
 * Created by Vladimir on 28.02.2016.
 */
public class SpinnerTeamAdapter extends BaseAdapter implements SpinnerAdapter {
    Context mContext;
    List<Team> mTeamList;

    public SpinnerTeamAdapter(Context context, List<Team> list){
        this.mContext = context;
        this.mTeamList = list;
    }

    @Override
    public int getCount() {
        return mTeamList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTeamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team team = mTeamList.get(position);
        View view = convertView;
        if (convertView == null){
           view  = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(android.R.layout.simple_spinner_item,null);
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(team.getTeamName());
        return view;
    }
}
