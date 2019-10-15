package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

;


public class DiaryActivity extends AppCompatActivity {
    TextView tvFecha;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity);
        init();
        String date = new SimpleDateFormat("dd-MMMM-yyyy").format(new Date());
        tvFecha.setText(date.toUpperCase());


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(inventario);
        rv.setAdapter(adapter);
    }

    private void init() {
        tvFecha = findViewById(R.id.tv_fecha);
        rv = findViewById(R.id.rv);
        initializeData();


    }

    class Inventario {
        String departamento;
        String porcentaje;
        String operador;

        Inventario(String departamento, String porcentaje, String operador) {
            this.departamento = departamento;
            this.porcentaje = porcentaje;
            this.operador = operador;
        }
    }

    private List<Inventario> inventario;


    private void initializeData() {
        inventario = new ArrayList<>();
        inventario.add(new Inventario("Linea blanca", "30%",
                "Luis Miguel Sanchez Lopez"));
        inventario.add(new Inventario("Telefonia", "40%",
                "Francisca Lorenza Isaraga"));
        inventario.add(new Inventario("Electronica", "50%",
                "Alma Maria Silva de Alegria"));
        inventario.add(new Inventario("Televisiones", "35%",
                "Adolfo Hitler Gonzalez Juarez"));
        inventario.add(new Inventario("Muebles", "100%",
                "Jose Miguel Guaderama Dodinez"));
    }
}
