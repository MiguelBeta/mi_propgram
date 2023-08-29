package com.example.mi_propgram.controller.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GuardScheduleAdapter;
import com.example.mi_propgram.controller.consultas.BuscarGuarda;
import com.example.mi_propgram.controller.consultas.ListarCuadroTurnosPorDia;
import com.example.mi_propgram.controller.interfaces.InfoGuardaCallback;
import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.controller.interfaces.ResponseListGuardsInterface;
import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.models.GuardData;
import com.example.mi_propgram.models.InfoGuarda;

import java.util.List;

public class DetalleInfoGuardaActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private GuardScheduleAdapter adapter;
    private TextView idTxtName, idTxtSurnames, idTxtIdentification, idTxtPhone, idTxtEmail;
    private RecyclerView idRcvListGuardTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_info_guarda);

        setupInitializeUi();
        initRecyclerView();
        setupGetParams();
    }

    private void setupGetParams() {
        Intent intent = getIntent();
        if (intent != null) {
            String nameGuard = intent.getStringExtra("nameGuard");
            setupListGuardsByDay(nameGuard);
            setupGuardInfo(nameGuard);
        }
    }


    private void initRecyclerView() {
        idRcvListGuardTurn.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GuardScheduleAdapter();

        idRcvListGuardTurn.setAdapter(adapter);
    }

    private void setupInitializeUi() {
        idTxtName = findViewById(R.id.idTxtName);
        idTxtSurnames = findViewById(R.id.idTxtSurnames);
        idTxtIdentification = findViewById(R.id.idTxtIdentification);
        idTxtPhone = findViewById(R.id.idTxtPhone);
        idTxtEmail = findViewById(R.id.idTxtEmail);
        idRcvListGuardTurn = findViewById(R.id.idRcvListGuardTurn);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupListGuardsByDay(String name) {
        progressBar.setVisibility(View.VISIBLE);
        ListarCuadroTurnosPorDia.listGuardsByDayAndSort(null, name, new ResponseListGuardsInterface<List<GuardData>>() {
            @Override
            public void onSuccess(List<GuardData> result) {
                if (result.size() > 0) {
                    adapter.setGuardSchedules(result, "nameGuard");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String errorMessage) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setupGuardInfo(String name) {
        BuscarGuarda.searchGuard(name, new InfoGuardaCallback() {
            @Override
            public void onFindRegister(InfoGuarda result) {
                setupPasteInfoToUi(result);
            }

            @Override
            public void onNotFoundRegister() {
                Toast.makeText(DetalleInfoGuardaActivity.this, "Información no encontrada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupPasteInfoToUi(InfoGuarda result) {
        idTxtName.setText(result.getName());
        idTxtSurnames.setText(result.getSurname());
        idTxtIdentification.setText(result.getIdentification());
        idTxtPhone.setText(result.getPhone());
        idTxtEmail.setText(result.getEmail());
    }
}