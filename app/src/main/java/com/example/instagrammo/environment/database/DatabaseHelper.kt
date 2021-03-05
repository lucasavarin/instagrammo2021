package com.example.instagrammo.environment.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.instagrammo.beans.business.followers.FollowerBean

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, CONST_DATABASE_NAME, null, CONST_DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(Companion.SQL_CREATE_FOLLOWER)
        db?.execSQL(Companion.SQL_CREATE_POST)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Companion.SQL_DELETE_FOLLOWER)
        db?.execSQL(Companion.SQL_DELETE_POST)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db,oldVersion, newVersion)
    }

    //CRUD operations

    fun addFollower(id : String, nome : String = "", descrizione : String = "", picture : String = ""){
        val db = this.writableDatabase
        var addFollowerQuery = "insert into ${DbFollower.TABLE_NAME} values (${id},${nome},${descrizione},${picture})"
        db?.execSQL(addFollowerQuery)
    }

    fun getFollower() : Array<String> {
        val db = this.writableDatabase
        val projection = arrayOf(DbFollower.FollowerEntry.COLUMN_ID, DbFollower.FollowerEntry.COLUMN_NAME, DbFollower.FollowerEntry.COLUMN_DESCRIPTION, DbFollower.FollowerEntry.COLUMN_PICTURE)
        val response = db?.query(
            DbFollower.TABLE_NAME,          //tabella sulla quale eseguo la query
            projection,                     //array di ritorno
            null,                  //condizione WHERE
            null,              //valori della WHERE
            null,                  //gruppi righe
            null,                   //valori gruppi
            null                   //ordinamento
        )
        return projection
    }

    fun addPost(id : String, profileId : String = "", title : String = "", upload : String = "", picture : String = "", profile : String = ""){
        val db = this.writableDatabase
        var addFollowerQuery = "insert into ${DbPost.TABLE_NAME} values (${id},${profileId},${title},${upload},${picture},${profile})"
        db?.execSQL(addFollowerQuery)
    }

    fun getPost() : Array<String> {
        val db = this.writableDatabase
        val projection = arrayOf(DbPost.PostEntry.COLUMN_ID, DbPost.PostEntry.COLUMN_TITLE, DbPost.PostEntry.COLUMN_UPLOAD, DbPost.PostEntry.COLUMN_PROFILE_ID)
        val response = db?.query(
            DbPost.TABLE_NAME,              //tabella sulla quale eseguo la query
            projection,                     //array di ritorno
            null,                  //condizione WHERE
            null,              //valori della WHERE
            null,                  //gruppi righe
            null,                   //valori gruppi
            null                   //ordinamento
        )
        return projection
    }

    companion object {
        const val CONST_DATABASE_VERSION = 1
        const val CONST_DATABASE_NAME = "FollowerEntry.db"

        //drop tabella follower
        private const val SQL_DELETE_FOLLOWER = "DROP TABLE IF EXISTS ${DbFollower.TABLE_NAME}"
        //drop tabella post
        private const val SQL_DELETE_POST = "DROP TABLE IF EXISTS ${DbPost.TABLE_NAME}"

        //create follower
        private const val SQL_CREATE_FOLLOWER =
            "CREATE TABLE ${DbFollower.TABLE_NAME} (" +
                    "${DbFollower.FollowerEntry.COLUMN_ID} TEXT PRIMARY KEY," +
                    "${DbFollower.FollowerEntry.COLUMN_NAME} TEXT," +
                    "${DbFollower.FollowerEntry.COLUMN_DESCRIPTION} TEXT," +
                    "${DbFollower.FollowerEntry.COLUMN_PICTURE} TEXT)"


        //create post
        private const val SQL_CREATE_POST =
            "CREATE TABLE ${DbPost.TABLE_NAME} (" +
                    "${DbPost.PostEntry.COLUMN_ID} TEXT PRIMARY KEY," +
                    "${DbPost.PostEntry.COLUMN_PROFILE_ID} TEXT," +
                    "${DbPost.PostEntry.COLUMN_TITLE} TEXT," +
                    "${DbPost.PostEntry.COLUMN_UPLOAD} TEXT," +
                    "${DbPost.PostEntry.COLUMN_PICTURE} TEXT,"+
                    "${DbPost.PostEntry.COLUMN_PROFILE} TEXT, "+
                    "FOREIGN KEY (${DbPost.PostEntry.COLUMN_PROFILE}) REFERENCES ${DbFollower.TABLE_NAME} (${DbFollower.FollowerEntry.COLUMN_ID}))"
    }



}