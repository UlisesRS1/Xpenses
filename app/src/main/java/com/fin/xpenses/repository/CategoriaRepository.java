package com.fin.xpenses.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.CategoriaContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.TipoCategoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoriaRepository implements ICategoriaRepository{
    private DatabaseHelper databaseHelper;

    public CategoriaRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean agregarCategoria(Categoria categoria) {
        SQLiteDatabase db;
        ContentValues values;

        try{
            db = databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(CategoriaContract.CategoriasEntry.CATEGORIA, categoria.getCategoria());
            values.put(CategoriaContract.CategoriasEntry.ID_TIPO_CATEGORIA, categoria.getIdTipoCategoria().getIdTipoCategoria());

            long insert = db.insert(CategoriaContract.CategoriasEntry.TABLE_NAME, null, values);
            return insert != -1;
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminarCategoria(int idCategoria) {
        SQLiteDatabase db;
        String selection;

        try{
            db = this.databaseHelper.getWritableDatabase();

            selection = CategoriaContract.CategoriasEntry.ID_CATEGORIAS + " = ? ";
            String[] selectionArgs = { String.valueOf(idCategoria) };

            int deleteRows = db.delete(CategoriaContract.CategoriasEntry.TABLE_NAME, selection, selectionArgs);
            return deleteRows != -1;

        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean actualizarCategoria(int idCategoria, Categoria categoria) {
        SQLiteDatabase db;
        ContentValues values;

        try {
            db = this.databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(CategoriaContract.CategoriasEntry.CATEGORIA, categoria.getCategoria());
            values.put(CategoriaContract.CategoriasEntry.ID_TIPO_CATEGORIA, categoria.getIdTipoCategoria().getIdTipoCategoria());

            int update = db.update(CategoriaContract.CategoriasEntry.TABLE_NAME,
                    values,
                    CategoriaContract.CategoriasEntry.ID_CATEGORIAS +
                            " = ?", new String[]{String.valueOf(idCategoria)});
            return update != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public Categoria obtenerCategoria(int idCategoria) {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        Categoria categoria;
        ITipoCategoria tipoCategoriaRepository;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + CategoriaContract.CategoriasEntry.TABLE_NAME + " WHERE " + CategoriaContract.CategoriasEntry.ID_CATEGORIAS + " = ?";

            cursor = db.rawQuery(sql, new String[]{String.valueOf(idCategoria)});
            tipoCategoriaRepository = new TipoCategoriaRepository(databaseHelper);

            if (cursor.moveToFirst()) {
                categoria = new Categoria();
                categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriasEntry.ID_CATEGORIAS)));
                categoria.setCategoria(cursor.getString(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriasEntry.CATEGORIA)));

                TipoCategoria tipoCategoria = tipoCategoriaRepository.obtenerTipoCategoria
                        (cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriasEntry.ID_TIPO_CATEGORIA)));
                categoria.setIdTipoCategoria(tipoCategoria);

                cursor.close();
                db.close();

                return categoria;
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            }

        return null;
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        List<Categoria> categorias;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + CategoriaContract.CategoriasEntry.TABLE_NAME;
            cursor = db.rawQuery(sql, null);
            categorias = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriasEntry.ID_CATEGORIAS)));
                    categoria.setCategoria(cursor.getString(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriasEntry.CATEGORIA)));
                    /*- Implementar despues de crer el metodo para obtener el tipo de categoria por id -*/
                    TipoCategoria tipoCategoria = new TipoCategoria();
                    tipoCategoria.setIdTipoCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriasEntry.ID_TIPO_CATEGORIA)));
                    categoria.setIdTipoCategoria(tipoCategoria);

                    boolean add = categorias.add(categoria);
                    Log.e("Add", String.valueOf(add));
                } while (cursor.moveToNext());
            }

            return categorias;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return Collections.emptyList();
    }
}
