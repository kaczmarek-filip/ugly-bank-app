package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_login = (Button) findViewById(R.id.buttonLogin);
        Button button_signIn = (Button) findViewById(R.id.buttonSignIn);

        /**
         * Stworzenie testowego użytkownika
         * Login: test
         * Hasło: test1234
         */
        User newUser = new User("test", "test1234");
        ManageUser.addUser(newUser);

        /**
         * Obsługa przycisku "Zaloguj się"
         */
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });
        /**
         * Obsługa przycisku "Zarejestruj się"
         */
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInPage.class);
                startActivity(intent);
            }
        });
    }
}