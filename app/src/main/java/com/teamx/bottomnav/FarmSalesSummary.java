package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FarmSalesSummary extends AppCompatActivity {

    EditText xdata, ydata;
    Button insertbtn;
    LineChart linechart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    LineData lineData;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_sales_summary);

        linechart = findViewById(R.id.lineChart);
        txt1 = findViewById(R.id.text);
        txt2 = findViewById(R.id.text2);
        txt3 = findViewById(R.id.text3);

        reff = FirebaseDatabase.getInstance().getReference("SaleDetails");

        lineDataSet.setLineWidth(5);

    }

    @Override
    protected void onStart() {
        retriveData();
        super.onStart();
    }

    public void retriveData() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> dataVals = new ArrayList<Entry>();
               // int totinc= Integer.parseInt(txt1.getText().toString());
              //  int totbac= Integer.parseInt(txt2.getText().toString());
              //  int cupri= Integer.parseInt(txt3.getText().toString());

                if (snapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        DataPoint dataPoint = dataSnapshot.getValue(DataPoint.class);
                        //String totalIncome
                       // int get1 = dataPoint.getPrice();
                      //  int get2 = dataPoint.getNumberOfBaskets();
                      //  int get3 = dataPoint.getPrice();
                      //  get1 += get1;
                       // get2 +=get2;
                     //   txt1.setText((get1 * get2));
                     //   txt2.setText(get2);
                     //   txt2.setText((get3));

                        dataVals.add(new Entry(dataPoint.getNumberOfBaskets(), dataPoint.getPrice()));
                    }

                    showChart(dataVals);
                }else {
                    linechart.clear();
                    linechart.invalidate();
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("The Number Of Baskets Sold Versus The Price");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        linechart.clear();
        linechart.setData(lineData);
        linechart.invalidate();
    }
}