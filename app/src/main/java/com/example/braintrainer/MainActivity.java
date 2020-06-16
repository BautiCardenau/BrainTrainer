package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button resetButton;
    TextView textView0;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView timerTextView;
    TextView solveTextView;
    TextView correctTextView;
    TextView infoTextView;
    int correctas = 0;
    int contador = 0;
    int a,b,c,d,e, correcto;
    Boolean gameActive;

    CountDownTimer countDownTimer;
    public void answerChosen (View view){

        TextView answer = (TextView) view;
        int tappedAnswer = Integer.parseInt(answer.getTag().toString());

        if (Integer.valueOf(answer.getText().toString()) == correcto && gameActive ) {
            correctas++;
            infoTextView.setText("CORRECT");
            contador++;
            generateRandom();
        } else if (Integer.valueOf(answer.getText().toString()) != correcto && gameActive ){
            contador++;
            infoTextView.setText("FALSE");
            generateRandom();
        }

        infoTextView.setVisibility(View.VISIBLE);
        Log.i("correctas", Integer.toString(correctas));
        if (gameActive){
            correctTextView.setText(correctas + "/" + contador );
        }


    }

    public void isGameOn (boolean status){
        if (status){
            goButton.setVisibility(View.INVISIBLE);
            textView0.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            timerTextView.setVisibility(View.VISIBLE);
            solveTextView.setVisibility(View.VISIBLE);
            correctTextView.setVisibility(View.VISIBLE);

        } else {
            goButton.setVisibility(View.VISIBLE);
            textView0.setVisibility(View.INVISIBLE);
            textView1.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            timerTextView.setVisibility(View.INVISIBLE);
            solveTextView.setVisibility(View.INVISIBLE);
            correctTextView.setVisibility(View.INVISIBLE);
            infoTextView.setVisibility(View.INVISIBLE);
        }
    }

    public void updateTimer (int timeLeft){
        if (timeLeft<10){

            timerTextView.setText("00:0" + Integer.toString(timeLeft));

        } else {

            timerTextView.setText("00:" + Integer.toString(timeLeft));

        }


    }

    public void generateRandom (){
        //(Math.random() * ((max - min) + 1)) + min

    a = (int)(Math.random() * ((50 - 10) + 1)) + 10;
    b = (int)(Math.random() * ((50 - 10) + 1)) + 10;
    correcto = a + b;
    c = (int)(Math.random() * ((99 - 10) + 1)) + 10;
    d = (int)(Math.random() * ((99 - 10) + 1)) + 10;
    e = (int)(Math.random() * ((99 - 10) + 1)) + 10;
    while (c == a + b){
        c = (int)(Math.random() * ((99 - 10) + 1)) + 10;
    }
    while (d == c || d == a + b){
        d = (int)(Math.random() * ((99 - 10) + 1)) + 10;
    }
    while (e == c || e == d || e == a + b){
        e = (int)(Math.random() * ((99 - 10) + 1)) + 10;
    }


    //Muestro la cuenta a resolver

    solveTextView.setText(a + " + " + b);

    //Genero mis respuestas random

    ArrayList<String> answersAvailable = new ArrayList<String>();
    answersAvailable.add(Integer.toString(correcto));
    answersAvailable.add(Integer.toString(c));
    answersAvailable.add(Integer.toString(d));
    answersAvailable.add(Integer.toString(e));

    Collections.shuffle(answersAvailable);
    textView0.setText(answersAvailable.get(0));
    textView1.setText(answersAvailable.get(1));
    textView2.setText(answersAvailable.get(2));
    textView3.setText(answersAvailable.get(3));

    }


    public void goGame (View view){

        isGameOn(true);
        gameActive = true;
        generateRandom();
        countDownTimer = new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long l) {
                updateTimer((int) l/1000);

            }

            @Override
            public void onFinish() {
                Log.i("Fini","shed");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                resetButton.setVisibility(View.VISIBLE);
                infoTextView.setVisibility(View.VISIBLE);
                infoTextView.setText("DONE");
                gameActive = false;
                contador = 0;
                correctas = 0;
            }
        }.start();

    }

    public void reset (View view){
        isGameOn(false);
        resetButton.setVisibility(View.INVISIBLE);
        correctTextView.setText("0/0");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        resetButton = findViewById(R.id.resetButton);
        textView0 = findViewById(R.id.textView0);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        timerTextView = findViewById(R.id.timerTextView);
        solveTextView = findViewById(R.id.solveTextView);
        correctTextView = findViewById(R.id.correctTextView);
        infoTextView = findViewById(R.id.infoTextView);


        isGameOn(false);
        gameActive = false;
        resetButton.setVisibility(View.INVISIBLE);
        correctTextView.setText("0/0");

    }
}
