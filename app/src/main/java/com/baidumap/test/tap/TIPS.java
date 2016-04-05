package com.baidumap.test.tap;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidumap.test.tap.R;
import com.bumptech.glide.Glide;

public class TIPS extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    private  int pic;
    private String tips_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);
        setTitle("温馨提示");
        tv= (TextView) findViewById(R.id.tv_tips);
        iv= (ImageView) findViewById(R.id.iv_tips);
        Intent intent=getIntent();
        int tips_num=intent.getIntExtra("tips",0);
        switch (tips_num){
            case 1:
                pic=R.drawable.image1;
                tips_content="早上节约用水";
                break;
            case 2:
                pic=R.drawable.image2;
                tips_content="中午节约用水";
                break;
            case 3:
                pic=R.drawable.image3;
                tips_content="晚上节约用水";
                break;
        }
        Glide.with(this).load(pic).into(iv);
        tv.setText(tips_content);
    }
}
