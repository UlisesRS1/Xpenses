package com.fin.xpenses.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fin.xpenses.contract.CategoriaContract;
import com.fin.xpenses.contract.MovimientoContract;
import com.fin.xpenses.contract.RepeticionContract;
import com.fin.xpenses.data.DatabaseHelper;
import com.fin.xpenses.model.Categoria;
import com.fin.xpenses.model.Movimiento;
import com.fin.xpenses.model.TipoCategoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovimientoRepository implements IMovimientoRepository{
    private DatabaseHelper databaseHelper;

    public MovimientoRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean agregarMovimiento(Movimiento movimiento) {
        SQLiteDatabase db;
        ContentValues values;

        try{
            db = databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(MovimientoContract.MovimientoEntry.MONTO, movimiento.getMonto());
            values.put(MovimientoContract.MovimientoEntry.FECHA, movimiento.getFecha());
            values.put(MovimientoContract.MovimientoEntry.ID_CATEGORIAS, movimiento.getIdCategoria().getIdCategoria());
            values.put(MovimientoContract.MovimientoEntry.ES_FUTURO, movimiento.isEsFuturo());
            values.put(MovimientoContract.MovimientoEntry.DESCRIPCION, movimiento.getDescripcion());
            values.put(MovimientoContract.MovimientoEntry.FECHA_REGISTRO, movimiento.getFechaRegistro());
            long insert = db.insert(MovimientoContract.MovimientoEntry.TABLE_NAME, null, values);

            db.close();
            return insert != -1;
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarMovimiento(int idMovimiento) {
        SQLiteDatabase db;
        String selection;

        try{
            db = this.databaseHelper.getWritableDatabase();

            selection = MovimientoContract.MovimientoEntry.ID_MOVIMIENTO + " = ? ";
            String[] selectionArgs = { String.valueOf(idMovimiento) };

            int deleteRows = db.delete(MovimientoContract.MovimientoEntry.TABLE_NAME, selection, selectionArgs);
            db.close();

            return deleteRows != -1;

        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean actualizarMovimiento(int idMovimiento, Movimiento movimiento) {
        SQLiteDatabase db;
        ContentValues values;
        try {
            db = this.databaseHelper.getWritableDatabase();
            values = new ContentValues();
            values.put(MovimientoContract.MovimientoEntry.MONTO, movimiento.getMonto());
            values.put(MovimientoContract.MovimientoEntry.FECHA, movimiento.getFecha());
            values.put(MovimientoContract.MovimientoEntry.ID_CATEGORIAS, movimiento.getIdCategoria().getIdCategoria());
            values.put(MovimientoContract.MovimientoEntry.ES_FUTURO, movimiento.isEsFuturo());
            values.put(MovimientoContract.MovimientoEntry.FECHA_REGISTRO, movimiento.getFechaRegistro());

            int update = db.update(MovimientoContract.MovimientoEntry.TABLE_NAME,
                    values,
                    MovimientoContract.MovimientoEntry.ID_MOVIMIENTO +
                            " = ?", new String[]{String.valueOf(idMovimiento)});

            db.close();
            return update != -1;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return false;
    }

    @Override
    public Movimiento obtenerMovimiento(int idMovimiento) {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        Movimiento movimiento;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + MovimientoContract.MovimientoEntry.TABLE_NAME + " WHERE " + MovimientoContract.MovimientoEntry.ID_MOVIMIENTO + " = ?";
            cursor = db.rawQuery(sql, new String[]{String.valueOf(idMovimiento)});

            if (cursor.moveToFirst()) {
                movimiento = new Movimiento();
                Categoria categoria = new Categoria();
                movimiento.setIdMovimiento(cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.ID_MOVIMIENTO)));
                movimiento.setMonto(cursor.getDouble(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.MONTO)));
                movimiento.setFecha(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.FECHA)));
                movimiento.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.DESCRIPCION)));
                movimiento.setEsFuturo(cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.ES_FUTURO)) == 1);
                movimiento.setFechaRegistro(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.FECHA_REGISTRO)));


                categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.ID_CATEGORIAS)));
                movimiento.setIdCategoria(categoria);

                cursor.close();
                db.close();

                return movimiento;
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean eliminarTodosLosMovimietos(){
        SQLiteDatabase db;
        String sql;
        try {
            db = this.databaseHelper.getWritableDatabase();
            sql = "DELETE FROM " + MovimientoContract.MovimientoEntry.TABLE_NAME;
            db.execSQL(sql);

            db.close();
            return true;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos() {
        SQLiteDatabase db;
        Cursor cursor;
        String sql;
        List<Movimiento> movimientos;
        ICategoriaRepository categoriaRepository;

        try {
            db = this.databaseHelper.getReadableDatabase();
            sql = "SELECT * FROM " + MovimientoContract.MovimientoEntry.TABLE_NAME;
            cursor = db.rawQuery(sql, null);
            movimientos = new ArrayList<>();
            categoriaRepository = new CategoriaRepository(databaseHelper);

            if (cursor.moveToFirst()) {
                do {
                    Movimiento movimiento = new Movimiento();
                    Categoria categoria;

                    movimiento.setIdMovimiento(cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.ID_MOVIMIENTO)));
                    movimiento.setMonto(cursor.getDouble(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.MONTO)));
                    movimiento.setFecha(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.FECHA)));
                    movimiento.setEsFuturo(cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.ES_FUTURO)) == 1);
                    movimiento.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.DESCRIPCION)));
                    movimiento.setFechaRegistro(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.FECHA_REGISTRO)));

                    int categoriaId = cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.ID_CATEGORIAS));
                    categoria = categoriaRepository.obtenerCategoria(categoriaId);
                    movimiento.setIdCategoria(categoria);

                    movimientos.add(movimiento);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return movimientos;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return Collections.emptyList();
    }
}
