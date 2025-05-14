package com.fin.xpenses.model;

import lombok.Data;

@Data
public class Categoria {
    private int idCategoria;
    private String categoria;
    private TipoCategoria idTipoCategoria;
}
