package com.example.zakatpaymentgoldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textview = findViewById(R.id.TextViewLink);
        textview.setMovementMethod(LinkMovementMethod.getInstance());

    }
}