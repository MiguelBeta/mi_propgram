package com.example.mi_propgram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mi_propgram.controller.admin.MenuActivity;
import com.example.mi_propgram.controller.concierge.ConciergeActivity;
import com.example.mi_propgram.controller.jefeSecurity.JefeSeguridadActivity;

public class EliminarDespuesActivity extends AppCompatActivity {

    Button admin, portero,jefeSeguridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_despues);

        admin = findViewById(R.id.button);
        portero = findViewById(R.id.button2);
        jefeSeguridad = findViewById(R.id.button3);

        Intent intentAdmin = new Intent(this, MenuActivity.class);
        Intent intentPortero = new Intent(this, ConciergeActivity.class);
        Intent intentJefeSeguridad = new Intent(this, JefeSeguridadActivity.class);
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
        jefeSeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentJefeSeguridad);
            }
        });

    }
}