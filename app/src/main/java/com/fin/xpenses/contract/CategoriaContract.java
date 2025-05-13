package com.fin.xpenses.contract;

import android.provider.BaseColumns;

public class CategoriaContract {
    public static class CategoriasEntry implements BaseColumns {
        /* Nombre de la tabla */
        private static final String TABLE_NAME = "Categorias";

        /* Columnas de la tabla */
        private static final String ID_CATEGORIAS = "id_categorias";
        private static final String CATEGORIA = "categoria";
        private static final String ID_TIPO_CATEGORIA = "id_tipo_categoria";
    }
}
