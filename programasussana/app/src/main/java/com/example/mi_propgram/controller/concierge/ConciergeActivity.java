package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mi_propgram.R;

public class ConciergeActivity extends AppCompatActivity {

    private CardView idBtnReadCredentials, idCardViewInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concierge);

        idBtnReadCredentials = findViewById(R.id.idBtnReadCredetials);
        idCardViewInformation = findViewById(R.id.idCardViewInformation);

        setupButtonsListener();
    }

    private void setupButtonsListener() {
        final Intent[] intent = new Intent[1];
        idBtnReadCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent[0] = new Intent(v.getContext(), ReadCredentialsActivity.class);
                startActivity(intent[0]);
            }
        });
        idCardViewInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent[0] = new Intent(v.getContext(), ListadoPacientesActivity.class);
                startActivity(intent[0]);
            }
        });
    }
}