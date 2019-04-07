package com.peentar.pbb.ui.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.peentar.pbb.R;
import com.peentar.pbb.adapter.ReportAdapter;
import com.peentar.pbb.model.Report;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportActivity extends AppCompatActivity implements ReportContract.View {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    ReportPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        presenter = new ReportPresenter( this);
        presenter.getPhotoReport();
    }

    @Override
    public void showReport(ArrayList<Report> reportList) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        ReportAdapter adapter = new ReportAdapter(this, reportList);
        recyclerView.setAdapter(adapter);
    }

}
