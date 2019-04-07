package com.peentar.pbb.ui.report;

import com.peentar.pbb.model.Report;

import java.util.ArrayList;

/**
 * Created by Imam Sulthon on 3/29/2019.
 */


public interface ReportContract {

    interface View {

        void showReport(ArrayList<Report> reportList);

    }

    interface Presenter {

        void getPhotoReport();

    }

}
