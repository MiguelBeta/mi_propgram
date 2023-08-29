package com.example.mi_propgram.controller.jefeSecurity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.admin.CuadroTurnoSemanasActivity;
import com.example.mi_propgram.controller.consultas.CsvImportarInfoGuarda;
import com.example.mi_propgram.controller.consultas.CsvImportarPacientes;
import com.example.mi_propgram.controller.consultas.CsvImportarTurnos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JefeSeguridadActivity extends AppCompatActivity {

    private CardView idBtnViewCuadrosOfTurns, idBtnImportInfoGuard, idBtnImportCuadrosTurno;
    private static final int REQUEST_CODE = 125;
    private static final int REQUEST_CODE_CUADROS = 126;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jefe_seguridad);

        setupInitializeUi();
        setupClickListeners();
    }

    private void setupClickListeners() {

        idBtnViewCuadrosOfTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CuadroTurnoSemanasActivity.class);
                startActivity(intent);
            }
        });
        idBtnImportInfoGuard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentOpenExplorer(REQUEST_CODE);
            }
        });

        idBtnImportCuadrosTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentOpenExplorer(REQUEST_CODE_CUADROS);
            }
        });
    }

    private void importCuadrosTurn() {
    }

    private void intentOpenExplorer(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/csv");
        startActivityForResult(intent, requestCode);
    }

    private void setupInitializeUi() {
        idBtnViewCuadrosOfTurns = findViewById(R.id.idBtnViewCuadrosOfTurns);
        idBtnImportInfoGuard = findViewById(R.id.idBtnImportInfoGuard);
        idBtnImportCuadrosTurno = findViewById(R.id.idBtnImportCuadrosTurno);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                CsvImportarInfoGuarda.importCsvData(this, data.getData(), databaseReference);
            }
        }

        if (requestCode == REQUEST_CODE_CUADROS && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                CsvImportarTurnos.importCsvData(this, data.getData(), databaseReference);
            }
        }
    }
}