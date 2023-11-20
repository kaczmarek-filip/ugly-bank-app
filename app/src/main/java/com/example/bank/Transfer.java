package com.example.bank;

import static com.example.bank.ManageUser.authenticateUser;
import static com.example.bank.ManageUser.getUsersLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Transfer extends AppCompatActivity {
    String selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        String login = getIntent().getStringExtra("login");

        /*
        Obsługa spinnera
         */
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, getUsersLogin());
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*
        Po wybraniu elementu zapisuje wybranego użytkownika do zmiennej selectedUser
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = getUsersLogin().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        Po wciśnięciu przycisku wykonują się następujące operacje:
        1. Odjęcie od konta obecnego użytkownika $
        2. Dodanie takiej samej ilości $ do wybranego z listy użytkownika
        3. Wyświetlenie toasta
         */
        Button buttonTransfer = (Button) findViewById(R.id.buttonTransfer);

        buttonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextMoney = (EditText) findViewById(R.id.editTextMoney);
                try {
                    double money = Double.parseDouble(editTextMoney.getText().toString());

                    if (Double.parseDouble(authenticateUser(login).getBalance()) >= money) {
                        authenticateUser(selectedUser).setBalance(money);
                        authenticateUser(login).minusBalance(money);
                        editTextMoney.setText("");
                        Toast.makeText(Transfer.this, "Przelano " + money + "$", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Transfer.this, "Zbyt mało środków", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}