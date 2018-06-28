package com.example.blj0011.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    Button btnDoubleDown, btnSplit, btnHit, btnStand, btnNewHand;
    ConstraintLayout container;

    TextView tvDealerScore, tvPlayerScore;
    Deck deck;
    Point size = new Point();
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
        btnNewHand = findViewById(R.id.btnNewHand);

        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        deck = new Deck(getApplicationContext());
        deck.setLocation(container, (size.x / 2), (size.y / 2 - 200));
        deck.shuffle();
        AnimatorSet dealAnimatorSet =  deck.dealBlackJack();
        disableAllButton(true);
        dealAnimatorSet.start();
        dealAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                disableAllButton(false);
                if(Hand.calculateHandValue(deck.getPlayerHand()) == 21)
                {
                    checkWin("player");
                    Log.i("Player got BlackJack", "You Win!");
                }

                if(!deck.getPlayerHand().get(0).getFace().equals(deck.getPlayerHand().get(1).getFace()))
                {
                    btnSplit.setEnabled(false);
                }
            }
        });

        handleButtonHandlers();
    }

    private void handleButtonHandlers() {
        btnDoubleDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHit.performClick();
               Handler mHandler = new Handler();
               mHandler.postDelayed(new Runnable() {
                    public void run() {
                        btnStand.performClick();
                        disableAllButNextGame();
                    }
                }, 500);
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
                        Log.i("Player value", Integer.toString(Hand.calculateHandValue(deck.getPlayerHand())));
                        if(Hand.calculateHandValue(deck.getPlayerHand()) > 21)
                        {
                            checkWin("player bust");
                        }
                        else if(Hand.calculateHandValue(deck.getPlayerHand()) ==  21)
                        {
                            checkWin("player blackjack");
                        }
                        //Enable buttons
                    }
                });
            }
        });

        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Card> tempDealerHand = deck.getDealerHand();
                List<Card> tempPlayerHand = deck.getPlayerHand();

                tempDealerHand.get(1).showFrontImage();;

                if(Hand.calculateHandValue(tempDealerHand) == 21)
                {
                    checkWin("dealer");
                }
                else if(Hand.calculateHandValue(tempDealerHand) >= 17)
                {
                    if (Hand.calculateHandValue(tempDealerHand) > Hand.calculateHandValue(tempPlayerHand)) {
                        checkWin("dealer");
                    } else if (Hand.calculateHandValue(tempDealerHand) < Hand.calculateHandValue(tempPlayerHand)) {
                        checkWin("player");
                    } else {
                       checkWin("draw");
                    }
                }
                else
                {
                    List<Animator> animators = new ArrayList<>();
                    int counterControl = deck.simulateDealingDealerAnotherCard();
                    for(int i = 0; i < counterControl; i++)
                    {
                        animators.add(deck.dealDealerAnotherCard());
                    }
                    final AnimatorSet finalAnimatorSet = new AnimatorSet();
                     finalAnimatorSet.playSequentially(animators);
                     finalAnimatorSet.addListener(new AnimatorListenerAdapter() {
                         @Override
                         public void onAnimationEnd(Animator animation) {
                             super.onAnimationEnd(animation);
                             disableAllButNextGame();
                             List<Card> tempDealerHand = deck.getDealerHand();
                             List<Card> tempPlayerHand = deck.getPlayerHand();

                             if(Hand.calculateHandValue(tempDealerHand) > 21)
                             {
                                 checkWin("dealer bust");
                             }
                             else
                             {
                                 if (Hand.calculateHandValue(tempDealerHand) > Hand.calculateHandValue(tempPlayerHand)) {
                                     checkWin("dealer");
                                 } else if (Hand.calculateHandValue(tempDealerHand) < Hand.calculateHandValue(tempPlayerHand)) {
                                     checkWin("player");
                                 } else {
                                     checkWin("draw");
                                 }
                             }
                         }
                     });

                     finalAnimatorSet.start();
                }
            }
        });

        btnNewHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deck.clear();
                deck = new Deck(getApplicationContext());
                deck.setLocation(container, (size.x / 2), (size.y / 2 - 200));
                deck.shuffle();
                AnimatorSet dealAnimatorSet =  deck.dealBlackJack();
                disableAllButton(true);
                dealAnimatorSet.start();
                dealAnimatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        disableAllButton(false);
                        if(Hand.calculateHandValue(deck.getPlayerHand()) == 21)
                        {
                            checkWin("player blackjack");
                        }

                        if(!deck.getPlayerHand().get(0).getFace().equals(deck.getPlayerHand().get(1).getFace()))
                        {
                            btnSplit.setEnabled(false);
                        }
                    }
                });
            }
        });
    }

    public void disableAllButton(boolean tempControl)
    {
        btnDoubleDown.setEnabled(!tempControl);
        btnSplit.setEnabled(!tempControl);
        btnHit.setEnabled(!tempControl);
        btnStand.setEnabled(!tempControl);
        btnNewHand.setEnabled(!tempControl);
    }

    public void disableAllButNextGame()
    {
        btnDoubleDown.setEnabled(false);
        btnSplit.setEnabled(false);
        btnHit.setEnabled(false);
        btnStand.setEnabled(false);
        btnNewHand.setEnabled(true);
    }

    public void checkWin(String control)
    {
        disableAllButNextGame();
        switch (control)
        {
            case "player":
                Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();
                break;
            case "dealer":
                Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
                break;
            case "draw":
                Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
                break;
            case "dealer bust":
                Toast.makeText(this, "Dealer Bust! You Won!", Toast.LENGTH_SHORT).show();
                break;
            case "player bust":
                Toast.makeText(this, "You Bust! You Lose!", Toast.LENGTH_SHORT).show();
                break;
            case "dealer blackjack":
                Toast.makeText(this, "Dealer BlackJack! You Lose!", Toast.LENGTH_SHORT).show();
                break;
            case "player blackjack":
                Toast.makeText(this, "You Bust! You Lose!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
