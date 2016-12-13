package com.example.ongteckwu.iotproj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ongteckwu.iotproj.modules.DataModule;
import com.example.ongteckwu.iotproj.modules.ModType;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DataModuleActivity extends AbstractModuleActivity {
    private TextView titleView;
    private TextView dataView;
    private TextView graphView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_module);

        // get module
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        DataModule module = (DataModule) intent.getSerializableExtra("module");
        ModType modType = module.getModType();

        // initialize inner view
        titleView = (TextView) findViewById(R.id.data_component_moduleTitle);
        dataView = (TextView) findViewById(R.id.data_component_sensorText);
        graphView = (TextView) findViewById(R.id.data_component_graphText);

        String sensorText = modType.getName().toUpperCase() + " SENSOR";
        String graphText = modType.getName().toUpperCase() + " AGAINST TIME";

        titleView.setText(sensorText);
        dataView.setText(modType.getName().toUpperCase());
        graphView.setText(graphText);

        LineChart mChart = (LineChart) findViewById(R.id.data_component_chart);

        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            entries.add(new Entry(i, (i+2)*(i-3)*(i-4)));
        }

        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setMinimumHeight(1000);
        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setHighlightPerDragEnabled(true);

        // clear default description
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);

        // set an alternative background color
        mChart.setBackgroundColor(getResources().getColor(R.color.lightWhite));
        mChart.setViewPortOffsets(0f, 0f, 0f, 0f);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(10f);
        xAxis.setAxisMinimum(-1);
        xAxis.setAxisMaximum(25);
        xAxis.setTextColor(Color.rgb(255, 128, 192));
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setGranularityEnabled(true);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        LineDataSet dataSet = new LineDataSet(entries, "Great");
        dataSet.setDrawFilled(true);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setFillColor(R.color.colorPrimary);
        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);

    }
}
