package com.fin.xpenses.model;

import lombok.Data;

@Data
public class Movimiento {
    private int idMovimiento;
    private double monto;
    private String fecha;
    private Categoria idCategoria;
    private boolean esFuturo;
    private String fechaRegistro;
}
