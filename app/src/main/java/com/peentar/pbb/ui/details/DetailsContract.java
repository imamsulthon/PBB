package com.peentar.pbb.ui.details;

import com.peentar.pbb.model.Report;

/**
 * Created by Imam Sulthon on 4/3/2019.
 */


public interface DetailsContract {

    interface View {

        void showDetails(Report report);

        void showMessage(String message);

    }

    interface Presenter {

        void getReport(int id);

    }

}
