package com.peentar.pbb.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Imam Sulthon on 4/2/2019.
 */


public class ReportDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_REPORT = "photo_report";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE_PATH = "image_path";
    public static final String COLUMN_CAPTION = "caption";

    private static final String DB_NAME = "report.db";
    private static final int DB_VERSION = 1;

    public ReportDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "
                + TABLE_REPORT + "("
                + COLUMN_ID + " integer, "
                + COLUMN_TITLE + " text, "
                + COLUMN_IMAGE_PATH + " text, "
                + COLUMN_CAPTION + " text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
        onCreate(db);
    }
}
