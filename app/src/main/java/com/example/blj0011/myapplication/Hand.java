package com.example.blj0011.myapplication;

import java.util.Arrays;
import java.util.List;

public class Hand {

    private final List<String> faceCards = Arrays.asList("10", "j", "q", "k");
    private final List<String> numberCards = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

    private List<Card> hand;
    private int value;

    public  Hand(List<Card> hand)
    {
        this.hand = hand;
        value = 0;
        for(Card card : hand)
        {
            calculate(card);
        }
    }

    public void addCard(Card card)
    {
        hand.add(card);
        calculate(card);
    }
    private void calculate(Card card)
    {
        if(faceCards.contains(card.getFace()))
        {
            value += 10;
        }
        else if(numberCards.contains(card.getFace()))
        {
            value += Integer.parseInt(card.getFace());
        }
        else if("a".equals(card.getFace()))
        {
            if(value + 11 >= 17 && value + 11 <= 21)
            {
                value += 11;
            }
            else if(value + 11 > 21)
            {
                value += 1;
            }
        }
    }


    public int getValueOfCard()
    {
        return value;
    }

    public List<Card> getHand()
    {
        return hand;
    }
}
