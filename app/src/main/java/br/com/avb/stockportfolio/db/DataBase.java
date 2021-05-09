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
                "     code TEXT NOT NULL ," +
                "     name TEXT NOT NULL ," +
                "     purchase_date TEXT  ) ");

        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(1,'AZUL4','Azul SA','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(2,'ABEV3','Ambev','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(3,'BBAS3','Banco do Brasil','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(4,'BIDI4','Banco Inter','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(5,'BPAN4','Banco Pan','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(6,'CIEL3','Cielo','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(7,'CYRE3','Cyrela','2021-05-07')");
        db.execSQL("INSERT OR REPLACE INTO stock_company VALUES(8,'OIBR3','Oi','2021-05-07')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
