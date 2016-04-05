package com.baidumap.test.tap;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Send {
    public void SendUid(final int uid, final Handler handler, final Context context, final Long time, final BarChart bar){
        Long latest;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;

                try {
                    Log.e("sendtime",time+"");
                    //创建Socket
                    socket = new Socket("119.29.136.149", 9999); //查看本机IP,每次开机都不同
                    //向服务器发送消息
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    String uids=uid+"";
                    out.println(uids);

                    //接收来自服务器的消息
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg = br.readLine();

                    if (msg != null) {
//                                        tv_dianliang.setText(msg);
                        Message message=new Message();
                        message.obj=msg;
                        handler.handleMessage(message);
                        Log.e("content", msg);
                        Store store=new Store();
                        store.Save(context,msg,time,handler,bar);
                    } else {
                        Log.e("content", "数据错误!");
                    }
                    //关闭流
                    out.close();
                    br.close();
                    //关闭Socket
                    socket.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("error", e.toString());
                }
            }
        }).start();
    }


}
