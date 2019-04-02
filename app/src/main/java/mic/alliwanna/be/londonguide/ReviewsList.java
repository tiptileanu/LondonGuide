package mic.alliwanna.be.londonguide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    DatabaseReference dbRef;
    ArrayList<Reviews> reviewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);
        rvReviews = findViewById(R.id.id_rv_reviews);
        dbRef = FirebaseDatabase.getInstance().getReference("Reviews");
        managerReviews=new LinearLayoutManager(ReviewsList.this);
        rvReviews.setLayoutManager(managerReviews);
        dbRef.addValueEventListener(listener);
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
}
