package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.firebase.ui.auth.AuthUI;

public class MainActivity extends AppCompatActivity {
    FrameLayout attr, muses, parks;
    //Constant used to name  an intent extra, sent to next activity
    private String TYPE = "TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attr = findViewById(R.id.fl_attractions);
        muses = findViewById(R.id.fl_museums);
        parks = findViewById(R.id.fl_parks);
        //click listener on first category
        attr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PoiList.class);
                //assign value Attraction if 1st category is clicked on
                intent.putExtra(TYPE, "Attraction");
                startActivity(intent);
            }
        });
        //click listener on second category
        muses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PoiList.class);
                //assign value Attraction if 2nd category is clicked on
                intent.putExtra(TYPE, "Museum");
                startActivity(intent);

            }
        });
        //click listener on third category
        parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PoiList.class);
                //assign value Attraction if 3rd category is clicked on
                intent.putExtra(TYPE, "Park");
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                // if this option is chosen, user is logged out and app redirected to login screen
                AuthUI.getInstance().signOut(this);
                Intent intentReset = new Intent(this, FirstScreen.class);
                startActivity(intentReset);

                return true;
            case R.id.add_poi_menu:
                // if this option is chosen. go to add poi activity
                Intent intent = new Intent(this, ImagePickerActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
