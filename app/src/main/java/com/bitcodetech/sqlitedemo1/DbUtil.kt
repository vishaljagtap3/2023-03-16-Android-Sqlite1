package com.bitcodetech.sqlitedemo1

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

object DbUtil {

    fun addProduct(
        context: Context,
        srNo: Int,
        title: String,
        price: Int,
        dept: String? = null
    ): Long {

        val db = getDatabase(context)

        val values = ContentValues()
        values.put("sr_no", srNo)
        values.put("title", title)
        values.put("price", price)
        if (dept != null) {
            values.put("dept", dept)
        }

        val rowNum = db.insert(
            "products",
            if (dept == null) "dept" else null,
            values
        )

        db.close()
        return rowNum
    }

    fun getProducts(
        context: Context
    ): ArrayList<Product> {

        val db = getDatabase(context)
        val c: Cursor = db.query(
            "products",
            null,
            null,
            null,
            null,
            null,
            "price desc"
        )


        val products = ArrayList<Product>()

        while (c.moveToNext()) {
            products.add(
                Product(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3)
                )
            )
        }
        db.close()

        return products
        /*val c : Cursor = db.query(
            "products",
            arrayOf("sr_no", "title", "price", "dept"), //null,
            "dept = '?' and price > ? and price < ?",//"dept = 'electronics' and price > 1000 and price < 1200"
            arrayOf("electronics", "1000", "1200"),
            "dept, city",
            "avg(price) > 890",
            "sr_no desc"
        )*/
    }

    fun getProducts(
        context: Context,
        minPrice: Int,
        maxPrice: Int
    ): ArrayList<Product> {

        val db = getDatabase(context)
        val c: Cursor = db.query(
            "products",
            null,
            "price >= ? and price <= ?",
            arrayOf("$minPrice", "$maxPrice"),
            null,
            null,
            "price desc"
        )


        val products = ArrayList<Product>()

        while (c.moveToNext()) {
            products.add(
                Product(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3)
                )
            )
        }
        db.close()

        return products
        /*val c : Cursor = db.query(
            "products",
            arrayOf("sr_no", "title", "price", "dept"), //null,
            "dept = '?' and price > ? and price < ?",//"dept = 'electronics' and price > 1000 and price < 1200"
            arrayOf("electronics", "1000", "1200"),
            "dept, city",
            "avg(price) > 890",
            "sr_no desc"
        )*/
    }

    fun updateProductDept(
        context: Context,
        srNo: Int,
        dept: String
    ) : Int {
        val db = getDatabase(context)

        val values = ContentValues()
        values.put("dept", dept)

        val count = db.update(
            "products",
            values,
            "sr_no = ?",
            arrayOf("$srNo")
        )

        db.close()
        return count
    }

    fun deleteProduct(
        context: Context,
        srNo: Int
    ): Boolean {

        val db = getDatabase(context)

        val count = db.delete(
            "products",
            "sr_no = ?",
            arrayOf("$srNo")
        )
        db.close()

        return count != 0
    }

    private fun getDatabase(
        context: Context
    ): SQLiteDatabase {
        return context.openOrCreateDatabase(
            "db_ecom",
            Activity.MODE_PRIVATE,
            null
        )
    }

    fun rawQuery(
        context: Context
    ) {
        val db = getDatabase(context)
        val c = db.rawQuery("select * from products", null)

        while (c.moveToNext()) {
            Log.e("tag", "${c.getInt(0)} ${c.getString(1)}")
        }

        c.close()
        db.close()
    }
}