package com.example.mi_propgram.controller.consultas;

import androidx.annotation.NonNull;

import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BuscarPacienteDocumento {
    public static void buscarUsuario(String identification, RegisterCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosPacientes");
        Query query = databaseReference.orderByChild("pacienteDocumento").equalTo(identification);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    DataFileUsers registroEncontrado = firstChild.getValue(DataFileUsers.class);
                    callback.onFindRegister(registroEncontrado);
                } else {
                    callback.onNotFoundRegister();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface RegisterCallback {
        void onFindRegister(DataFileUsers registro);
        void onNotFoundRegister();
    }
}