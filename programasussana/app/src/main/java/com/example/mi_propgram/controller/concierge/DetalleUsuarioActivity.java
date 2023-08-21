package com.example.mi_propgram.controller.concierge;

import static com.example.mi_propgram.utils.Constantes.ESTADO_ASISTIO;
import static com.example.mi_propgram.utils.Constantes.ESTADO_PENDIENTE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.consultas.ActualizarEstadoCita;
import com.example.mi_propgram.controller.consultas.BuscarPacienteDocumento;
import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.controller.interfaces.UpdateCallback;
import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.utils.Constantes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetalleUsuarioActivity extends AppCompatActivity {
    private ProgressBar idProgressBar;
    private TextView idTxtNamePerson, idTxtNameHospital, idTxtPhone, idTxtGenger, idTxtIdentification, idTxtAge, idTxtTypeQuote, idTxtInitialDate, idTxtDuration, idTxtActivity, idTxtNameDoctor, idTxtTurnDate, idTxtSpecialty;
    private Button idBtnAsistio, idBtnNoAsistio;
    private ScrollView scrollView;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        Intent intent = getIntent();
        if (intent != null) {
            idUser = intent.getStringExtra("idUser");
            setupGetData(idUser);
        }

        setupInitializeUi();
        setupClickListeners();
    }

    private void setupClickListeners() {
        idBtnAsistio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idProgressBar.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                idBtnAsistio.setVisibility(View.GONE);
                idBtnNoAsistio.setVisibility(View.GONE);

                ActualizarEstadoCita.updateStatus(idUser, "Asistio", new UpdateCallback() {
                    @Override
                    public void onSuccessUpdate() {
                        Toast.makeText(DetalleUsuarioActivity.this, "Estado del paciente actualizado correctamente", Toast.LENGTH_SHORT).show();
                        setupGetData(idUser);
                    }

                    @Override
                    public void onNotFountRegister() {
                        Toast.makeText(DetalleUsuarioActivity.this, "No se encontro al paciente " + idUser, Toast.LENGTH_SHORT).show();
                        idProgressBar.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        idBtnAsistio.setVisibility(View.VISIBLE);
                        idBtnNoAsistio.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onErrorUpdate(String errorMessage) {
                        Toast.makeText(DetalleUsuarioActivity.this, "Ocurrio un error actualizando el estado, por favor vuelva a intentarlo", Toast.LENGTH_LONG).show();
                        idProgressBar.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        idBtnAsistio.setVisibility(View.VISIBLE);
                        idBtnNoAsistio.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void setupInitializeUi() {
        idProgressBar = findViewById(R.id.idProgressBar);
        idTxtNamePerson = findViewById(R.id.idTxtNamePerson);
        idTxtNameHospital = findViewById(R.id.idTxtNameHospital);
        idTxtPhone = findViewById(R.id.idTxtPhone);
        idTxtGenger = findViewById(R.id.idTxtGenger);
        idTxtIdentification = findViewById(R.id.idTxtIdentification);
        idTxtAge = findViewById(R.id.idTxtAge);
        idTxtTypeQuote = findViewById(R.id.idTxtTypeQuote);
        idTxtInitialDate = findViewById(R.id.idTxtInitialDate);
        idTxtDuration = findViewById(R.id.idTxtDuration);
        idTxtActivity = findViewById(R.id.idTxtActivity);
        idTxtNameDoctor = findViewById(R.id.idTxtNameDoctor);
        idTxtTurnDate = findViewById(R.id.idTxtTurnDate);
        idTxtSpecialty = findViewById(R.id.idTxtSpecialty);
        idBtnAsistio = findViewById(R.id.idBtnAsistio);
        idBtnNoAsistio = findViewById(R.id.idBtnNoAsistio);
        scrollView = findViewById(R.id.scrollView);
    }

    private void setupGetData(String idUser) {
        BuscarPacienteDocumento.searchUserByIdentification(idUser, new RegisterCallback() {
            @Override
            public void onFindRegister(DataFileUsers register) {
                idProgressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                idBtnAsistio.setVisibility(View.VISIBLE);
                idBtnNoAsistio.setVisibility(View.VISIBLE);

                setupUiData(register);
            }

            @Override
            public void onNotFoundRegister() {

            }
        });
    }

    private void setupUiData(DataFileUsers register) {
        idTxtNamePerson.setText(register.pacienteNombre);
        idTxtNameHospital.setText(register.turnoMedicoentroAtencionNombre);
        idTxtPhone.setText(register.pacienteTelefono);
        idTxtGenger.setText(register.pacienteSexo);
        idTxtIdentification.setText(register.pacienteDocumento);
        idTxtAge.setText(register.pacienteEdadPaciente);
        idTxtTypeQuote.setText(register.tipoCita);
        idTxtInitialDate.setText(register.fechaInicialCita);
        idTxtDuration.setText(register.duracion);
        idTxtActivity.setText(register.actividadNombre);
        idTxtNameDoctor.setText(register.turnoMedicoMedicoNombreCompleto);
        idTxtTurnDate.setText(register.turnoMedicoFechaTurno);
        idTxtSpecialty.setText(register.especialidadDescripcion);

        if (register.estado.equals(ESTADO_ASISTIO)) {
            idBtnAsistio.setVisibility(View.GONE);
            idBtnNoAsistio.setVisibility(View.GONE);
        }
    }
}