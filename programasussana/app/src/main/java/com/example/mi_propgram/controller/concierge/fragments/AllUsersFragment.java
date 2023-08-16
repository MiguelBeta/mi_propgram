package com.example.mi_propgram.controller.concierge.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GenericAdapter;
import com.example.mi_propgram.controller.concierge.DetalleUsuarioActivity;
import com.example.mi_propgram.controller.consultas.ListarPacientesCita;
import com.example.mi_propgram.holders.UsuarioCitaViewHolder;
import com.example.mi_propgram.models.DataFileUsers;

import java.util.List;


public class AllUsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar idProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = rootView.findViewById(R.id.idRcvListadoUsuariosCita);
        idProgressBar = rootView.findViewById(R.id.idProgressBar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        setupGetData();


        return rootView;
    }

    private void setupGetData() {
        ListarPacientesCita.obtenerDatos(new ListarPacientesCita.CallbackInterface<List<DataFileUsers>>() {
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
                                        Intent intent = new Intent(requireActivity(), DetalleUsuarioActivity.class);
                                        intent.putExtra("idUser", dataFileUsers.pacienteDocumento);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                );
                idProgressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(usuarioCitaAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                //
            }
        });
    }

}