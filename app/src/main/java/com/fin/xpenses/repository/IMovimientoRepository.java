package com.fin.xpenses.repository;

import com.fin.xpenses.model.Movimiento;

import java.util.List;

public interface IMovimientoRepository {
    boolean agregarMovimiento(Movimiento movimiento);
    boolean eliminarMovimiento(int idMovimiento);
    boolean actualizarMovimiento(Movimiento movimiento);
    Movimiento obtenerMovimiento(int idMovimiento);
    List<Movimiento> obtenerTodosLosMovimientos();
}
