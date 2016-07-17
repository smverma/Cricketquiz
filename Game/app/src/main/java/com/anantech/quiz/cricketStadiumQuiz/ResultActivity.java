package com.anantech.quiz.cricketStadiumQuiz;

/**
 * Created by Sandeep on 18-06-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class ResultActivity extends Activity {
    InterstitialAd mInterstitialAd;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4307565756625227~2383839591");
        button = (Button) findViewById(R.id.btn);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4307565756625227/2400735594");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                playagain(button.getRootView());
            }
        });

        requestNewInterstitial();

        TextView textResult = (TextView) findViewById(R.id.textResult);

        Bundle b = getIntent().getExtras();

        int score = b.getInt("score");

        textResult.setText("Your score is " + " " + score + ". Thanks for playing.");





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadInterstitial();
               /* if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    playagain(button.getRootView());
                }*/

                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons

            }
        });


    }

    private void loadInterstitial() {
        int i=0;
        while(!mInterstitialAd.isLoaded() && i<10000){
i++;
        }
        mInterstitialAd.show();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void playagain(View o) {

        Intent intent = new Intent(this, QuestionActivity.class);

        startActivity(intent);

    }

    public void closeApplication() {

        onStop();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.



    }

    @Override
    public void onStop() {
        super.onStop();



    }
}
