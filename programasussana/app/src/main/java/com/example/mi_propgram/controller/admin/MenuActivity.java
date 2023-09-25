package com.example.mi_propgram.controller.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mi_propgram.R;
import com.example.mi_propgram.SplashActivity;
import com.example.mi_propgram.controller.concierge.ListadoPacientesActivity;
import com.example.mi_propgram.controller.consultas.sharedPref.SharedPreferencesManager;

public class MenuActivity extends AppCompatActivity {

    private CardView idBtnManageQuotes, btnUserSystem, idBtnCloseSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initUI();
    }

    private void initUI() {
        initComponents();
        setupClickListeners();
    }

    private void setupClickListeners() {
        idBtnManageQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListadoPacientesActivity.class);
                intent.putExtra("isAdmin", "adminParam");
                startActivity(intent);
            }
        });

        btnUserSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListUsersSystemActivity.class);
                startActivity(intent);
            }
        });

        idBtnCloseSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupSession();
            }
        });
    }

    private void setupSession() {
        SharedPreferencesManager.remove(this, "isConnected");
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    private void initComponents() {
        idBtnManageQuotes = findViewById(R.id.idBtnManageQuotes);
        idBtnManageQuotes = findViewById(R.id.idBtnManageQuotes);
        btnUserSystem = findViewById(R.id.btnUserSystem);
        idBtnCloseSession = findViewById(R.id.idBtnCloseSession);
    }


}