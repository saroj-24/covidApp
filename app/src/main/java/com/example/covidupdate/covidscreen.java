package com.example.covidupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Objects;

public class covidscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidscreen);
        TextView textview = findViewById(R.id.text1);
        Objects.requireNonNull(getSupportActionBar()).hide();// this hide the actionbar screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent = new Intent(covidscreen.this,MainActivity.class);
                startActivity(splashintent);
                finish();
            }
        },4000);

       Animation ani  = AnimationUtils.loadAnimation(covidscreen.this,R.anim.anam);
       textview.startAnimation(ani);
    }
}