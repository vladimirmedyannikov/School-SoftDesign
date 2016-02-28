package com.softdesign.school.ui.activities;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Vladimir on 27.02.2016.
 */
public class ActiveAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
