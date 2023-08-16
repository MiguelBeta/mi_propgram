package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GenericAdapter;
import com.example.mi_propgram.controller.adapter.UsuariosCitaPagerAdapter;
import com.example.mi_propgram.holders.UsuarioCitaViewHolder;
import com.example.mi_propgram.models.DataFileUsers;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class ListadoPacientesActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pacientes);

        RecyclerView recyclerView = findViewById(R.id.idRcvListadoUsuariosCita);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Consultas.obtenerDatosPacientes(new Consultas.Callback<List<DataFileUsers>>() {
            @Override
            public void onSuccess(List<DataFileUsers> result) {
                GenericAdapter<DataFileUsers, UsuarioCitaViewHolder> usuarioCitaAdapter = new GenericAdapter<>(
                        result,
                        R.layout.item_usuario_cita,
                        new GenericAdapter.ViewCreator<UsuarioCitaViewHolder>() {
                            @Override
                            public UsuarioCitaViewHolder create(View view) {
                                return new UsuarioCitaViewHolder(view);
                            }
                        },
                        new GenericAdapter.ItemClickListener<UsuarioCitaViewHolder, DataFileUsers>() {
                            @Override
                            public void onClick(UsuarioCitaViewHolder viewHolder, DataFileUsers item) {
                                viewHolder.render(item, new UsuarioCitaViewHolder.OnItemClickListener() {
                                    @Override
                                    public void onClick(DataFileUsers dataFileUsers) {
                                        Bundle args = new Bundle();
                                        Toast.makeText(ListadoPacientesActivity.this, "Alfo", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        }
                );

                recyclerView.setAdapter(usuarioCitaAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ListadoPacientesActivity.this, "Error=> " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


        /*viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        UsuariosCitaPagerAdapter adapter = new UsuariosCitaPagerAdapter(this);
        adapter.addFragment(new PacientesPendientesFragment());
        adapter.addFragment(new PacientesAsistidosFragment());
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Pendientes");
                            break;
                        case 1:
                            tab.setText("Asistidos");
                            break;
                    }
                });
        tabLayoutMediator.attach();*/

    }
}