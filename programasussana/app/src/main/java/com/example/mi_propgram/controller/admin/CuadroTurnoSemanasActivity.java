package com.example.mi_propgram.controller.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GuardScheduleAdapter;
import com.example.mi_propgram.controller.consultas.ListarCuadroTurnosPorDia;
import com.example.mi_propgram.controller.interfaces.ResponseListGuardsInterface;
import com.example.mi_propgram.models.GuardData;

import java.util.List;

public class CuadroTurnoSemanasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GuardScheduleAdapter adapter;

    private TextView idFilterLunes, idFilterMartes, idFilterMiercoles, idFilterJueves, idFilterViernes, idFilterSabado, idFilterDomingo;

    private ProgressBar idProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadro_turno_semanas);

        setupInitializeUi();
        initRecyclerView();
        setupClickListeners();
        setupListGuardsByDay("LUNES");
    }

    private void changeColorItemSelected(TextView selectedTxt) {
        int colorIdSelected = ContextCompat.getColor(this, R.color.verde_oscuro);
        int colorIdDefault = ContextCompat.getColor(this, R.color.transparent);

        selectedTxt.setBackgroundColor(colorIdSelected);

        TextView[] allTextViews = {
                idFilterLunes, idFilterMartes, idFilterMiercoles,
                idFilterJueves, idFilterViernes, idFilterSabado, idFilterDomingo
        };

        for (TextView txt : allTextViews) {
            if (txt != selectedTxt) {
                txt.setBackgroundColor(colorIdDefault);
            }
        }
    }


    private void setupClickListeners() {
        idFilterLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("LUNES");
                changeColorItemSelected(idFilterLunes);
            }
        });

        idFilterMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("MARTES");
                changeColorItemSelected(idFilterMartes);
            }
        });
        idFilterMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("MIERCOLES");
                changeColorItemSelected(idFilterMiercoles);

            }
        });
        idFilterJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("JUEVES");
                changeColorItemSelected(idFilterJueves);

            }
        });
        idFilterViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("VIERNES");
                changeColorItemSelected(idFilterViernes);

            }
        });
        idFilterSabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("SABADO");
                changeColorItemSelected(idFilterSabado);

            }
        });
        idFilterDomingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupListGuardsByDay("DOMINGO");
                changeColorItemSelected(idFilterDomingo);
            }
        });
    }

    private void setupInitializeUi() {
        recyclerView = findViewById(R.id.recyclerView);
        idFilterLunes = findViewById(R.id.idFilterLunes);
        idFilterMartes = findViewById(R.id.idFilterMartes);
        idFilterMiercoles = findViewById(R.id.idFilterMiercoles);
        idFilterJueves = findViewById(R.id.idFilterJueves);
        idFilterViernes = findViewById(R.id.idFilterViernes);
        idFilterSabado = findViewById(R.id.idFilterSabado);
        idFilterDomingo = findViewById(R.id.idFilterDomingo);
        idProgressBar = findViewById(R.id.idProgressBar);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GuardScheduleAdapter();

        recyclerView.setAdapter(adapter);

    }

    private void setupListGuardsByDay(String dayFilter) {
        idProgressBar.setVisibility(View.VISIBLE);
        ListarCuadroTurnosPorDia.listGuardsByDayAndSort(dayFilter, null, new ResponseListGuardsInterface<List<GuardData>>() {
            @Override
            public void onSuccess(List<GuardData> result) {
                if (result.size() > 0) {
                    adapter.setGuardSchedules(result, "dayName");
                    idProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String errorMessage) {
                idProgressBar.setVisibility(View.GONE);
            }
        });
    }
}