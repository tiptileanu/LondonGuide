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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PoiList extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.LayoutManager manager;
    PoiAdapter adapter;
    DatabaseReference dbRef;
    ArrayList<POI> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_list);
        rv=findViewById(R.id.id_rv);
        //set root in "Attraction" node
        dbRef= FirebaseDatabase.getInstance().getReference("Attraction");
        manager=new LinearLayoutManager(PoiList.this);
        rv.setLayoutManager(manager);
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // Populate the ArrayList with entries from Firebase Database
            for (DataSnapshot dss:dataSnapshot.getChildren()){
                POI poi=dss.getValue(POI.class);
                list.add(poi);
            }
            adapter = new PoiAdapter(list);
            rv.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
