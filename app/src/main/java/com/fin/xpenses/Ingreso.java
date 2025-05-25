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
import com.fin.xpenses.databinding.ActivityIngresoBinding;
import com.fin.xpenses.databinding.ActivityInicioBinding;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.CategoriaRepository;
import com.fin.xpenses.repository.ICategoriaRepository;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

import java.util.ArrayList;
import java.util.Calendar;

public class Ingreso extends AppCompatActivity {

    private ActivityIngresoBinding binding;
    private IMovimientoRepository iMovimientoRepository;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        run();
    }

    private void init(){
        this.binding = ActivityIngresoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.databaseHelper = new DatabaseHelper(this);
        this.iMovimientoRepository = new MovimientoRepository(this.databaseHelper);
    }

    private void run() {
        init();
        setListItems();
        execButtonActionAceptar();
        execButtonActionCancelar();
    }

    private void execButtonActionCancelar(){
        this.binding.btnCancelarIngreso.setOnClickListener(v -> {
            this.finish();
        });
    }

    private void setListItems() {
        ArrayList<Categoria> categorias;
        ICategoriaRepository categoriaRepository = new CategoriaRepository(this.databaseHelper);

        categorias = (ArrayList<Categoria>) categoriaRepository.obtenerTodasLasCategorias();
        String [] categoriasString;

        ArrayList<Categoria> ingresos = new ArrayList<>();

        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdTipoCategoria().getIdTipoCategoria() == 2)
                ingresos.add(categorias.get(i));
        }

        categoriasString = new String[ingresos.size()];

        for (int i = 0; i < ingresos.size(); i++) {
            categoriasString[i] = ingresos.get(i).getCategoria();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.binding.spConceptoIng.setAdapter(adapter);
    }

    private void execButtonActionAceptar(){
        this.binding.btnAceptarIngreso.setOnClickListener(v -> {
            try {
                Movimiento movimiento = new Movimiento();
                Categoria categoria;

                double doubleMonto = Double.parseDouble(String.valueOf(this.binding.edtIngreso.getText()));
                String textCategoria = this.binding.spConceptoIng.getSelectedItem().toString();

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