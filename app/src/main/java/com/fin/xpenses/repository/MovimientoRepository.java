package com.fin.xpenses.repository;

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
            values.put(MovimientoContract.MovimientoEntry.ID_MOVIMIENTO, movimiento.getIdMovimiento());
            values.put(MovimientoContract.MovimientoEntry.MONTO, movimiento.getMonto());
            values.put(MovimientoContract.MovimientoEntry.FECHA, movimiento.getFecha());
            values.put(MovimientoContract.MovimientoEntry.ID_CATEGORIAS, movimiento.getIdCategoria().getIdCategoria());
            values.put(MovimientoContract.MovimientoEntry.ES_FUTURO, movimiento.isEsFuturo());
            values.put(MovimientoContract.MovimientoEntry.FECHA_REGISTRO, movimiento.getFechaRegistro());
            long insert = db.insert(MovimientoContract.MovimientoEntry.TABLE_NAME, null, values);
            return insert != -1;
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarMovimiento(int idMovimiento) {
        return false;
    }

    @Override
    public boolean actualizarMovimiento(Movimiento movimiento) {
        return false;
    }

    @Override
    public Movimiento obtenerMovimiento(int idMovimiento) {
        return null;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return Collections.emptyList();
    }
}
