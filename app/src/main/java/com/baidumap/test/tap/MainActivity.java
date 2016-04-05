package com.baidumap.test.tap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.baidumap.test.tap.widget.TIPS;
import com.baidumap.test.tap.esptouch.demo_activity.EsptouchDemoActivity;
import com.baidumap.test.tap.widget.XScrollView;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import net.tsz.afinal.FinalDb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements XScrollView.IXScrollViewListener{
    private XScrollView mScrollView;

    private Handler mHandler,recHandler;

    private FinalDb db;


    private Button btn_lookmore;
    private View view1, view2, view3;
    private ViewPager viewPager;
    private List<View> viewList; //把需要滑动的页卡添加到这个list中

    private View content;
    public BarChart barChart;
    private TextView tv_totaluse,tv_totalpro;
    public ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    public ImageView img_1,img_2,img_3;
    private ImageView iv_drop,iv_ele;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Long time;
    private Long lastrefresh=0l;
    private String maxid;

    private int uid=1;//设备id

    List<DayData> listshow=new ArrayList<DayData>();

    public static final int LATEST_TIME=1;
    public static final int BAR_DATA=2;
    public static final int MAX_ID=3;
    public static final int SUM_USE=4;

    //初始化那个viewpager的view里面的控件。
    public BarDataSet dataset;
//    public TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,tv19,tv20,tv21,tv22,tv23,tv24,tv25,tv26,tv27,tv28;
    public ArrayList<String> labels = new ArrayList<String>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("节水");

        initView();
        initSharedPreferences();
        initDB();

        initBarData();
        initListener();
//        show();
        initTips();


        //设置viewPager适配器
        PagerAdapter pagerAdapter = new PagerAdapter() {

            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            public int getCount() {
                return viewList.size();
            }

            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));
            }

            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

        };
        viewPager.setAdapter(pagerAdapter);

    }

    private void initBarData() {
        int index=0;
        entries=new ArrayList<BarEntry>();
        labels=new ArrayList<String>();
        maxid=sharedPreferences.getInt("maxid",0)-7+"";
        listshow=db.findAllByWhere(DayData.class,"id>"+maxid);
        for (DayData daydata:listshow){
            double sum=daydata.getSum();
            entries.add(new BarEntry((float) sum,index));
            index++;
            Date date=daydata.getDate();
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String datestr=sdf.format(date);
            datestr=datestr.substring(5,10);
            labels.add(datestr);
        }
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawHighlightArrow(false);
        dataset = new BarDataSet(entries, "");

        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //color可自定格式例如 Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120),
//        Color.rgb(106, 167, 134), Color.rgb(53, 194, 209)的数组
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData data = new BarData(labels, dataset);
        LimitLine line = new LimitLine(10f);
        barChart.setData(data);
        barChart.animateY(2000);
        barChart.setDescription("最近一周用水情况");
    }

    private void initDB() {
        db=FinalDb.create(this,"tap.db");
        String muid=uid+"";
        List<Sum> Sumlist=db.findAll(Sum.class,"uid="+muid);
        if (Sumlist.size()>0){
            tv_totaluse.setText(Sumlist.get(0).getTotaluse()+"");
        }else {
            tv_totaluse.setText("0");
        }

    }

    private void initSharedPreferences() {
        sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        time=sharedPreferences.getLong("time",0);
        Log.e("sptime",time+"");
        lastrefresh=sharedPreferences.getLong("lastrefresh",0);

    }


    protected void initView() {
        recHandler=new Handler(){
            @Override
            public void handleMessage(final Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case LATEST_TIME:
                        if (msg.obj!=null){
                            Log.e("receive",(Long)msg.obj+"");
                            editor.putLong("time", (Long) msg.obj);
                            editor.commit();
                        }
                        break;
                    case BAR_DATA:
                        if (msg.obj!=null){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
//                                            updateUI((BarData) msg.obj);
                                            initBarData();
                                        }
                                    });

                                }
                            }).start();
                        }
                        break;
                    case MAX_ID:
                        if (msg.obj!=null){
                            Log.e("maxid",msg.obj+"");
                            editor.putInt("maxid", (int) msg.obj);
                            editor.commit();
                        }
                        break;
                    case SUM_USE:
                        if (msg.obj!=null){
                            Sum sum=new Sum();
                            List<Sum> sumlist=db.findAll(Sum.class);
                            if (sumlist.size()==0){
                                sum.setTotaluse((Double) msg.obj);
                            }else {
                                String muid=uid+"";
                                List<Sum> sumlist2=db.findAllByWhere(Sum.class,"uid="+muid);
                                int item_id=sumlist2.get(0).getId();
                                sum.setId(item_id);
                                sum.setTotaluse(sumlist2.get(0).getTotaluse()+(double)msg.obj);
                            }
                            db.save(sum);
                        }
                        break;
                }
//                UpdateUI();
//                Long latest =sharedPreferences.getLong("latestdate",0);

//                Log.e("收到",message);
//                new Store(MainActivity.this,message);
//                Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
            }
        };
        mHandler = new Handler();
        mScrollView = (XScrollView) findViewById(R.id.scroll_view);
        mScrollView.setPullRefreshEnable(true);
        mScrollView.setPullLoadEnable(false);
        mScrollView.setAutoLoadEnable(false);
        mScrollView.setIXScrollViewListener(this);
        mScrollView.setRefreshTime(getTime());

        content= LayoutInflater.from(this).inflate(R.layout.testscrollview,null);
        barChart = (BarChart)content.findViewById(R.id.chart);
        mScrollView.setView(content);

        iv_drop= (ImageView) findViewById(R.id.iv_drop);
        iv_ele= (ImageView) findViewById(R.id.iv_ele);
        Glide.with(MainActivity.this).load(R.drawable.drop).into(iv_drop);
        Glide.with(MainActivity.this).load(R.drawable.eletricity).into(iv_ele);

        tv_totalpro= (TextView) findViewById(R.id.totalpro);
        tv_totaluse= (TextView) findViewById(R.id.totaluse);
        btn_lookmore=(Button)findViewById(R.id.btn_lookmore);

        //加载viewpager并实现adapter
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        //加载viewpager布局图片
        LayoutInflater lf = getLayoutInflater().from(MainActivity.this);

        view1 = lf.inflate(R.layout.image1, null);
//        view1 = LayoutInflater.from(this).inflate(R.layout.content_lishiyongshui_activity, null);
//        view2 = LayoutInflater.from(this).inflate(R.layout.content_lishiyongshui_activity, null);
//        view3 = LayoutInflater.from(this).inflate(R.layout.content_lishiyongshui_activity, null);

        view2 = lf.inflate(R.layout.image2, null);
        view3 = lf.inflate(R.layout.image3, null);
        //初始化那个viewpager的view里面的控件。
        img_1=(ImageView) view1.findViewById(R.id.img_1);
        img_2=(ImageView) view2.findViewById(R.id.img_2);
        img_3=(ImageView) view3.findViewById(R.id.img_3);
        Glide.with(this).load(R.drawable.image1).into(img_1);
        Glide.with(this).load(R.drawable.image2).into(img_2);
        Glide.with(this).load(R.drawable.image3).into(img_3);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
    }

    private void initTips() {
    }


    private void initListener() {
//        设置点击柱子的监听
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//                Toast.makeText(MainActivity.this, e.getXIndex() + "", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("item",listshow.get(e.getXIndex()));
                intent1.putExtras(bundle);
                intent1.setClass(MainActivity.this,dangtianyongshui_activity.class);
                startActivity(intent1);
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //查看更多的监听！！跳到历史节水

        btn_lookmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,History.class);
                startActivity(intent);
            }
        });

        //viewpager的监听跳转到tips
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("tips",1);
                intent1.setClass(MainActivity.this, TIPS.class);
                startActivity(intent1);
            }
        });
        img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.putExtra("tips",2);
                intent2.setClass(MainActivity.this, TIPS.class);
                startActivity(intent2);
            }
        });
        img_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent();
                intent3.putExtra("tips",3);
                intent3.setClass(MainActivity.this, TIPS.class);
                startActivity(intent3);
            }
        });

    }

//    private void show() {
//        barChart.setDoubleTapToZoomEnabled(false);
//        barChart.setDrawHighlightArrow(false);
//        dataset = new BarDataSet(entries, "");
////        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        //color可自定格式例如 Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120),
////        Color.rgb(106, 167, 134), Color.rgb(53, 194, 209)的数组
//        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        BarData data = new BarData(labels, dataset);
//        LimitLine line = new LimitLine(10f);
//        barChart.setData(data);
//        barChart.animateY(2000);
//        barChart.setDescription("最近一周用水情况");
//    }


//看到界面自动刷新
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        if (hasFocus) {
//            mScrollView.autoRefresh();
//        }
//    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                Log.e("readytosend",time+"");
                new Send().SendUid(1,recHandler,MainActivity.this,time,barChart);

                onLoad();
            }
        }, 2500);

    }





    public void onLoadMore() {

    }


    private void onLoad() {
        mScrollView.stopRefresh();
        mScrollView.stopLoadMore();
        mScrollView.setRefreshTime(getTime());
    }

    private String getTime() {
        if (lastrefresh==0){
            return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
        }
       else {
            return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date(lastrefresh));
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(MainActivity.this,EsptouchDemoActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

