package com.example.bank;

import static com.example.bank.ManageUser.authenticateUser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;
import java.util.Random;

/**
 * Klasa odpowiada za funcjonowanie Activity Karty
 */
public class Cards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        TextView textCardNumber = findViewById(R.id.textCardNumber);
        /*
        Pobieranie loginu użytkownika
         */
        String login = getIntent().getStringExtra("login");

        textCardNumber.setText(authenticateUser(login).getCardNumber());

        Button buttonAddCard = findViewById(R.id.buttonAddCard);

        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input = new EditText(Cards.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setHint("Wprowadź numer karty");

                /*
                Po wciśnięciu przycisku, otwiera się okno dialogowe
                 */
                AlertDialog alertDialog = new AlertDialog.Builder(Cards.this)
                        .setTitle("Dodaj kartę")
                        .setMessage("Numer karty musi zawierać 18 cyfr")
                        .setView(input)
                        .setCancelable(false)
                        .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(input.getText().toString().length() != 18){
                                    Toast.makeText(Cards.this, "Numer karty powinien zawierać 18 znaków", Toast.LENGTH_SHORT).show();
                                } else {
                                    /*
                                    Jeśli wpisanych przez użytkownika cyfr jest 18 następuje:
                                    Aktualizacja numeru karty na ekranie
                                    Aktualizacja numeru karty w obiekcie User
                                     */
                                    CardsNewNumber cardsNewNumber = new CardsNewNumber();
                                    String newCardNumber = cardsNewNumber.newCardNumber(input.getText().toString());

                                    textCardNumber.setText(newCardNumber);
                                    authenticateUser(login).setCardNumber(newCardNumber);
                                }
                            }
                        })
                        .setNeutralButton("Anuluj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
//                                editCardNumber("121234123412341234", textCardNumber);
                            }
                        })
                        .show();
            }
        });
    }
}