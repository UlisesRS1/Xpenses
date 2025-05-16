package com.fin.xpenses.repository;

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
        return false;
    }

    @Override
    public boolean eliminarRecordatorio(int idRecordatorio) {
        return false;
    }

    @Override
    public boolean actualizarRecordatorio(Recordatorio recordatorio) {
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
