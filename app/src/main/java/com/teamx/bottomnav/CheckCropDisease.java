package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckCropDisease extends AppCompatActivity {
    CardView expert, bot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_crop_disease);

        expert = (CardView) findViewById(R.id.expert);
        bot = (CardView) findViewById(R.id.bot);

        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCropDisease.this, FindBuyer.class));
            }
        });
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckCropDisease.this, CropDisease.class));
            }
        });

    }
}