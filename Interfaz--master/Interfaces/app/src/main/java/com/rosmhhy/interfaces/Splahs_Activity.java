package com.rosmhhy.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splahs_Activity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splahs_);

        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                Intent intent  = new Intent(Splahs_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,SPLASH_SCREEN_DELAY);
    }
}
