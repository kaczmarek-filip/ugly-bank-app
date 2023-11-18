package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginPage extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText textLogin = (EditText) findViewById(R.id.textLoginLogin);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText textPassword = (EditText) findViewById(R.id.textLoginPassword);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonLoginSubmit = (Button) findViewById(R.id.buttonLoginSubmit);

        String loginFromSignIn = getIntent().getStringExtra("login");
        String passwordFromSignIn = getIntent().getStringExtra("password");

        if(loginFromSignIn != null && passwordFromSignIn != null){
            textLogin.setText(loginFromSignIn);
            textPassword.setText(passwordFromSignIn);
        }

        buttonLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = textLogin.getText().toString();
                String password = textPassword.getText().toString();

                /**
                 * Tworzenie listy użytkowników
                 * userFound - zmienna informująca o tym czy użytkownik jest w bazie danych czy nie.
                 */
                List<User> users = ManageUser.getUsers();
                boolean userFound = false;
                for (User user : users) {
                    if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                        userFound = true;
                        break;
                    }
                }
                /**
                 * Jeżeli użytkownik znajduje się w bazie danych:
                 * 1. Nadaj o tym komunikat
                 * 2. Przejdź do strony głównej
                 * 3. Prześlij do strony głównej login użytkownika
                 */
                if (userFound) {
                    Toast.makeText(LoginPage.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this, MainSiteAcvitivty.class);
                    intent.putExtra("login", textLogin.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginPage.this, "Błędny login lub hasło", Toast.LENGTH_SHORT).show();
                }

            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonSwitchToSignIn = (Button) findViewById(R.id.buttonSwitchToSignIn);
        buttonSwitchToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, SignInPage.class);
                startActivity(intent);
            }
        });


    }
}