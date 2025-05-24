package com.fin.xpenses.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.TipoCategoriaContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.TipoCategoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TipoCategoriaRepository implements ITipoCategoria {
    private DatabaseHelper databaseHelper;

    public TipoCategoriaRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public TipoCategoria obtenerTipoCategoria(int idTipoCategoria) {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        TipoCategoria tipoCategoria;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + TipoCategoriaContract.TipoCategoriaEntry.TABLE_NAME + " WHERE " + TipoCategoriaContract.TipoCategoriaEntry.ID_TIPO_CATEGORIA + " = ?";
            cursor = db.rawQuery(sql, new String[]{String.valueOf(idTipoCategoria)});
            if (cursor.moveToFirst()) {
                tipoCategoria = new TipoCategoria();
                tipoCategoria.setIdTipoCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(TipoCategoriaContract.TipoCategoriaEntry.ID_TIPO_CATEGORIA)));
                tipoCategoria.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(TipoCategoriaContract.TipoCategoriaEntry.TIPO)));

                cursor.close();
                db.close();

                return tipoCategoria;
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return null;
    }

    @Override
    public List<TipoCategoria> obtenerTodosLosTiposCategorias() {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        List<TipoCategoria> tiposCategorias;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + TipoCategoriaContract.TipoCategoriaEntry.TABLE_NAME;
            tiposCategorias = new ArrayList<>();
            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    TipoCategoria tipoCategoria = new TipoCategoria();
                    tipoCategoria.setIdTipoCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(TipoCategoriaContract.TipoCategoriaEntry.ID_TIPO_CATEGORIA)));
                    tipoCategoria.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(TipoCategoriaContract.TipoCategoriaEntry.TIPO)));

                    tiposCategorias.add(tipoCategoria);
                    } while (cursor.moveToNext());
                }

            cursor.close();
            db.close();

            return tiposCategorias;
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

        return Collections.emptyList();
    }
}
