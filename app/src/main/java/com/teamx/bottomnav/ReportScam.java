package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportScam extends AppCompatActivity {
    Button send;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_scam);

        send = findViewById(R.id.button3);
        editText = findViewById(R.id.editTextTextMultiLine);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                Toast.makeText(ReportScam.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}