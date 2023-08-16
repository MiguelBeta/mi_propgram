package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.consultas.BuscarPacienteDocumento;
import com.example.mi_propgram.models.DataFileUsers;

public class ReadCredentialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_credentials);

        BuscarPacienteDocumento.buscarUsuario("1431846", new BuscarPacienteDocumento.RegistroCallback() {
            @Override
            public void onRegistroEncontrado(DataFileUsers registro) {

            }

            @Override
            public void onRegistroNoEncontrado() {

            }
        });
    }
}