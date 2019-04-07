package com.peentar.pbb.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.peentar.pbb.R;
import com.peentar.pbb.ui.addphoto.AddPhotoActivity;
import com.peentar.pbb.ui.report.ReportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.btn_kirim_photo) Button btnSendPhoto;
    @BindView(R.id.btn_lihat_laporan) Button btnSendReport;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        btnSendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.menuClickCamera();
            }
        });

        btnSendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.menuClickReport();
            }
        });
    }

    @Override
    public void openCamera() {
        Intent intent = new Intent(MainActivity.this, AddPhotoActivity.class);
        startActivity(intent);
    }

    @Override
    public void openReport() {
        Intent intent = new Intent(MainActivity.this, ReportActivity.class);
        startActivity(intent);
    }

}
