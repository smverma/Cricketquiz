package com.anantech.quiz.cricketStadiumQuiz;

/**
 * Created by Sandeep on 18-06-2016.
 * Updated 2024: Migrated to AndroidX, new InterstitialAd API.
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class ResultActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        button = findViewById(R.id.btn);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        loadInterstitialAd();

        TextView textResult = findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = (b != null) ? b.getInt("score") : 0;
        textResult.setText("Your score is " + score + ". Thanks for playing!");

        button.setOnClickListener(v -> showInterstitialOrPlayAgain());
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-4307565756625227/2400735594", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    private void showInterstitialOrPlayAgain() {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    mInterstitialAd = null;
                    playAgain();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    mInterstitialAd = null;
                    playAgain();
                }
            });
            mInterstitialAd.show(this);
        } else {
            playAgain();
        }
    }

    private void playAgain() {
        try {
            if (!isFinishing()) {
                Intent intent = new Intent(this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (IllegalStateException ignored) {
        }
    }
}
