package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class SplashScreen extends AppCompatActivity {

    private static int splashTimeOut=5000; // splashscreen time


    TextView tvsplash;

    // Animation
    Animation anim_left_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvsplash = findViewById(R.id.tv_splash);



        // load the animation from res->anim
       anim_left_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_right);

        // start the animation anim_left_right
       tvsplash.startAnimation(anim_left_right);





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, FirstScreen.class); // screen after spashscreen
                startActivity(i);
                finish();

            }
        },splashTimeOut);


    }
}