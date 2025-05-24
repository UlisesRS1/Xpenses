package com.fin.xpenses.repository;

import com.fin.xpenses.model.Categoria;

import java.util.List;

public interface ICategoriaRepository {
    boolean agregarCategoria(Categoria categoria);
    boolean eliminarCategoria(int idCategoria);
    boolean actualizarCategoria(int idCategoria, Categoria categoria);
    Categoria obtenerCategoria(int idCategoria);
    List<Categoria> obtenerTodasLasCategorias();
}
