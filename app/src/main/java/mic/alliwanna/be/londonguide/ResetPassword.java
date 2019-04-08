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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
public class ResetPassword extends AppCompatActivity {
    private EditText email;
    private Button reset;
    private FirebaseAuth mAuth;

    @Override
    // overrides the back button on the phone
    public void onBackPressed()
    {
        Intent i = new Intent(ResetPassword.this, FirstScreen.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = findViewById(R.id.email_reset);
        reset = findViewById(R.id.button_pass_reset);
        mAuth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Sends a password reset email to the given email address.
                mAuth.sendPasswordResetEmail(email.getText().toString().toLowerCase().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            //Feedback on complete
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                //Message shown after the password reset is requested
                                Toast.makeText(ResetPassword.this,
                                        "Check your email for password reset link",
                                        Toast.LENGTH_SHORT).show();
                                // The user is redirected to main menu
                                Intent i= new Intent(ResetPassword.this, FirstScreen.class);
                                startActivity(i);
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() // listener in case password reset fails
                        {
                            //feedback on failure
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(ResetPassword.this,
                                        "If this email is matching an existing user you will " +
                                                "receive a password reset link in your mail",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}