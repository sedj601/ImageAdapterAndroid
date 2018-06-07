package com.example.blj0011.myapplication;


import android.content.Context;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    // references to our images
    final private Integer[] cardIds = {
        R.drawable.spades_a, R.drawable.spades_2, R.drawable.spades_3, R.drawable.spades_4, R.drawable.spades_5, R.drawable.spades_6,
        R.drawable.spades_7, R.drawable.spades_8, R.drawable.spades_9, R.drawable.spades_10, R.drawable.spades_j, R.drawable.spades_q, R.drawable.spades_k,
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

    final private  String[] suits = {"spades", "hearts", "clubs", "diamonds"};
    final private String[] faceValues = {"a", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"};

    int topCard;
    List<Card> cards;

    public Deck(Context context)
    {
        topCard = 0;
        cards = new ArrayList();

        for(int i = 0; i < cardIds.length; i++)
        {
            String suit = suits[i / 12];
            String faceValue = faceValues[i % 12];
            cards.add(new Card(suit, faceValue, cardBackIds[0], cardIds[i], context));
        }
    }

    public Card getNextCard()
    {
        Card tempCard = cards.get(topCard);
        topCard++;

        return tempCard;
    }

}