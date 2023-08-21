package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AlertDialog;
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
import com.example.mi_propgram.controller.consultas.EliminarPacienteDocumento;
import com.example.mi_propgram.controller.interfaces.ConfirmationActionInterface;
import com.example.mi_propgram.controller.interfaces.DeleteCallback;
import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.utils.AlertDialogBasic;

public class ReadCredentialsActivity extends AppCompatActivity {

    private EditText idTxtIdentificationInput;
    private Button idBtnSearchUser;
    private ProgressBar idProgressBar;

    private String isAdminParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_credentials);

        setupInitializeUi();
        setupClickListeners();
        setupParams();

    }

    private void setupParams() {
        Intent intent = getIntent();
        if (intent != null && (intent.getExtras() != null)) {
            isAdminParams = intent.getStringExtra("AdminToSearch");
            Toast.makeText(this, "" + isAdminParams, Toast.LENGTH_SHORT).show();
        }
    }

    private void setupClickListeners() {
        idBtnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idTxtIdentificationInput.getText().toString().isEmpty()) {
                    idProgressBar.setVisibility(View.VISIBLE);
                    BuscarPacienteDocumento.searchUserByIdentification(idTxtIdentificationInput.getText().toString(), new RegisterCallback() {
                        @Override
                        public void onFindRegister(DataFileUsers register) {
                            idProgressBar.setVisibility(View.GONE);
                            if (isAdminParams.equals("Delete")) {
                                showAlertConfirm(register.pacienteDocumento, register.pacienteNombre);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), DetalleUsuarioActivity.class);
                                intent.putExtra("idUser", register.pacienteDocumento);
                                startActivity(intent);
                            }
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

    private void showAlertConfirm(String identification, String name) {
        AlertDialogBasic.showAlertDialogConfirm(this, "Confirmación", name + " ¿Esta seguro de querer eliminar este usuario?", new ConfirmationActionInterface() {
            @Override
            public void onOk() {
                idProgressBar.setVisibility(View.VISIBLE);
                deleteUser(identification);
            }

            @Override
            public void onCancel() {

            }
        });

    }

    private void deleteUser(String identification) {
        EliminarPacienteDocumento.deleteUserByIdentification(identification, new DeleteCallback() {
            @Override
            public void onDeleteSuccess() {
                Toast.makeText(ReadCredentialsActivity.this, "Se elimino con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReadCredentialsActivity.this, ListadoPacientesActivity.class);
                intent.putExtra("isAdmin", "adminParam");
                startActivity(intent);
                finish();
            }

            @Override
            public void onNotFoundDelete() {
                Toast.makeText(ReadCredentialsActivity.this, "No se encontro el registro", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteError(String errorMessage) {
                Toast.makeText(ReadCredentialsActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupInitializeUi() {
        idTxtIdentificationInput = findViewById(R.id.idTxtIdentificationInput);
        idBtnSearchUser = findViewById(R.id.idBtnSearchUser);
        idProgressBar = findViewById(R.id.idProgressBar);
    }
}