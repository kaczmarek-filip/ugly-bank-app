package com.example.bank;

import static com.example.bank.ManageUser.users;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        EditText textSignInLogin = (EditText) findViewById(R.id.textSignInLogin);
        EditText textSignInPassword = (EditText) findViewById(R.id.textSignInPassword);
        EditText textSignInPassword2 = (EditText) findViewById(R.id.textSignInPassword2);

        textSignInLogin.setHint("Login");
        textSignInPassword.setHint("Hasło");
        textSignInPassword2.setHint("Powtórz hasło");


        Button buttonSignInSubmit = (Button) findViewById(R.id.buttonSignInSubmit);
        buttonSignInSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Kod pobiera wartości pól formularza
                 */
                EditText textSignInLogin = (EditText) findViewById(R.id.textSignInLogin);
                EditText textSignInPassword = (EditText) findViewById(R.id.textSignInPassword);
                EditText textSignInPassword2 = (EditText) findViewById(R.id.textSignInPassword2);

                /*
                 * Wyciągnięcie wartości z obiektów
                 */
                String login = textSignInLogin.getText().toString();
                String password = textSignInPassword.getText().toString();
                String password2 = textSignInPassword2.getText().toString();

                /*
                 * Sprawdzenie wymagań dot. haseł
                 */
                if(password.length() < 8){
                    Toast.makeText(SignInPage.this, "Hasło musi zawierać min. 8 znaków", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password2)) {
                    Toast.makeText(SignInPage.this, "Hasła nie są sobie równe", Toast.LENGTH_SHORT).show();
                } else if (isUpper(password)) {
                    Toast.makeText(SignInPage.this, "Hasło musi mieć min. 1 wielką literę", Toast.LENGTH_SHORT).show();
                } else if (isLower(password)) {
                    Toast.makeText(SignInPage.this, "Hasło musi mieć min. 1 małą literę", Toast.LENGTH_SHORT).show();
                } else if (isDigit(password)) {
                    Toast.makeText(SignInPage.this, "Hasło musi mieć min. 1 cyfrę", Toast.LENGTH_SHORT).show();
                } else if (isLetter(password)) {
                    Toast.makeText(SignInPage.this, "Hasło musi zawierać litery", Toast.LENGTH_SHORT).show();
                } else if (login.equals("")) {
                    Toast.makeText(SignInPage.this, "Podaj login", Toast.LENGTH_SHORT).show();
                }
                else {
                    /*
                     * Jeśli hasło spełnia wymagania:
                     * 1. Wyświetl komunikat o poprawności danych
                     * 2. Prześlij login i hasło do strony logowania
                     * 3. Przejdź do strony logowania
                     * 4. Stwórz użytkownika z podanych danych
                     * 5. Sprawdź czy login nie jest już używany
                     */
                    boolean isLoginTaken = false;

                    for (User user : users) {
                        if (login.equals(user.getLogin())) {
                            isLoginTaken = true;
                            break;
                        }
                    }
                    if (isLoginTaken) {
                        Toast.makeText(SignInPage.this, "Istnieje już użytkownik o podanym loginie", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(SignInPage.this, LoginPage.class);
                        Toast.makeText(SignInPage.this, "Dane są poprawne", Toast.LENGTH_SHORT).show();
                        intent.putExtra("login", login);
                        intent.putExtra("password", password);
                        startActivity(intent);
                        User newUser = new User(login, password);
                        ManageUser.addUser(newUser);
                    }

                }


            }
        });

        /*
        Obsługuje przycisk przejścia do strony logowania
         */

        Button buttonSwitchToLogin = (Button) findViewById(R.id.buttonSwitchToLogin);
        buttonSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

    }

    /**
     * @param password Hasło użytkownika
     * @return Zwraca prawdę, jeśli haslo zawiera wielką literę
     */
    boolean isUpper(String password){
        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                return false;
            }
        }
        return true;
    }
    /**
     * @param password Hasło użytkownika
     * @return Zwraca prawdę, jeśli haslo zawiera małą literę
     */
    boolean isLower(String password){
        for (char character : password.toCharArray()) {
            if (Character.isLowerCase(character)) {
                return false;
            }
        }
        return true;
    }
    /**
     * @param password Hasło użytkownika
     * @return Zwraca prawdę, jeśli haslo zawiera jakąkolwiek literę
     */
    boolean isLetter(String password){
        for (char character : password.toCharArray()) {
            if (Character.isLetter(character)) {
                return false;
            }
        }
        return true;
    }
    /**
     * @param password Hasło użytkownika
     * @return Zwraca prawdę, jeśli haslo zawiera cyfrę
     */
    boolean isDigit(String password){
        for (char character : password.toCharArray()) {
            if (Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }
}