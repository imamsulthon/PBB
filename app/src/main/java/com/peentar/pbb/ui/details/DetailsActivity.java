package com.peentar.pbb.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.peentar.pbb.R;
import com.peentar.pbb.model.Report;
import com.peentar.pbb.util.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    public static final String KEY = "key";

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_caption) TextView tvCaption;
    @BindView(R.id.imageview) ImageView imageView;

    DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        presenter = new DetailsPresenter(this);
        if (getIntent().getIntExtra(KEY, 0) != 0) {
            presenter.getReport(getIntent().getIntExtra(KEY, 0));
        } else {
            showMessage("Error!");
        }
    }

    @Override
    public void showDetails(Report report) {
        tvTitle.setText(report.getImagePath());
        tvDate.setText(report.getDate());
        tvCaption.setText(report.getCaption());
        if (report.getImagePath() != null) {
            imageView.setImageBitmap(FileUtils.getResizedBitmap(report.getImagePath(), 512, 512));
        } else {
            showMessage("Something error happened!");
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
