package com.softdesign.school.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.navigation_view) NavigationView mNavigationView;
    @Bind(R.id.navigation_drawer) DrawerLayout mDrawerLayout;
    @Bind(R.id.fragment_container) FrameLayout mFrameLayout;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.appbar_layout) AppBarLayout mAppBarLayout;
    @Bind(R.id.image_back) AppCompatImageView mImageView;

    private static final String TAG_FRAGMENT = "current_fragment";

    private Fragment mFragment;
    private AppBarLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolBar();
        setupNavigationDrawer();
        params = (AppBarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams();
        if (savedInstanceState == null){
            mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment(), TAG_FRAGMENT).commit();
        }
    }

    public void setTitle(String title){
        mCollapsingToolbarLayout.setTitle(title);
    }

    /**
     * Метод блокировки положения AppBarLayout
     * @param collapse true - сжать
     *                 false - отобразить
     */
    public void lockAppBar(boolean collapse){
        params = (AppBarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams();
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        final AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) p.getBehavior();

        if(collapse) {
            AppBarLayout.OnOffsetChangedListener listener = new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (mCollapsingToolbarLayout.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbarLayout) + getStatusBarHeight()) {
                        appBarLayout.removeOnOffsetChangedListener(this);
                        params.setScrollFlags(0);

                        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                            @Override
                            public boolean canDrag(AppBarLayout appBarLayout) {
                                return false;
                            }
                        });
                    }
                }
            };
            mAppBarLayout.addOnOffsetChangedListener(listener);
            mAppBarLayout.setExpanded(false);
        }
        else{
            mAppBarLayout.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
            mCollapsingToolbarLayout.setLayoutParams(params);
        }
    }

    private int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = getResources().getDimensionPixelSize(resourceId);
        return result;
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
                        //mFragment = new ProfileFragment();
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
    }

    /**
     * Реакция на нажания кнопки назад.
     * Поиск по тэгу текущий фрагмент и отмечаем пункт меню
     */
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment != null) {
            if (fragment instanceof ProfileFragment) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
        super.onBackPressed();
    }
}
