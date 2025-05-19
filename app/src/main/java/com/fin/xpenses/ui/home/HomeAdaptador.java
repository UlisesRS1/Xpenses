package com.fin.xpenses.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.fin.xpenses.databinding.ListaInicioBinding;
import com.fin.xpenses.model.Movimiento;

import java.util.List;

public class HomeAdaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private List<Movimiento> movimientos;
    Context contexto;

    public HomeAdaptador(@NonNull Context contexto, List<Movimiento> movimientos) {
        this.contexto = contexto;
        inflater = LayoutInflater.from(contexto);
        this.movimientos = movimientos;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaInicioBinding listaInicioBinding;

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
