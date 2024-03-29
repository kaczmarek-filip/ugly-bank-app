package com.example.bank;

import static com.example.bank.ManageUser.authenticateUser;
import static com.example.bank.ManageUser.users;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.time.LocalTime;
/**
 * Klasa odpowiada za funcjonowanie Activity strony głównej
 * Główne zadanie to przyjęcie loginu użytkownika i przekierowania do podstron
 */
public class MainSiteAcvitivty extends AppCompatActivity {

    /**
     * Jeśli użytkownik wciśnie przycisk wstecz zostanie wylogowany
     */
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

        TextView textMainSiteWelcome = (TextView) findViewById(R.id.textMainSiteWelcome);

        String login = getIntent().getStringExtra("login");

        textMainSiteWelcome.setText("Witaj " + login + "!");





        /*
        Działanie przycisku BLIK
         */
        Button buttonBlik = (Button) findViewById(R.id.buttonBlik);

        buttonBlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSiteAcvitivty.this, Blik.class);
                startActivity(intent);
            }
        });
        /*
        Działanie przycisku Mini Gry
         */
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonMiniGame = (Button) findViewById(R.id.buttonMiniGame);

        buttonMiniGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSiteAcvitivty.this, MiniGame.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });
        /*
        Działanie przycisku lokaty
         */
        Button buttonInvestment = (Button) findViewById(R.id.buttonInvestment);

        buttonInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSiteAcvitivty.this, Deposit.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });
        /*
        Działanie przycisku przelewy
         */
        Button buttonTransfer = (Button) findViewById(R.id.buttonTransfer);

        buttonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSiteAcvitivty.this, Transfer.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });
        /*
        Działanie przycisku kart
         */
        Button buttonCards = (Button) findViewById(R.id.buttonCards);

        buttonCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSiteAcvitivty.this, Cards.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });
    }

    /**
     * Metoda przy uruchomieniu Activity pobiera aktualne saldo i lokatę użytkownika
     */
    @Override
    protected void onStart() {
        super.onStart();

        TextView textBalance = (TextView) findViewById(R.id.textBalance);
        TextView textDeposit = (TextView) findViewById(R.id.textDeposit);

        String login = getIntent().getStringExtra("login");

        try{
            textBalance.setText("$ " + authenticateUser(login).getBalance());
            textDeposit.setText("$ " + authenticateUser(login).getDeposit());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}