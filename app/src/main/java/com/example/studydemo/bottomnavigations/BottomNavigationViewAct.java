package com.example.studydemo.bottomnavigations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.studydemo.ArouterConstants;
import com.example.studydemo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@Route(path = ArouterConstants.BOTTOM_NAVIGATIONVIEW_ACT)
public class BottomNavigationViewAct extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("Home");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("Dashboard");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Notifications");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
