package com.example.mi_propgram.controller.consultas;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mi_propgram.controller.interfaces.InfoGuardaCallback;
import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.models.InfoGuarda;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BuscarGuarda {

    public static void searchGuard(String name, InfoGuardaCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datosGuarda");
        Query query = databaseReference.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                    InfoGuarda registerFound = firstChild.getValue(InfoGuarda.class);
                    callback.onFindRegister(registerFound);
                } else {
                    callback.onNotFoundRegister();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }

}
