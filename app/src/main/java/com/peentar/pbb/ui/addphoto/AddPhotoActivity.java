package com.peentar.pbb.ui.addphoto;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.peentar.pbb.util.FileUtils;
import com.peentar.pbb.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPhotoActivity extends AppCompatActivity implements AddPhotoContract.View {

    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final int REQUEST_TAKE_PHOTO = 2;
    static String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };;

    @BindView(R.id.btn_add_photo) Button btnAddPhoto;
    @BindView(R.id.btn_save_photo) Button btnSavePhoto;
    @BindView(R.id.iv_result) ImageView imageView;
    @BindView(R.id.et_caption) EditText etCaption;

    private Uri capturedImageURI = null;
    private String currentImagePath = null;
    private String caption = null;
    private AlertDialog unlockDialog;

    AddPhotoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        ButterKnife.bind(this);

        presenter = new AddPhotoPresenter(this);

        btnAddPhoto.setOnClickListener(v -> presenter.takePicture());

        btnSavePhoto.setOnClickListener(v -> {
            presenter.saveImage(capturedImageURI, currentImagePath, etCaption.getText().toString());
        });
    }

    @Override
    public boolean checkPermission() {
        for (String p: permissions) {
            int result = ActivityCompat.checkSelfPermission(getApplicationContext(), p);
            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void showPermissionDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        }
    }

    @Override
    public boolean shouldShowDialog() {
        for (String p: permissions) {
            boolean b = ActivityCompat.shouldShowRequestPermissionRationale(this, p);
            if (!b) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void showUnlockPermissionsDialog() {
        if(unlockDialog == null) {
            unlockDialog = new AlertDialog.Builder(getApplicationContext())
                    .setTitle(getString(R.string.permission_dialog_title))
                    .setMessage(getString(R.string.permission_dialog_message))
                    .create();
        }

        unlockDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (!allPermissionsGranted(grantResults)) {
                presenter.permissionDenied();
            }
        }
    }

    private boolean allPermissionsGranted(int[] grantResults) {
        for (int result : grantResults) {
            if(result == PackageManager.PERMISSION_DENIED)
                return false;
        }

        return true;
    }

    @Override
    public void chooseImageFrom() {

        File rootDir = new File(FileUtils.PICTURE_DIRECTORY);
        rootDir.mkdirs();

        File file = new File(rootDir, FileUtils.generateImageName());
        capturedImageURI = Uri.fromFile(file);

        final List<Intent> cameraIntents = new ArrayList<>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();

        if (packageManager != null) {
            final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo resolveInfo: listCam) {
                if (resolveInfo.activityInfo != null) {
                    final String packageName = resolveInfo.activityInfo.packageName;
                    final Intent intent = new Intent(captureIntent);
                    intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    intent.setPackage(packageName);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageURI);
                    cameraIntents.add(intent);
                }
            }
        }

        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
        startActivityForResult(chooserIntent, REQUEST_TAKE_PHOTO);
    }

    @Override
    public void saveToGallery(Intent intent) {
        this.sendBroadcast(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            final Uri selectedImageUri = (data == null) ? capturedImageURI : data.getData();
            if (selectedImageUri != null) {
                currentImagePath = FileUtils.getPath(this, selectedImageUri);
                if (currentImagePath != null && !currentImagePath.isEmpty()) {
                    imageView.setImageBitmap(FileUtils.getResizedBitmap(currentImagePath, 512, 512));
                    btnAddPhoto.setText(R.string.retake_photo);
                    etCaption.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
