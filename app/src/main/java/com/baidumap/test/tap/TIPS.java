package com.baidumap.test.tap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TIPS extends Activity {
    private TextView tv_show,tv_show_main1,tv_show_main2,tv_show_tips1,tv_show_tips2;
    private ImageView iv;
    private  int pic;
    private String tips_content;
    private Button feedback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.tips);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.tips_title);
        feedback=(Button)findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TIPS.this, feedback.class);
                startActivity(intent);
            }
        });
//        setTitle("温馨提示");

        tv_show_main1=(TextView)findViewById(R.id.tv_show_main1);
        tv_show_main2=(TextView)findViewById(R.id.tv_show_main2);
        tv_show_tips1=(TextView)findViewById(R.id.tv_show_tips1);
        tv_show_tips2=(TextView)findViewById(R.id.tv_show_tips2);
        TextPaint tp = tv_show_main1.getPaint();
        TextPaint tp1 = tv_show_main2.getPaint();
        tp.setFakeBoldText(true);
        tp1.setFakeBoldText(true);
        iv= (ImageView) findViewById(R.id.iv_tips);
        Intent intent=getIntent();
        int tips_num=intent.getIntExtra("tips",0);
        switch (tips_num){
            case 1:
                pic=R.drawable.image_morning;
                tips_content="早上节约用水";
                tv_show_main1.setText("1.检测到您今天洗手用水量和用水频率过高，系统根据您的情况为您推送以下建议");
                tv_show_main2.setText("2.您最近一次用水耗水量太多，不符合您的用水习惯，我们推送的解决方案是：");
                tv_show_tips1.setText("如因工作需要所造成洗手的大量用水，建议您把洗手用水积蓄起来用作冲厕所。如不是，建议你配合硬" +
                        "件模块养成洗手节约用水习惯。");
                tv_show_tips2.setText("如因需要，建议判断用水级别来节约。请第一级：户外用水。比如，浇灌花草等，这个对水质要求不高，不需要净化；第二级：生活用水，有一定的水质要求用于冲厕，以及室内卫生。");
                break;
            case 2:
                pic=R.drawable.image_noon;
                tips_content="中午节约用水";
                tv_show_main1.setText("1.根据您的今天用水量和用水频率显示:");
                tv_show_main2.setText("2.系统为您推送以下tips：");
                tv_show_tips1.setText("今天用水量基本正常，但是今天中午做饭用水量明显高于平常做饭的用水量。");
                tv_show_tips2.setText("做饭时候的用水可用于：1.浇灌花木   2.将淘米水煮沸以后用来浆洗衣服     3.铁制的炊具如菜刀，如果放在淘米水中，可避免生锈。");
                break;
            case 3:
                pic=R.drawable.image8;
                tips_content="晚上节约用水";
                break;
        }
        Glide.with(this).load(pic).into(iv);
    }
}
