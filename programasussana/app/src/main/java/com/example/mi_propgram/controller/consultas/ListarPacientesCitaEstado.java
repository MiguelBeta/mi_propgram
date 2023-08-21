package com.example.mi_propgram.controller.consultas;

import androidx.annotation.NonNull;

import com.example.mi_propgram.controller.interfaces.CallbackInterface;
import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarPacientesCitaEstado {
    public static void obtenerDatos(final String estado, final CallbackInterface<List<DataFileUsers>> callback) {
        DatabaseReference citasRef = FirebaseDatabase.getInstance().getReference().child("datosPacientes");
        citasRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataFileUsers> registrosCitas = new ArrayList<>();

                for (DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()) {
                    DataFileUsers usuario = usuarioSnapshot.getValue(DataFileUsers.class);

                    if (usuario != null && usuario.estado.equals(estado)) {
                        registrosCitas.add(usuario);
                    }
                }
                callback.onSuccess(registrosCitas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }


}
