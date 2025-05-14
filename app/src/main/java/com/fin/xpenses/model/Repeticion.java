package com.fin.xpenses.model;

import lombok.Data;

@Data
public class Repeticion {
    private int idRepeticion;
    private Categoria idCategoria;
    private double monto;
    private int frecuencia;
    private String fechaInicia;
    private String fechaTermina;
    private String diasEspecificos;
}
