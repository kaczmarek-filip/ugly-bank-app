package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.time.LocalTime;

public class MainSiteAcvitivty extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Wylogowano", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_site_acvitivty);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textMainSiteWelcome = (TextView) findViewById(R.id.textMainSiteWelcome);

        String login = getIntent().getStringExtra("login");

        textMainSiteWelcome.setText("Witaj " + login + "!");
    }

}