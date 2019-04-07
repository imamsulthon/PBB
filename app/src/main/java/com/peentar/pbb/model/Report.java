package com.peentar.pbb.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Imam Sulthon on 4/1/2019.
 */


public class Report extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private String title;
    private String imagePath;
    private String caption;
    private String date;

    public Report() {
    }

    public Report(String imagePath, String caption, String date) {
        this.imagePath = imagePath;
        this.caption = caption;
        this.date = date;
    }

    protected Report(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imagePath = in.readString();
        caption = in.readString();
        date = in.readString();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(imagePath);
        dest.writeString(caption);
        dest.writeString(date);
    }
}
