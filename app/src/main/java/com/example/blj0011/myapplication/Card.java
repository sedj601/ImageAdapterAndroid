package com.example.blj0011.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by blj0011 on 6/6/2018.
 */
public final class Card
{
    int cardWidth = 150;
    int cardHeight = (int)(cardWidth * 1.4);
    private final ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(cardWidth, cardHeight);

    private final String title;
    private final String suit;
    private final String face;
    private boolean showing;
    private final Integer backImage;
    private final Integer frontImage;
    private boolean isMatched;
    private ImageView imageView;

    private float mLastTouchX;
    private float mLastTouchY;


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
        imageView.setLayoutParams(layoutParams);
        setOnTouch();
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
        imageView.setLayoutParams(layoutParams);
        setOnTouch();
    }

    private void setOnTouch()
    {
        imageView.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               switch (event.getAction())
               {
                   case MotionEvent.ACTION_DOWN:
                       mLastTouchX = event.getRawX();
                       mLastTouchY = event.getRawY();
                       break;
                   case MotionEvent.ACTION_MOVE:
                       final float x = event.getRawX();
                       final float y = event.getRawY();
                       final float dx = x - mLastTouchX;
                       final float dy = y - mLastTouchY;

                       imageView.setX(imageView.getX() + dx);
                       imageView.setY(imageView.getY() + dy);


                       mLastTouchX = x;
                       mLastTouchY = y;
                       break;
               }

               return true;
           }

       });
    }

    public void setLocation(float x, float y)
    {
        imageView.setX(x - layoutParams.width / 2);
        imageView.setY(y - layoutParams.height / 2);
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
