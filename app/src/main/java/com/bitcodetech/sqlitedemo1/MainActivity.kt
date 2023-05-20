package com.bitcodetech.sqlitedemo1

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var db : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DbUtil.rawQuery(this)

       /* DbUtil.addProduct(
            this,
            106,
            "T-Shirt",
            450,
            "Garments"
        )
        DbUtil.addProduct(
            this,
            107,
            "Jacket",
            850,
            "Garments"
        )

        var products = DbUtil.getProducts(this)
        for(product in products) {
            Log.e("tag", product.toString())
        }

        mt("--------------------------------")

        val count = DbUtil.updateProductDept(
            this,
            103,
            "Electronics"
        )
        Log.e("tag", "count: $count")

        products = DbUtil.getProducts(this)
        for(product in products) {
            Log.e("tag", product.toString())
        }

        mt("--------------------------------")
        val delCount = DbUtil.deleteProduct(
            this,
            103
        )
        Log.e("tag", "Del count: $delCount")
        products = DbUtil.getProducts(this)
        for(product in products) {
            Log.e("tag", product.toString())
        }*/


       /* db = openOrCreateDatabase(
            "db_ecom",
            Activity.MODE_PRIVATE,
            null
        ) {
            Log.e("tag", "Error!")
        }

        try {
            db.execSQL("create table products(sr_no integer primary key, title text NOT NULL, price integer, dept text)")
        }
        catch (e : SQLiteException){}

        //addProducts()

        db.close()*/
    }

    private fun addProducts() {

        val values = ContentValues()
        values.put("sr_no", 101)
        values.put("title", "Samsung Galaxy 21")
        values.put("price", 1000)

        var rowNum = db.insert(
            "products",
            "dept",
            values
        )
        mt("$rowNum")

        values.put("sr_no", 102)
        values.put("title", "Laptop")
        values.put("price", 1200)

        rowNum = db.insert(
            "products",
            "dept",
            values
        )
        mt("$rowNum")


        values.put("sr_no", 103)
        values.put("title", "Projector")
        values.put("price", 1450)

        rowNum = db.insert(
            "products",
            "dept",
            values
        )
        mt("$rowNum")


        values.put("sr_no", 104)
        values.put("title", "Television")
        values.put("price", 1210)

        rowNum = db.insert(
            "products",
            "dept",
            values
        )
        mt("$rowNum")

        values.put("sr_no", 105)
        values.put("title", "Watch")
        values.put("price", 900)

        rowNum = db.insert(
            "products",
            "dept",
            values
        )
        mt("$rowNum")
    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}