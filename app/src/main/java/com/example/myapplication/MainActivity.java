package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.content.IntentFilter;
import android.widget.EditText;

import android.widget.TextView;
//import com.example.myapplication.SmsReceiver;
public class MainActivity extends AppCompatActivity {
    private EditText edtPassword;
    private TextView txt1;
    private SmsReceiver mSMSBroadcastReceiver;

    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtPassword = (EditText) findViewById(R.id.ename);
        txt1 = (TextView) findViewById(R.id.txvv);
        txt1.setText("测试");

    }
    @Override
    protected void onStart() {
        super.onStart();
        //生成广播处理
        mSMSBroadcastReceiver = new SmsReceiver();

        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter(ACTION);
        intentFilter.setPriority(Integer.MAX_VALUE);
        //注册广播
        this.registerReceiver(mSMSBroadcastReceiver, intentFilter);

        mSMSBroadcastReceiver.setOnReceivedMessageListener(new SmsReceiver.MessageListener() {
            @Override
            // System.out.println("hello oooo");
            public void onReceived(final String message) {
                System.out.println("hello mmm");
                edtPassword.setText(message);
                send_db(message);
             //  DBConnection.insertmydb("22",message);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销短信监听广播
        this.unregisterReceiver(mSMSBroadcastReceiver);
    }

    int size=30;
  //  public dissms(string  a){
     //   TextView text3= (TextView) findViewById(R.id.txvv);
     //   text3.setText(a);
   // }
    public void send_db(final String mm1){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello ccc");
                DBConnection.insertmydb("21",mm1);
               // DBConnection.qMysql();
                // DBConnection.linkMysql();
            }
        }).start();
    }
    public void bigger(View v){
        TextView txv;
        txv= (TextView) findViewById(R.id.txvv);  // 根据ID找到对应的text对象

        txv.setTextSize(++size);
        //new SmsReceiver();
    }
    public void display(View v){
        EditText name= (EditText) findViewById(R.id.ename);
        TextView text2= (TextView) findViewById(R.id.txvv);
        text2.setText(name.getText().toString());

    }
    }
