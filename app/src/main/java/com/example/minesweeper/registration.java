package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class registration extends AppCompatActivity {
    EditText eemail,ename,epassoword;
    Button reg ;

    FirebaseAuth uth;
    ProgressBar par;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        uth =FirebaseAuth.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            ename = findViewById(R.id.regname);
            eemail = findViewById(R.id.regemail);
            epassoword= findViewById(R.id.regpass);
            par = findViewById(R.id.parreg);
            reg = findViewById(R.id.regbutton);
            reg.setOnClickListener(new  View.OnClickListener() {
                @Override
                public void onClick(View view) { // Change onclick to onClick
                    par.setVisibility(View.VISIBLE); // Change view.VISIBLE to View.VISIBLE
                    String email, pass, name;
                    email = String.valueOf(eemail.getText());
                    name = String.valueOf(ename.getText());
                    pass = String.valueOf(epassoword.getText());
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(registration.this, "enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(pass)) {
                        Toast.makeText(registration.this, "enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(registration.this, "enter name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    uth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        par.setVisibility(View.GONE); // Change view.GONE to View.GONE
                                        Toast.makeText(registration.this, "Done",
                                                Toast.LENGTH_SHORT).show();
                                        Intent login_intent = new Intent(registration.this ,loginin.class);
                                        startActivity(login_intent);
                                    } else {
                                        Toast.makeText(registration.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
            return insets;

        });
    }
}