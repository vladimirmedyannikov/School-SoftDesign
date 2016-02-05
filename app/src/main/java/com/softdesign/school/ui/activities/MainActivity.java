package com.softdesign.school.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TaskFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VISIBLE_KEY = "visible";
    public static final String TOOLBAR_COLOR_KEY = "toolbar_color";
    public static final String STATUSBAR_COLOR_KEY = "statusbar_color";
    private static final String TAG_FRAGMENT = "current_fragment";
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Fragment mFragment;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolBar();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mFrameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        setupNavigationDrawer();

        if (savedInstanceState == null){
            mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment(), TAG_FRAGMENT).commit();
        }
    }

    private void setupNavigationDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        break;
                    case R.id.drawer_task:
                        mFragment = new TaskFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_task).setChecked(true);
                        break;
                    case R.id.drawer_settings:
                        mFragment = new SettingsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_settings).setChecked(true);
                        break;
                    default:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                }
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment, TAG_FRAGMENT).addToBackStack(null).commit();
                }
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
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
        setTitle("Lesson 1");
    }

    /**
     * Имплементация обработчика нажатия {@link android.view.View.OnClickListener}
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        Lg.e(this.getLocalClassName(),"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Lg.e(this.getLocalClassName(),"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Lg.e(this.getLocalClassName(),"onStop");
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        Lg.e(this.getLocalClassName(),"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Lg.e(this.getLocalClassName(),"onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Lg.e(this.getLocalClassName(),"onStart");
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Из сохраненных данных восстанавливаем состояние текстового поля и цветовой схемы
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onRestore");
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Сохраняем состояние текстового поля и цветовой схемы
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Lg.e(this.getLocalClassName(), "onSave");
        super.onSaveInstanceState(outState);

        outState.putInt(TOOLBAR_COLOR_KEY, ((ColorDrawable)mToolbar.getBackground()).getColor());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            outState.putInt(STATUSBAR_COLOR_KEY, getWindow().getStatusBarColor());
    }

    /**
     * Реакция на нажания кнопки назад.
     * Поиск по тэгу текущий фрагмент и отмечаем пункт меню
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment != null) {
            if (fragment instanceof ProfileFragment) {
                mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
            } else if (fragment instanceof ContactsFragment) {
                mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
            } else if (fragment instanceof TeamFragment) {
                mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
            } else if (fragment instanceof TaskFragment) {
                mNavigationView.getMenu().findItem(R.id.drawer_task).setChecked(true);
            } else if (fragment instanceof SettingsFragment) {
                mNavigationView.getMenu().findItem(R.id.drawer_settings).setChecked(true);
            } else mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
        }

    }
}
