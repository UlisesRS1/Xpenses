package com.fin.xpenses;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.ActivityGastoBinding;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

public class activity_gasto extends AppCompatActivity {

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
        execButtonActionAceptar();
    }

    private void execButtonActionAceptar(){
        Movimiento movimiento = new Movimiento();
        Categoria categoria = new Categoria();

        this.binding.button.setOnClickListener(v -> {
            try {
                double doubleMonto = Double.parseDouble(String.valueOf(this.binding.edtGasto.getText()));
                String textCategoria = this.binding.spConcepto.getSelectedItem().toString();

                categoria.setCategoria(textCategoria);
                movimiento.setMonto(doubleMonto);
                movimiento.setIdCategoria(categoria);
                // movimiento.setEsFuturo(); Pendiente de implementar

                this.iMovimientoRepository.agregarMovimiento(movimiento);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

        });
    }
}