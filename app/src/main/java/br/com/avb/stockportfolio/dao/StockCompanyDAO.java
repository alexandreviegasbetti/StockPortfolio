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

    private static final String TABLE_NAME = "stock_company";

    public static void insert(StockCompany stockCompany, Context context) {
        ContentValues values = new ContentValues();
        values.put("name", stockCompany.getName());
        values.put("code", stockCompany.getCode());
        values.put("quantity", stockCompany.getQuantity());
        values.put("value", stockCompany.getValue());
        values.put("total_value", stockCompany.getTotalValue());
        values.put("purchase_date", stockCompany.getPurchaseDate());

        SQLiteDatabase sqLite = new DataBase(context).getWritableDatabase();

        sqLite.insert(TABLE_NAME, null, values);

    }

    public static void edit(StockCompany stockCompany, Context context) {
        ContentValues values = new ContentValues();
        values.put("name", stockCompany.getName());
        values.put("code", stockCompany.getCode());
        values.put("quantity", stockCompany.getQuantity());
        values.put("value", stockCompany.getValue());
        values.put("total_value", stockCompany.getTotalValue());
        values.put("purchase_date", stockCompany.getPurchaseDate());

        SQLiteDatabase sqLite = new DataBase(context).getWritableDatabase();

        sqLite.update(TABLE_NAME, values, "id = " + stockCompany.getId(), null);
    }

    public static void delete(Integer id, Context context) {
        SQLiteDatabase sqLite = new DataBase(context).getWritableDatabase();
        sqLite.delete(TABLE_NAME, "id = " + id, null);
    }

    public static List<StockCompany> getAllStockCompanies(Context context) {
        List<StockCompany> stockCompanies = new ArrayList<>();
        SQLiteDatabase sqLite = new DataBase(context).getReadableDatabase();
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
        SQLiteDatabase sqLite = new DataBase(context).getReadableDatabase();
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
        SQLiteDatabase sqLite = new DataBase(context).getReadableDatabase();
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
