package com.anantech.quiz.cricketStadiumQuiz;

/**
 * Created by Sandeep on 18-06-2016.
 */

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.animation.ObjectAnimator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class QuestionActivity extends Activity {
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    int wrong = 0;

    Question currentQ;
    TextView txtQuestion, times, scored;
    Button button1, button2, button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4307565756625227~2383839591");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        QuizHelper db = new QuizHelper(this);  // my question bank class
        quesList = db.getAllQuestions();  // this will fetch all quetonall questions
        Collections.shuffle(quesList);
        currentQ = quesList.get(qid); // the current question

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        // the textview in which the question will be displayed

        // the three buttons,
        // the idea is to set the text of three buttons with the options from question bank
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        // the textview in which score will be displayed
        scored = (TextView) findViewById(R.id.score);

        // the timer
        times = (TextView) findViewById(R.id.timers);

        // method which will set the things up for our game
        setQuestionView();
        times.setText("00:02:00");

        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
       CounterClass timer = new CounterClass(600000, 1000);

      timer.start();

        // button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                getAnswer(button1.getText().toString(),button1,button2,button3);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button2.getText().toString(),button2,button1,button3);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button3.getText().toString(),button3,button1,button2);
            }
        });
    }

    public void getAnswer(String AnswerString,Button currentButton, Button other1,Button other2) {

        if (!times.getText().equals("Time is up") && currentQ.getANSWER().equals(AnswerString)) {

            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
        //    currentButton.setBackgroundColor(Color.LTGRAY);
            score=score+10;
            txtQuestion.setText("CORRECT !!!");
            txtQuestion.setTextColor(Color.GREEN);
            scored.setText("Score:" + score+"             "+"Wrong:" + wrong);
            currentButton.setEnabled(false);
            other1.setEnabled(false);
            other2.setEnabled(false);


        }
        else if(!times.getText().equals("Time is up") && wrong<10){
          //  currentButton.setBackgroundColor(Color.RED);
            wrong++;
            txtQuestion.setText("WRONG ANSWER");
            currentButton.setEnabled(false);
            other1.setEnabled(false);
            other2.setEnabled(false);
            txtQuestion.setTextColor(Color.RED);
            scored.setText("Score:" + score+"             "+"Wrong:" + wrong);
        }

        else {

            // if unlucky start activity and finish the game

            Intent intent = new Intent(QuestionActivity.this,
                    ResultActivity.class);

            // passing the int value
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }
        if (qid < 30) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    txtQuestion.setTextColor(Color.WHITE);

                    currentQ = quesList.get(qid);
                    setQuestionView();
                }
            }, 2000);

            // if questions are not over then do this

        } else {

            // if over do this
            Intent intent = new Intent(QuestionActivity.this,
                    ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            times.setText("Time is up");


        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText("Remaining time "+hms);
        }

    }

    private void setQuestionView() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        // the method which will put all things together
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());

        qid++;
    }

}
