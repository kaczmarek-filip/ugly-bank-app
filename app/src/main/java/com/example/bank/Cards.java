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

public class Cards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        TextView textCardNumber = findViewById(R.id.textCardNumber);

        String login = getIntent().getStringExtra("login");

        textCardNumber.setText(authenticateUser(login).getCardNumber());

        Button buttonAddCard = findViewById(R.id.buttonAddCard);

        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input = new EditText(Cards.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setHint("Wprowadź numer karty");

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
//                                    editCardNumber(input.getText().toString(), textCardNumber);
//                                    newCardNumber(input.getText().toString());
                                    textCardNumber.setText(newCardNumber(input.getText().toString()));
                                    authenticateUser(login).setCardNumber(newCardNumber(input.getText().toString()));
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
    public static String generateCardNumber(){

        StringBuilder cardNumber = new StringBuilder();

        for(int i = 1; i <= 18; i++){
            Random random = new Random();
            int randInt = random.nextInt(10);
            cardNumber.append(randInt);

            if (i == 2 || (i > 2 && (i - 2) % 4 == 0)) {
                cardNumber.append(" ");
            }
        }
        return cardNumber.toString();
    }
    String newCardNumber(String newCardNumber){

        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < newCardNumber.length(); i++){
            cardNumber.append(newCardNumber.charAt(i));

            if (i == 1 || (i > 1 && (i -1) % 4 == 0)) {
                cardNumber.append(" ");
            }
        }


        return cardNumber.toString();
    }
}