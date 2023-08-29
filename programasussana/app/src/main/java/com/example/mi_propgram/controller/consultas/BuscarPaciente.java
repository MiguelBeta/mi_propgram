package com.example.mi_propgram.controller.consultas;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BuscarPaciente {

    //Buscar paciente por documento de identificacion o nombre
    public static void search(String paramFilter, RegisterCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosPacientes");

        Query queryDocumento = databaseReference.orderByChild("pacienteDocumento").equalTo(paramFilter);
        Query queryNombre = databaseReference.orderByChild("pacienteNombre").startAt(paramFilter.toUpperCase()).endAt(paramFilter.toUpperCase() + "\uf8ff");

        queryDocumento.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    DataFileUsers registroEncontrado = firstChild.getValue(DataFileUsers.class);
                    callback.onFindRegister(registroEncontrado);
                } else {
                    queryNombre.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Encontrar el registro con mayor coincidencia en el nombre
                                DataFileUsers registroEncontrado = null;
                                int maxMatchCount = 0;

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    DataFileUsers registro = snapshot.getValue(DataFileUsers.class);
                                    String nombre = registro.pacienteNombre.toUpperCase();
                                    int matchCount = calculateNameMatchCount(paramFilter.toUpperCase(), nombre);

                                    if (matchCount > maxMatchCount) {
                                        maxMatchCount = matchCount;
                                        registroEncontrado = registro;
                                    }
                                }

                                if (registroEncontrado != null) {
                                    callback.onFindRegister(registroEncontrado);
                                } else {
                                    callback.onNotFoundRegister();
                                }
                            } else {
                                callback.onNotFoundRegister();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Manejar el error de Firebase
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar el error de Firebase
            }
        });
    }

    private static int calculateNameMatchCount(String query, String name) {
        int count = 0;
        String[] queryWords = query.split(" ");

        for (String queryWord : queryWords) {
            if (name.contains(queryWord)) {
                count++;
            }
        }

        return count;
    }


}
