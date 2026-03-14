package com.anantech.quiz.cricketStadiumQuiz;

/**
 * Created by Sandeep on 18-06-2016.
 * Updated 2024: Migrated to AndroidX, fixed deprecated APIs.
 */

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class QuestionActivity extends AppCompatActivity {
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    int wrong = 0;
    final AtomicBoolean gameOver = new AtomicBoolean(false);

    Question currentQ;
    TextView txtQuestion, times, scored;
    Button button1, button2, button3;
    volatile CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {});

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        QuizHelper db = new QuizHelper(this);
        quesList = db.getAllQuestions();
        Collections.shuffle(quesList);
        currentQ = quesList.get(qid);

        txtQuestion = findViewById(R.id.txtQuestion);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        scored = findViewById(R.id.score);
        times = findViewById(R.id.timers);

        setQuestionView();
        times.setText("00:10:00");

        timer = new CounterClass(600000, 1000);
        timer.start();

        button1.setOnClickListener(v ->
                getAnswer(button1.getText().toString(), button1, button2, button3));

        button2.setOnClickListener(v ->
                getAnswer(button2.getText().toString(), button2, button1, button3));

        button3.setOnClickListener(v ->
                getAnswer(button3.getText().toString(), button3, button1, button2));
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }

    public void getAnswer(String answerString, Button currentButton, Button other1, Button other2) {
        if (gameOver.get()) return;

        if (currentQ.getANSWER().equals(answerString)) {
            score += 10;
            txtQuestion.setText("CORRECT !!!");
            txtQuestion.setTextColor(Color.GREEN);
            scored.setText("Score:" + score + "             " + "Wrong:" + wrong);
        } else {
            wrong++;
            txtQuestion.setText("WRONG ANSWER");
            txtQuestion.setTextColor(Color.RED);
            scored.setText("Score:" + score + "             " + "Wrong:" + wrong);
        }

        currentButton.setEnabled(false);
        other1.setEnabled(false);
        other2.setEnabled(false);

        if (wrong >= 10 || qid >= quesList.size()) {
            if (gameOver.compareAndSet(false, true)) {
                CountDownTimer t = timer;
                if (t != null) {
                    t.cancel();
                }
                new Handler(Looper.getMainLooper()).postDelayed(this::navigateToResult, 1500);
            }
            return;
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!gameOver.get() && !isFinishing()) {
                txtQuestion.setTextColor(Color.WHITE);
                currentQ = quesList.get(qid);
                setQuestionView();
            }
        }, 2000);
    }

    private void navigateToResult() {
        try {
            if (!isFinishing()) {
                Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("score", score);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        } catch (IllegalStateException ignored) {
        }
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            times.setText("Time is up!");
            if (gameOver.compareAndSet(false, true)) {
                new Handler(Looper.getMainLooper()).postDelayed(
                        QuestionActivity.this::navigateToResult, 1500);
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            times.setText("Remaining time " + hms);
        }
    }

    private void setQuestionView() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        txtQuestion.setText(currentQ.getQUESTION());
        txtQuestion.setTextColor(Color.WHITE);
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());
        qid++;
    }
}
