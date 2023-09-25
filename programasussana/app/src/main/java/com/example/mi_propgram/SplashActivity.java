package com.example.mi_propgram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.mi_propgram.controller.admin.MenuActivity;
import com.example.mi_propgram.controller.auth.LoginActivity;
import com.example.mi_propgram.controller.concierge.ConciergeActivity;
import com.example.mi_propgram.controller.consultas.sharedPref.SharedPreferencesManager;
import com.example.mi_propgram.controller.jefeSecurity.JefeSeguridadActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 2000;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initUI();
    }

    private void initUI() {
        progressBar = findViewById(R.id.progressBar);
        initSplashScreen();
    }

    private void initSplashScreen() {

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(ProgressBar.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setupGetSharedPref();
            }
        }, SPLASH_DURATION);
    }

    private void setupGetSharedPref() {
        Boolean isConnected = SharedPreferencesManager.getBoolean(this, "isConnected", false);
        if (isConnected) {
            String role = SharedPreferencesManager.getString(this, "role");
            if (role != null) {
                setupRolIntentPermission(role);
            }
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setupRolIntentPermission(String rol) {

        switch (rol) {
            case "Administrador": {
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case "Portero": {
                Intent intent = new Intent(this, ConciergeActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case "Jefe de seguridad": {
                Intent intent = new Intent(this, JefeSeguridadActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
}