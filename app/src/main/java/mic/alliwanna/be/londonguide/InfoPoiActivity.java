package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InfoPoiActivity extends AppCompatActivity {

    ImageView iv;
    TextView name, description, address, opening, website, booking;
    Button viewReviews, submitReview;
    private String websiteUrl, bookingUrl, photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_poi);
        Intent intent = getIntent();
        final POI singlePoi = intent.getParcelableExtra("Poi");
        iv = findViewById(R.id.iv_poi_photo);
        name = findViewById(R.id.tv_poi_name);
        description = findViewById(R.id.tv_poi_description);
        address = findViewById(R.id.tv_poi_address);
        opening = findViewById(R.id.tv_poi_opening);
        website = findViewById(R.id.tv_poi_website);
        booking = findViewById(R.id.tv_poi_booking);
        submitReview = findViewById(R.id.btn_submit_review);
        viewReviews = findViewById(R.id.btn_read_reviews);

        photoUrl = singlePoi.getPoiPhotoUrl();
        bookingUrl = "<a href='" + singlePoi.getPoiBookingUrl() + "'> Press here to make a booking </a>";
        websiteUrl = "<a href='" + singlePoi.getPoiWebsite() + "'> Press here to visit the official website</a>";

        Picasso.get().load(photoUrl).fit().into(iv);
        name.setText(singlePoi.getPoiName());
        description.setText(singlePoi.getPoiDescription());
        address.setText(singlePoi.getPoiAddress());
        opening.setText(singlePoi.getPoiOpeningHours());


        // make TextView clickable and set it to the POI's web page
        website.setClickable(true);
        website.setMovementMethod(LinkMovementMethod.getInstance());
        website.setText(Html.fromHtml(websiteUrl));


        //check if POI needs an advanced booking to visit
        if (singlePoi.isPoiCanBook()) {
            booking.setClickable(true);
            booking.setMovementMethod(LinkMovementMethod.getInstance());
            booking.setText(Html.fromHtml(bookingUrl));
        } else {
            booking.setClickable(false);
            booking.setText("No advanced booking is needed.");
        }

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser loggedUser = FirebaseAuth.getInstance().getCurrentUser();
                final String loggedUserMail = loggedUser.getEmail();

                if (loggedUserMail != null) {
                    Intent i = new Intent(InfoPoiActivity.this, SubmitReview.class);
                    i.putExtra("reviewPoi", singlePoi);
                    startActivity(i);

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "You can not post reviews as Guest", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });

        viewReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoPoiActivity.this, ReviewsList.class);
                intent.putExtra("poiName", singlePoi.getPoiName());
                startActivity(intent);
            }
        });
    }
}
