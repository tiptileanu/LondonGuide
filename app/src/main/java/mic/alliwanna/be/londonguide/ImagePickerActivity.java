package mic.alliwanna.be.londonguide;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ImagePickerActivity extends AppCompatActivity {

    public static final int REQUEST = 1;
    public static final String URL = "URL";
    public static final String TYPE="TYPE";

    private Button btnPick, btnUpload;
    private ImageView ivPoi;
    private EditText etPhotoName;
    private Spinner mySpinner;
    String poiType, photoUrl;
    private Uri url;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        btnPick=findViewById(R.id.btn_pick_image);
        btnUpload=findViewById(R.id.btn_upload);
        ivPoi=findViewById(R.id.iv_poi_image);
        etPhotoName=findViewById(R.id.et_photo_name);
        mySpinner=findViewById(R.id.sp_poi_type);
        storageReference= FirebaseStorage.getInstance().getReference("poiPhotos");

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                // only images are visible in the new open window
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, REQUEST);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StorageReference reference=storageReference.child(etPhotoName.getText().toString() + "." + getExtension(url));
                reference.putFile(url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadUrl=uri.toString();
                                String poiType = mySpinner.getSelectedItem().toString();
                                Intent intent=new Intent(ImagePickerActivity.this, PoiDetailsActivity.class);
                                intent.putExtra(URL, downloadUrl);
                                intent.putExtra(TYPE, poiType);
                                startActivity(intent);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong, upload failed!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        ProgressDialog progress;
                        progress = new ProgressDialog(ImagePickerActivity.this);
                        progress.setTitle("Please Wait!");
                        progress.setMessage("Your photo is being uploaded.");
                        progress.setCancelable(true);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.show();
                    }
                });
            }
        });
    }



    private String getExtension(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST && resultCode == RESULT_OK && data.getData() != null)
        {
            url = data.getData();
            Picasso.get().load(url).into(ivPoi);
        }
    }
}
