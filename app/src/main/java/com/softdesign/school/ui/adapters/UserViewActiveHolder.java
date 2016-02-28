package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.softdesign.school.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Vladimir on 13.02.2016.
 */
public class UserViewActiveHolder extends RecyclerView.ViewHolder {

    protected TextView mFullName;
    protected CircleImageView mAvatar;
    protected TextView mTeamName;

    public UserViewActiveHolder(View itemView) {
        super(itemView);
        mFullName = (TextView) itemView.findViewById(R.id.user_full_name);
        mAvatar = (CircleImageView) itemView.findViewById(R.id.user_avatar);
        mTeamName = (TextView) itemView.findViewById(R.id.user_team_name);
    }
}
