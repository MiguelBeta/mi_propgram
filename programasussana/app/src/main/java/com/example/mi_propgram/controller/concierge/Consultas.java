package com.example.mi_propgram.controller.concierge;


import androidx.annotation.NonNull;

import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Consultas {

    public static void obtenerDatosPacientes(final Callback<List<DataFileUsers>> callback) {
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

    public static void buscarUsuarioPorDocumento(String documento, RegistroCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosPacientes");
        Query query = databaseReference.orderByChild("PacienteDocumento").equalTo(documento);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    DataFileUsers registroEncontrado = firstChild.getValue(DataFileUsers.class);
                    callback.onRegistroEncontrado(registroEncontrado);
                } else {
                    callback.onRegistroNoEncontrado();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface Callback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }

    public interface RegistroCallback {
        void onRegistroEncontrado(DataFileUsers registro);
        void onRegistroNoEncontrado();
    }
}
