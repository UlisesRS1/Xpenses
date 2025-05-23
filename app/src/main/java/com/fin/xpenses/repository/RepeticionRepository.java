package com.fin.xpenses.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.RepeticionContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Repeticion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepeticionRepository implements IRepeticionRepository{
    private DatabaseHelper databaseHelper;

    public RepeticionRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean agregarRepeticion(Repeticion repeticion) {
        SQLiteDatabase db;
        ContentValues values;
        try {
            db = databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(RepeticionContract.RepeticionesEntry.ID_REPETICION, repeticion.getIdRepeticion());
            values.put(RepeticionContract.RepeticionesEntry.ID_CATEGORIA, repeticion.getIdCategoria().getIdCategoria());
            values.put(RepeticionContract.RepeticionesEntry.MONTO, repeticion.getMonto());
            values.put(RepeticionContract.RepeticionesEntry.FECHA_INICIO, repeticion.getFechaInicia());
            values.put(RepeticionContract.RepeticionesEntry.FECHA_TERMINO, repeticion.getFechaTermina());
            values.put(RepeticionContract.RepeticionesEntry.DIAS_ESPECIFICOS, repeticion.getDiasEspecificos());
            long insert = db.insert(RepeticionContract.RepeticionesEntry.TABLE_NAME, null, values);
            return insert != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarRepeticion(int idRepeticion) {
        SQLiteDatabase db;
        String selection;

        try{
            db = this.databaseHelper.getWritableDatabase();

            selection = RepeticionContract.RepeticionesEntry.ID_REPETICION + " = ?";
            String[] selectionArgs = { String.valueOf(idRepeticion) };

            int deleteRows = db.delete(RepeticionContract.RepeticionesEntry.TABLE_NAME, selection, selectionArgs);
            return deleteRows != -1;

        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarRepeticion(int idRepeticion, Repeticion repeticion) {
        SQLiteDatabase db;
        ContentValues values;

        try {
            db = this.databaseHelper.getWritableDatabase();
            values = new ContentValues();

            values.put(RepeticionContract.RepeticionesEntry.ID_CATEGORIA, repeticion.getIdCategoria().getIdCategoria());
            values.put(RepeticionContract.RepeticionesEntry.MONTO, repeticion.getMonto());
            values.put(RepeticionContract.RepeticionesEntry.FECHA_INICIO, repeticion.getFechaInicia());
            values.put(RepeticionContract.RepeticionesEntry.FRECUENCIA, repeticion.getFrecuencia());
            values.put(RepeticionContract.RepeticionesEntry.FECHA_TERMINO, repeticion.getFechaTermina());
            values.put(RepeticionContract.RepeticionesEntry.DIAS_ESPECIFICOS, repeticion.getDiasEspecificos());
            int update = db.update(RepeticionContract.RepeticionesEntry.TABLE_NAME,
                    values,
                    RepeticionContract.RepeticionesEntry.ID_REPETICION +
                            " = ?", new String[]{String.valueOf(idRepeticion)});
            return update != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public Repeticion obtenerRepeticion(int idRepeticion) {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        Repeticion repeticion;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + RepeticionContract.RepeticionesEntry.TABLE_NAME + " WHERE " + RepeticionContract.RepeticionesEntry.ID_REPETICION + " = ?";
            cursor = db.rawQuery(sql, new String[]{String.valueOf(idRepeticion)});

            if (cursor.moveToFirst()) {
                repeticion = new Repeticion();
                Categoria categoria = new Categoria();
                repeticion.setIdRepeticion(cursor.getInt(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.ID_REPETICION)));
                repeticion.setMonto(cursor.getDouble(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.MONTO)));
                repeticion.setFechaInicia(cursor.getString(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.FECHA_INICIO)));
                repeticion.setFechaTermina(cursor.getString(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.FECHA_TERMINO)));
                repeticion.setFrecuencia(cursor.getInt(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.FRECUENCIA)));
                repeticion.setDiasEspecificos(cursor.getString(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.DIAS_ESPECIFICOS)));
                categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.ID_CATEGORIA)));
                repeticion.setIdCategoria(categoria);

                cursor.close();
                db.close();

                return repeticion;
            }
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        return null;
    }

    @Override
    public List<Repeticion> obtenerTodasLasRepeticiones() {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        List<Repeticion> repeticiones;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + RepeticionContract.RepeticionesEntry.TABLE_NAME;
            cursor = db.rawQuery(sql, null);
            repeticiones = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    Repeticion repeticion = new Repeticion();
                    Categoria categoria = new Categoria();

                    repeticion.setIdRepeticion(cursor.getInt(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.ID_REPETICION)));
                    repeticion.setMonto(cursor.getDouble(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.MONTO)));
                    repeticion.setFechaInicia(cursor.getString(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.FECHA_INICIO)));
                    repeticion.setFechaTermina(cursor.getString(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.FECHA_TERMINO)));
                    repeticion.setFrecuencia(cursor.getInt(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.FRECUENCIA)));
                    repeticion.setDiasEspecificos(cursor.getString(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.DIAS_ESPECIFICOS)));

                    categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(RepeticionContract.RepeticionesEntry.ID_CATEGORIA)));
                    repeticion.setIdCategoria(categoria);

                    repeticion.setIdCategoria(categoria);
                } while (cursor.moveToNext());
            }

            return repeticiones;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return Collections.emptyList();
    }
}
