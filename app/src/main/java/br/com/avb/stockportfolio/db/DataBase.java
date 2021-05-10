package br.com.avb.stockportfolio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "StockPortfolio";

    public DataBase(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS stock_company ( " +
                "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "     code TEXT ," +
                "     name TEXT ," +
                "     quantity INTEGER ," +
                "     value NUMERIC (10,2) ," +
                "     total_value NUMERIC (10,2) ," +
                "     purchase_date TEXT  ) ");

        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(1,'AZUL4','Azul SA',100, 10.00,1000.00,'2021/05/07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(2,'ABEV3','Ambev',100,20.00,2000.00,'2021/05/07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(3,'BBAS3','Banco do Brasil',100,30.00,3000.00,'2021/05/07')");

        db.execSQL("CREATE TABLE IF NOT EXISTS company ( " +
                "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "     code TEXT UNIQUE," +
                "     name TEXT ) ");

        db.execSQL("INSERT OR REPLACE INTO company VALUES(1,'OIBR3','Oi')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(2,'ABEV3','Ambev')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(3,'CIEL3','Cielo')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(4,'AZUL4','Azul SA')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(5,'BBAS3','Banco do Brasil')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(6,'SANB11','Banco Santander Brasil')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(7,'BRFS3','Brf')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(8,'BPAC3','BTG Pactual')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(9,'CVCB3','CVC Brasil')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(10,'PNVL3','Dimed')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(11,'IRBR3','IRB Brasil')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(12,'ITUB4','Itau Unibanco')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(13,'LINX3','Linx')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(14,'LREN3','Lojas Renner')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(15,'MGLU3','Magazine Luisa')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(16,'GOAU4','Gerdau')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(17,'PETR4','Petrobras')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(18,'RADL3','Raia Drogasil')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(19,'WEGE3','Weg SA')");
        db.execSQL("INSERT OR REPLACE INTO company VALUES(20,'TOTS3','Totvs')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
