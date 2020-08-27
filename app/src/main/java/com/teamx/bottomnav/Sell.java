package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sell extends AppCompatActivity {

    EditText CN, CE, Price, CTS, NBTS;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff, reff1;
    FloatingActionButton insertbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        CN = (EditText) findViewById(R.id.editTextTextPersonName);
        CE = (EditText) findViewById(R.id.editTextTextPersonName4);
        CTS = (EditText) findViewById(R.id.editTextTextPersonName5);
        Price = (EditText) findViewById(R.id.editTextNumber);
        NBTS = (EditText) findViewById(R.id.editTextNumber2);

        insertbtn = findViewById(R.id.floatingActionButton4);

      //  reff = FirebaseDatabase.getInstance().getReference("ChartValues");
        reff = FirebaseDatabase.getInstance().getReference("SaleDetails");
        insertData();

    }

    public void insertData() {
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = reff.push().getKey();
                int x = Integer.parseInt(NBTS.getText().toString());
                int y = Integer.parseInt(Price.getText().toString());
                String NameOfCustomer = CN.getText().toString();
                String Email = CE.getText().toString();
                String CropToSell = CTS.getText().toString();

               // DataPoint dataPoint = new DataPoint(x, y);
               // reff.child(id).setValue(dataPoint);

                DataPoint dataPoint1 = new DataPoint(x, y, NameOfCustomer, Email, CropToSell);
                reff.child(id).setValue(dataPoint1);
            }
        });
    }
}