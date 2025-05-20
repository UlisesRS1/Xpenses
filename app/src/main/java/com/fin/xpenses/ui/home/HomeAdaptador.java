package com.fin.xpenses.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.fin.xpenses.R;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.ListaInicioBinding;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

import java.util.List;

public class HomeAdaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context contexto;
    private List<Movimiento> movimientos;

    public HomeAdaptador(@NonNull Context contexto, List<Movimiento> movimientos) {
        this.contexto = contexto;
        inflater = LayoutInflater.from(contexto);
        this.movimientos = movimientos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaInicioBinding listaInicioBinding;
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            listaInicioBinding = ListaInicioBinding.inflate(inflater, parent, false);
            holder = new ViewHolder(listaInicioBinding);
            convertView = listaInicioBinding.getRoot();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            listaInicioBinding = holder.binding;
        }

        Movimiento movimiento = movimientos.get(position);
        listaInicioBinding.txtCategoria.setText(movimiento.getIdMovimiento());
        listaInicioBinding.txtDescripcion.setText("Descripcion (Test de descripcion)");
        listaInicioBinding.txtCifra.setText(String.valueOf(movimiento.getMonto()));

        return convertView;
    }

    static class ViewHolder {
        ListaInicioBinding binding;


        ViewHolder(ListaInicioBinding binding) {
            this.binding = binding;
        }
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
