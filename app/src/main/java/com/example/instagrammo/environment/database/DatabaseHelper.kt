package com.example.instagrammo.environment.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.instagrammo.environment.database.DatabaseTables

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, CONST_DATABASE_NAME, null, CONST_DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        //db?.execSQL(/*DatabaseTables.SQL_CREATE_ENTRIES*/)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Companion.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db,oldVersion, newVersion)
    }


    companion object {
        const val CONST_DATABASE_VERSION = 1
        const val CONST_DATABASE_NAME = "FeedReader.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"
    }

}