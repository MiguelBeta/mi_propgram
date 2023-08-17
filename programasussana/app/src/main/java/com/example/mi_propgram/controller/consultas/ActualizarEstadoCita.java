package com.example.mi_propgram.controller.consultas;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ActualizarEstadoCita {

    public static void updateStatus(String identification, String nuevoEstado, UpdateCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosPacientes");
        Query query = databaseReference.orderByChild("pacienteDocumento").equalTo(identification);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    String keyRegister = firstChild.getKey();

                    assert keyRegister != null;
                    DatabaseReference registroRef = databaseReference.child(keyRegister);
                    Map<String, Object> updateData = new HashMap<>();
                    updateData.put("estado", nuevoEstado);

                    registroRef.updateChildren(updateData)
                            .addOnSuccessListener(aVoid -> {
                                callback.onSuccessUpdate();
                            })
                            .addOnFailureListener(e -> {
                                callback.onErrorUpdate(e.getMessage());
                            });
                } else {
                    callback.onNotFountRegister();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onErrorUpdate(databaseError.getMessage());
            }
        });
    }

    public interface UpdateCallback {
        void onSuccessUpdate();

        void onNotFountRegister();

        void onErrorUpdate(String errorMessage);
    }
}
