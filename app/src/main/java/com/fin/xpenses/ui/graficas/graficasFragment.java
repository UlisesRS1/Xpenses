package com.fin.xpenses.ui.graficas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.fin.xpenses.databinding.FragmentGraficasBinding;
import com.fin.xpenses.R;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.FragmentGraficasBinding;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class graficasFragment extends Fragment {

    private FragmentGraficasBinding graficasBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        List<Movimiento> movimientos;

        DatabaseHelper db = new DatabaseHelper(this.getContext());
        IMovimientoRepository movimientoRepository = new MovimientoRepository(db);

        movimientos = movimientoRepository.obtenerTodosLosMovimientos();

        graficasBinding = FragmentGraficasBinding.inflate(inflater, container, false);

        graficasBinding.btnGraficaIngresoGasto.setOnClickListener(v -> {
            List<String> lista = new ArrayList<>();
            for (Movimiento m : movimientos) {
                int tipo = m.getIdCategoria().getIdCategoria();
                String tipoStr = (tipo == 1) ? "Gasto" : (tipo == 2) ? "Ingreso" : "Otro";
                lista.add(tipoStr + " - " + m.getMonto() + " - " + m.getFecha());
            }

            graficasBinding.lvInicio.setAdapter(new ArrayAdapter<>(
                    requireContext(), android.R.layout.simple_list_item_1, lista
            ));

            graficar(lista, "IngresoGasto");
        });


        graficasBinding.btnGraficaGasto.setOnClickListener(v -> {
            List<String> lista = new ArrayList<>();
            for (Movimiento m : movimientos) {
                if (m.getIdCategoria().getIdCategoria() == 1) {
                    lista.add(m.getIdCategoria() + " - " + m.getMonto() + " - " + m.getFecha());
                }
            }

            graficasBinding.lvInicio.setAdapter(new ArrayAdapter<>(
                    requireContext(), android.R.layout.simple_list_item_1, lista
            ));

            graficar(lista, "Gasto");
        });

        graficasBinding.btnGraficaIngresos.setOnClickListener(v -> {
            List<String> lista = new ArrayList<>();
            for (Movimiento m : movimientos) {
                if (m.getIdCategoria().getIdCategoria() == 2) {
                    lista.add(m.getIdCategoria() + " - " + m.getMonto() + " - " + m.getFecha());
                }
            }

            graficasBinding.lvInicio.setAdapter(new ArrayAdapter<>(
                    requireContext(), android.R.layout.simple_list_item_1, lista
            ));

            graficar(lista, "Ingreso");
        });

        return graficasBinding.getRoot();
    }

    public void graficar(List<String> lista, String tipo) {
        PieChart pieChart = graficasBinding.graficaPastel;

        ArrayList<PieEntry> entries = new ArrayList<>();
        float totalGastos = 0f;
        float totalIngresos = 0f;

        for (String item : lista) {
            String[] partes = item.split(" - ");
            if (partes.length >= 2) {
                try {
                    float monto = Float.parseFloat(partes[1]);
                    if (partes[0].equalsIgnoreCase("Gasto")) {
                        totalGastos += monto;
                    } else if (partes[0].equalsIgnoreCase("Ingreso")) {
                        totalIngresos += monto;
                    }
                } catch (NumberFormatException e) {
                    // ignorar
                }
            }
        }

        String titulo = "";

        switch (tipo) {
            case "IngresoGasto":
                if (totalGastos > 0) entries.add(new PieEntry(totalGastos, "Gastos"));
                if (totalIngresos > 0) entries.add(new PieEntry(totalIngresos, "Ingresos"));
                titulo = "Ingresos vs Gastos";
                break;

            case "Gasto":
                if (totalGastos > 0) entries.add(new PieEntry(totalGastos, "Gastos"));
                titulo = "Gastos";
                break;

            case "Ingreso":
                if (totalIngresos > 0) entries.add(new PieEntry(totalIngresos, "Ingresos"));
                titulo = "Ingresos";
                break;
        }

        PieDataSet dataSet = new PieDataSet(entries, titulo);
        dataSet.setColors(new int[]{Color.RED, Color.GREEN});
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText(titulo);
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();
    }

}
