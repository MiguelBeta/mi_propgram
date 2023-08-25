package com.example.mi_propgram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mi_propgram.controller.admin.MenuActivity;
import com.example.mi_propgram.controller.concierge.ConciergeActivity;

public class EliminarDespuesActivity extends AppCompatActivity {

    Button admin, portero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_despues);

        admin = findViewById(R.id.button);
        portero = findViewById(R.id.button2);

        Intent intentAdmin = new Intent(this, MenuActivity.class);
        Intent intentPortero = new Intent(this, ConciergeActivity.class);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentAdmin);
            }
        });
        portero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPortero);
            }
        });

    }
}