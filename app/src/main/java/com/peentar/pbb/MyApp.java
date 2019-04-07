package com.peentar.pbb;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Imam Sulthon on 4/1/2019.
 */


public class MyApp extends Application {

    RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        realmConfiguration = new RealmConfiguration.Builder()
                .name("pbb.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
