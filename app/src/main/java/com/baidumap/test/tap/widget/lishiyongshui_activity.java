package com.baidumap.test.tap.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
    public TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,tv19,tv20,tv21,tv22,tv23,tv24,tv25,tv26,tv27,tv28;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lishiyongshui_activity);
        initView();
        initListener();

    }
    //初始化所有日期view
    private void initView() {
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        tv3= (TextView) findViewById(R.id.tv3);
        tv4= (TextView) findViewById(R.id.tv4);
        tv5= (TextView) findViewById(R.id.tv5);
        tv6= (TextView) findViewById(R.id.tv6);
        tv7= (TextView) findViewById(R.id.tv7);
        tv8= (TextView) findViewById(R.id.tv8);
        tv9= (TextView) findViewById(R.id.tv9);
        tv10= (TextView) findViewById(R.id.tv10);
        tv11= (TextView) findViewById(R.id.tv11);
        tv12= (TextView) findViewById(R.id.tv12);
        tv13= (TextView) findViewById(R.id.tv13);
        tv14= (TextView) findViewById(R.id.tv14);
        tv15= (TextView) findViewById(R.id.tv15);
        tv16= (TextView) findViewById(R.id.tv16);
        tv17= (TextView) findViewById(R.id.tv17);
        tv18= (TextView) findViewById(R.id.tv18);
        tv19= (TextView) findViewById(R.id.tv19);
        tv20= (TextView) findViewById(R.id.tv20);
        tv21= (TextView) findViewById(R.id.tv21);
        tv22= (TextView) findViewById(R.id.tv22);
        tv23= (TextView) findViewById(R.id.tv23);
        tv24= (TextView) findViewById(R.id.tv24);
        tv25= (TextView) findViewById(R.id.tv25);
        tv26= (TextView) findViewById(R.id.tv26);
        tv27= (TextView) findViewById(R.id.tv27);
        tv28= (TextView) findViewById(R.id.tv28);
    }

        //监听日期，实现跳转到当天用水
    public void initListener(){
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent1);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent();
                intent3.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent3);
            }
        });

        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7=new Intent();
                intent7.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent7);
            }
        });

        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9=new Intent();
                intent9.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent9);
            }
        });
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11=new Intent();
                intent11.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent11);
            }
        });
        tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent13=new Intent();
                intent13.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent13);
            }
        });
        tv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent15=new Intent();
                intent15.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent15);
            }
        });
        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent17=new Intent();
                intent17.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent17);
            }
        });
        tv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent19=new Intent();
                intent19.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent19);
            }
        });
        tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent21=new Intent();
                intent21.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent21);
            }
        });
        tv23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent23=new Intent();
                intent23.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent23);
            }
        });
        tv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent25=new Intent();
                intent25.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent25);
            }
        });
        tv27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent27=new Intent();
                intent27.setClass(lishiyongshui_activity.this,dangtianyongshui_activity.class);
                startActivity(intent27);
            }
        });
    }
    private void  show(){
        chart.setDescription("");

    }
}
