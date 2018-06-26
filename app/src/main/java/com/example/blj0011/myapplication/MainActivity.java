package com.example.blj0011.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
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
    //Hand dealer, player;

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

    private void handleButtonHandlers() {
        btnDoubleDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet finalAnimatorSet = new AnimatorSet();
                finalAnimatorSet.play(deck.dealPlayerAnotherCard());
                finalAnimatorSet.start();
                finalAnimatorSet.setDuration(500);
                finalAnimatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        deck.getPlayerHand().get(deck.getPlayerHand().size() - 1).showFrontImage();

                        if(Hand.calculateHandValue(deck.getPlayerHand()) > 21)
                        {
                            Log.i("You Bust!", "Dealer Wins!");
                        }
                        //Enable buttons
                    }
                });
            }
        });

        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deck.getDealerHand().get(1).showFrontImage();

                if (Hand.calculateHandValue(deck.getDealerHand()) == 21) {
                    Log.i("btnStand", "BlackJack! Dealer Won!");
                } else {
                    if (Hand.calculateHandValue(deck.getDealerHand()) > 21) {
                        Log.i("btnStand", "Dealer Bust! You Won!");
                    } else if (Hand.calculateHandValue(deck.getDealerHand()) >= 17 && Hand.calculateHandValue(deck.getDealerHand()) <= 20) {
                        if (Hand.calculateHandValue(deck.getDealerHand()) > Hand.calculateHandValue(deck.getPlayerHand())) {
                            Log.i("Results", "Dealer Win!");
                        } else if (Hand.calculateHandValue(deck.getDealerHand()) < Hand.calculateHandValue(deck.getPlayerHand())) {
                            Log.i("Results", "You Won!");
                        } else {
                            Log.i("Results", "Draw!");
                        }
                    } else {
                        AnimatorSet finalAnimatorSet = new AnimatorSet();
                        while (Hand.calculateHandValue(deck.getDealerHand()) < 17) {
                            finalAnimatorSet.play(deck.dealDealerAnotherCard());

                            Log.i("dealer hand", Integer.toString(Hand.calculateHandValue(deck.getDealerHand())));
                            finalAnimatorSet.setDuration(500);
                            finalAnimatorSet.start();
                            finalAnimatorSet.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    deck.getDealerHand().get(deck.getDealerHand().size() - 1).showFrontImage();
                                }
                            });
                        }

                        if (Hand.calculateHandValue(deck.getDealerHand()) > Hand.calculateHandValue(deck.getPlayerHand())) {
                            Log.i("Results", "Dealer Win!");
                        } else if (Hand.calculateHandValue(deck.getDealerHand()) < Hand.calculateHandValue(deck.getPlayerHand())) {
                            Log.i("Results", "You Won!");
                        } else {
                            Log.i("Results", "Draw!");
                        }
                    }
                }
            }

        });
    }
}
