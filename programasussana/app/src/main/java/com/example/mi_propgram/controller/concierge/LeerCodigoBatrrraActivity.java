package com.example.mi_propgram.controller.concierge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.consultas.BuscarPaciente;
import com.example.mi_propgram.controller.interfaces.RegisterCallback;
import com.example.mi_propgram.models.DataFileUsers;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LeerCodigoBatrrraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_codigo_batrrra);

        // Iniciar el escaneo al abrir la actividad
        startBarcodeScanner();
    }

    private void startBarcodeScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Escanea un código de barras");
        integrator.setCameraId(0);  // Cámara trasera
        integrator.setBeepEnabled(false);  // Sin sonido
        integrator.initiateScan();
    }

    private String extractCedulaNumber(String scannedData) {
        // Supongamos que el número de cédula está en los primeros 10 caracteres del código escaneado
        int cedulaLength = 10;

        if (scannedData.length() >= cedulaLength) {
            String cedulaNumber = scannedData.substring(48, 58);
            return cedulaNumber;
        } else {
            return null; // No se pudo extraer el número de cédula
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Se cancelo el scaneo", Toast.LENGTH_SHORT).show();
            } else {
                String scannedData = result.getContents();
                String cedulaNumber = extractCedulaNumber(scannedData);
                if (cedulaNumber != null) {
                    Toast.makeText(this, cedulaNumber, Toast.LENGTH_SHORT).show();
                    getInfoUser(cedulaNumber);
                } else {
                    Toast.makeText(this, "No se pudo extraer el número de cédula", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void getInfoUser(String cc) {
        BuscarPaciente.search(cc, new RegisterCallback() {
            @Override
            public void onFindRegister(DataFileUsers register) {
                Intent intent = new Intent(getApplicationContext(), DetalleUsuarioActivity.class);
                intent.putExtra("idUser", register.pacienteDocumento);
                startActivity(intent);
                finish();
            }

            @Override
            public void onNotFoundRegister() {
                Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ReadCredentialsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
