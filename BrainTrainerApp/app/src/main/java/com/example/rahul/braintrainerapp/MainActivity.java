package com.example.rahul.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView ResultTextView;
    TextView Score;
    TextView Sum;
    TextView Timer;
    Button startButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    GridLayout gridLayoutGame;
    int locationOfCorrectAnswer;
    ArrayList<Integer> Answers = new ArrayList<Integer>();
    int score=0;
    int numberOfQuestions=0;
    Random rand = new Random();

    public void generateQuestion(){

        int a = rand.nextInt(31);
        int b = rand.nextInt(31);
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;

        Answers.clear();

        Sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for(int i=0; i<4; i++){

            if(i==locationOfCorrectAnswer) Answers.add(a+b);
            else{

                incorrectAnswer = rand.nextInt(61);
                while(incorrectAnswer==a+b) incorrectAnswer = rand.nextInt(61);

                Answers.add(incorrectAnswer);

            }

        }

        button1.setText(Integer.toString(Answers.get(0)));
        button2.setText(Integer.toString(Answers.get(1)));
        button3.setText(Integer.toString(Answers.get(2)));
        button4.setText(Integer.toString(Answers.get(3)));



    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        Sum.setVisibility(View.VISIBLE);
        Score.setVisibility(View.VISIBLE);
        Timer.setVisibility(View.VISIBLE);
        gridLayoutGame.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void playAgain(View view){

        score=0;
        numberOfQuestions=0;
        Timer.setText("30s");
        Score.setText("0/0");
        ResultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {

                Timer.setText(String.valueOf(l/1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                Timer.setText("0s");
                ResultTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();


    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++ ;
            Log.i("Correct", "Correct Answer!!");
            ResultTextView.setText("Correct!");

        } else {

            Log.i("Incorrect", "Incorrect Answer!");
            ResultTextView.setText("Incorrect!");

        }

        numberOfQuestions++ ;
        Score.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sum = (TextView) findViewById(R.id.Sum);
        ResultTextView = (TextView) findViewById(R.id.resultTextView);
        Timer = (TextView) findViewById(R.id.Timer);
        Score = (TextView) findViewById(R.id.Score);
        startButton = (Button) findViewById(R.id.startButton);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gridLayoutGame = (GridLayout) findViewById(R.id.gridLayoutScore);

        playAgainButton.setVisibility(View.INVISIBLE);

        startButton.setText("GO!");

    }
}
