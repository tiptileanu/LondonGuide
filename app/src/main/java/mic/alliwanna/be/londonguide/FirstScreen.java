package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class FirstScreen extends AppCompatActivity {
    private Button login, register;
    private TextView reset, appName;
    private Animation anim_left_right;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        final VideoView videoView = findViewById(R.id.vv1);
        // find button by id from res->layout
        login = findViewById(R.id.login_button);

        // find button by id from  res->layout
        register = findViewById(R.id.register_button);

        // find textview by id from  res->layout
        reset = findViewById(R.id.tv_resetpass);
        appName=findViewById(R.id.tv_splash2);

        // call animation by id from  res->anim
        anim_left_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_right);
        appName.startAnimation(anim_left_right);
        register.startAnimation(anim_left_right);
        login.startAnimation(anim_left_right);

        // video must be .mp4 format mostly because of the size, .avi will take more than 40 MB.
        //getPackageName()  ** Return the name of this application's package
        // and by using R.raw.video3nosound we are calling the video from res->raw
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video3nosound;

        // using this command the video cannot be clicked
        videoView.setClickable(false);

        //the method Uri.parse creates a new Uri object from a properly formatted String
        Uri uri = Uri.parse(videoPath);

        // Sets video URI
        videoView.setVideoURI(uri);

        // will create a default set of controls and put them in a window floating above the application
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        //starts the video
        videoView.start();
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        // Sets what action to be taken after the video finishes
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            // Creates a loop, restarting the video when it reaches the end
            public void onCompletion(MediaPlayer mp) {
                videoView.start();

            }
        });

        // Starts Login activity
        login.setOnClickListener(new View.OnClickListener() //  implementing this interface will override onClick and take as a parameter View.OnClickListener
        {


            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstScreen.this, LoginScreen.class));


            }
        });

        // Starts Register activity
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstScreen.this, RegisterUser.class));
            }
        });

        // Starts ResetPassword activity
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstScreen.this, ResetPassword.class));
            }
        });
    }
}
