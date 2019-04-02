package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_review);

        //        //method to extract logged in user's info
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getEmail();

        tvName = findViewById(R.id.tv_pname);
        etDecription = findViewById(R.id.et_pdescription);
        rbUserRating = findViewById(R.id.rb_prating);
        btnCancel = findViewById(R.id.btn_pcancel);
        btnSubmit = findViewById(R.id.btn_psubmit);
        Intent intent = getIntent();
        final POI reviewPoi = intent.getParcelableExtra("reviewPoi");

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
                Reviews newReview;
                if (userId.isEmpty()){
                    newReview = new Reviews(tvName.getText().toString(), etDecription.getText().toString(), userId+"anonymous", rbUserRating.getRating());
                } else{
                    newReview = new Reviews(tvName.getText().toString(), etDecription.getText().toString(), userId, rbUserRating.getRating());
                }

                dbReviews.child(dbReviews.push().getKey()).setValue(newReview);
                Toast toast = Toast.makeText(getApplicationContext(), "Review saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
