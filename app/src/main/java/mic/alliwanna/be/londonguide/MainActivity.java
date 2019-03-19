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
    private String TYPE="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attr=findViewById(R.id.fl_attractions);
        muses=findViewById(R.id.fl_museums);
        parks=findViewById(R.id.fl_parks);

        attr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, PoiList.class);
                intent.putExtra(TYPE, "Attraction");
                startActivity(intent);
            }
        });

        muses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, PoiList.class);
                intent.putExtra(TYPE, "Museum");
                startActivity(intent);

            }
        });

        parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, PoiList.class);
                intent.putExtra(TYPE, "Park");
                startActivity(intent);

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                // sign out
                AuthUI.getInstance().signOut(this);
                return true;
            case R.id.add_poi_menu:
                // go to add poi activity
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
