package mic.alliwanna.be.londonguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {
    private EditText email, password;
    private TextView resetPass;
    private Button login;
    // instance of FirebaseAuth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // declare a TAG constant // used to log events that should never happen
    private static final String TAG = "Firebase Login ";

    @Override
    public void onBackPressed() {
        Intent i = new Intent(LoginScreen.this, FirstScreen.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        login = findViewById(R.id.log_button);
        resetPass = findViewById(R.id.forgot_pass);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString().toLowerCase().trim();
                String _pass = password.getText().toString().toLowerCase().trim();
                if (_email.length() > 0 && _pass.length() > 0) {
                    mAuth.signInWithEmailAndPassword(_email, _pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                //On task completion
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginScreen.this,
                                                "Error signing in!",
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(LoginScreen.this,
                                                "Sign in success!",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginScreen.this,
                                                MainActivity.class));
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginScreen.this,
                                            "Cannot find details in database",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this, ResetPassword.class));
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Instatiate a FirebaseUser object and get the current user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //console log message for our own use
                    Log.d(TAG, "Sign in successful");
                    if (user != null) {
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                    }
                } else {
                    Log.d(TAG, "User signed out!");
                }
            }
        };
    }


    @Override
    //Called when the activity is becoming visible to the user.
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    //Called when the activity is becoming hidden from the user
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}
