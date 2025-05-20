package com.fin.xpenses.contract;

import android.provider.BaseColumns;

public class RepeticionContract {
    public static class RepeticionesEntry implements BaseColumns {
        /* Nombre de la tabla */
        public static final String TABLE_NAME = "repeticiones";

        /* Columnas de la tabla */
        public static final String ID_REPETICION = "id_repeticion";
        public static final String ID_CATEGORIA = "id_categoria";
        public static final String MONTO = "monto";
        public static final String FRECUENCIA = "frecuencia";
        public static final String FECHA_INICIO = "fecha_inicio";
        public static final String FECHA_TERMINO = "fecha_termino";
        public static final String DIAS_ESPECIFICOS = "dias_especificos";
    }
}
