package com.fin.xpenses.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fin.xpenses.contract.CategoriaContract;
import com.fin.xpenses.contract.MovimientoContract;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "xpenses.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_MOVIMIENTOS = "CREATE TABLE IF NOT EXISTS "+
            MovimientoContract.MovimientoEntry.TABLE_NAME +" ( "+
            MovimientoContract.MovimientoEntry.ID_MOVIMIENTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            MovimientoContract.MovimientoEntry.MONTO + " REAL NOT NULL, "+
            MovimientoContract.MovimientoEntry.FECHA + " TEXT NOT NULL, "+
            MovimientoContract.MovimientoEntry.ID_CATEGORIAS + " INTEGER NOT NULL, "+
            MovimientoContract.MovimientoEntry.ES_FUTURO + " INTEGER NOT NULL, "+
            MovimientoContract.MovimientoEntry.FEHCA_REGISTRO + " TEXT NOT NULL, "+
            "FOREIGN KEY (" + MovimientoContract.MovimientoEntry.ID_CATEGORIAS + ") REFERENCES "+
            CategoriaContract.CategoriasEntry.TABLE_NAME +"(" + CategoriaContract.CategoriasEntry.ID_CATEGORIAS + "));";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
