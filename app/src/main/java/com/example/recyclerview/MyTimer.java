package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import cn.wandersnail.fileselector.FileSelector;

public class MyTimer extends AppCompatActivity {

    private static final String TAG = "MyTimer";
    Button btn;
    Button btn2;
    private final Timer mTimer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_timer);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(listen);

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(listen2);
    }

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            Log.d(TAG, "timer");
        }
    };

    class MyTimerTask extends TimerTask {
        public void run() {
            Log.d(TAG, "timer");
            //mTimer.cancel();

        }
    }

    Button.OnClickListener listen2 = new Button.OnClickListener() {
        public void onClick(View v){
            mTimer.cancel();
        }
    };

    Button.OnClickListener listen = new Button.OnClickListener() {
        public void onClick(View v){
            mTimer.schedule(new MyTimerTask(), 3000);
        }
    };
}
