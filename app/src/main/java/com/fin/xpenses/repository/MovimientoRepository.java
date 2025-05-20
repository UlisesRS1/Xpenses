package com.fin.xpenses.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.MovimientoContract;
import com.fin.xpenses.contract.RepeticionContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Movimiento;

import java.util.Collections;
import java.util.List;

public class MovimientoRepository implements IMovimientoRepository{
    private DatabaseHelper databaseHelper;

    public MovimientoRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean agregarMovimiento(Movimiento movimiento) {
        SQLiteDatabase db;
        ContentValues values;

        try{
            db = databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(MovimientoContract.MovimientoEntry.ID_MOVIMIENTO, movimiento.getIdMovimiento());
            values.put(MovimientoContract.MovimientoEntry.MONTO, movimiento.getMonto());
            values.put(MovimientoContract.MovimientoEntry.FECHA, movimiento.getFecha());
            values.put(MovimientoContract.MovimientoEntry.ID_CATEGORIAS, String.valueOf(movimiento.getIdCategoria()));
            values.put(MovimientoContract.MovimientoEntry.ES_FUTURO, movimiento.isEsFuturo());
            values.put(MovimientoContract.MovimientoEntry.FECHA_REGISTRO, movimiento.getFechaRegistro());
            long insert = db.insert(MovimientoContract.MovimientoEntry.TABLE_NAME, null, values);
            return insert != -1;
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarMovimiento(int idMovimiento) {
        SQLiteDatabase db;
        String selection;

        try{
            db = this.databaseHelper.getWritableDatabase();

            selection = MovimientoContract.MovimientoEntry.TABLE_NAME + " WHERE = ? ";
            String[] selectionArgs = { String.valueOf(idMovimiento) };

            int deleteRows = db.delete(MovimientoContract.MovimientoEntry.TABLE_NAME, selection, selectionArgs);
            return deleteRows != -1;

        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean actualizarMovimiento(int idMovimiento, Movimiento movimiento) {
        SQLiteDatabase db;
        ContentValues values;
        try {
            db = this.databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(MovimientoContract.MovimientoEntry.MONTO, movimiento.getMonto());
            values.put(MovimientoContract.MovimientoEntry.FECHA, movimiento.getFecha());
            values.put(MovimientoContract.MovimientoEntry.ID_CATEGORIAS, movimiento.getIdCategoria().getIdCategoria());
            values.put(MovimientoContract.MovimientoEntry.ES_FUTURO, movimiento.isEsFuturo());
            values.put(MovimientoContract.MovimientoEntry.FECHA_REGISTRO, movimiento.getFechaRegistro());

            int update = db.update(MovimientoContract.MovimientoEntry.TABLE_NAME,
                    values,
                    MovimientoContract.MovimientoEntry.ID_MOVIMIENTO +
                            " = ?", new String[]{String.valueOf(idMovimiento)});
            return update != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public Movimiento obtenerMovimiento(int idMovimiento) {
        return null;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return Collections.emptyList();
    }
}
