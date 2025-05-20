package com.fin.xpenses.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.RecordatorioContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Recordatorio;

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

            selection = RecordatorioContract.RecordatorioEntry.TABLE_NAME + " WHERE = ? ";
            String[] selectionArgs = { String.valueOf(idRecordatorio) };

            int deleteRows = db.delete(RecordatorioContract.RecordatorioEntry.TABLE_NAME, selection, selectionArgs);
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
            return update != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public Recordatorio obtenerRecordatorio(int idRecordatorio) {
        return null;
    }

    @Override
    public List<Recordatorio> obtenerTodosLosRecordatorios() {
        return Collections.emptyList();
    }
}
