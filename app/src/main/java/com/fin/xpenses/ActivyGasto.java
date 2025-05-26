package com.fin.xpenses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.ActivityGastoBinding;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.CategoriaRepository;
import com.fin.xpenses.repository.ICategoriaRepository;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivyGasto extends AppCompatActivity {

    private ActivityGastoBinding binding;
    private IMovimientoRepository iMovimientoRepository;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gasto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        run();
    }

    private void init(){
        this.binding = ActivityGastoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.databaseHelper = new DatabaseHelper(this);
        this.iMovimientoRepository = new MovimientoRepository(this.databaseHelper);
    }

    private void run(){
        init();
        setListItems();
        execButtonActionAceptar();
        execButtonActionCancelar();
    }

    private void execButtonActionCancelar(){
        this.binding.button.setOnClickListener(v -> {
            this.finish();
        });
    }

    private void setListItems() {
        ArrayList<Categoria> categorias;
        ICategoriaRepository categoriaRepository = new CategoriaRepository(this.databaseHelper);

        categorias = (ArrayList<Categoria>) categoriaRepository.obtenerTodasLasCategorias();
        String [] categoriasString;

        ArrayList<Categoria> gastos = new ArrayList<>();

        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdTipoCategoria().getIdTipoCategoria() == 1)
                gastos.add(categorias.get(i));
        }

        categoriasString = new String[gastos.size()];

        for (int i = 0; i < gastos.size(); i++) {
            categoriasString[i] = gastos.get(i).getCategoria();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.binding.spConcepto.setAdapter(adapter);
    }

    private void execButtonActionAceptar(){
        this.binding.button2.setOnClickListener(v -> {
            try {
                Movimiento movimiento = new Movimiento();
                Categoria categoria;

                double doubleMonto = Double.parseDouble(String.valueOf(this.binding.edtGasto.getText()));
                String textCategoria = this.binding.spConcepto.getSelectedItem().toString();
                String textDescripcion = this.binding.edtDescripcionGasto.getText().toString();

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String fecha = day + "/" + month + "/" + year;

                ICategoriaRepository categoriaRepository = new CategoriaRepository(this.databaseHelper);
                categoria = categoriaRepository.obtenerCategoriaPorNombre(textCategoria);

                movimiento.setIdCategoria(categoria);
                movimiento.setMonto(doubleMonto);
                movimiento.setFecha(fecha); // fecha actual -- Faltan agregar apartados --
                movimiento.setDescripcion(textCategoria);
                movimiento.setEsFuturo(false); // -- Falta agregar apartados --
                movimiento.setFechaRegistro(fecha); // fecha correcta
                movimiento.setDescripcion(textDescripcion);

                // movimiento.setEsFuturo(); // -- Falta agregar apartados --

                this.iMovimientoRepository.agregarMovimiento(movimiento);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

        });
    }
}