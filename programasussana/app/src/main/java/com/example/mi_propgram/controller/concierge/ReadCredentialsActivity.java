package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import com.example.mi_propgram.R;
import com.example.mi_propgram.models.DataFileUsers;

public class ReadCredentialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_credentials);

        Consultas.buscarUsuarioPorDocumento("1431846", new Consultas.RegistroCallback() {
            @Override
            public void onRegistroEncontrado(DataFileUsers registro) {
                //Toast.makeText(ReadCredentialsActivity.this, ""+registro.getPacienteDocumento(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRegistroNoEncontrado() {

            }
        });
    }
}