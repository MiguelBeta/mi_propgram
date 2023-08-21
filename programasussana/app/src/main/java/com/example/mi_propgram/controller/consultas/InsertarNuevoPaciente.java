package com.example.mi_propgram.controller.consultas;

import androidx.annotation.NonNull;

import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertarNuevoPaciente {

    public static void Insert(@NonNull DataFileUsers data, InsertPatientCallBack callBack) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosPacientes");
        databaseReference.child(data.pacienteDocumento).setValue(data).addOnSuccessListener(aVoid -> {
            callBack.onSuccessInsert();
        }).addOnFailureListener(eVoid -> {
            callBack.onErrorInsert(eVoid.getMessage());
        });
    }

    public interface InsertPatientCallBack {
        void onSuccessInsert();

        void onErrorInsert(String errorMessage);
    }
}
