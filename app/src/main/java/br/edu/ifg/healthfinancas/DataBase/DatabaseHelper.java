package br.edu.ifg.healthfinancas.DataBase;

/**
 * Created by KuroisarU on 13/07/16.
 * #malandramente
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome das Tabelas
    public static final String TABLE_NAME = "TIPOTRANSACAO";
    public static final String TABLE_NAME2 = "CATEGORIA";
    public static final String TABLE_NAME3 = "CONTROLEGASTOS";
    public static final String TABLE_NAME4 = "RELATORIO";
    public static final String TABLE_NAME5 = "TRANSACAO";

    // Coluna da tabela TIPOTRANSACAO
    public static final String _ID = "_id";
    public static final String TIPTIPO = "tip_tipo";

    //COLUNA DA TABELA CATEGORIA
    public static final String _ID1 = "_id";
    public static final String CATNOME = "cat_nome";
    //public static final String TRANTIPCODIGO = "tran_tip_codigo";

    //COLUNA DA TABELA CONTROLEGASTOS
    public static final String _ID2 = "_id";
    public static final String CONTLIMITE = "cont_limite";
    public static final String CONTCATCODIGO = "cont_cat_codigo";

    //COLUNA DA TABELA RELATORIO
    public static final String _ID3 = "_id";
    public static final String RELDTINICIO = "rel_dtinicio";
    public static final String RELDTFIM = "rel_dtfim";

    //COLUNA DA TABELA TRANSACAO
    public static final String _ID4 = "_id";
    public static final String TRANDESCRICAO = "tran_descricao";
    public static final String TRANDATA = "tran_data";
    public static final String TRANVALOR = "tran_valor";
    public static final String TRANCATCODIGO = "tran_cat_codigo";

    //Informação da Database
    static final String DB_NAME = "HEALTHFINANCAS.DB";

    // Database versão
    static final int DB_VERSION = 1;

    // Criando query da tabela
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIPTIPO + " VARCHAR (45));";
    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "(" + _ID1
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATNOME + " VARCHAR(45) NOT NULL ";
    private static final String CREATE_TABLE3 = "create table " + TABLE_NAME3 + "(" + _ID2
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTLIMITE + " DECIMAL (5, 2) NOT NULL, " + CONTCATCODIGO
            + " INTEGER REFERENCES CATEGORIA (_id));";
    private static final String CREATE_TABLE4 = "create table " + TABLE_NAME4 + "(" + _ID3
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RELDTINICIO + " DATETIME NOT NULL, " + RELDTFIM
            + " DATETIME NOT NULL);";
    private static final String CREATE_TABLE5 = "create table " + TABLE_NAME5 + "(" + _ID4
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TRANDESCRICAO + " VARCHAR (45)    NOT NULL, " + TRANDATA
            + " DATETIME       NOT NULL,"+TRANVALOR+"DECIMAL (5, 2) NOT NULL"+TRANCATCODIGO+"INTEGER REFERENCES CATEGORIA (_id));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE4);
        db.execSQL(CREATE_TABLE5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
        onCreate(db);
    }
}
