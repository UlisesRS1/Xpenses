package com.fin.xpenses.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.RecordatorioContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.model.Recordatorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordatorioRepository implements IRecordatorioRepository{
    private DatabaseHelper databaseHelper;
    public RecordatorioRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean agregarRecordatorio(Recordatorio recordatorio) {
        SQLiteDatabase db;
        ContentValues values;
        try {
            db = databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(RecordatorioContract.RecordatorioEntry.ID_RECORDATORIO, recordatorio.getIdRecordatorio());
            values.put(RecordatorioContract.RecordatorioEntry.ID_MOVIMIENTO, recordatorio.getIdMovimiento().getIdMovimiento());
            values.put(RecordatorioContract.RecordatorioEntry.MENSAJE, recordatorio.getMensaje());
            values.put(RecordatorioContract.RecordatorioEntry.FECHA_ALARMA, recordatorio.getFechaAlarma());
            long insert = db.insert(RecordatorioContract.RecordatorioEntry.TABLE_NAME, null, values);

            db.close();
            return insert != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarRecordatorio(int idRecordatorio) {
        SQLiteDatabase db;
        String selection;

        try{
            db = this.databaseHelper.getWritableDatabase();

            selection = RecordatorioContract.RecordatorioEntry.ID_RECORDATORIO + " = ?";
            String[] selectionArgs = { String.valueOf(idRecordatorio) };

            int deleteRows = db.delete(RecordatorioContract.RecordatorioEntry.TABLE_NAME, selection, selectionArgs);
            db.close();

            return deleteRows != -1;
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarRecordatorio(int idRecordatorio, Recordatorio recordatorio) {
        SQLiteDatabase db;
        ContentValues values;

        try {
            db = this.databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(RecordatorioContract.RecordatorioEntry.ID_MOVIMIENTO, recordatorio.getIdMovimiento().getIdMovimiento());
            values.put(RecordatorioContract.RecordatorioEntry.MENSAJE, recordatorio.getMensaje());
            values.put(RecordatorioContract.RecordatorioEntry.FECHA_ALARMA, recordatorio.getFechaAlarma());

            int update = db.update(RecordatorioContract.RecordatorioEntry.TABLE_NAME,
                    values,
                    RecordatorioContract.RecordatorioEntry.ID_RECORDATORIO +
                            " = ?", new String[]{String.valueOf(idRecordatorio)});
            db.close();
            return update != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public Recordatorio obtenerRecordatorio(int idRecordatorio) {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        Recordatorio recordatorio;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + RecordatorioContract.RecordatorioEntry.TABLE_NAME + " WHERE " + RecordatorioContract.RecordatorioEntry.ID_RECORDATORIO + " = ?";
            cursor = db.rawQuery(sql, new String[]{String.valueOf(idRecordatorio)});

            if (cursor.moveToFirst()) {
                recordatorio = new Recordatorio();
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimiento(cursor.getInt(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.ID_MOVIMIENTO)));
                recordatorio.setIdMovimiento(movimiento);
                recordatorio.setMensaje(cursor.getString(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.MENSAJE)));
                recordatorio.setFechaAlarma(cursor.getString(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.FECHA_ALARMA)));

                cursor.close();
                db.close();

                return recordatorio;
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return null;
    }

    @Override
    public List<Recordatorio> obtenerTodosLosRecordatorios() {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        List<Recordatorio> recordatorios;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + RecordatorioContract.RecordatorioEntry.TABLE_NAME;
            cursor = db.rawQuery(sql, null);
            recordatorios = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    Recordatorio recordatorio = new Recordatorio();
                    Movimiento movimiento = new Movimiento();
                    recordatorio.setIdRecordatorio(cursor.getInt(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.ID_RECORDATORIO)));
                    movimiento.setIdMovimiento(cursor.getInt(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.ID_MOVIMIENTO)));

                    recordatorio.setIdMovimiento(movimiento);
                    recordatorio.setMensaje(cursor.getString(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.MENSAJE)));

                    recordatorio.setFechaAlarma(cursor.getString(cursor.getColumnIndexOrThrow(RecordatorioContract.RecordatorioEntry.FECHA_ALARMA)));

                    recordatorios.add(recordatorio);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            return recordatorios;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return Collections.emptyList();
    }
}
