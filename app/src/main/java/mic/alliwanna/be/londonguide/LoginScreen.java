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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginScreen extends AppCompatActivity {


    private EditText email, password;
    private TextView resetPass;
    private Button login;


    private FirebaseDatabase database; // declare instance of FirebaseDatabase
    private DatabaseReference dbRef;


    private FirebaseAuth mAuth;  // instance of FirebaseAuth
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "Firebase Login "; // declare a TAG constant // used to log events that should never happen


    @Override
    public void onBackPressed() {
        Intent i = new Intent(LoginScreen.this, FirstScreen.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        mAuth = FirebaseAuth.getInstance();//initialize the FirebaseAuth instance


        email = findViewById(R.id.email_login);       // find edittext by id from res->layout
        password = findViewById(R.id.password_login); // find edittext by id from res->layout
        login = findViewById(R.id.log_button);        // find button by id from res->layout
        resetPass = findViewById(R.id.forgot_pass);   // find textview by id from res->layout




        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String _email = email.getText().toString().toLowerCase().trim();
                String _pass = password.getText().toString().toLowerCase().trim();
                if(_email.length() > 0 && _pass.length() > 0)
                {

                    mAuth.signInWithEmailAndPassword(_email, _pass) // After a user signs in for the first time, a new user account is created and linked to the credentials

                            .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                            {
                                //On task completion
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if(!task.isSuccessful())
                                    {
                                        Toast.makeText(LoginScreen.this, "Error signing in!", Toast.LENGTH_SHORT).show();

                                    } else{
                                        Toast.makeText(LoginScreen.this, "Sign in success!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                                    }
                                }
                            })


                            .addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(LoginScreen.this, "Cannot find details in database", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        resetPass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginScreen.this, ResetPassword.class));
            }
        });




        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                //Instatiate a FirebaseUser object and get the current user
                FirebaseUser user = firebaseAuth.getCurrentUser();// Firebase catches the user already signed in

                if (user !=null)
                {

                    Log.d(TAG, "Sign in successful"); //                  declare a TAG constant

                    if(user!=null)

                    {
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));

                    }
                } else
                {

                    Log.d(TAG, "User signed out!");
                }
            }
        };

    }


    @Override
    protected void onStart() //Called when the activity is becoming visible to the user.

    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() //Called when the activity is becoming hidden from the user

    {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }


}
