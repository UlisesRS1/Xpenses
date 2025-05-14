package com.fin.xpenses.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fin.xpenses.contract.CategoriaContract;
import com.fin.xpenses.contract.MovimientoContract;
import com.fin.xpenses.contract.TipoCategoriaContract;

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
    private static final String SQL_CREATE_CATEGORIA = "CREATE TABLE IF NOT EXISTS "+
            CategoriaContract.CategoriasEntry.TABLE_NAME +" ( "+
            CategoriaContract.CategoriasEntry.ID_CATEGORIAS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CategoriaContract.CategoriasEntry.CATEGORIA + " TEXT NOT NULL, "+
            CategoriaContract.CategoriasEntry.ID_TIPO_CATEGORIA + " INTEGER NOT NULL, "+
            "FOREIGN KEY (" + CategoriaContract.CategoriasEntry.ID_TIPO_CATEGORIA + ") REFERENCES "+
            TipoCategoriaContract.TipoCategoriaEntry.TABLE_NAME+"("+ TipoCategoriaContract.TipoCategoriaEntry.ID_TIPO_CATEGORIA+ "));";
    private static final String SQL_CREATE_TIPOCATEGORIA = "CREATE TABLE IF NOT EXISTS "+
            TipoCategoriaContract.TipoCategoriaEntry.TABLE_NAME +" ( "+
            TipoCategoriaContract.TipoCategoriaEntry.ID_TIPO_CATEGORIA +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TipoCategoriaContract.TipoCategoriaEntry.TIPO + " TEXT NOT NULL)";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
