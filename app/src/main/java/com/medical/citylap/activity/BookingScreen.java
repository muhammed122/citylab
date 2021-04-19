package com.medical.citylap.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.medical.citylap.R;
import com.medical.citylap.helperfunction.FileData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BookingScreen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
Button buttongetimage;
ImageView imageView;
TextView textView;
RadioGroup radioGroup;
RadioButton radioButtonhome;
RadioButton radioButtonlab;
ImageView imageViewback;
LinearLayout linearLayout;
    List<FileData> files = new ArrayList<>();
    ByteArrayOutputStream bytes;
    InputStream inputStream;
    public static final int REQUEST_CAMERA_CODE = 101;
    public static final int REQUEST_GALLERY_CODE = 102;
    private static final int PDF_REQUEST_CODE =103 ;
    //storage data
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_CAMERA = 122;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_GALLERY = 123;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PDF = 124;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen);
        inti();

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(BookingScreen.this,Home.class));
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.inlab_radio_id)
                {
                    linearLayout.setVisibility( View.GONE);

                }
                else
                {
                    linearLayout.setVisibility( View.VISIBLE);
                }



            }
        });
buttongetimage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        popUpMenu(v);
    }
});
    }

    public void inti()
    {
        buttongetimage=findViewById(R.id.upload_image_booking_id);
        textView=findViewById(R.id.text_imag_reservation);
        imageView= findViewById(R.id.imag_imag_reservation);
        radioGroup=findViewById(R.id.radiogroup);
        radioButtonlab=findViewById(R.id.inlab_radio_id);
        radioButtonhome=findViewById(R.id.inhome_test_id);
        linearLayout=findViewById(R.id.layoutaddress);
        imageViewback=findViewById(R.id.backfrom_reservation_to_home);
    }
    private void popUpMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(BookingScreen.this);
        popup.inflate(R.menu.file_type_items);
        popup.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera_item:
                cameraIntent();

                return true;
            case R.id.gallery_item:
                galleryIntent();
                return true;
        }


        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraIntent();
                    return;
                }
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_GALLERY:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent();
                    return;
                }

            default:
                Toast.makeText(this, "يجب السماح بالوصول للملفات لتمام العملية", Toast.LENGTH_SHORT).show();

        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void cameraIntent() {
        if (checkStoragePermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void galleryIntent() {
        if (checkStoragePermission(MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_GALLERY)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);//
            startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_GALLERY_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (data.getData() != null) {
                try {
                    inputStream = this.getContentResolver()
                            .openInputStream(data.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (requestCode == REQUEST_GALLERY_CODE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA_CODE)
                onCaptureImageResult(data);



        }
    }
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                if (bytes == null)
                    bytes = new ByteArrayOutputStream();
                bytes.reset();
                bm.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                String sImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT).trim();

                //add image to adapter
                //    inputStream = requireContext().getContentResolver().openInputStream(data.getData());
                files.add(new FileData(bm, sImage));


                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(files.get(0).getBitmap());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        if (data != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            if (bytes == null)
                bytes = new ByteArrayOutputStream();
            bytes.reset();
            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);

            String sImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT).trim();

            //add image to adapter
            files.add(new FileData(thumbnail, sImage));
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(files.get(0).getBitmap());



        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Boolean checkStoragePermission(final int REQUEST_CODE) {
        if (isStoragePermissionAllowed())
            return true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestStoragePermission(REQUEST_CODE);
        } else {
            return true;
        }

        return true;
    }
    private Boolean isStoragePermissionAllowed() {
        return ContextCompat.
                checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission(final int REQUEST_CODE) {
        if (ActivityCompat.shouldShowRequestPermissionRationale
                (this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //explain why do u want
            showUIMessage("امكانية الوصول الي الملفات لتنفيذ العملية", REQUEST_CODE);
        } else {
            //request permission
            askStoragePermission(REQUEST_CODE);
        }
    }

    private void showUIMessage(String message, final int REQUEST_CODE) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //request permission again
                askStoragePermission(REQUEST_CODE);
            }
        });

        builder.setCancelable(false);
        builder.show();

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askStoragePermission(final int REQUEST_CODE) {
        requestPermissions(
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE);
    }

}