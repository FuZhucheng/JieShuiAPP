package com.baidumap.test.tap.widget;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidumap.test.tap.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class dangtianyongshui_activity extends AppCompatActivity {

    public LineChart chart;
    public ArrayList<String> labels = new ArrayList<String>();
    public ArrayList<Entry> entries = new ArrayList<Entry>();
    public LineDataSet dataset;
    int color = Color.argb(127,255,0,255);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangtianyongshui_activity);
        dataset = new LineDataSet(entries, "");

        chart = (LineChart) findViewById(R.id.chart);
        initLableData();
        initEntriesData();
        show();
    }
    private void  show(){
        chart.setDescription("时刻");
        chart.setBorderColor(color);

        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        LineData data = new LineData(labels, dataset);
        LimitLine line = new LimitLine(10f);

        chart.setData(data);

    }
    private void initLableData() {
        labels.add("03/22");
        labels.add("03/23");
        labels.add("03/24");
        labels.add("03/25");
        labels.add("03/26");
        labels.add("03/27");
        labels.add("03/28");
    }
    private void initEntriesData() {
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(16f, 4));
        entries.add(new Entry(9f, 5));
        entries.add(new Entry(5f, 6));
    }
}
