package com.example.minesweeper;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginin extends AppCompatActivity {
    EditText lemail,lname,lpassoword;
    Button log ;
    FirebaseAuth luth;
    ProgressBar lpar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = luth.getCurrentUser();
        if(currentUser != null){
            Intent login_intent = new Intent(loginin.this ,MainActivity.class);
            startActivity(login_intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginin);
        lemail = findViewById(R.id.logemail);
        lpassoword= findViewById(R.id.logpass);
        lpar =findViewById(R.id.parlog);
        log = findViewById(R.id.logbutton);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lpar.setVisibility(View.VISIBLE);
                String email, pass, name;
                email = String.valueOf(lemail.getText());
                name = String.valueOf(lname.getText());
                pass = String.valueOf(lpassoword.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(loginin.this, "enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(loginin.this, "enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                luth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    lpar.setVisibility(View.GONE);
                                    Toast.makeText(loginin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent login_intent = new Intent(loginin.this ,MainActivity.class);
                                    startActivity(login_intent);
                                   //FirebaseUser user = luth.getCurrentUser();
                                } else {
                                    Toast.makeText(loginin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void reg(View view) {
        Intent login_intent = new Intent(loginin.this ,registration.class);
        startActivity(login_intent);
    }

}