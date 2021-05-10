package br.com.avb.stockportfolio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.avb.stockportfolio.db.DataBase;
import br.com.avb.stockportfolio.entity.StockCompany;

public class StockCompanyDAO {

    public static void insert(StockCompany stockCompany, Context context) {
        ContentValues values = new ContentValues();
        values.put("name", stockCompany.getName());
        values.put("code", stockCompany.getCode());
        values.put("quantity", stockCompany.getQuantity());
        values.put("value", stockCompany.getValue());
        values.put("total_value", stockCompany.getTotalValue());
        values.put("purchase_date", stockCompany.getPurchaseDate());

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase sqLite = dataBase.getWritableDatabase();

        sqLite.insert("stock_company", null, values);
    }

    public static void edit(StockCompany stockCompany, Context context) {
        ContentValues values = new ContentValues();
        values.put("name", stockCompany.getName());
        values.put("code", stockCompany.getCode());
        values.put("quantity", stockCompany.getQuantity());
        values.put("value", stockCompany.getValue());
        values.put("total_value", stockCompany.getTotalValue());
        values.put("purchase_date", stockCompany.getPurchaseDate());

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase sqLite = dataBase.getWritableDatabase();

        sqLite.update("stock_company", values, "id = " + stockCompany.getId(), null);
    }

    public static void delete(Integer id, Context context) {
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase sqLite = dataBase.getWritableDatabase();
        sqLite.delete("stock_company", "id = " + id, null);
    }

    public static List<StockCompany> getAllStockCompanies(Context context) {
        List<StockCompany> stockCompanies = new ArrayList<>();
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase sqLite = dataBase.getReadableDatabase();
        Cursor cursor = sqLite.rawQuery("SELECT id, code, name, purchase_date, quantity, value, total_value " +
                "FROM stock_company ORDER BY code", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                StockCompany stockCompany = StockCompany.builder()
                        .id(cursor.getInt(0))
                        .code(cursor.getString(1))
                        .name(cursor.getString(2))
                        .purchaseDate(cursor.getString(3))
                        .quantity(cursor.getInt(4))
                        .value(cursor.getDouble(5))
                        .totalValue(cursor.getDouble(6))
                        .build();
                stockCompanies.add(stockCompany);
            } while (cursor.moveToNext());
        }
        return stockCompanies;
    }

    public static StockCompany getStockCompanyById(Context context, Integer id) {
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase sqLite = dataBase.getReadableDatabase();
        Cursor cursor = sqLite.rawQuery("SELECT id, code, name, purchase_date, quantity, value, total_value " +
                "FROM stock_company WHERE id = " + id, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return StockCompany.builder()
                    .id(cursor.getInt(0))
                    .code(cursor.getString(1))
                    .name(cursor.getString(2))
                    .purchaseDate(cursor.getString(3))
                    .quantity(cursor.getInt(4))
                    .value(cursor.getDouble(5))
                    .totalValue(cursor.getDouble(6))
                    .build();
        } else {
            return null;
        }
    }

    public static StockCompany getStockCompanyByCode(Context context, String code) {
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase sqLite = dataBase.getReadableDatabase();
        Cursor cursor = sqLite.rawQuery("SELECT id, code, name, purchase_date, quantity, value, total_value " +
                "FROM stock_company WHERE code LIKE '%" + code + "%' ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return StockCompany.builder()
                    .id(cursor.getInt(0))
                    .code(cursor.getString(1))
                    .name(cursor.getString(2))
                    .purchaseDate(cursor.getString(3))
                    .quantity(cursor.getInt(4))
                    .value(cursor.getDouble(5))
                    .totalValue(cursor.getDouble(6))
                    .build();
        } else {
            return null;
        }
    }

}
