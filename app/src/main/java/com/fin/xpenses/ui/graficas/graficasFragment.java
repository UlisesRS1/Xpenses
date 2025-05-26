package com.fin.xpenses.ui.graficas;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.CategoriaRepository;
import com.fin.xpenses.repository.ICategoriaRepository;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class graficasFragment extends Fragment {

    private FragmentGraficasBinding graficasBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        List<Movimiento> lista;

        DatabaseHelper db = new DatabaseHelper(this.getContext());
        IMovimientoRepository movimientoRepository = new MovimientoRepository(db);

        lista = movimientoRepository.obtenerTodosLosMovimientos();

        graficasBinding = FragmentGraficasBinding.inflate(inflater, container, false);

        graficasBinding.btnGraficaIngresoGasto.setOnClickListener(v -> {
            graficarTodo(lista);
        });


        graficasBinding.btnGraficaGasto.setOnClickListener(v -> {
            graficarGastos(lista);
        });

        graficasBinding.btnGraficaIngresos.setOnClickListener(v -> {
            graficarIngresos(lista);
        });

        return graficasBinding.getRoot();
    }

    public void graficarGastos(List<Movimiento> lista) {
        PieChart pieChart = graficasBinding.graficaPastel;

        // Mapa para acumular montos por categoría
        Map<String, Float> sumaPorCategoria = new HashMap<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        ICategoriaRepository categoriaRepository = new CategoriaRepository(databaseHelper);

        // Inicializar categorías conocidas
        String[] categorias = {"Alimentacion", "Servicios", "Entretenimiento", "Salud y bienestar", "Suscripciones", "Otros"};
        for (String cat : categorias) {
            sumaPorCategoria.put(cat, 0f);
        }

        // Recorrer la lista de movimientos
        for (Movimiento mov : lista) {
            Categoria cat = mov.getIdCategoria();

            // Validar que sea gasto (idTipoCategoria == 1)
            if (cat != null && cat.getIdTipoCategoria() != null && cat.getIdTipoCategoria().getIdTipoCategoria() == 1) {
                String nombreCategoria = cat.getCategoria();

                // Si la categoría está en las conocidas, se suma, si no, va a "Otros"
                if (sumaPorCategoria.containsKey(nombreCategoria)) {
                    float montoActual = sumaPorCategoria.get(nombreCategoria);
                    sumaPorCategoria.put(nombreCategoria, montoActual + (float) mov.getMonto());
                } else {
                    float montoActual = sumaPorCategoria.get("Otros");
                    sumaPorCategoria.put("Otros", montoActual + (float) mov.getMonto());
                }
            }
        }

        // Crear los entries de la gráfica
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (String cat : categorias) {
            float monto = sumaPorCategoria.get(cat);
            if (monto > 0f) {
                entries.add(new PieEntry(monto, cat));
            }
        }

        // Configurar la gráfica
        PieDataSet dataSet = new PieDataSet(entries, "Gastos");
        dataSet.setColors(new int[]{Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW, Color.CYAN});
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText("Gastos");
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate(); // refrescar

        // NUEVO: Mostrar solo gastos en el ListView
        List<String> gastos = new ArrayList<>();
        for (Movimiento mov : lista) {
            Categoria cat = mov.getIdCategoria();
            if (cat != null && cat.getIdTipoCategoria() != null && cat.getIdTipoCategoria().getIdTipoCategoria() == 1) {
                gastos.add(mov.getDescripcion() + ": " + mov.getMonto() + "   Fecha: " + mov.getFecha());
            }
        }

        graficasBinding.lvInicioF.setAdapter(new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_list_item_1, gastos
        ));
    }

    public void graficarIngresos(List<Movimiento> lista) {
        PieChart pieChart = graficasBinding.graficaPastel;

        // Mapa para acumular montos por categoría
        Map<String, Float> sumaPorCategoria = new HashMap<>();

        // Inicializar categorías conocidas
        String[] categorias = {"Beca", "Regalo", "Prestamo", "Negocio", "Otros"};
        for (String cat : categorias) {
            sumaPorCategoria.put(cat, 0f);
        }

        // Recorrer la lista de movimientos
        for (Movimiento mov : lista) {
            Categoria cat = mov.getIdCategoria();

            // Validar que sea ingreso (idTipoCategoria == 2)
            if (cat != null && cat.getIdTipoCategoria() != null && cat.getIdTipoCategoria().getIdTipoCategoria() == 2) {
                String nombreCategoria = cat.getCategoria();

                // Si la categoría está en las conocidas, se suma, si no, va a "Otros"
                if (sumaPorCategoria.containsKey(nombreCategoria)) {
                    float montoActual = sumaPorCategoria.get(nombreCategoria);
                    sumaPorCategoria.put(nombreCategoria, montoActual + (float) mov.getMonto());
                } else {
                    float montoActual = sumaPorCategoria.get("Otros");
                    sumaPorCategoria.put("Otros", montoActual + (float) mov.getMonto());
                }
            }
        }

        // Crear los entries de la gráfica
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (String cat : categorias) {
            float monto = sumaPorCategoria.get(cat);
            if (monto > 0f) {
                entries.add(new PieEntry(monto, cat));
            }
        }

        // Configurar la gráfica
        PieDataSet dataSet = new PieDataSet(entries, "Ingresos");
        dataSet.setColors(new int[]{Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW, Color.CYAN});
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText("Ingresos");
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate(); // refrescar

        // Mostrar solo ingresos en el ListView
        List<String> ingresos = new ArrayList<>();
        for (Movimiento mov : lista) {
            Categoria cat = mov.getIdCategoria();
            if (cat != null && cat.getIdTipoCategoria() != null && cat.getIdTipoCategoria().getIdTipoCategoria() == 2) {
                ingresos.add(mov.getDescripcion() + ": " + mov.getMonto() + "   Fecha: " + mov.getFecha());
            }
        }

        graficasBinding.lvInicioF.setAdapter(new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_list_item_1, ingresos
        ));
    }

    public void graficarTodo(List<Movimiento> lista) {
        PieChart pieChart = graficasBinding.graficaPastel;

        float totalGastos = 0f;
        float totalIngresos = 0f;

        for (Movimiento mov : lista) {
            Categoria cat = mov.getIdCategoria();
            if (cat != null && cat.getIdTipoCategoria() != null) {
                int tipo = cat.getIdTipoCategoria().getIdTipoCategoria();
                if (tipo == 1) {
                    totalGastos += mov.getMonto();
                } else if (tipo == 2) {
                    totalIngresos += mov.getMonto();
                }
            }
        }

        ArrayList<PieEntry> entries = new ArrayList<>();
        if (totalGastos > 0f) {
            entries.add(new PieEntry(totalGastos, "Gastos"));
        }
        if (totalIngresos > 0f) {
            entries.add(new PieEntry(totalIngresos, "Ingresos"));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Resumen financiero");
        dataSet.setColors(new int[]{Color.RED, Color.GREEN}); // Rojo para gastos, verde para ingresos
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText("Total");
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate(); // refrescar gráfico

        // Mostrar solo ingresos en el ListView
        List<String> gastosIngresos = new ArrayList<>();
        for (Movimiento mov : lista) {
            gastosIngresos.add(mov.getDescripcion() + ": " + mov.getMonto() + "   Fecha: " + mov.getFecha());
        }

        graficasBinding.lvInicioF.setAdapter(new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_list_item_1, gastosIngresos
        ));
    }


}
