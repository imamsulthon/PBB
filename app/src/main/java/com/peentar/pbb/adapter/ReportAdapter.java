package com.peentar.pbb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peentar.pbb.ui.details.DetailsActivity;
import com.peentar.pbb.util.FileUtils;
import com.peentar.pbb.R;
import com.peentar.pbb.model.Report;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Imam Sulthon on 4/1/2019.
 */


public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Report> arrayList;

    public ReportAdapter(Context context, ArrayList<Report> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report report = arrayList.get(position);
        String currentImagePath = report.getImagePath();
        holder.imageView.setImageBitmap(FileUtils.getResizedBitmap(currentImagePath, 512, 512));
        holder.tvTitle.setText(String.valueOf(report.getId()));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(DetailsActivity.KEY,  report.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview) ImageView imageView;
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_caption) TextView tvCaption;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
