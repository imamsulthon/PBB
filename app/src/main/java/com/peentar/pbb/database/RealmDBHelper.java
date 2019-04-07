package com.peentar.pbb.database;

import com.peentar.pbb.model.Report;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Imam Sulthon on 4/1/2019.
 */


public class RealmDBHelper {

    private Realm realm;

    public RealmDBHelper() {
    }

    public void open() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("pbb.realm")
                .build();
        realm = Realm.getInstance(configuration);
    }

    public void close() {
        realm.close();
    }

    public void addItem(Report report) {
        realm.executeTransaction(realm1 -> {
            Number currentIdNum = realm.where(Report.class).max("id");
            int nextId;
            if (currentIdNum == null) {
                nextId = 1;
            } else {
                nextId = currentIdNum.intValue() + 1;
            }
            Report r = new Report();
            r.setId(nextId);
            r.setImagePath(report.getImagePath());
            realm.insertOrUpdate(r);
        });
    }

    public void deleteItem(Report item) {
        realm.executeTransaction(realm -> {
            Report object = realm.where(Report.class).equalTo("id", item.getId()).findFirst();
            object.deleteFromRealm();
        });
    }

    public void deleteAllItem() {
        realm.executeTransaction(realm -> {
            RealmResults<Report> results = realm.where(Report.class).findAll();
            results.deleteAllFromRealm();
        });
    }

    public Report getReport(int id) {
        return realm.where(Report.class).equalTo("id", id).findFirst();
    }

    public ArrayList<Report> getAllReport() {
        RealmResults<Report> items = realm.where(Report.class).findAll();
        ArrayList<Report> itemArrayList = new ArrayList<>();
        itemArrayList.addAll(realm.copyFromRealm(items));
        return itemArrayList;
    }

}
