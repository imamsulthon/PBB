package com.peentar.pbb.ui.details;

import com.peentar.pbb.database.RealmDBHelper;
import com.peentar.pbb.model.Report;

/**
 * Created by Imam Sulthon on 4/3/2019.
 */


public class DetailsPresenter implements DetailsContract.Presenter {

    DetailsContract.View view;
    RealmDBHelper database;

    public DetailsPresenter(DetailsContract.View view) {
        this.view = view;
        database = new RealmDBHelper();
    }

    @Override
    public void getReport(int id) {
        try {
            database.open();
            Report report = database.getReport(id);
            view.showDetails(report);
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error!");
        }
    }

}
