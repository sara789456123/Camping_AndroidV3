package com.example.camping_androidv3.ActivityLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.camping_androidv3.R;

public class MenuMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        Button participerButton = findViewById(R.id.participerButton);
        participerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMainActivity.this, CampingActivityDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}
