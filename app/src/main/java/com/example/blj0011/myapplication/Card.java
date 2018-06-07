package com.example.blj0011.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by blj0011 on 6/6/2018.
 */
public final class Card
{

    private final String title;
    private final String suit;
    private final String face;
    private boolean showing;
    private final Integer backImage;
    private final Integer frontImage;
    private boolean isMatched;
    private ImageView imageView;

    public Card(String suit, String face, Integer backImage, Integer frontImage, Context context)
    {
        this.face = face;
        this.suit = suit;
        this.title = face + suit;
        this.backImage = backImage;
        this.frontImage = frontImage;
        showing = false;
        isMatched = false;

        imageView = new ImageView(context);
        imageView.setImageResource(this.backImage);
    }

    public Card(String suit, String face, Integer backImage, Integer frontImage, Integer width, Integer height, Context context)
    {
        this.face = face;
        this.suit = suit;
        this.title = face + suit;
        this.backImage = backImage;
        this.frontImage = frontImage;
        showing = false;
        isMatched = false;

        imageView = new ImageView(context);
        imageView.setImageResource(this.backImage);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
    }

    public String getTitle()
    {
        return title;
    }

    public String getSuit()
    {
        return this.suit;
    }

    public String getFace()
    {
        return this.face;
    }

    public void showBackImage()
    {
        this.imageView.setImageResource(backImage);
        showing = false;
    }

    public void showFrontImage()
    {
        this.imageView.setImageResource(frontImage);
        showing = true;
    }

    public boolean isShowing()
    {
        return showing;
    }

    public void setMatched(boolean control)
    {
        this.isMatched = control;
    }

    public boolean isMatched()
    {
        return isMatched;
    }

    public ImageView getImageView()
    {
        return imageView;
    }
}
