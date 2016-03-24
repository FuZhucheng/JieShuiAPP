package com.baidumap.test.tap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidumap.test.tap.widget.XScrollView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements XScrollView.IXScrollViewListener{
    private XScrollView mScrollView;

    private Handler mHandler;

    private View content;
    public BarChart barChart;
    private TextView tv_flow,tv_save,tv_pro,tv_use;
    public ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();
        initGridData();
        initEntriesData();
        initLableData();
        initListener();
        show();
        initTips();
    }

    protected void initView() {

        mHandler = new Handler();
        mScrollView = (XScrollView) findViewById(R.id.scroll_view);
        mScrollView.setPullRefreshEnable(true);
        mScrollView.setPullLoadEnable(false);
        mScrollView.setAutoLoadEnable(false);
        mScrollView.setIXScrollViewListener(this);
        mScrollView.setRefreshTime(getTime());
        content= LayoutInflater.from(this).inflate(R.layout.testscrollview,null);
        barChart = (BarChart)content.findViewById(R.id.chart);
        tv_flow= (TextView) content.findViewById(R.id.flow);
        tv_save= (TextView)content.findViewById(R.id.save);
        tv_use= (TextView) content.findViewById(R.id.use);
        tv_pro= (TextView) content.findViewById(R.id.pro);
        mScrollView.setView(content);
    }

    private void initTips() {
    }

    private void initGridData() {
    }
    private void initListener() {
//        设置点击柱子的监听
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Toast.makeText(MainActivity.this,e.getXIndex()+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void show() {
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawHighlightArrow(false);
        dataset = new BarDataSet(entries, "");
//        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //color可自定格式例如 Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120),
//        Color.rgb(106, 167, 134), Color.rgb(53, 194, 209)的数组
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData data = new BarData(labels, dataset);
        LimitLine line = new LimitLine(10f);
        barChart.setData(data);
        barChart.animateY(2000);
        barChart.setDescription("最近一周用水情况");
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
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(16f, 4));
        entries.add(new BarEntry(9f, 5));
        entries.add(new BarEntry(5f, 6));
    }
//看到界面自动刷新
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        if (hasFocus) {
//            mScrollView.autoRefresh();
//        }
//    }

//核心蓝牙传输代码
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
                onLoad();
            }
        }, 2500);
    }

    @Override
    public void onLoadMore() {

    }


    private void onLoad() {
        mScrollView.stopRefresh();
        mScrollView.stopLoadMore();
        mScrollView.setRefreshTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
}
