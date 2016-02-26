package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUsersAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Vladimir on 05.02.2016.
 */
public class ContactsFragment extends Fragment {
    @Bind(R.id.contacts_recyclerview) RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<User> mUserList = new ArrayList<User>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, null, false);
        getActivity().setTitle(getResources().getString(R.string.drawer_menu_contacts));
        ((MainActivity)getActivity()).setTitle(getResources().getString(R.string.drawer_menu_contacts));
        ButterKnife.bind(this, view);
        generateData();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecycleUsersAdapter(mUserList);
        mRecyclerView.setAdapter(mAdapter);

        ((MainActivity)getActivity()).lockAppBar(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*Переопределяем положение Fab*/
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        layoutParams.setAnchorId(R.id.coordinator_layout);
        layoutParams.anchorGravity = Gravity.BOTTOM | Gravity.END;

        mFloatingActionButton.setLayoutParams(layoutParams);
        mFloatingActionButton.setImageResource(R.drawable.ic_add_24dp);
    }

    private void generateData(){
        if (mUserList == null) mUserList = new ArrayList<User>();
        mUserList.add(new User(getResources().getDrawable(R.drawable.avatar_image), "Владимир", "Медянников"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp), "Антон", "Иванов"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp), "Семен", "Дроздов"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp), "Маргарита", "Семенова"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp),"Валентина","Иваха"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp),"Дмитрий","Иванов"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp), "Анатолий", "Казаков"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp),"Станислав","Леонов"));
        mUserList.add(new User(getResources().getDrawable(R.drawable.ic_mood_48dp), "Дмитрий", "Васельков"));
    }
}
