package com.example.mi_propgram.controller.concierge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GenericAdapter;
import com.example.mi_propgram.holders.UsuarioCitaViewHolder;
import com.example.mi_propgram.models.DataFileUsers;

import java.util.List;


public class PacientesPendientesFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pacientes_pendientes, container, false);
        recyclerView = rootView.findViewById(R.id.idRcvListadoUsuariosCita);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        Consultas.obtenerDatosPacientes( new Consultas.Callback<List<DataFileUsers>>() {
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
                                        //args.putString("idDoc", dataFileUsers.Actividad_Nombre);
                                    }
                                });
                            }
                        }
                );

                recyclerView.setAdapter(usuarioCitaAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                //
            }
        });
        return rootView;
    }


}