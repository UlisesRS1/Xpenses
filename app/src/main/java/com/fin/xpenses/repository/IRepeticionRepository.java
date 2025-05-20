package com.fin.xpenses.repository;

import com.fin.xpenses.model.Repeticion;

import java.util.List;

public interface IRepeticionRepository {
    boolean agregarRepeticion(Repeticion repeticion);
    boolean eliminarRepeticion(int idRepeticion);
    boolean actualizarRepeticion(int idRepeticion, Repeticion repeticion);
    Repeticion obtenerRepeticion(int idRepeticion);
    List<Repeticion> obtenerTodasLasRepeticiones();
}
