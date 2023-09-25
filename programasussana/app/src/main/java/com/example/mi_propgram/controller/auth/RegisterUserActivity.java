package com.example.mi_propgram.controller.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.admin.ListUsersSystemActivity;
import com.example.mi_propgram.models.UsuarioModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserActivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    private FirebaseAuth auth;
    private EditText tvNameSurname, tvIdentification, tvPhone, tvEmail, tvPassword;
    private Spinner spinnerRoles;
    private Button btnRegister;
    private ImageView btnBack;
    private UsuarioModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        initUI();
    }

    private void initUI() {
        initComponents();
        setupClickListeners();
    }

    private void setupClickListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userModel = setupDataModel();
                if (formIsValid()) createNewUserEmailPass(userModel);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private Boolean formIsValid() {
        boolean response = true;
        String requiredMessage = "Campo requerido";
        if (tvNameSurname.getText().toString().isEmpty()) {
            tvNameSurname.setError(requiredMessage);
            response = false;
        } else {
            tvNameSurname.setError(null);
        }
        if (tvIdentification.getText().toString().isEmpty()) {
            tvIdentification.setError(requiredMessage);
            response = false;
        } else {
            tvIdentification.setError(null);
        }
        if (tvPhone.getText().toString().isEmpty()) {
            tvPhone.setError(requiredMessage);
            response = false;
        } else {
            tvPhone.setError(null);
        }
        if (tvEmail.getText().toString().isEmpty()) {
            tvEmail.setError(requiredMessage);
            response = false;
        } else {
            tvEmail.setError(null);
        }
        if (tvPassword.getText().toString().isEmpty()) {
            tvPassword.setError(requiredMessage);
            response = false;
        } else {
            tvPassword.setError(null);
            if (tvPassword.getText().length() >= 7) {
                tvPassword.setError(null);
            } else {
                tvPassword.setError("Mínimo 6 caracteres");
                response = false;
            }
        }
        return response;
    }

    private void createNewUserEmailPass(UsuarioModel userModel) {
        try {
            auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(userModel.getCoreo(), userModel.getContrasena()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String id = task.getResult().getUser().getUid();
                        userModel.setIdUser(id);
                        registerNewUserDatabase();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterUserActivity.this, "FailureAuth: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(RegisterUserActivity.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void registerNewUserDatabase() {
        try {
            dbRef = FirebaseDatabase.getInstance().getReference("usuariosSistema");
            dbRef.child(userModel.getIdUser()).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterUserActivity.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(RegisterUserActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterUserActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(RegisterUserActivity.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private UsuarioModel setupDataModel() {
        UsuarioModel response = new UsuarioModel();
        response.setNombre(tvNameSurname.getText().toString());
        response.setCedula(tvIdentification.getText().toString());
        response.setTelefono(tvPhone.getText().toString());
        response.setCoreo(tvEmail.getText().toString());
        response.setContrasena(tvPassword.getText().toString());
        response.setRol(spinnerRoles.getSelectedItem().toString());

        return response;
    }

    private void initComponents() {
        tvNameSurname = findViewById(R.id.tvNameSurname);
        tvIdentification = findViewById(R.id.tvIdentification);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        spinnerRoles = findViewById(R.id.spinnerRoles);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);
    }
}