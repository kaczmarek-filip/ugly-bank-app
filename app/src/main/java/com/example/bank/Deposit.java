package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.bank.ManageUser.authenticateUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Deposit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView textBalance = (TextView) findViewById(R.id.textBalance);
        TextView textDeposit = (TextView) findViewById(R.id.textDeposit);
        Button buttonOnBalance = (Button) findViewById(R.id.buttonOnBalance);
        Button buttonOnDeposit = (Button) findViewById(R.id.buttonOnDeposit);

        String login = getIntent().getStringExtra("login");

        textBalance.setText("$ " + authenticateUser(login).getBalance());
        textDeposit.setText("$ " + authenticateUser(login).getDeposit());

        buttonOnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText editTextMoney = (EditText) findViewById(R.id.editTextMoney);
                    double money = Double.parseDouble(editTextMoney.getText().toString());

                    if (Double.parseDouble(authenticateUser(login).getBalance()) >= money) {
                        authenticateUser(login).setDeposit(money);
                        authenticateUser(login).minusBalance(money);
//                    textDeposit.setText("$ " + money);
                        textBalance.setText("$ " + authenticateUser(login).getBalance());
                        textDeposit.setText("$ " + authenticateUser(login).getDeposit());
                        editTextMoney.setText("");
                    } else {
                        Toast.makeText(Deposit.this, "Zbyt mało środków", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        buttonOnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText editTextMoney = (EditText) findViewById(R.id.editTextMoney);
                    double money = Double.parseDouble(editTextMoney.getText().toString());

                    if (Double.parseDouble(authenticateUser(login).getDeposit()) >= money) {
                        authenticateUser(login).setBalance(money);
                        authenticateUser(login).minusDeposit(money);
//                    textDeposit.setText("$ " + money);
                        textBalance.setText("$ " + authenticateUser(login).getBalance());
                        textDeposit.setText("$ " + authenticateUser(login).getDeposit());
                        editTextMoney.setText("");
                    } else {
                        Toast.makeText(Deposit.this, "Zbyt mało środków", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


    }

}