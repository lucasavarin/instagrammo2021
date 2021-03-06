package com.costa.dataBaseSQLLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.costa.beans.rest.PostOut
import com.costa.beans.rest.ProfileOut
import com.costa.dataBaseSQLLite.PostContract.SQL_CREATE_POSTS_ENTRIES
import com.costa.dataBaseSQLLite.PostContract.SQL_DELETE_ENTRIES
import com.costa.utils.db

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "post.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_POSTS_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }


    fun addPostToDB(post: PostOut) {
        val db = db.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(PostContract.PostsEntry.COLUMN_NAME_PROFILE_ID, post.profileId)
            put(PostContract.PostsEntry.COLUMN_NAME_POST_ID, post.postId)
            put(PostContract.PostsEntry.COLUMN_NAME_TITLE, post.title)
            put(PostContract.PostsEntry.COLUMN_NAME_PICTURE, post.picture)
            put(PostContract.PostsEntry.COLUMN_NAME_UPLOADTIME, post.uploadTime)

        }
        val newRowId = db.insert(PostContract.PostsEntry.TABLE_NAME, null, values)

        }

    fun delateRows(){
        val db = db.writableDatabase

        db.execSQL("delete from "+ PostContract.PostsEntry.TABLE_NAME)
    }

    fun addProfileToDB(profile: ProfileOut) {
        val db = db.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(profileContract.ProfileEntry.COLUMN_NAME_PROFILE_ID, profile.profileId)
            put(profileContract.ProfileEntry.COLUMN_NAME_NAME, profile.name)
            put(profileContract.ProfileEntry.COLUMN_NAME_DESCRIPTION, profile.description)
            put(profileContract.ProfileEntry.COLUMN_NAME_PICTURE, profile.picture)
            put(profileContract.ProfileEntry.COLUMN_NAME_FOLLOWER_NUMBER, profile.followersNumber)
            put(profileContract.ProfileEntry.COLUMN_NAME_POSTS_NUMBER, profile.postsNumber)
        }
        val newRowId = db.insert(profileContract.ProfileEntry.TABLE_NAME, null, values)

    }


    }


