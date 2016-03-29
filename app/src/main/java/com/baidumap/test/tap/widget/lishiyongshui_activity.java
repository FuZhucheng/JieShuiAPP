package com.baidumap.test.tap.widget;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidumap.test.tap.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class lishiyongshui_activity extends AppCompatActivity {

    public LineChart chart;
    public ArrayList<String> labels = new ArrayList<String>();
    public ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    public LineDataSet dataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lishiyongshui_activity);
    }

    private void  show(){
        chart.setDescription("");

    }
}
