package com.fin.xpenses.repository;


import com.fin.xpenses.model.Recordatorio;

import java.util.List;

public interface IRecordatorioRepository {
    boolean agregarRecordatorio(Recordatorio recordatorio);
    boolean eliminarRecordatorio(int idRecordatorio);
    boolean actualizarRecordatorio(Recordatorio recordatorio);
    Recordatorio obtenerRecordatorio(int idRecordatorio);
    List<Recordatorio> obtenerTodosLosRecordatorios();
}
