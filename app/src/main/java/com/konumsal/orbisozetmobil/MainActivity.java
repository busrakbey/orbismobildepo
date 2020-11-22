package com.konumsal.orbisozetmobil;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.konumsal.orbisozetmobil.OrtakUI.LoginActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView imgLogoview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar =(ProgressBar)findViewById(R.id.main_act_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setDrawingCacheBackgroundColor(Color.WHITE);
        progressBar.setHorizontalScrollBarEnabled(true);
        BackToMainPage();
    }

    private void BackToMainPage()
    {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
       /* Animation anim = AnimationUtils.loadAnimation(this, R.anim.sctrl_anim);
        ImageView girislogo=(ImageView)findViewById(R.id.ImageViewGirisLogo);
        anim.reset();
        girislogo.clearAnimation();
        girislogo.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/
    }



}
