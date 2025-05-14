package com.fin.xpenses.contract;

import android.provider.BaseColumns;

public class RecordatorioContract {
    public static class RecordatorioEntry implements BaseColumns{

        public static final String TABLE_NAME = "Recordatorio";
        public static final String ID_RECORDATORIO = "id_recordatorio";
        public static final String ID_MOVIMIENTO = "id_movimiento";
        public static final String MENSAJE = "mensaje";
        public static final String FECHA_ALARMA = "fecha_alarma";
    }
}
