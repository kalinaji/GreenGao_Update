package com.jowney.database;

import android.app.Application;
import android.content.Context;

import com.jowney.database.dao.DaoManager;

/**
 *
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = this;
        DaoManager.getInstance(this).setDebug(true);
      //  DaoManager.getInstance(this).encryptDatabase("www");
    }

    public static Context getContext() {
        return MyApplication.context;
    }

}
