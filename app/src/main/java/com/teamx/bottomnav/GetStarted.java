package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GetStarted extends AppCompatActivity {
    RadioButton radioButton, radioButton2, radioButton3;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        // creating an intent object and passing the new activity to navigate to
        final Intent intent = new Intent(GetStarted.this, SignUp.class);
        // creating an object of bundle
        final Bundle bundle = new Bundle();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    bundle.putString("Choice", "Farmer Account");
                    intent.putExtras(bundle);
                    // starting the activity
                    startActivity(intent);
                }
                else if (checkedId == R.id.radioButton2) {
                    bundle.putString("Choice", "Buyer Account");
                    intent.putExtras(bundle);
                    // starting the activity
                    startActivity(intent);
                }
                else if (checkedId == R.id.radioButton3) {
                    bundle.putString("Choice", "Expert Account");
                    intent.putExtras(bundle);
                    // starting the activity
                    startActivity(intent);
                }
            }
        });
    }
}