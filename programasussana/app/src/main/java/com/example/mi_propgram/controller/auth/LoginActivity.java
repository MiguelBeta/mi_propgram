package com.example.mi_propgram.controller.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mi_propgram.MainActivity;
import com.example.mi_propgram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private Button btnLogin;
    private EditText tvEmail, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    private void initUI() {
        initComponents();
        setupListeners();
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithEmailAndPass();
            }
        });
    }

    private void loginWithEmailAndPass() {
        try {
            String email = tvEmail.getText().toString();
            String pass = tvPassword.getText().toString();
            auth = FirebaseAuth.getInstance();

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String id = task.getResult().getUser().getUid();
                        getInfoUser(id);
                        Log.e("Hola ", id);
                        Toast.makeText(LoginActivity.this, id, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getInfoUser(String id) {
        try {
            dbRef = FirebaseDatabase.getInstance().getReference("usuariosSistema");
            dbRef.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String nombre = snapshot.child("rol").getValue(String.class);
                        String email = snapshot.child("name").getValue(String.class);
                        Toast.makeText(LoginActivity.this, "Nombre: " + nombre + ", Email: " + email, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LoginActivity.this, "Error al obtener datos del usuario: " + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initComponents() {
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
}