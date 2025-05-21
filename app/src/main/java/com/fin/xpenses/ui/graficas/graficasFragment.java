package com.fin.xpenses.ui.graficas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fin.xpenses.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class graficasFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_graficas, container, false);

        // Referencia al gráfico
        PieChart pieChart = root.findViewById(R.id.graficaPastel);

        // Crear datos
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40f, "Comida"));
        entries.add(new PieEntry(30f, "Transporte"));
        entries.add(new PieEntry(20f, "Servicios"));
        entries.add(new PieEntry(10f, "Otros"));

        // Dataset
        PieDataSet dataSet = new PieDataSet(entries, "Gastos");
        dataSet.setColors(new int[]{Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED});
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        // Asignar al gráfico
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setUsePercentValues(true); // opcional: muestra porcentajes
        pieChart.setCenterText("Gastos");
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false); // quitar descripción
        pieChart.invalidate(); // refresca

        return root;
    }
}
