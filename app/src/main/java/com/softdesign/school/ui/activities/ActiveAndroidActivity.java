package com.softdesign.school.ui.activities;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.ActiveUser;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.ui.adapters.RecycleUsersActiveAdapter;
import com.softdesign.school.ui.adapters.SpinnerTeamAdapter;
import com.softdesign.school.ui.adapters.UserViewActiveHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vladimir on 27.02.2016.
 */
public class ActiveAndroidActivity extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<List<ActiveUser>> {

    @Bind(R.id.usersRecycler) RecyclerView userRecyclerView;
    @Nullable @Bind(R.id.userName) EditText userNameDialog;
    @Nullable @Bind(R.id.userLastName) EditText userLastNameDialog;
    @Nullable @Bind(R.id.teamName) EditText teamNameText;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    private RecycleUsersActiveAdapter mAdapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_active_android);
        ButterKnife.bind(this);
        setupToolBar();
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getLoaderManager().initLoader(0, null, this);
        mAdapterUser = new RecycleUsersActiveAdapter(ActiveUser.getAll());
        userRecyclerView.setAdapter(mAdapterUser);

        /**
         * Удаление пользователя по свайпу
         */
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ActiveUser user = mAdapterUser.getUser(position);
                user.delete();
                getLoaderManager().getLoader(0).forceLoad();
            }
        });
        itemTouchHelper.attachToRecyclerView(userRecyclerView);
    }

    /**
     * Инициализация ToolBar
     */
    private void setupToolBar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        }
        setTitle("Lesson 6");
    }

    /**
     * Добавление команды
     */
    @Nullable @OnClick(R.id.btnTeamAdd)
    public void teamAdd(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ActiveAndroidActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_team,null,false);
        teamNameText = (EditText) dialogView.findViewById(R.id.teamName);

        builder.setTitle("Создать команду")
                .setCancelable(false)
                .setView(dialogView)
                .setPositiveButton("Создать", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Team team = new Team(teamNameText.getText().toString());
                            team.save();
                        }catch (NullPointerException e){
                            Toast.makeText(getApplication(),"Нет привязки к данным",Toast.LENGTH_SHORT).show();
                        }
                        finally {
                            dialog.cancel();
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.btnUserAdd)
    public void userAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_user,null,false);

        final Spinner mSpinner = (Spinner) dialogView.findViewById(R.id.teamSpinner);
        SpinnerTeamAdapter teamAdapter = new SpinnerTeamAdapter(this, Team.getAll());
        mSpinner.setAdapter(teamAdapter);

        userNameDialog = (EditText) dialogView.findViewById(R.id.userName);
        userLastNameDialog = (EditText) dialogView.findViewById(R.id.userLastName);

        builder.setTitle("Создать пользователя")
                .setCancelable(false)
                .setPositiveButton("Создать", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ActiveUser user = new ActiveUser();
                            user.setmFirstName(userNameDialog.getText().toString());
                            user.setmLastName(userLastNameDialog.getText().toString());
                            user.setTeam((Team) mSpinner.getSelectedItem());
                            user.save();
                            getLoaderManager().getLoader(0).forceLoad();
                        }catch (NullPointerException e){
                            Toast.makeText(getApplication(),"Нет привязки к данным",Toast.LENGTH_SHORT).show();
                        }
                        finally {
                            dialog.cancel();
                        }

                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public Loader<List<ActiveUser>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<ActiveUser>>(this) {
            @Override
            public List<ActiveUser> loadInBackground() {
                return ActiveUser.getAll();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<ActiveUser>> loader, List<ActiveUser> data) {
        mAdapterUser = new RecycleUsersActiveAdapter(data);
        userRecyclerView.setAdapter(mAdapterUser);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
