package com.mtalaeii.marketapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.mtalaeii.marketapp.R;

public class SplashScreen extends AppCompatActivity {
    public Handler handler = new Handler(Looper.myLooper());
    private TextView splashTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashTextView = findViewById(R.id.textView);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String name = "Mtalaeii-market";
                    String a[] = name.split("",0);
                    StringBuilder end = new StringBuilder();
                    for (String s: a){
                        Thread.sleep(150);
                        end.append(s);
                        final StringBuilder helper = end;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                splashTextView.setText(helper.toString());
                            }
                        });
                    }

                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}