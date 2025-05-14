package com.fin.xpenses.model;

import lombok.Data;

@Data
public class Recordatorio {
    private int idRecordatorio;
    private Moviento idMovimiento;
    private String mensaje;
    private String fechaAlarma;
}
