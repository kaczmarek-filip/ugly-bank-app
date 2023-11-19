package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class Blik extends AppCompatActivity {
    Timer timer;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blik);


        setCode();
        startProgressBar();

        Button buttonRefreshCode = (Button) findViewById(R.id.buttonRefreshCode);

        buttonRefreshCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCode();
                restartProgressBar();
            }
        });
    }

    /**
     * Metoda odpowiedzalna za umieszczenie stworzonego w generateCode() kodu w odpowiednim miejscu
     * @see Blik#generateCode()
     */
    void setCode(){
        TextView textBlikCode = (TextView) findViewById(R.id.textBlikCode);
        textBlikCode.setText(generateCode());
    }

    /**
     * @return zwraca kod gotowy do umieszczenia w wybranym polu
     * Kod składa się z 3 cyfr, spacji, 3 cyfr
     * Przykład:
     * XXX XXX
     */
    static String generateCode(){
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < 6; i++){
            Random random = new Random();
            int randInt = random.nextInt(10);
            code.append(randInt);

            if(i == 2){
                code.append(" ");
            }
        }


        return code.toString();
    }

    /**
     * Metoda odpowiedzialna za rozpoczęcie pracy progressBara
     * @see Blik#restartProgressBar()
     * @see Blik#stopProgressBar()
     */
    void startProgressBar(){
        if(timer == null){
            timer = new Timer();

            progressBar = (ProgressBar) findViewById(R.id.progressBar);

            progressBar.setProgress(0);

//        Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                int miliSeconds = 0;
                @Override
                public void run() {
                    if(miliSeconds <= 3000){
                        progressBar.setProgress(miliSeconds);
                        miliSeconds += 1;
                    } else {
                        miliSeconds = 0;
                        try{
                            setCode();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };

            timer.scheduleAtFixedRate(timerTask, 0, 10);
        }


    }

    /**
     * Metoda odpowiedzialna za zrestartowanie progressBara
     * @see Blik#stopProgressBar()
     * @see Blik#startProgressBar()
     */
    void restartProgressBar(){
        stopProgressBar();
        progressBar.setProgress(0);
        startProgressBar();
    }

    /**
     * Metoda odpowiedzialna za zatrzymanie progressBara
     * @see Blik#startProgressBar()
     * @see Blik#restartProgressBar()
     */
    void stopProgressBar(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}