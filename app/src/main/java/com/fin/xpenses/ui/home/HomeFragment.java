package com.fin.xpenses.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fin.xpenses.ActivyGasto;
import com.fin.xpenses.Ingreso;
import com.fin.xpenses.Inicio;
import com.fin.xpenses.MainActivity;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.FragmentHomeBinding;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DatabaseHelper databaseHelper;
    private IMovimientoRepository iMovimientoRepository;
    private List<Movimiento> movimientos;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String FIRST_RUN_KEY = "isFirstRun";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        run();

        return root;
    }

    private void run() {
        execFirstScreen();
        init();
        showDataInList();
        setListeners();
        setTotal();
    }

    private void execFirstScreen() {
        SharedPreferences preferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean(FIRST_RUN_KEY, true);

        if (isFirstRun) {
            // Aquí haces lo que necesites en la primera instalación
            // Por ejemplo: mostrar tutorial o pantalla de bienvenida
            Log.e("FirstRun", "Ejecutando la primera ejecución");

            // Marcar como ya ejecutada una vez
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(FIRST_RUN_KEY, false);
            editor.apply();

            Intent intent = new Intent(requireContext(), Inicio.class);
            startActivity(intent);
        } else {
            Log.e("FirstRun", "No es la primera ejecución");
        }
    }

    public void setTotal() {
        double total = 0;
        for (Movimiento movimiento : this.movimientos) {
            if (movimiento.getIdCategoria().getIdTipoCategoria().getIdTipoCategoria() == 1){
                total -= movimiento.getMonto();
                continue;
            }

            total += movimiento.getMonto();
        }
        if (total < 0) {
            this.binding.txtSaldo.setTextColor(Color.RED);
        } else {
            this.binding.txtSaldo.setTextColor(Color.BLACK);
        }
        this.binding.txtSaldo.setText(String.valueOf(total));
    }

    private void setListeners() {
        this.binding.btnIngreso.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(requireContext(), Ingreso.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });

        this.binding.btnGasto.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(requireContext(), ActivyGasto.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

    private void init() {
        this.databaseHelper = new DatabaseHelper(requireContext());
        this.iMovimientoRepository = new MovimientoRepository(this.databaseHelper);
        this.movimientos = this.iMovimientoRepository.obtenerTodosLosMovimientos();
    }

    private void showDataInList() {
        this.binding.lvInicioF.setAdapter(new HomeAdaptador(requireContext(), this.movimientos));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}