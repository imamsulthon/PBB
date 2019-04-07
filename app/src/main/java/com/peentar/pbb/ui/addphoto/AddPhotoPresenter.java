package com.peentar.pbb.ui.addphoto;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.peentar.pbb.database.RealmDBHelper;
import com.peentar.pbb.model.Report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Imam Sulthon on 3/30/2019.
 */


public class AddPhotoPresenter implements AddPhotoContract.Presenter {

    AddPhotoContract.View view;
    private RealmDBHelper databaseHelper;

    public AddPhotoPresenter(AddPhotoContract.View view) {
        this.view = view;
        databaseHelper = new RealmDBHelper();
    }

    @Override
    public void permissionDenied() {
        if (view.shouldShowDialog()) {
            view.showPermissionDialog();
        } else {
            view.showUnlockPermissionsDialog();
        }
    }

    @Override
    public void takePicture() {
        if (!view.checkPermission()) {
            view.showPermissionDialog();
            return;
        }
        view.chooseImageFrom();
    }

    @Override
    public void saveImage(Uri uri, String imagePath, String caption) {
        if (uri != null) {
            final Intent mediascanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediascanIntent.setData(uri);
            Report report = new Report(imagePath, caption, getDate());
            Log.e("data", report.getDate() + ", " + report.getCaption() + ", " + report.getId());
            try {
                databaseHelper.open();
                databaseHelper.addItem(report);
                databaseHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            view.saveToGallery(mediascanIntent);
            view.showMessage("Success adding report");
        } else {
            view.showMessage("Choose photo first!");
        }
    }

    public static String getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }

}
