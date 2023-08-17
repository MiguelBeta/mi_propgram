package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.consultas.BuscarPacienteDocumento;
import com.example.mi_propgram.models.DataFileUsers;

public class ReadCredentialsActivity extends AppCompatActivity {

    private EditText idTxtIdentificationInput;
    private Button idBtnSearchUser;
    private ProgressBar idProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_credentials);

        setupInitializeUi();
        setupClickListeners();

    }

    private void setupClickListeners() {
        idBtnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idTxtIdentificationInput.getText().toString().isEmpty()) {
                    idProgressBar.setVisibility(View.VISIBLE);
                    BuscarPacienteDocumento.buscarUsuario(idTxtIdentificationInput.getText().toString(), new BuscarPacienteDocumento.RegisterCallback() {
                        @Override
                        public void onFindRegister(DataFileUsers register) {
                            idProgressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(), DetalleUsuarioActivity.class);
                            intent.putExtra("idUser", register.pacienteDocumento);
                            startActivity(intent);
                        }

                        @Override
                        public void onNotFoundRegister() {
                            Toast.makeText(ReadCredentialsActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            idProgressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    idTxtIdentificationInput.setError("Obligatorio");
                }
            }
        });
    }

    private void setupInitializeUi() {
        idTxtIdentificationInput = findViewById(R.id.idTxtIdentificationInput);
        idBtnSearchUser = findViewById(R.id.idBtnSearchUser);
        idProgressBar = findViewById(R.id.idProgressBar);
    }
}