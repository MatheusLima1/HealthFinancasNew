package br.edu.ifg.healthfinancas.DataBase;

/**
 * Created by KuroiSaru on 10/07/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //---------------------------------------------------------------------------------//
    //INSERTS
    //INSERINDO TIPPOTRANSAÇÂO
    public void insert(String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TIPTIPO, name);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
    //INSERINDO CATEGORIA
    public void insert2(String catnome /*Integer trantipcodigo*/) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CATNOME, catnome);
        //contentValue.put(DatabaseHelper.TRANTIPCODIGO/, trantipcodigo);
        database.insert(DatabaseHelper.TABLE_NAME2, null, contentValue);
    }

    //INSERINDO CONTROLEGASTOS
    public void insert3(String contlimite, Integer contcatcodigo) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CONTLIMITE, contlimite);
        contentValue.put(DatabaseHelper.CONTCATCODIGO, contcatcodigo);
        database.insert(DatabaseHelper.TABLE_NAME3, null, contentValue);
    }

    //INSERINDO RELATORIO
    //COMO TRATAR DATA NO ANDROID? OS CAMPOS ABAIXO SÃO DO TIPO DATA
    public void insert4(String reldtinicio, String reldtfim) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.RELDTINICIO, reldtinicio);
        contentValue.put(DatabaseHelper.RELDTFIM, reldtfim);
        database.insert(DatabaseHelper.TABLE_NAME4, null, contentValue);
    }

    //INSERINDO TRANSACAO
    public void insert5(String trandescricao, String trandata, String tranvalor, Integer trancatcodigo) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TRANDESCRICAO, trandescricao);
        //COMO Q TRATA DATA?
        contentValue.put(DatabaseHelper.TRANDATA, trandata);
        //E DECIMAL?
        contentValue.put(DatabaseHelper.TRANVALOR, tranvalor);
        contentValue.put(DatabaseHelper.TRANCATCODIGO, trancatcodigo);
        database.insert(DatabaseHelper.TABLE_NAME5, null, contentValue);
    }

    //---------------------------------------------------------------------------------------------------//
    //CURSORES

    //CURSOR TIPOTRANSACAO
        public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TIPTIPO };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //CURSOR CATEGORIA
    public Cursor fetch1() {
        String[] columns = new String[] { DatabaseHelper._ID1, DatabaseHelper.CATNOME /*,DatabaseHelper.TRANCATCODIGO*/ };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME2, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    //CURSOR CONTROLEGASTOS
    public Cursor fetch2() {
        String[] columns = new String[] { DatabaseHelper._ID2, DatabaseHelper.CONTLIMITE,DatabaseHelper.CONTCATCODIGO };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME3, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //CURSOR RELATORIO
    public Cursor fetch3() {
        String[] columns = new String[] { DatabaseHelper._ID3, DatabaseHelper.RELDTINICIO,DatabaseHelper.RELDTFIM };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME4, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //CURSOR TRANSACAO
    public Cursor fetch4() {
        String[] columns = new String[] { DatabaseHelper._ID4, DatabaseHelper.TRANDESCRICAO,DatabaseHelper.TRANDATA,DatabaseHelper.TRANVALOR,DatabaseHelper.TRANCATCODIGO };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME5, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    //--------------------------------------------------------------------------------------------//
    //UPDATES

    //Update TIPPOTRANSAÇÂO
    public int update(long _id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TIPTIPO, name);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    //Update Categoria
    public int update1(long _id, String catNome/*,String trantipcodigo*/) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CATNOME, catNome);
        //contentValues.put(DatabaseHelper.TRANCATCODIGO ,trantipcodigo);
        int i = database.update(DatabaseHelper.TABLE_NAME2, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    //Update CONTROLEGASTOS
    public int update2(long _id, String contLimite,String contCatCodigo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTLIMITE, contLimite);
        contentValues.put(DatabaseHelper.CONTCATCODIGO,contCatCodigo);
        int i = database.update(DatabaseHelper.TABLE_NAME3, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    //Update RELATORIO
    public int update3(long _id, String relDtInicio,String relDtFim) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.RELDTINICIO, relDtInicio);
        contentValues.put(DatabaseHelper.RELDTFIM ,relDtFim);
        int i = database.update(DatabaseHelper.TABLE_NAME4, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    //Update TRANSACAO
    public int update4(long _id, String tranDescricao,String tranData,String tranValor,String tranCatCodigo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TRANDESCRICAO ,tranDescricao);
        contentValues.put(DatabaseHelper.TRANDATA, tranData);
        contentValues.put(DatabaseHelper.TRANVALOR, tranValor);
        contentValues.put(DatabaseHelper.TRANCATCODIGO, tranCatCodigo);
        int i = database.update(DatabaseHelper.TABLE_NAME5, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }


    //--------------------------------------------------------------------------------------------//
    //DELETES

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete1(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME2, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete2(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME3, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete3(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME4, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete4(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME5, DatabaseHelper._ID + "=" + _id, null);
    }

}
