package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final String s = getIntent().getStringExtra("textintent");
        findViewById(R.id.otherModelTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, s, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}