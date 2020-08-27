package com.teamx.bottomnav.Buyer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.teamx.bottomnav.R;
import com.teamx.bottomnav.User;

import java.io.IOException;

public class EditBuyerProfile extends AppCompatActivity {
    FloatingActionButton fab;
    Button btnbrowse, btnupload;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    EditText fullName, phoneNo, otherPhoneNo, email, SOR, COR, YOF, HA, StateOFR, ShopLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_buyer_profile);

        storageReference = FirebaseStorage.getInstance().getReference("ProfilePictures");
        databaseReference = FirebaseDatabase.getInstance().getReference("ProfileDetails");
        fab = findViewById(R.id.floatingActionButton3);

        btnbrowse = (Button)findViewById(R.id.button3);
        btnupload= (Button)findViewById(R.id.button4);

        otherPhoneNo = (EditText)findViewById(R.id.ETOPN);
        SOR = (EditText)findViewById(R.id.ETSOOrigin);
        COR = (EditText)findViewById(R.id.editTextTextPersonName2);
        YOF = (EditText)findViewById(R.id.ETYOF);
        HA = (EditText)findViewById(R.id.ETHomeAddress);
        ShopLoc = findViewById(R.id.ETFarmLocation);
        StateOFR = findViewById(R.id.editTextTextPersonName3);

        imgview = (ImageView)findViewById(R.id.imageView5);
        progressDialog = new ProgressDialog(EditBuyerProfile.this);

        clicklistener();
    }

    public void clicklistener(){
        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, Image_Request_Code);

            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadImage();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // String ImageUri = fullName.getText().toString().trim();

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            User imageUploadInfo = new User(taskSnapshot.getUploadSessionUri().toString());
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(EditBuyerProfile.this, "Please Select Image", Toast.LENGTH_LONG).show();

        }
    }

    public void uploadData(){
        // change name just incase the user only wants to change a single item
        User fProfile = new User();
        fProfile.setOtherPhoneNumber(otherPhoneNo.getText().toString().trim());
        fProfile.setHomeAddress(HA.getText().toString().trim());
        fProfile.setFarmLocation(ShopLoc.getText().toString().trim());
        fProfile.setStateOfOrigin(SOR.getText().toString().trim());
        fProfile.setCityOfResidenc(COR.getText().toString().trim());
        fProfile.setStateOfResidence(SOR.getText().toString().trim());

        try {
            databaseReference.push().setValue(fProfile);
            Toast.makeText(EditBuyerProfile.this, "Data is Inserted", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(EditBuyerProfile.this, "Missing Entries", Toast.LENGTH_LONG).show();
        }

    }
}