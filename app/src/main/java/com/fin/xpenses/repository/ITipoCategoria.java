package com.fin.xpenses.repository;

import com.fin.xpenses.model.TipoCategoria;

import java.util.List;

public interface ITipoCategoria {
    TipoCategoria obtenerTipoCategoria(int idTipoCategoria);
    List<TipoCategoria> obtenerTodosLosTiposCategorias();
}
