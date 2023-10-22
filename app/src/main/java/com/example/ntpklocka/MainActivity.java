package com.example.ntpklocka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);



                TextView textView = findViewById(R.id.vyn);


                SNTPClient.getDate(TimeZone.getTimeZone("Europe/Stockholm"), new SNTPClient.Listener(){

                    @Override
                    public void onTimeResponse(String rawDate, Date date, Exception ex) {

                        if(date == null){
                            SimpleDateFormat sdf = new SimpleDateFormat("'\nTime\n'HH:mm:ss");
                            String currentDateAndTime = sdf.format(new Date());
                            textView.setText(currentDateAndTime);
                            System.out.println(rawDate);
                            System.out.println("Offline, systemtid");

                        } else {

                            SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");
                            String ntimeString = timeFormat.format(date);
                            textView.setText(ntimeString);
                            System.out.println(rawDate);
                            System.out.println("Online, ntpTid");
                        }

                    }
                });




            }
        }, delay);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
    }




}