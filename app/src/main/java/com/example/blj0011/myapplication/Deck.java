package com.example.blj0011.myapplication;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Deck {
    // references to our images
    final private Integer[] cardIds = {
        R.drawable.spades_4, R.drawable.spades_a, R.drawable.spades_k, R.drawable.spades_2, R.drawable.spades_5, R.drawable.spades_6,
        R.drawable.spades_7, R.drawable.spades_8, R.drawable.spades_9, R.drawable.spades_10, R.drawable.spades_j, R.drawable.spades_q, R.drawable.spades_3,
        R.drawable.hearts_a, R.drawable.hearts_2, R.drawable.hearts_3, R.drawable.hearts_4, R.drawable.hearts_5, R.drawable.hearts_6,
        R.drawable.hearts_7, R.drawable.hearts_8, R.drawable.hearts_9, R.drawable.hearts_10, R.drawable.hearts_j, R.drawable.hearts_q, R.drawable.hearts_k,
        R.drawable.clubs_a, R.drawable.clubs_2, R.drawable.clubs_3, R.drawable.clubs_4, R.drawable.clubs_5, R.drawable.clubs_6,
        R.drawable.clubs_7, R.drawable.clubs_8, R.drawable.clubs_9, R.drawable.clubs_10, R.drawable.clubs_j, R.drawable.clubs_q, R.drawable.clubs_k,
        R.drawable.diamonds_a, R.drawable.diamonds_2, R.drawable.diamonds_3, R.drawable.diamonds_4, R.drawable.diamonds_5, R.drawable.diamonds_6,
        R.drawable.diamonds_7, R.drawable.diamonds_8, R.drawable.diamonds_9, R.drawable.diamonds_10, R.drawable.diamonds_j, R.drawable.diamonds_q, R.drawable.diamonds_k
    };

    final private Integer[] cardBackIds = {
        R.drawable.back_blue1, R.drawable.back_blue2, R.drawable.back_blue3, R.drawable.back_blue4, R.drawable.back_blue5,
        R.drawable.back_green1, R.drawable.back_green2, R.drawable.back_green3, R.drawable.back_green4, R.drawable.back_green5,
        R.drawable.back_red1, R.drawable.back_red2, R.drawable.back_red3, R.drawable.back_red4, R.drawable.back_red5
    };

    int topCard;
    int dealerCardXCounter = 1;

    List<Card> cards;
    List<Card> dealerHand;
    List<Card> playerHand;



    public Deck(Context context)
    {
        cards = new ArrayList();
        dealerHand = new ArrayList();
        playerHand = new ArrayList();

        for(int i = 0; i < cardIds.length; i++)
        {
            String name = context.getResources().getResourceEntryName(cardIds[i]);
            String tempSuit = name.split("_")[0];
            String tempFace = name.split("_")[1];
            cards.add(new Card(tempSuit, tempFace, cardBackIds[0], cardIds[i], context));
        }
    }

    public void setLocation(ConstraintLayout layout, float x, float y)
    {
        for(Card card : cards)
        {
            card.setLocation(x, y);
            layout.addView(card.getImageView());
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public AnimatorSet dealBlackJack()
    {
        final Card dealerCard1 = cards.get(topCard++);
        dealerHand.add(dealerCard1);
        ObjectAnimator dealerAnimator1X = ObjectAnimator.ofFloat(dealerCard1.getImageView(), "translationX", dealerCard1.getImageView().getX() - 20);
        ObjectAnimator dealerAnimator1Y = ObjectAnimator.ofFloat(dealerCard1.getImageView(), "translationY", dealerCard1.getImageView().getY() - 400);
        AnimatorSet dealerAnimatorSet1 = new AnimatorSet();
        dealerAnimatorSet1.playTogether(dealerAnimator1X, dealerAnimator1Y);
        dealerAnimatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dealerCard1.showFrontImage();
            }
        });

        final Card playerCard1 = cards.get(topCard++);
        playerHand.add(playerCard1);
        ObjectAnimator playerAnimator1X = ObjectAnimator.ofFloat(playerCard1.getImageView(), "translationX", playerCard1.getImageView().getX() - 20);
        ObjectAnimator playerAnimator1Y = ObjectAnimator.ofFloat(playerCard1.getImageView(), "translationY", playerCard1.getImageView().getY() + 400);
        AnimatorSet playerAnimatorSet1 = new AnimatorSet();
        playerAnimatorSet1.playTogether(playerAnimator1X, playerAnimator1Y);
        playerAnimatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                playerCard1.showFrontImage();
            }
        });

        final Card dealerCard2 = cards.get(topCard++);
        dealerCard2.getImageView().bringToFront();
        dealerHand.add(dealerCard2);
        ObjectAnimator dealerAnimator2X = ObjectAnimator.ofFloat(dealerCard2.getImageView(), "translationX", dealerCard2.getImageView().getX() + 20);
        ObjectAnimator dealerAnimator2Y = ObjectAnimator.ofFloat(dealerCard2.getImageView(), "translationY", dealerCard2.getImageView().getY() - 400);
        AnimatorSet dealerAnimatorSet2 = new AnimatorSet();
        dealerAnimatorSet2.playTogether(dealerAnimator2X, dealerAnimator2Y);


        final Card playerCard2 = cards.get(topCard++);
        playerHand.add(playerCard2);
        ObjectAnimator playerAnimator2X = ObjectAnimator.ofFloat(playerCard2.getImageView(), "translationX", playerCard2.getImageView().getX() + 20);
        ObjectAnimator playerAnimator2Y = ObjectAnimator.ofFloat(playerCard2.getImageView(), "translationY", playerCard2.getImageView().getY() + 400);
        AnimatorSet playerAnimatorSet2 = new AnimatorSet();
        playerAnimatorSet2.playTogether(playerAnimator2X, playerAnimator2Y);
        playerAnimatorSet2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                playerCard2.getImageView().bringToFront();
                playerCard2.showFrontImage();
            }
        });


        AnimatorSet finalAnimatorSet = new AnimatorSet();
        finalAnimatorSet.playSequentially(dealerAnimatorSet1, playerAnimatorSet1, dealerAnimatorSet2, playerAnimatorSet2);
        finalAnimatorSet.setDuration(500);


        return finalAnimatorSet;
    }

    public int simulateDealingDealerAnotherCard()
    {
        int control = 0;

        int innerTopCard = topCard;
        List<Card> innerDealerHand = dealerHand;

        while(Hand.calculateHandValue(innerDealerHand) < 17)
        {
            ++control;
            innerDealerHand.add(cards.get(innerTopCard++));
            Log.i("SimulationDealer Values", Integer.toString(Hand.calculateHandValue(innerDealerHand)));
        }


        return  control;
    }

    public AnimatorSet dealPlayerAnotherCard()
    {
        if(topCard < 52) {
            Card lastPlayerCard = playerHand.get(playerHand.size() - 1);

            Card tempPlayerCard = cards.get(topCard++);
            tempPlayerCard.getImageView().bringToFront();
            playerHand.add(tempPlayerCard);

            ObjectAnimator dealerAnimator1X = ObjectAnimator.ofFloat(tempPlayerCard.getImageView(), "translationX", lastPlayerCard.getImageView().getX() + 40);
            ObjectAnimator dealerAnimator1Y = ObjectAnimator.ofFloat(tempPlayerCard.getImageView(), "translationY", lastPlayerCard.getImageView().getY());

            AnimatorSet tempDealerAnimatorSet = new AnimatorSet();
            tempDealerAnimatorSet.playTogether(dealerAnimator1X, dealerAnimator1Y);

            return tempDealerAnimatorSet;
        }

        return null;
    }

    public AnimatorSet dealDealerAnotherCard()
    {
        if(topCard < 52) {

            final Card tempDealerCard = cards.get(topCard++);
            tempDealerCard.getImageView().bringToFront();
            dealerHand.add(tempDealerCard);

            ObjectAnimator dealerAnimator1X = ObjectAnimator.ofFloat(tempDealerCard.getImageView(), "translationX", cards.get(topCard).getImageView().getX() + 20 + (dealerCardXCounter++ * 40));
            ObjectAnimator dealerAnimator1Y = ObjectAnimator.ofFloat(tempDealerCard.getImageView(), "translationY", tempDealerCard.getImageView().getY() - 400);

            final AnimatorSet tempDealerAnimatorSet = new AnimatorSet();
            tempDealerAnimatorSet.playTogether(dealerAnimator1X, dealerAnimator1Y);
            tempDealerAnimatorSet.setDuration(500);
            tempDealerAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    tempDealerCard.showFrontImage();
                }
            });

            return tempDealerAnimatorSet;
        }

        return null;
    }

    public List<Card> getDealerHand()
    {
        return dealerHand;
    }

    public List<Card> getPlayerHand()
    {
        return playerHand;
    }

    public void clear()
    {
        for(Card tempCard : cards)
        {
            tempCard.getImageView().setVisibility(View.GONE);
        }
    }


}