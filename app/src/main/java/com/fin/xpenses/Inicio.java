package com.fin.xpenses;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.ActivityInicioBinding;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

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
    }

    private void setListeners() {
        this.binding.btnContinuarLog.setOnClickListener(v -> {
            double saldoActual= Double.parseDouble(this.binding.edtSaldoActual.getText().toString());
            boolean salarioFijol = this.binding.cbxSalarioFijo.isChecked();
            Movimiento movimiento = new Movimiento();

            if(salarioFijol){
                double salarioFijo= Double.parseDouble(this.binding.edtSalarioFijo.getText().toString());
                //movimiento.setMonto();
            }



        });
    }
}