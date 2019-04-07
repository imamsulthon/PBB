package com.peentar.pbb.ui.main;

/**
 * Created by Imam Sulthon on 3/29/2019.
 */


public interface MainContract {

    interface View {

        void openCamera();

        void openReport();

    }

    interface Presenter {

        void menuClickCamera();

        void menuClickReport();

    }

}
