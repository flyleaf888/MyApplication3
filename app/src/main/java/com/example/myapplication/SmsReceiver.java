package com.example.myapplication;
//package com.example.fanlei.cutnotedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
//import java.io.UnsupportedEncodingException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 广播接收器
 */
public class SmsReceiver extends BroadcastReceiver {
    private static MessageListener mMessageListener;
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public SmsReceiver() {
        super();
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for(Object pdu:pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte [])pdu);
                String sender = smsMessage.getDisplayOriginatingAddress();
                System.out.println("hello furong");
                //短信内容
                String content = smsMessage.getDisplayMessageBody();
                //content = new String(content.getDisplayMessageBody().getBytes(),"UTF-8");
                System.out.println(content);
                long date = smsMessage.getTimestampMillis();
                Date tiemDate = new Date(date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = simpleDateFormat.format(tiemDate);
                //Toast.makeText(context,"dd"+content, Toast.LENGTH_LONG).show();
                //过滤不需要读取的短信的发送号码
                //content = content.getBytes();
                //content = new String(message.getDisplayMessageBody().getBytes(),"UTF-8");
                mMessageListener.onReceived(content);

            }
        }
}
public interface MessageListener {
    public void onReceived(String message);
}

public void setOnReceivedMessageListener(MessageListener messageListener) {
        this.mMessageListener = messageListener;
    }
}