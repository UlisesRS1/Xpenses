package com.fin.xpenses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.ActivityInicioBinding;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.model.TipoCategoria;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;
import com.fin.xpenses.ui.home.HomeFragment;

import java.util.Calendar;

public class Inicio extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private IMovimientoRepository iMovimientoRepository;
    private ActivityInicioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        run();
    }

    private void run() {
        init();
        setListeners();
    }

    private void init() {
        this.databaseHelper = new DatabaseHelper(this);
        this.iMovimientoRepository = new MovimientoRepository(this.databaseHelper);
        this.binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
    }

    private void setListeners() {
        this.binding.btnContinuarLog.setOnClickListener(v -> {
            double saldoActual= Double.parseDouble(this.binding.edtSaldoActual.getText().toString());
            boolean salarioFijo = this.binding.cbxSalarioFijo.isChecked();
            Movimiento movimiento = new Movimiento();

            if(salarioFijo){
                double salarioFijoMonto= Double.parseDouble(this.binding.edtSalarioFijo.getText().toString());
            }

            movimiento.setDescripcion("Primer ingreso");
            movimiento.setMonto(saldoActual);

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String fecha = day + "/" + month + "/" + year;

            movimiento.setFechaRegistro(fecha);

            Categoria categoria = new Categoria();
            categoria.setIdCategoria(10);

            movimiento.setIdCategoria(categoria);
            movimiento.setEsFuturo(false);
            movimiento.setFecha(fecha);

            this.iMovimientoRepository.agregarMovimiento(movimiento);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.e("Inicio", "Inicio");
            this.finish();
        });
    }
}