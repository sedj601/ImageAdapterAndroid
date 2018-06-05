package com.example.blj0011.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnChangeImage;
    ImageView ivChangeImage;

    float mLastTouchX, mLastTouchY;

    RotateAnimation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnChangeImage = findViewById(R.id.btnChangeImage);
        ivChangeImage = findViewById(R.id.ivChangeImage);

        ivChangeImage.setImageResource(R.drawable.ic_home_black_24dp);
        //anim = new RotateAnimation(0f, 350f, ivChangeImage.getX() + (ivChangeImage.getLayoutParams().width / 2), ivChangeImage.getY() + (ivChangeImage.getLayoutParams().height / 2));

        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivChangeImage.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_home_black_24dp).getConstantState())
                {
                    ivChangeImage.setImageResource(R.drawable.card_spades_a);

                    anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setRepeatCount(Animation.INFINITE);
                    anim.setDuration(700);

                    ivChangeImage.startAnimation(anim);
                }
                else
                {
                    ivChangeImage.setImageResource(R.drawable.ic_home_black_24dp);
                    ivChangeImage.setAnimation(null);
                }

            }
        });


       ivChangeImage.setOnTouchListener(new View.OnTouchListener() {
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

                       ivChangeImage.setX(ivChangeImage.getX() + dx);
                       ivChangeImage.setY(ivChangeImage.getY() + dy);


                       mLastTouchX = x;
                       mLastTouchY = y;
                       break;
               }

               return true;
           }

       });

    }

}
