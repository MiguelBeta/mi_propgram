package com.example.mi_propgram.controller.admin;

import static com.example.mi_propgram.utils.Constantes.ESTADO_PENDIENTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.concierge.ListadoPacientesActivity;
import com.example.mi_propgram.controller.consultas.InsertarNuevoPaciente;
import com.example.mi_propgram.controller.interfaces.InsertPatientCallBack;
import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.utils.Constantes;
import com.example.mi_propgram.utils.DateUtils;
import com.example.mi_propgram.utils.FunctionsOfExtensions;

public class AddUserQuoteActivity extends AppCompatActivity {
    private EditText name, identification, phone, age, sede, duration, activity, professional, turn, speciality;
    private Spinner typeQuote, gender;
    private Button date, saveData;
    private DataFileUsers dataUser;
    private FunctionsOfExtensions extensions;
    private ProgressBar idProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_quote);

        extensions = new FunctionsOfExtensions();
        dataUser = new DataFileUsers();

        setupInitializeUi();
        setupAdapterToSpinners();
        setupClickListeners();

    }

    private void setupClickListeners() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.fechaInicialCita = new DateUtils().showDatePickerDialog((Button) v);
            }
        });
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.actividadNombre = activity.getText().toString();
                dataUser.duracion = duration.getText().toString();
                dataUser.especialidadDescripcion = speciality.getText().toString();
                dataUser.fechaInicialCita = date.getText().toString();
                dataUser.pacienteDocumento = identification.getText().toString();
                dataUser.pacienteNombre = name.getText().toString();
                dataUser.pacienteTelefono = phone.getText().toString();
                dataUser.pacienteEdadPaciente = age.getText().toString();
                dataUser.turnoMedicoCentroAtencionCodigo = speciality.getText().toString();
                dataUser.turnoMedicoFechaTurno = turn.getText().toString();
                dataUser.turnoMedicoMedicoCodigo = activity.getText().toString();
                dataUser.turnoMedicoMedicoNombreCompleto = professional.getText().toString();
                dataUser.turnoMedicoentroAtencionNombre = sede.getText().toString();
                dataUser.estado = ESTADO_PENDIENTE;

                insertData();
            }
        });
    }

    private void insertData() {
        idProgressBar.setVisibility(View.VISIBLE);
        InsertarNuevoPaciente.Insert(dataUser, new InsertPatientCallBack() {
            @Override
            public void onSuccessInsert() {
                Intent intent = new Intent(getApplicationContext(), ListadoPacientesActivity.class);
                intent.putExtra("isAdmin", "adminParam");
                startActivity(intent);
                finish();
            }

            @Override
            public void onErrorInsert(String errorMessage) {
                idProgressBar.setVisibility(View.GONE);
                Toast.makeText(AddUserQuoteActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapterToSpinners() {
        SpinnerAdapter adapterTypeQuote = extensions.createArrayAdapterFromResource(this, R.array.items_type_quote);
        typeQuote.setAdapter(adapterTypeQuote);

        extensions.setOnItemSelectedListener(typeQuote, new FunctionsOfExtensions.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, int position, String item) {
                dataUser.tipoCita = item;
            }
        });


        SpinnerAdapter adapterGender = extensions.createArrayAdapterFromResource(this, R.array.genero);
        gender.setAdapter(adapterGender);

        extensions.setOnItemSelectedListener(gender, new FunctionsOfExtensions.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, int position, String item) {
                dataUser.pacienteSexo = item;
            }
        });
    }

    private void setupInitializeUi() {
        name = findViewById(R.id.idTxtName);
        identification = findViewById(R.id.idTxtIdentification);
        phone = findViewById(R.id.idTxtPhone);
        age = findViewById(R.id.idTxtAge);
        typeQuote = findViewById(R.id.idSpinnerTypeQuote);
        gender = findViewById(R.id.idSpinnerGender);
        sede = findViewById(R.id.idTxtSede);
        date = findViewById(R.id.idBtnDate);
        duration = findViewById(R.id.idTxtDuration);
        activity = findViewById(R.id.idTxtActivity);
        professional = findViewById(R.id.idTxtProfessional);
        turn = findViewById(R.id.idTxtTurn);
        speciality = findViewById(R.id.idTxtSpeciality);
        saveData = findViewById(R.id.idBtnSaveData);
        idProgressBar = findViewById(R.id.idProgressBar);
    }
}