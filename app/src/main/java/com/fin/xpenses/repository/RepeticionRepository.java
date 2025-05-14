package com.fin.xpenses.repository;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fin.xpenses.contract.RepeticionContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Repeticion;

import java.util.Collections;
import java.util.List;

public class RepeticionRepository implements IRepeticionRepository{
    private DatabaseHelper databaseHelper;

    public RepeticionRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean agregarRepeticion(Repeticion repeticion) {
        return false;
    }

    @Override
    public boolean eliminarRepeticion(int idRepeticion) {
        return false;
    }

    @Override
    public boolean actualizarRepeticion(Repeticion repeticion) {
        SQLiteDatabase db;
        ContentValues values;

        try {
            db = this.databaseHelper.getWritableDatabase();
            values = new ContentValues();

            values.put(RepeticionContract.RepeticionesEntry.ID_CATEGORIA, repeticion.getIdCategoria().getIdCategoria());
            values.put(RepeticionContract.RepeticionesEntry.MONTO, repeticion.getMonto());
            values.put(RepeticionContract.RepeticionesEntry.FECHA_INICIO, repeticion.getFechaInicia());
            values.put(RepeticionContract.RepeticionesEntry.FECHA_TERMINO, repeticion.getFechaTermina());
            values.put(RepeticionContract.RepeticionesEntry.DIAS_ESPECIFICOS, repeticion.getDiasEspecificos());

            long update = db.update(RepeticionContract.RepeticionesEntry.TABLE_NAME, values, RepeticionContract.RepeticionesEntry.ID_REPETICION + " = ?", new String[]{String.valueOf(repeticion.getIdRepeticion())});
            return update != -1;

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public Repeticion obtenerRepeticion(int idRepeticion) {
        return null;
    }

    @Override
    public List<Repeticion> obtenerTodasLasRepeticiones() {
        return Collections.emptyList();
    }
}
