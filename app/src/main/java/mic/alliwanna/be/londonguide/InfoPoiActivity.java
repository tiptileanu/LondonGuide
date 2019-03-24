package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        POI singlePoi = intent.getParcelableExtra("Poi");
        iv = findViewById(R.id.iv_poi_photo);
        name = findViewById(R.id.tv_poi_name);
        description = findViewById(R.id.tv_poi_description);
        address = findViewById(R.id.tv_poi_address);
        opening = findViewById(R.id.tv_poi_opening);
        website = findViewById(R.id.tv_poi_website);
        booking = findViewById(R.id.tv_poi_booking);

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
    }
}
