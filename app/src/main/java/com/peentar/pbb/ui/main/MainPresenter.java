package com.peentar.pbb.ui.main;

/**
 * Created by Imam Sulthon on 3/29/2019.
 */


public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void menuClickCamera() {
        view.openCamera();
    }

    @Override
    public void menuClickReport() {
        view.openReport();
    }

}
