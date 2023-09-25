package com.example.mi_propgram.controller.consultas;

import androidx.annotation.NonNull;

import com.example.mi_propgram.controller.interfaces.CallbackInterface;
import com.example.mi_propgram.models.UsuarioModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarUsuariosSistema {
    public static void getUsersSystem(final CallbackInterface<List<UsuarioModel>> callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("usuariosSistema");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UsuarioModel> listUsers = new ArrayList<>();

                for (DataSnapshot citaSnapshot : dataSnapshot.getChildren()) {
                    UsuarioModel cita = citaSnapshot.getValue(UsuarioModel.class);
                    listUsers.add(cita);
                }
                callback.onSuccess(listUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }


}
