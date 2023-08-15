package com.example.mi_propgram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Comentario de PRUEBA

    EditText txtNombre, txtCedula, txtTelefono, txtDireccion,txtContraseña, txtFechaNacimiento;
    Spinner spinnerRoles, spinnerGenero;
    //Button btnRegistrar;
    ImageButton btnRegistrar;


    AutoCompleteTextView autoCompleteTxt;

    private DatabaseReference InfoUsuario;
    //...


    private View EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
        InfoUsuario = FirebaseDatabase.getInstance().getReference("datosUsuario");

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        spinnerRoles = (Spinner) findViewById(R.id.spinnerRoles);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);
        //btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar = (ImageButton) findViewById(R.id.btnRegistrar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();

            }
        });

    }

    public void registrarUsuario(){

        //String Nombre = arreglo[0];
        String Nombre = txtNombre.getText().toString();
        String Cedula = txtCedula.getText().toString();
        String Telefono = txtTelefono.getText().toString();
        String Direccion = txtDireccion.getText().toString();
        String Contrasena = txtContraseña.getText().toString();
        String FechaNacimiento = txtFechaNacimiento.getText().toString();
        String Roles = spinnerRoles.getSelectedItem().toString();
        String Genero = spinnerGenero.getSelectedItem().toString();



        String id = UUID.randomUUID().toString();

        DatosUsuario usuario = new DatosUsuario(id, Nombre, Cedula, Telefono, Direccion, Contrasena,
                                        FechaNacimiento, Roles, Genero );
        InfoUsuario.child(usuario.claseid).setValue(usuario);
        Toast.makeText(this, "Datos insertados", Toast.LENGTH_SHORT).show();
    }

}