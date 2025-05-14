package com.fin.xpenses.contract;

import android.provider.BaseColumns;

public class MovimientoContract {
    public static class MovimientoEntry implements BaseColumns {
        /* Nombre de la tabla */
        public static final String TABLE_NAME = "movimientos";

        /* Columnas de la tabla */
        public static final String ID_MOVIMIENTO = "id_movimiento";
        public static final String MONTO = "monto";
        public static final String FECHA = "fecha";
        public static final String ID_CATEGORIAS = "id_categorias";
        public static final String ES_FUTURO = "es_futuro";
        public static final String FECHA_REGISTRO = "fecha_registro";
    }
}
