package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewsList extends AppCompatActivity {

    RecyclerView rvReviews;
    RecyclerView.LayoutManager managerReviews;
    ReviewsAdapter reviewsAdapter;
    Query queryRef;
    ArrayList<Reviews> reviewsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);
        rvReviews = findViewById(R.id.id_rv_reviews);
        managerReviews=new LinearLayoutManager(ReviewsList.this);
        rvReviews.setLayoutManager(managerReviews);

        String pname=getIntent().getStringExtra("poiName");

        queryRef = FirebaseDatabase.getInstance().getReference("Reviews").orderByChild("nameOfPoi").equalTo(pname);
        queryRef.addValueEventListener(listener);
//        queryRef = dbRef.orderByChild("nameOfPoi").equalTo()
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dsr:dataSnapshot.getChildren()){
                Reviews review=dsr.getValue(Reviews.class);
                reviewsList.add(review);
            }

            reviewsAdapter=new ReviewsAdapter(reviewsList);
            rvReviews.setAdapter(reviewsAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

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
