package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.teamx.bottomnav.Buyer.ui.BuyerMainActivity;
import com.teamx.bottomnav.Expert.ui.ui.ExpertMainActivity;

public class Enter extends AppCompatActivity {

    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        radioGroup = findViewById(R.id.radioGroup);
        try{

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioButton) {
                        Toast.makeText(Enter.this, "Main Activity", Toast.LENGTH_SHORT).show();
                        Intent mainIntent=new Intent(Enter.this,MainActivity.class);
                        startActivity(mainIntent);
                    }
                    else if (checkedId == R.id.radioButton2) {
                        Toast.makeText(Enter.this, "BuyerMainActivity", Toast.LENGTH_SHORT).show();
                        Intent mainIntent1=new Intent(Enter.this, BuyerMainActivity.class);
                        startActivity(mainIntent1);
                    }
                    else if (checkedId == R.id.radioButton3) {
                        // do operations specific to this selection
                        Toast.makeText(Enter.this, "ExpertMainActivity", Toast.LENGTH_SHORT).show();
                        Intent mainIntent2=new Intent(Enter.this, ExpertMainActivity.class);
                        startActivity(mainIntent2);
                    }
                }
            });
        } catch (Exception e) {
            Intent mainIntent=new Intent(Enter.this,MainActivity.class);
            startActivity(mainIntent);
        }

    }
}