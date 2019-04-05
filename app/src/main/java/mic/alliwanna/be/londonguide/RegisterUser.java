package mic.alliwanna.be.londonguide;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private Button register;


    private FirebaseAuth mAuth;

    @Override
    public void onBackPressed()// overrides the back button on the phone
    {
        Intent i = new Intent(RegisterUser.this, FirstScreen.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        mAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.email_register);        // find edittext by id from res->layout
        password = findViewById(R.id.password_register);  // find edittext by id from res->layout
        register = findViewById(R.id.button_register);   // find button by id from res->layout


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())                                              // Creates a new user account associated with the specified email address and passwor
                                                                                                                                                             // gets the email and password and puts it in a string
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {

                                if (task.isSuccessful())                                                                                                       // if the user and password are successfully created
                                {
                                    Toast.makeText(RegisterUser.this, email.getText().toString() + " registered", Toast.LENGTH_SHORT).show();    //create toast for this class and get the email address to string
                                    startActivity(new Intent(RegisterUser.this, MainActivity.class));                                           // after the toast, go to LoginScreen class
                                } else
                                {
                                    Toast.makeText(RegisterUser.this, "Unfortunatelly, registration has failed!", Toast.LENGTH_SHORT).show(); // in case the registration is not successful then show toast with error
                                }
                            }
                        });
            }
        });


    }


}
