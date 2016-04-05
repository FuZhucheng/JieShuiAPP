package com.baidumap.test.tap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import net.tsz.afinal.FinalDb;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Store {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private BarChart barChart;
    private ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    private BarDataSet dataset;
    private ArrayList<String> labels = new ArrayList<String>();

    public void Save(Context context, String msg,Long time,Handler handler,BarChart bar){
        barChart=bar;
        Long latest = null;
        int index=0;
        int maxid = 0;
        FinalDb db= FinalDb.create(context,"tap.db");
        String[] DaysData=msg.split("@");
        for (String each:DaysData){
            try {
//                Toast.makeText(context,each,Toast.LENGTH_SHORT).show();
                Log.e("each",each);
                DayData dayData=new DayData();
                double sum=0.0;
                double flow;
                JSONObject jsonObject=new JSONObject(each);
                flow=jsonObject.getDouble("1");
                sum+=flow;
                dayData.setFlow1(flow);
                flow=jsonObject.getDouble("2");
                sum+=flow;
                dayData.setFlow2(flow);
                flow=jsonObject.getDouble("3");
                sum+=flow;
                dayData.setFlow3(flow);
                flow=jsonObject.getDouble("4");
                sum+=flow;
                dayData.setFlow4(flow);
                flow=jsonObject.getDouble("5");
                sum+=flow;
                dayData.setFlow5(flow);
                flow=jsonObject.getDouble("6");
                sum+=flow;
                dayData.setFlow6(flow);
                flow=jsonObject.getDouble("7");
                sum+=flow;
                dayData.setFlow7(flow);
                flow=jsonObject.getDouble("8");
                sum+=flow;
                dayData.setFlow8(flow);
                flow=jsonObject.getDouble("9");
                sum+=flow;
                dayData.setFlow9(flow);
                flow=jsonObject.getDouble("10");
                sum+=flow;
                dayData.setFlow10(flow);
                flow=jsonObject.getDouble("11");
                sum+=flow;
                dayData.setFlow11(flow);
                flow=jsonObject.getDouble("12");
                sum+=flow;
                dayData.setFlow12(flow);
                flow=jsonObject.getDouble("13");
                sum+=flow;
                dayData.setFlow13(flow);
                flow=jsonObject.getDouble("14");
                sum+=flow;
                dayData.setFlow14(flow);
                flow=jsonObject.getDouble("15");
                sum+=flow;
                dayData.setFlow15(flow);
                flow=jsonObject.getDouble("16");
                sum+=flow;
                dayData.setFlow16(flow);
                flow=jsonObject.getDouble("17");
                sum+=flow;
                dayData.setFlow17(flow);
                flow=jsonObject.getDouble("18");
                sum+=flow;
                dayData.setFlow18(flow);
                flow=jsonObject.getDouble("19");
                sum+=flow;
                dayData.setFlow19(flow);
                flow=jsonObject.getDouble("20");
                sum+=flow;
                dayData.setFlow20(flow);
                flow=jsonObject.getDouble("21");
                sum+=flow;
                dayData.setFlow21(flow);
                flow=jsonObject.getDouble("22");
                sum+=flow;
                dayData.setFlow22(flow);
                flow=jsonObject.getDouble("23");
                sum+=flow;
                dayData.setFlow23(flow);
                flow=jsonObject.getDouble("24");
                sum+=flow;
                dayData.setFlow24(flow);
                SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                String dateString =jsonObject.getString("date");
                String datestr=dateString.substring(5,10);
                dateString = dateString.replaceAll("\"", "");
                Log.e("date",dateString);
                Date date=sdf.parse(dateString);
                entries.add(new BarEntry((float) sum,index));
                index++;

                Log.e("datestr",datestr);
                labels.add(datestr);
                int uid=jsonObject.getInt("uid");
                dayData.setDate(date);
                Long datatime=date.getTime();
                dayData.setSum(sum);
                dayData.setUid(uid);
                Log.e("storetime",time+"");
                if (datatime>time){
                    maxid=dayData.getId();
                    Message me=new Message();
                    me.what=MainActivity.SUM_USE;
                    me.obj=dayData.getSum();
                    handler.handleMessage(me);
                    db.save(dayData);
                    latest=datatime;
                    List<DayData> dayDataList=db.findAll(DayData.class);
                    maxid=dayDataList.get(dayDataList.size()-1).getId();

                    Log.e("success","储存成功");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dataset = new BarDataSet(entries, "");
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData data = new BarData(labels, dataset);
        Message messag=new Message();
        messag.what=MainActivity.BAR_DATA;
        messag.obj=data;
        handler.handleMessage(messag);
        if (latest!=null){
            Message message=new Message();
            message.what=MainActivity.LATEST_TIME;
            message.obj=latest;
            handler.handleMessage(message);
            Log.e("latesttime",latest+"");
        }
        if (maxid!=0){
            Message message=new Message();
            message.what=MainActivity.MAX_ID;
            message.obj=maxid;
            handler.handleMessage(message);

        }
    }

}
