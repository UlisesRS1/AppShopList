package com.shop.appshoplist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {
    private TextView txtAcecarDe;
    private TextView txtNombres;
    private Button btnVolver;
    private String Nombres;
    private String AcercaDe;
    private Intent principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.about);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        show();
    }

    private void init(){
        this.btnVolver = findViewById(R.id.btnVolver);
        this.txtAcecarDe = findViewById(R.id.txtAcercaDe);
        this.txtNombres = findViewById(R.id.txtNombres);
        this.Nombres = "Ulises Rodriguez Solozano\nCesar Espino";
        this.AcercaDe = "Somos un par de estudiantes que les gustar aprender sobre temas de programación;" +
                "Esta aplicacion la desarrollamos usando estructuras de desarrollo que aprendimos es cursos" +
                "de Java, los diseños visuales fueron pensados para ser simples y dar un aire de app academica";
        this.principal = new Intent(this, MainActivity.class);
    }

    private void show(){
        init();
        this.txtAcecarDe.setText(this.AcercaDe);
        this.txtNombres.setText(this.Nombres);
        this.btnVolver.setOnClickListener(v -> {
            startActivity(this.principal);
            finish();
        });
    }
}