package com.bitcodetech.sqlitedemo1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductsDBHelper(
    context: Context?,
    name: String?,
    factory : SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {

    //database mgmt logic
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table products(sr_no integer primary key, title text NOT NULL, price integer, dept text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

}