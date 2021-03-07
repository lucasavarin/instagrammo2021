package com.example.instagrammo.environment.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.instagrammo.beans.business.post.PostBean

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, CONST_DATABASE_NAME, null, CONST_DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(Companion.SQL_CREATE_FOLLOWER)
        db?.execSQL(Companion.SQL_CREATE_PROFILE)
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

    fun addPost(post : PostBean){
        val db = this.writableDatabase
        val query =
            "INSERT INTO ${DbProfile.TABLE_NAME} " +
                "( ${DbProfile.ProfileEntry.COLUMN_PROFILEID} , " +
                    "${DbProfile.ProfileEntry.COLUMN_NAME}, " +
                    " ${DbProfile.ProfileEntry.COLUMN_DESCRIPTION}, " +
                    " ${DbProfile.ProfileEntry.COLUMN_PICTURE}, " +
                    " ${DbProfile.ProfileEntry.COLUMN_FOLLOWERSNUMBER}, " +
                    " ${DbProfile.ProfileEntry.COLUMN_POSTSNUMBER} )" +
                "VALUES ('${post.profile.profileId}' ," +
                    "'${post.profile.name}', " +
                    "'${post.profile.description}', " +
                    "'${post.profile.picture}', " +
                    "'${post.profile.followersNumber}', " +
                    "'${post.profile.postsNumber}') "
        db?.execSQL(query)

        val query1 =
            "INSERT INTO ${DbPost.TABLE_NAME} " +
                    "( ${DbPost.PostEntry.COLUMN_PROFILEID} , " +
                    "${DbPost.PostEntry.COLUMN_POSTID}, " +
                    " ${DbPost.PostEntry.COLUMN_TITLE}, " +
                    " ${DbPost.PostEntry.COLUMN_PICTURE}, " +
                    " ${DbPost.PostEntry.COLUMN_UPLOADTIME}, " +
                    " ${DbPost.PostEntry.COLUMN_PROFILE} )" +
                    "VALUES ('${post.profile.profileId}' ," +
                    "'${post.postId}', " +
                    "'${post.title}', " +
                    "'${post.picture}', " +
                    "'${post.uploadTime}', " +
                    "'${post.profile}') "
        db?.execSQL(query1)

/*

        var addFollowerQuery = "INSERT INTO ${DbPost.TABLE_NAME} " +
                "(profileid, postId, title, picture, uploadTime, profile) " +
                "VALUES (" +
                "'$profileId','$postId','$title','$picture','$uploadTime', '$profile'" +
                ")" */
       //Log.i("debug", addFollowerQuery)
        db?.execSQL(query)
    }

    fun getPost() : Array<String> {
        val db = this.writableDatabase
        val projection = arrayOf(DbPost.PostEntry.COLUMN_PROFILEID, DbPost.PostEntry.COLUMN_POSTID, DbPost.PostEntry.COLUMN_TITLE, DbPost.PostEntry.COLUMN_PROFILE)
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
                    "${DbPost.PostEntry.COLUMN_PROFILEID} TEXT PRIMARY KEY," +
                    "${DbPost.PostEntry.COLUMN_POSTID} TEXT," +
                    "${DbPost.PostEntry.COLUMN_TITLE} TEXT," +
                    "${DbPost.PostEntry.COLUMN_PICTURE} TEXT,"+
                    "${DbPost.PostEntry.COLUMN_UPLOADTIME} TEXT, "+
                    "${DbPost.PostEntry.COLUMN_PROFILE} TEXT," +
                    "FOREIGN KEY (${DbPost.PostEntry.COLUMN_PROFILE}) REFERENCES ${DbPost.TABLE_NAME} (${DbProfile.ProfileEntry.COLUMN_PROFILEID}))"


        private const val SQL_CREATE_PROFILE =
            "CREATE TABLE ${DbProfile.TABLE_NAME} (" +
                    "${DbProfile.ProfileEntry.COLUMN_PROFILEID} TEXT PRIMARY KEY," +
                    "${DbProfile.ProfileEntry.COLUMN_NAME} TEXT," +
                    "${DbProfile.ProfileEntry.COLUMN_DESCRIPTION} TEXT," +
                    "${DbProfile.ProfileEntry.COLUMN_PICTURE} TEXT,"+
                    "${DbProfile.ProfileEntry.COLUMN_FOLLOWERSNUMBER} TEXT, "+
                    "${DbProfile.ProfileEntry.COLUMN_POSTSNUMBER} TEXT)"


    }



}