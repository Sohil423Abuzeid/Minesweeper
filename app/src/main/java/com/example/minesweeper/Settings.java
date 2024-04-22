package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void homset(View view) {
        Intent nt =new Intent(Settings.this , MainActivity.class);
        startActivity(nt);
    }

    public void ldrset(View view) {
        Intent ny = new Intent(Settings.this ,leaderbord.class);
        startActivity(ny);
    }

    public void noads(View view) {
        Intent nu = new Intent(Settings.this,noads.class);
        startActivity(nu);
    }

    public void stat(View view) {
        Intent ni = new Intent(Settings.this ,statistics.class);
        startActivity(ni);
    }
}