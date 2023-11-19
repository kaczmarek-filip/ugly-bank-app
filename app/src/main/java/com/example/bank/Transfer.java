package com.example.bank;

import static com.example.bank.ManageUser.getUsersLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, getUsersLogin());
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}