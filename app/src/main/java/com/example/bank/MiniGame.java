package com.example.bank;

import static com.example.bank.ManageUser.authenticateUser;
import static com.example.bank.ManageUser.users;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//TODO: Gra ma opierać się o zarabianie lub stratę
// W przypadku w którym nie dojdzie do zwycięstwa, gracz może starcić środki
// Nie może stracić środków dostępnych na lokacie

public class MiniGame extends AppCompatActivity {

    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     * Metoda pobiera przyciski z mini gry i ukrywa je razem z progresem gry
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);

        List<Button> buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));

        for(Button button : buttons){
            button.setVisibility(View.INVISIBLE);
        }

        TextView textGameProgress = (TextView) findViewById(R.id.textGameProgress);

        textGameProgress.setVisibility(View.INVISIBLE);
    }

    /**
     * Metoda pobiera saldo użytkownika i wypisuje je na ekranie
     * Metoda programuje przycisk, który uruchamia mini grę
     */
    @Override
    protected void onStart() {
        super.onStart();


        String login = getIntent().getStringExtra("login");
        TextView textTemp = (TextView) findViewById(R.id.textTemp);

        textTemp.setText("$ "+ authenticateUser(login).getBalance());


        Button buttonEarn = (Button) findViewById(R.id.buttonEarn);
        buttonEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonEarn.getText().toString().equals("Zakończ")){
                    buttonEarn.setBackgroundColor(Color.parseColor("#4CAF50"));
                    buttonEarn.setText("Graj!");
                    finish();
                } else if (buttonEarn.getText().toString().equals("Graj!")) {
                    buttonEarn.setBackgroundColor(Color.RED);
                    buttonEarn.setText("Zakończ");
                    startGame();
                }
            }
        });
    }

    /**
     * @param maxEarnings Maksymalny zakres wygranej. Od 0 do maxEarings
     * @return wylosowaną liczbę
     */
    double randomEarnings(int maxEarnings){
        Random random = new Random();
        double randInt = random.nextInt(maxEarnings);

        return randInt;
    }
    private Handler handler = new Handler();
    private Runnable showRunnable;
    private Runnable hideRunnable;
    private int progress = 0;

    /**
     * Metoda pobiera login zalogowanego użytkownika
     * Metoda pokazuje wszystkie przyciski i progres gry
     * Metoda obsługuje progres gry
     * Po osiągnięciu wyniku 10/10 dodaje wylosowane za pomocą randomEarnings saldo
     * @see MiniGame#randomEarnings(int)
     *
     * Uruchamia też metodę odpowiedzialną za pojawianie się i znikanie przycisków
     * @see MiniGame#hideAndShow(Button)
     */
    void miniGame(){
        String login = getIntent().getStringExtra("login");

        TextView textTemp = (TextView) findViewById(R.id.textTemp);



        TextView textGameProgress = (TextView) findViewById(R.id.textGameProgress);

        textGameProgress.setVisibility(View.VISIBLE);

        List<Button> buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));

        for(Button button : buttons){
            button.setVisibility(View.INVISIBLE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progress++;
                    textGameProgress.setText(progress + "/10");
                    button.setVisibility(View.INVISIBLE);
                }
            });
        }

        Random random = new Random();
        int randIndex = random.nextInt(buttons.size());
        Button button = buttons.get(randIndex);
        hideAndShow(button);

        if(progress == 10){
            progress = 0;
            authenticateUser(login).setBalance(randomEarnings(200));
            textTemp.setText("$ "+ authenticateUser(login).getBalance());
        }


    }

    /**
     * @param button przyjmuje przycisk, który ma schować i pokazać
     * Na losowo wygenerowany czas pokazuje i chowa przycisk
     *
     */
    void hideAndShow(Button button){

        showRunnable = new Runnable() {
            /*
            Na ile mają się pojawiać
             */
            Random random = new Random();
            int randTime = random.nextInt(500) + 500;
            @Override
            public void run() {
                button.setVisibility(View.VISIBLE);
                handler.postDelayed(hideRunnable, randTime);
            }
        };
        hideRunnable = new Runnable() {
            @Override
            public void run() {
                button.setVisibility(View.INVISIBLE);
            }
        };
        handler.postDelayed(showRunnable, 0);

    }
    private Runnable startGame;

    /**
     * Uruchamia mini grę
     * Co losowo wygenerowany czas pokazuje przycisk
     * @see MiniGame#miniGame()
     */
    void startGame(){
        startGame = new Runnable() {
            @Override
            public void run() {
                /*
                Co ile mają się pojawiać
                 */
                Random random = new Random();
                int randTime = random.nextInt(500) + 500;

                miniGame();
                handler.postDelayed(startGame, randTime);
            }
        };
        handler.postDelayed(startGame, 0);
    }
}