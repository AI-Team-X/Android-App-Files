package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SignUp extends AppCompatActivity {
    EditText emailId, password, fullName, NIN, phoneNumber;
    Button btnSignup;
    TextView tvSignIn;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ImageView showUserProfile;
    private final Integer PICK_IMAGE_REQUEST=1;
    Bitmap bitmap;
    Uri uri;
    FirebaseAuth auth;
    String user_email,user_password,user_full_name,user_nin,user_phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();

        // setup the alert builder
        // fetching the data from the bundle

        initView();
        defineView();
        addCLicklistener();

        firebaseAuth = FirebaseAuth.getInstance();

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));

            }
        });
    }

    private void defineView(){
        emailId = (EditText) findViewById(R.id.emailLogin);
        password = findViewById(R.id.sign_up_password);
        fullName = (EditText) findViewById(R.id.fullName);
        NIN = findViewById(R.id.NIN);
        phoneNumber = findViewById(R.id.phoneNumber);
        btnSignup = findViewById(R.id.btn_signup);
        tvSignIn = findViewById(R.id.textView11);
        showUserProfile=findViewById(R.id.imageView3);

    }
    private void initView(){
        auth=FirebaseAuth.getInstance();

    }

    private boolean validate(){
        boolean isValid=false;

        user_email = emailId.getText().toString();
        user_password = password.getText().toString();
         user_full_name = fullName.getText().toString();
        user_nin = NIN.getText().toString();
        user_phone_number = phoneNumber.getText().toString();
        if (TextUtils.isEmpty(user_email)) {
            emailId.setError("Please enter email");
            emailId.requestFocus();
        } else if (TextUtils.isEmpty(user_password)) {
            password.setError("Please enter your password");
            password.requestFocus();
        } else if (TextUtils.isEmpty(user_full_name)) {
            password.setError("Please enter your full name");
            password.requestFocus();
        } else if (TextUtils.isEmpty(user_nin)) {
            password.setError("Please enter your NIN");
            password.requestFocus();
        } else if (TextUtils.isEmpty(user_phone_number)) {
            password.setError("Please enter your phone number");
            password.requestFocus();
        }
        else if(uri==null)
            Toast.makeText(this, "Please select the image", Toast.LENGTH_SHORT).show();
        else
            isValid=true;
        return isValid;
    }
    private void addCLicklistener(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                    registerUserToDatabse();
            }
        });
        showUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

    }

    private void registerUserToDatabse(){

        try{
            auth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(SignUp.this, "succesfully created user::email is:"+ task.getResult().getUser().getEmail(), Toast.LENGTH_SHORT).show();
                    addUserInDatabse(task.getResult().getUser());
                }
            });
        } catch (Exception e) {
            Toast.makeText(SignUp.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();

        }



    }
    private void addUserInDatabse(final FirebaseUser firebaseUser){

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] data = bytes.toByteArray();
        Bundle bundle = getIntent().getExtras();

        final String user_type = bundle.getString("Choice");

        FirebaseStorage.getInstance().getReference().child("all_users").child(firebaseUser.getUid())
                .child("profile_pic")
                .putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url=taskSnapshot.getUploadSessionUri().toString();
                        User user=new User(user_type,user_nin,firebaseUser.getEmail(), user_password, user_phone_number, user_full_name,url,firebaseUser.getUid());
                        FirebaseDatabase.getInstance().getReference().child("all_users")
                                .child(firebaseUser.getUid()).setValue(user);

                        Intent intent = new Intent(SignUp.this, SignIn.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Choice", user_type);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(SignUp.this, "succesfully added!:", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                showUserProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}