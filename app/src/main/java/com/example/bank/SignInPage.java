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
                } //TODO: dodać więcej warunków
                else {
                    /*
                     * Jeśli hasło spełnia wymagania:
                     * 1. Wyświetl komunikat o poprawności danych
                     * 2. Prześlij login i hasło do strony logowania
                     * 3. Przejdź do strony logowania
                     * 4. Stwórz użytkownika z podanych danych
                     * 5. Sprawdź czy login nie jest już używany
                     */
                    for(User user : users){
                        if(login.equals(user.getLogin())){
                            Toast.makeText(SignInPage.this, "Użytkownik o podanym loginie już istnieje", Toast.LENGTH_SHORT).show();
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


            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonSwitchToLogin = (Button) findViewById(R.id.buttonSwitchToLogin);
        buttonSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

    }
}