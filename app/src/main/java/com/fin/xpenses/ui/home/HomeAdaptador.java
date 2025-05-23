package com.fin.xpenses.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.fin.xpenses.R;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.databinding.ListaInicioBinding;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.repository.IMovimientoRepository;
import com.fin.xpenses.repository.MovimientoRepository;

import java.util.List;

public class HomeAdaptador extends BaseAdapter {
    private static LayoutInflater inflater;
    private Context contexto;
    private List<Movimiento> movimientos;

    public HomeAdaptador(@NonNull Context contexto, List<Movimiento> movimientos) {
        this.contexto = contexto;
        HomeAdaptador.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.movimientos = movimientos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtDescripcion;
        TextView txtCategoria;
        TextView txtCifra;

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lista_inicio, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.binding = ListaInicioBinding.bind(convertView);
            viewHolder.txtDescripcion = viewHolder.binding.txtDescripcion;
            viewHolder.txtCategoria = viewHolder.binding.txtCategoria;
            viewHolder.txtCifra = viewHolder.binding.txtCifra;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Movimiento movimiento = movimientos.get(position);

        viewHolder.txtDescripcion.setText(movimiento.getDescripcion());
        viewHolder.txtCategoria.setText(movimiento.getIdCategoria().getCategoria());
        viewHolder.txtCifra.setText(String.valueOf(movimiento.getMonto()));

        return convertView;
    }

    static class ViewHolder {
        ListaInicioBinding binding;
        TextView txtDescripcion;
        TextView txtCategoria;
        TextView txtCifra;
    }

    @Override
    public int getCount() {
        return movimientos != null ? movimientos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return movimientos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
