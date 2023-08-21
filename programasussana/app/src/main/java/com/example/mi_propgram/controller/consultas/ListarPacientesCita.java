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

public class ListarPacientesCita {
    public static void obtenerDatos(final CallbackInterface<List<DataFileUsers>> callback) {
        DatabaseReference citasRef = FirebaseDatabase.getInstance().getReference().child("datosPacientes");
        citasRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataFileUsers> registrosCitas = new ArrayList<>();

                for (DataSnapshot citaSnapshot : dataSnapshot.getChildren()) {
                    DataFileUsers cita = citaSnapshot.getValue(DataFileUsers.class);
                    registrosCitas.add(cita);
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
