package com.ladddd.canvasdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.ladddd.canvasdemo.animate.FlipView;
import com.ladddd.canvasdemo.animate.SportsView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int count = 0;
    private SportsView mSportsView;
    private FlipView flip;
    private CameraRotateView mCameraRotateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image);
        mSportsView = findViewById(R.id.sportView);
        flip = findViewById(R.id.flip);
        mCameraRotateView = findViewById(R.id.rotate);

        //ViewAnimation
//        mImageView.animate().setDuration(3000).translationX(300);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mImageView.animate().translationX(500);
//                mImageView.animate().translationXBy(500); //加by是增量 300+500
//                mImageView.animate().setDuration(3000).x(1000).y(1000);

                //ViewAnimation
                if (count%3 == 0) {
                    mImageView.animate().setDuration(3000).rotation(180*count).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                } else if (count%3 == 1) {
                    mImageView.animate().setDuration(3000).scaleX(1.2f).scaleY(1.5f).withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            //只执行一次
                        }
                    });
                } else if (count%3 == 2) {
                    mImageView.animate().setDuration(3000).alpha(0.2f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            //正常结束时执行
                        }
                    });
                }
                count++;
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ObjectAnimation
                ObjectAnimator animator = ObjectAnimator.ofFloat(mSportsView, "progress", 0, 65)
                        .setDuration(1000);
//                animator.setInterpolator(new AnticipateOvershootInterpolator()); //回弹
                animator.setInterpolator(new BounceInterpolator());
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                animator.start();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofInt(mCameraRotateView, "degree", 0, -30)
                        .setDuration(1000);
                ObjectAnimator animator1 = ObjectAnimator.ofInt(mCameraRotateView, "pivot", 0, -270)
                        .setDuration(1000);
                ObjectAnimator animator2 = ObjectAnimator.ofInt(mCameraRotateView, "oppositeDegree", 0, 30)
                        .setDuration(1000);
                AnimatorSet set = new AnimatorSet();
                set.playSequentially(animator, animator1, animator2);
                set.start();
            }
        });

    }
}
