package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitReview extends AppCompatActivity {

    private TextView tvName;
    private EditText etDecription;
    private RatingBar rbUserRating;
    private Button btnCancel, btnSubmit;
    private DatabaseReference dbReviews;
    private float poiRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_review);

        //method to extract logged in user's info
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userEmail = user.getEmail();
        final String userId=userEmail.split("@")[0];


        tvName = findViewById(R.id.tv_pname);
        etDecription = findViewById(R.id.et_pdescription);
        rbUserRating = findViewById(R.id.rb_prating);
        btnCancel = findViewById(R.id.btn_pcancel);
        btnSubmit = findViewById(R.id.btn_psubmit);
        Intent intent = getIntent();
        final POI reviewPoi = intent.getParcelableExtra("reviewPoi");

        poiRating = reviewPoi.getPoiRating();

        dbReviews = FirebaseDatabase.getInstance().getReference("Reviews");


        tvName.setText(reviewPoi.getPoiName());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reviews newReview = new Reviews(tvName.getText().toString(), etDecription.getText().toString(), userId, rbUserRating.getRating());

                dbReviews.child(dbReviews.push().getKey()).setValue(newReview);
                Toast toast = Toast.makeText(getApplicationContext(), "Review saved", Toast.LENGTH_SHORT);
                toast.show();

                float avRating = reviewPoi.getPoiRating();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    //menu options are defined
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                // sign out
                AuthUI.getInstance().signOut(this);
                Intent intentReset = new Intent(this, FirstScreen.class);
                startActivity(intentReset);

                return true;
            case R.id.add_poi_menu:
                // go to add poi activity
                Intent intent = new Intent(this, ImagePickerActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //menu is inflated on activity creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // menu option is hidden on this activity
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            menu.findItem(R.id.add_poi_menu).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
