package com.example.mmanoghna.myapplication1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int seconds = 0;
    private boolean running;
    private boolean was_running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            was_running=savedInstanceState.getBoolean("was running");
        }
        runtimer();
    }

    public void OnSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("was running",was_running);


    }
    @Override
    protected void onResume() {
        super.onResume();
        if (was_running) {
            running = true;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        was_running = running;
        running = false;
    }
    @Override
    protected void onStart(){
        super.onStart();
        if (was_running){
            running=true;
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        running=false;
    }

    public void onclickstart(View view) {
        running = true;
    }

    public void onclickstop(View view) {
        running = false;
    }

    public void onclickreset(View view) {
        running = false;
        seconds = 0;
    }

    public void runtimer() {
        final TextView timeview = (TextView) findViewById(R.id.timeview);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }
}
