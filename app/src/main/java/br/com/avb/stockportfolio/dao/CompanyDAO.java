package br.com.avb.stockportfolio.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.avb.stockportfolio.db.DataBase;
import br.com.avb.stockportfolio.entity.Company;

public class CompanyDAO {

    public static List<Company> getAllStockCompanies(Context context) {
        List<Company> listCode = new ArrayList<>();
        SQLiteDatabase sqLite = new DataBase(context).getReadableDatabase();
        Cursor cursor = sqLite.rawQuery("SELECT name, code " +
                "FROM company ORDER BY code", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                listCode.add(Company.builder()
                        .name(cursor.getString(0))
                        .code(cursor.getString(1))
                        .build());
            } while (cursor.moveToNext());
        }
        return listCode;
    }

}
