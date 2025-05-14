package com.fin.xpenses.contract;

import android.provider.BaseColumns;

public class TipoCategoriaContract {
    public static class TipoCategoriaEntry implements BaseColumns{

        public static final String TABLE_NAME = "tipo_categoria";

        public static final String ID_TIPO_CATEGORIA = "id_tipo_categoria";
        public static final String TIPO = "tipo";
    }
}
