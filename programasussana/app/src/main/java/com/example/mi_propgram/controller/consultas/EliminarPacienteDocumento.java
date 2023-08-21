package com.example.mi_propgram.controller.consultas;

import androidx.annotation.NonNull;

import com.example.mi_propgram.controller.interfaces.DeleteCallback;
import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EliminarPacienteDocumento {

    public static void deleteUserByIdentification(String identification, DeleteCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosPacientes");
        Query query = databaseReference.orderByChild("pacienteDocumento").equalTo(identification);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    firstChild.getRef().removeValue()
                            .addOnSuccessListener(aVoid -> callback.onDeleteSuccess())
                            .addOnFailureListener(e -> callback.onDeleteError(e.getMessage()));
                } else {
                    callback.onNotFoundDelete();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onDeleteError(databaseError.getMessage());
            }
        });
    }
}
