package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class FarmerProduce extends AppCompatActivity implements View.OnClickListener {

    Button btnUpload, btnChoose;
    FloatingActionButton save;
    EditText cA, sPB, NOBA, NOHPY, PLS, PLST, HSS, HSSST;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    CheckBox maize, pepper, tomato, soyabeans, potato, orange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_produce);


       // cA = findViewById(R.id.cropsAvail);
        sPB = findViewById(R.id.sale_per_basket);
        NOBA = findViewById(R.id.no_of_baskets);
        NOHPY = findViewById(R.id.num_harvest_per_year);
        PLS = findViewById(R.id.planting_starts);
        PLST = findViewById(R.id.planting_ends);
        HSS = findViewById(R.id.harvest_starts);
        HSSST = findViewById(R.id.harvest_ends);

        // initiate views
        maize = (CheckBox) findViewById(R.id.maize);
        maize.setOnClickListener(this);
        orange = (CheckBox) findViewById(R.id.orange);
        orange.setOnClickListener(this);
        potato = (CheckBox) findViewById(R.id.potato);
        potato.setOnClickListener(this);
        tomato = (CheckBox) findViewById(R.id.tomato);
        tomato.setOnClickListener(this);
        pepper = (CheckBox) findViewById(R.id.pepper);
        pepper.setOnClickListener(this);
        soyabeans = (CheckBox) findViewById(R.id.soyabeans);
        soyabeans.setOnClickListener(this);

        //   btnChoose = findViewById(R.id.button7);
     //   btnUpload = findViewById(R.id.button8);
        save = findViewById(R.id.floatingActionButton5);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();
            }
        });
    }

    public void saveDetails() {
        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.orange:
                if (orange.isChecked())
                    Toast.makeText(getApplicationContext(), "Orange", Toast.LENGTH_LONG).show();
                break;
            case R.id.potato:
                if (potato.isChecked())
                    Toast.makeText(getApplicationContext(), "Potato", Toast.LENGTH_LONG).show();
                break;
            case R.id.soyabeans:
                if (soyabeans.isChecked())
                    Toast.makeText(getApplicationContext(), "Soya Beans", Toast.LENGTH_LONG).show();
                break;
            case R.id.maize:
                if (maize.isChecked())
                    Toast.makeText(getApplicationContext(), "Maize", Toast.LENGTH_LONG).show();
                break;
            case R.id.pepper:
                if (pepper.isChecked())
                    Toast.makeText(getApplicationContext(), "Pepper", Toast.LENGTH_LONG).show();
                break;
            case R.id.tomato:
                if (tomato.isChecked())
                    Toast.makeText(getApplicationContext(), "Tomato", Toast.LENGTH_LONG).show();
                break;
        }
    }
}