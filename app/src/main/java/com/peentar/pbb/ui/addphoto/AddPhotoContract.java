package com.peentar.pbb.ui.addphoto;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by Imam Sulthon on 3/30/2019.
 */


public interface AddPhotoContract {

    interface View {

        boolean checkPermission();

        void showPermissionDialog();

        boolean shouldShowDialog();

        void showUnlockPermissionsDialog();

        void chooseImageFrom();

        void saveToGallery(Intent intent);

        void showMessage(String message);



    }

    interface Presenter {

        void permissionDenied();

        void takePicture();

        void saveImage(Uri uri, String imagePath, String caption);

    }

}
