package com.baidumap.test.tap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.tsz.afinal.FinalDb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {
    private ListView lv_history;
    private Map<String,Object> each=new HashMap<String,Object>();
    private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String s="{\"1\":1.0,\"2\":2.0,\"3\":3.0,\"4\":4.0,\"5\":5.0,\"6\":6.0,\"7\":7.0,\"8\":8.0,\"9\":9.0,\"10\":10.0,\"11\":11.0,\"12\":12.0,\"13\":13.0,\"14\":14.0,\"15\":15.0,\"16\":16.0,\"17\":17.0,\"18\":18.0,\"19\":19.0,\"20\":20.0,\"21\":21.0,\"22\":22.0,\"23\":23.0,\"24\":24.0,\"date\":\"2016-04-01 11:29:18\",\"uid\":1}@{\"1\":1.0,\"2\":2.0,\"3\":3.0,\"4\":4.0,\"5\":5.0,\"6\":6.0,\"7\":7.0,\"8\":8.0,\"9\":9.0,\"10\":10.0,\"11\":11.0,\"12\":12.0,\"13\":13.0,\"14\":14.0,\"15\":15.0,\"16\":16.0,\"17\":17.0,\"18\":18.0,\"19\":19.0,\"20\":20.0,\"21\":21.0,\"22\":22.0,\"23\":23.0,\"24\":24.0,\"date\":\"2016-04-01 11:29:18\",\"uid\":1}";
//        new Store(this,s);
        setContentView(R.layout.activity_history);
        setTitle("历史用水量");
        FinalDb db= FinalDb.create(this,"tap.db");
        final List<DayData> resultList = db.findAllByWhere(DayData.class,"uid=1");
        int id;
        for (int i=resultList.size()-1;i>=0;i--){
            Date da=resultList.get(i).getDate();
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String date=sdf.format(da);
            date=date.substring(0,11);
            String sum=resultList.get(i).getSum()+"";
            if (resultList.get(i).getId()>1){
                resultList.get(i).setDec(resultList.get(i-1).getSum()-resultList.get(i).getSum());
            }else {
                resultList.get(i).setDec(0);
            }

            each=new HashMap<String,Object>();
            each.put("date",date);
            each.put("water",sum);
            list.add(each);
        }
        lv_history= (ListView) findViewById(R.id.lv_history);
        Myadapter myadapter=new Myadapter(this);
        lv_history.setAdapter(myadapter);
        lv_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(History.this,position+"",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("item", resultList.get(resultList.size()-position-1));
                intent.putExtras(bundle);
                intent.setClass(History.this,dangtianyongshui_activity.class);
                startActivity(intent);
            }
        });
    }
    class Myadapter extends BaseAdapter {
        private Context context;
        public  Myadapter(Context context){
            this.context=context;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                int layout=position%2==0?R.layout.history_item1:R.layout.history_item2;
                convertView = LayoutInflater.from(context).inflate(layout,null);

            ImageView iv= (ImageView) convertView.findViewById(R.id.iv_to);
            Glide.with(context).load(R.drawable.to).into(iv);
            String date=new String();
            date= (String) list.get(position).get("date");
            String water=new String();
            water= (String) list.get(position).get("water");
            TextView tv_date= (TextView) convertView.findViewById(R.id.date);
            tv_date.setText(date);
            TextView tv_water= (TextView) convertView.findViewById(R.id.water);
            tv_water.setText(water);
            return convertView;
        }
    }
}

