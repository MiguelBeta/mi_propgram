package com.example.mi_propgram.controller.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.auth.RegisterUserActivity;

public class ListUsersSystemActivity extends AppCompatActivity {

    private LinearLayout addNewUserSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users_system);
        initUI();
    }

    private void initUI() {
        initComponents();
        setupClicksListeners();
    }

    private void setupClicksListeners() {
        addNewUserSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        addNewUserSystem = findViewById(R.id.addNewUserSystem);
    }
}