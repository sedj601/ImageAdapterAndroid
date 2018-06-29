package com.example.blj0011.myapplication;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        for (Card card : hand) {
            if (staticFaceCards.contains(card.getFace())) {
                value += 10;
            } else if (staticNumberCards.contains(card.getFace())) {
                value += Integer.parseInt(card.getFace());
            }
            if("a".equals(card.getFace()))
            {
                value += 11;
            }
        }

        if(value > 21)
        {
            List<String> tempString = new ArrayList<>();
            for(Card card : hand)
            {
                tempString.add(card.getFace());
            }

            int tempFrequency = Collections.frequency(tempString, "a");
            for(int i =0; i < tempFrequency; i++)
            {
                value -= 10;
                if(value < 21)
                {
                    break;
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
                int tempCal1 = value + 1;
                int tempCal2 = value + 11;
                if(tempCal2 > 21)
                {
                    value = tempCal1;
                }
                else {
                    value = tempCal2;
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
