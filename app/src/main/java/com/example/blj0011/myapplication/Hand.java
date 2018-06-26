package com.example.blj0011.myapplication;

import java.util.Arrays;
import java.util.List;

public class Hand {

    private List<Card> hand;


    public  Hand(List<Card> hand)
    {
        this.hand = hand;
    }

    public void addCard(Card card)
    {
        hand.add(card);
    }

    public static int calculateHandValue(List<Card> hand)
    {
        final List<String> staticFaceCards = Arrays.asList("10", "j", "q", "k");
        final List<String> staticNumberCards = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

        int value = 0;

        for(Card card : hand)
        {
            if(staticFaceCards.contains(card.getFace()))
            {
                value += 10;
            }
            else if(staticNumberCards.contains(card.getFace()))
            {
                value += Integer.parseInt(card.getFace());
            }
            else if("a".equals(card.getFace()))
            {
                if(value + 11 >= 11 && value + 11 <= 21)
                {
                    value += 11;
                }
                else if(value + 11 > 21)
                {
                    value += 1;
                }
            }
        }

        return value;
    }

    public static int calculateHandValue(Hand hand)
    {
        final List<String> staticFaceCards = Arrays.asList("10", "j", "q", "k");
        final List<String> staticNumberCards = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

        int value = 0;

        for(Card card : hand.getHand())
        {
            if(staticFaceCards.contains(card.getFace()))
            {
                value += 10;
            }
            else if(staticNumberCards.contains(card.getFace()))
            {
                value += Integer.parseInt(card.getFace());
            }
            else if("a".equals(card.getFace()))
            {
                if(value + 11 >= 11 && value + 11 <= 21)
                {
                    value += 11;
                }
                else if(value + 11 > 21)
                {
                    value += 1;
                }
            }
        }

        return value;
    }

    public List<Card> getHand()
    {
        return hand;
    }
}
