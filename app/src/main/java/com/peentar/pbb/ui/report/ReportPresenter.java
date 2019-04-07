package com.peentar.pbb.ui.report;

import android.util.Log;

import com.peentar.pbb.database.RealmDBHelper;
import com.peentar.pbb.model.Report;

import java.util.ArrayList;

/**
 * Created by Imam Sulthon on 3/29/2019.
 */


public class ReportPresenter implements ReportContract.Presenter {

    ReportContract.View view;
    private RealmDBHelper dataSource;

    public ReportPresenter(ReportContract.View view) {
        this.view = view;
        dataSource = new RealmDBHelper();
    }

    @Override
    public void getPhotoReport() {
        try {
            ArrayList<Report> reports = new ArrayList<>();
            dataSource.open();
            reports.addAll(dataSource.getAllReport());
            if (reports.size() > 0) {
                view.showReport(reports);
                Log.e("DATA", String.valueOf(reports.size()));
            } else {
                Log.e("DATA", "No report saved");
            }
            dataSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
