package com.baidumap.test.tap;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.baidumap.test.tap.DayData;
import com.baidumap.test.tap.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class dangtianyongshui_activity extends AppCompatActivity {

    public LineChart chart;
    public ArrayList<String> labels = new ArrayList<String>();
    public ArrayList<Entry> entries = new ArrayList<Entry>();
    public LineDataSet dataset;
    int color = Color.argb(127,255,0,255);
    private DayData dayData;
    private TextView tv_flow,tv_top,tv_compare,tv_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangtianyongshui_activity);
        setTitle("当天用水量");

        initview();
        initData();
        show();
    }

    private void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        dayData= (DayData) bundle.getSerializable("item");
        String sum=dayData.getSum()+"";
        Log.e("sum",sum);
        tv_flow.setText(sum);
        tv_compare.setText(dayData.getDec()+"");
        double max=0;
        double flow=0;
        int index=0;
        int maxtime=0;
        flow=dayData.getFlow1();
        if (flow>max){
            max=flow;
            maxtime=1;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow2();
        if (flow>max){
            max=flow;
            maxtime=2;
        }
//        entries.add(new Entry((float) dayData.getFlow2(),index));
        index++;
        flow=dayData.getFlow3();
        if (flow>max){
            max=flow;
            maxtime=3;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow4();
        if (flow>max){
            max=flow;
            maxtime=4;
        }
//        entries.add(new Entry((float) dayData.getFlow4(),index));
        index++;
        flow=dayData.getFlow5();
        if (flow>max){
            max=flow;
            maxtime=5;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow6();
        if (flow>max){
            max=flow;
            maxtime=index+1;
        }
//        entries.add(new Entry((float) dayData.getFlow6(),index));
        index++;
        flow=dayData.getFlow7();
        if (flow>max){
            max=flow;
            maxtime=index+1;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow8();
        if (flow>max){
            max=flow;
            maxtime=8;
        }
//        entries.add(new Entry((float) dayData.getFlow8(),index));
        index++;
        flow=dayData.getFlow9();
        if (flow>max){
            max=flow;
            maxtime=9;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow10();
        if (flow>max){
            max=flow;
            maxtime=10;
        }
//        entries.add(new Entry((float) dayData.getFlow10(),index));
        index++;
        flow=dayData.getFlow11();
        if (flow>max){
            max=flow;
            maxtime=11;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow12();
        if (flow>max){
            max=flow;
            maxtime=12;
        }
//        entries.add(new Entry((float) dayData.getFlow12(),index));
        index++;
        flow=dayData.getFlow13();
        if (flow>max){
            max=flow;
            maxtime=13;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow14();
        if (flow>max){
            max=flow;
            maxtime=14;
        }
//        entries.add(new Entry((float) dayData.getFlow14(),index));
        index++;
        flow=dayData.getFlow15();
        if (flow>max){
            max=flow;
            maxtime=15;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow16();
        if (flow>max){
            max=flow;
            maxtime=16;
        }
//        entries.add(new Entry((float) dayData.getFlow16(),index));
        index++;
        flow=dayData.getFlow17();
        if (flow>max){
            max=flow;
            maxtime=17;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow18();
        if (flow>max){
            max=flow;
            maxtime=18;
        }
//        entries.add(new Entry((float) dayData.getFlow18(),index));
        index++;
        flow=dayData.getFlow19();
        if (flow>max){
            max=flow;
            maxtime=19;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow20();
        if (flow>max){
            max=flow;
            maxtime=20;
        }
//        entries.add(new Entry((float) dayData.getFlow20(),index));
        index++;
        flow=dayData.getFlow21();
        if (flow>max){
            max=flow;
            maxtime=21;
        }
        entries.add(new Entry((float) flow,index));
        index++;
        flow=dayData.getFlow22();
        if (flow>max){
            max=flow;
            maxtime=22;
        }
//        entries.add(new Entry((float) dayData.getFlow22(),index));
        index++;
        flow=dayData.getFlow23();
        if (flow>max){
            max=flow;
            maxtime=23;
        }
        entries.add(new Entry((float) flow,index));
        index++;

        flow=dayData.getFlow24();
        if (flow>max){
            max=flow;
            maxtime=24;
        }
        tv_top.setText(maxtime+"");
//        entries.add(new Entry((float) dayData.getFlow24(),index));
        for(int i=1;i<=24;i++){
            labels.add(i+"时");
        }
    }

    private void initview() {
        tv_flow= (TextView) findViewById(R.id.flow);
        tv_compare= (TextView) findViewById(R.id.compare);
        tv_top= (TextView) findViewById(R.id.top_use);
        tv_n= (TextView) findViewById(R.id.support_n);
        chart = (LineChart) findViewById(R.id.chart);
    }

    private void  show(){
        chart.setDescription("时刻");
        chart.setBorderColor(color);
        dataset = new LineDataSet(entries, "");
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
