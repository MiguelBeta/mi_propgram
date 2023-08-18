package com.example.mi_propgram.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.example.mi_propgram.R;
import com.example.mi_propgram.models.Particle;

import java.util.ArrayList;
import java.util.List;

public class ParticleActivity extends AppCompatActivity {
    private ParticleView particleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        particleView = new ParticleView(this);
        setContentView(particleView);
    }

    private class ParticleView extends View {
        private List<Particle> particles = new ArrayList<>();
        private Paint paint = new Paint();

        public ParticleView(Context context) {
            super(context);
            // Configurar la animación aquí
            // Por ejemplo, inicializar partículas y configurar su velocidad, posición, etc.
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Dibujar las partículas en el lienzo
            for (Particle particle : particles) {
                paint.setColor(particle.color);
                canvas.drawCircle(particle.x, particle.y, particle.size, paint);
            }

            // Actualizar las posiciones de las partículas aquí
            // Por ejemplo, aplicar velocidad y actualizar posiciones
            // Llamar a postInvalidate() para actualizar la vista
        }
    }
}
