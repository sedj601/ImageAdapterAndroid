package com.example.blj0011.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnDoubleDown, btnSplit, btnHit, btnStand;
    ConstraintLayout container;

    Deck deck;
    Hand dealer, player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        btnDoubleDown = findViewById(R.id.btnDoubleDown);
        btnSplit = findViewById(R.id.btnSplit);
        btnHit = findViewById(R.id.btnHit);
        btnStand = findViewById(R.id.btnStand);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deck = new Deck(this);
        deck.setLocation(container, (size.x / 2), (size.y / 2 - 200));
        deck.shuffle();
        deck.dealBlackJack();

        handleButtonHandlers();



    }

    private void handleButtonHandlers()
    {
        btnDoubleDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        btnSplit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        btnHit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        btnStand.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dealer = new Hand(deck.getDealerHand());
                dealer.getHand().get(0).showFrontImage();

                player = new Hand(deck.getPlayerHand());

                AnimatorSet finalAnimatorSet = new AnimatorSet();
                while(dealer.getValueOfCard() < 17)
                {
                    finalAnimatorSet.play(deck.dealDealerAnotherCard(dealer));
                    dealer = new Hand(deck.getDealerHand());

                    Log.i("dealer hand", Integer.toString(dealer.getValueOfCard()));
                    finalAnimatorSet.setDuration(500);
                    finalAnimatorSet.start();
                    finalAnimatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        dealer.getHand().get(dealer.getHand().size() - 1).showFrontImage();
                    }
                });
                }
//
//                finalAnimatorSet.playSequentially(animatorSet);
//                finalAnimatorSet.setDuration(500);
//                finalAnimatorSet.start();

                if(dealer.getValueOfCard() >= 17 && dealer.getValueOfCard() <= 21)
                {
                    if(dealer.getValueOfCard() > player.getValueOfCard())
                    {
                        Log.i("Results", "Dealer Win!");
                    }
                    else if(dealer.getValueOfCard() > player.getValueOfCard())
                    {
                        Log.i("Results", "You Won!");
                    }
                    else {
                        Log.i("Results", "Draw!");
                    }
                }
                return true;
            }
        });

    }

}
