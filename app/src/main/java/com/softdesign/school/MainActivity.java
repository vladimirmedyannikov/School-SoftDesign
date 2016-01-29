package com.softdesign.school;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.utils.Lg;

import ru.medyannikov.school_softdesign.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VISIBLE_KEY = "visible";
    public static final String TOOLBAR_COLOR_KEY = "toolbar_color";
    public static final String STATUSBAR_COLOR_KEY = "statusbar_color";
    private int colorStatusBar;
    private int colorToolBar;
    private CheckBox mCheckBox;
    private Button mButtonBlue;
    private Button mButtonRed;
    private Button mButtonGreen;
    private EditText mEditText1;
    private EditText mEditText2;
    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCheckBox = (CheckBox)findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mButtonBlue = (Button) findViewById(R.id.btnBlue);
        mButtonBlue.setOnClickListener(this);
        mButtonGreen = (Button) findViewById(R.id.btnGreen);
        mButtonGreen.setOnClickListener(this);
        mButtonRed = (Button) findViewById(R.id.btnRed);
        mButtonRed.setOnClickListener(this);
        mEditText1 = (EditText) findViewById(R.id.editText1);
        mEditText2 = (EditText) findViewById(R.id.editText2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setupToolBar();
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
        switch (v.getId()){
            case R.id.checkBox:
                Toast.makeText(this,"CheckBox",Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()){
                    mEditText2.setVisibility(View.INVISIBLE);
                }
                else{
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnBlue:
                setupColorBar(getResources().getColor(R.color.color_primary), getResources().getColor(R.color.color_primary_dark));
                break;
            case R.id.btnRed:
                setupColorBar(getResources().getColor(R.color.red), getResources().getColor(R.color.red_dark));
                break;
            case R.id.btnGreen:
                setupColorBar(getResources().getColor(R.color.green), getResources().getColor(R.color.green_dark));
                break;
        }
    }

    /**
     * Устанавливаем параметры для ToolBar и StatusBar (SDK 21+)
     * @param color Основной цвет ToolBar
     * @param colorDark Цвет StatusBar
     */
    private void setupColorBar(int color, int colorDark) {
        mToolbar.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colorDark);
        }
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

    /**
     * Из сохраненных данных восстанавливаем состояние текстового поля и цветовой схемы
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(),"onRestore");
        super.onRestoreInstanceState(savedInstanceState);
        int visible = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText2.setVisibility(visible);
        setupColorBar(savedInstanceState.getInt(TOOLBAR_COLOR_KEY), savedInstanceState.getInt(STATUSBAR_COLOR_KEY));

    }

    /**
     * Сохраняем состояние текстового поля и цветовой схемы
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Lg.e(this.getLocalClassName(), "onSave");
        super.onSaveInstanceState(outState);
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
        outState.putInt(TOOLBAR_COLOR_KEY, ((ColorDrawable)mToolbar.getBackground()).getColor());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            outState.putInt(STATUSBAR_COLOR_KEY, getWindow().getStatusBarColor());
    }
}
