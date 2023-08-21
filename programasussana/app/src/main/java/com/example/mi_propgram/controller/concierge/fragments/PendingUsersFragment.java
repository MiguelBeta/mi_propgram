package com.example.mi_propgram.controller.concierge.fragments;

import static com.example.mi_propgram.utils.Constantes.ESTADO_PENDIENTE;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GenericAdapter;
import com.example.mi_propgram.controller.concierge.DetalleUsuarioActivity;
import com.example.mi_propgram.controller.consultas.ListarPacientesCitaEstado;
import com.example.mi_propgram.controller.interfaces.CallbackInterface;
import com.example.mi_propgram.holders.UsuarioCitaViewHolder;
import com.example.mi_propgram.models.DataFileUsers;

import java.util.List;


public class PendingUsersFragment extends Fragment {

    private ProgressBar idProgressBar;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = rootView.findViewById(R.id.idRcvListadoUsuariosCita);
        idProgressBar = rootView.findViewById(R.id.idProgressBar);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        ListarPacientesCitaEstado.obtenerDatos(ESTADO_PENDIENTE, new CallbackInterface<List<DataFileUsers>>() {
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

            }
        });
        return rootView;
    }


}