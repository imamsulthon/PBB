package com.peentar.pbb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.peentar.pbb.model.Report;

import java.util.ArrayList;

/**
 * Created by Imam Sulthon on 4/2/2019.
 */


public class ReportDBSource {

    private SQLiteDatabase database;
    private ReportDBHelper dbHelper;
    private Context context;

    private String[] allColumns = {
            ReportDBHelper.COLUMN_ID,
            ReportDBHelper.COLUMN_TITLE,
            ReportDBHelper.COLUMN_IMAGE_PATH,
            ReportDBHelper.COLUMN_CAPTION,
    };

    public ReportDBSource(Context context) {
        dbHelper = new ReportDBHelper(context);
        this.context = context;
    }

    public void open() throws SQLiteException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addReport(Report report) {
        ContentValues values = new ContentValues();
        values.put(ReportDBHelper.COLUMN_ID, report.getId());
        values.put(ReportDBHelper.COLUMN_TITLE, report.getTitle());
        values.put(ReportDBHelper.COLUMN_IMAGE_PATH, report.getImagePath());
        values.put(ReportDBHelper.COLUMN_CAPTION, report.getCaption());
    }

    public ArrayList<Report> getAllReport() {
        ArrayList<Report> reports = new ArrayList<>();
        Cursor cursor = database.query(ReportDBHelper.TABLE_REPORT, allColumns, null, null,
                null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report report = getData(cursor);
            reports.add(report);
            cursor.moveToNext();
        }
        cursor.close();
        return reports;
    }

    private Report getData(Cursor cursor) {
        Report report = new Report();
        report.setId(cursor.getInt(0));
        report.setTitle(cursor.getString(1));
        report.setImagePath(cursor.getString(2));
        report.setCaption(cursor.getString(3));
        return report;
    }
}
