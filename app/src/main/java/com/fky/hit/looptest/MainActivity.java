package com.fky.hit.looptest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private static final int SET = 1;
    public static final String Loader= null;
    private Button but;
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.info);
        but = findViewById(R.id.but);
        this.but.setOnClickListener(new OnClickListenerLoop());
    }
    private class OnClickListenerLoop implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Looper looper = Looper.myLooper();
            myHandler = new MyHandler(looper);
            myHandler.removeMessages(0);
            String data ="clock";
            Message msg = myHandler.obtainMessage(SET,1,1,data);
            myHandler.sendMessage(msg);

        }
    }
    private class Mytask extends TimerTask
    {
        @Override
        public void run()
        {
            Message msg = new Message();
            msg.what = SET;
            MainActivity.this.myHandler.sendMessage(msg);
        }
    }
    private class MyHandler extends  Handler
    {
        public MyHandler(Looper looper)
        {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case SET:
                    MainActivity.this.info.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

}
