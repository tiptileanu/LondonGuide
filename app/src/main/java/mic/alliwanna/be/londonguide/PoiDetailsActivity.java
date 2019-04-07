package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PoiDetailsActivity extends AppCompatActivity {


    EditText poiName, poiAddress, poiOpeningHours, poiWebsite, poiBookingUrl, poiDescription;
    RatingBar poiRatingBar;
    Switch poiCanBook;
    Button btnCancel, btnSave;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_details);

        poiName = findViewById(R.id.et_poi_name);
        poiAddress = findViewById(R.id.et_poi_address);
        poiOpeningHours = findViewById(R.id.et_opening_hours);
        poiWebsite = findViewById(R.id.et_website);
        poiBookingUrl = findViewById(R.id.et_booking_url);
        poiDescription = findViewById(R.id.et_poi_description);
        poiRatingBar = findViewById(R.id.rating_bar);
        poiCanBook = findViewById(R.id.sw_can_book);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);


        Intent i=getIntent();
        final String downloadUrl=i.getStringExtra("URL");
        final String poiType=i.getStringExtra("TYPE");

        dbRef= FirebaseDatabase.getInstance().getReference(poiType);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                POI poi=new POI( poiName.getText().toString(),
                        poiDescription.getText().toString(),
                        poiAddress.getText().toString(),
                        poiOpeningHours.getText().toString(),
                        poiWebsite.getText().toString(),
                        downloadUrl,
                        poiBookingUrl.getText().toString(),
                        poiType, poiRatingBar.getRating(),
                        poiCanBook.isChecked());
                dbRef.child(poi.getPoiName()).setValue(poi).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast toast = Toast.makeText(getApplicationContext(), "POI saved, going back to main screen!", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong, upload failed!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        // Add delay to display toast(3 seconds)
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent =new Intent(PoiDetailsActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }, 3000);



                    }
                });

//                Intent intent =new Intent(PoiDetailsActivity.this, MainActivity.class);
//                startActivity(intent);
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
