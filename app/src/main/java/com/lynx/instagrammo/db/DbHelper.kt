package com.lynx.instagrammo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.beanDao.PostDB
import com.lynx.instagrammo.beanDao.ProfileDB
import com.lynx.instagrammo.db.PostContract.PostEntry.COLUMN_NAME_PICTURE
import com.lynx.instagrammo.db.PostContract.PostEntry.COLUMN_NAME_POST_ID
import com.lynx.instagrammo.db.PostContract.PostEntry.COLUMN_NAME_PROFILE_ID
import com.lynx.instagrammo.db.PostContract.PostEntry.COLUMN_NAME_TITLE
import com.lynx.instagrammo.db.PostContract.PostEntry.COLUMN_NAME_UPLOAD_TIME
import com.lynx.instagrammo.db.PostContract.PostEntry.TABLE_NAME
import com.lynx.instagrammo.db.PostContract.SQL_CREATE_ENTRIES
import com.lynx.instagrammo.db.PostContract.SQL_DELETE_ENTRIES
import com.lynx.instagrammo.db.ProfileContract.SQL_CREATE_PROFILE_ENTRIES
import com.lynx.instagrammo.db.ProfileContract.SQL_DELETE_PROFILE_ENTRIES
import com.lynx.instagrammo.dbHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, BATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
        db?.execSQL(SQL_CREATE_PROFILE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        db?.execSQL(SQL_DELETE_PROFILE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val BATABASE_NAME = "instagrammoDB"
    }

    fun insertData(post: Post) {
        val db = dbHelper.writableDatabase
        val cv = ContentValues()
            cv.put(COLUMN_NAME_PROFILE_ID, post.profileId)
            cv.put(COLUMN_NAME_POST_ID, post.postId)
            cv.put(COLUMN_NAME_TITLE, post.title)
            cv.put(COLUMN_NAME_PICTURE, post.picture)
            cv.put(COLUMN_NAME_UPLOAD_TIME, post.uploadTime)
        val result = db.insert(TABLE_NAME, null, cv)
    }

    fun insertProfile(profile: Profile) {
        val db = dbHelper.writableDatabase
        val cv = ContentValues()
        cv.put(ProfileContract.ProfileEntry.COLUMN_NAME_PROFILE_ID, profile.profileId)
        cv.put(ProfileContract.ProfileEntry.COLUMN_NAME_NAME, profile.name)
        cv.put(ProfileContract.ProfileEntry.COLUMN_NAME_DESCRIPTION, profile.description)
        cv.put(ProfileContract.ProfileEntry.COLUMN_NAME_PICTURE, profile.picture)
        cv.put(ProfileContract.ProfileEntry.COLUMN_NAME_FOLLOWERS_NUMBER, profile.followersNumber)
        cv.put(ProfileContract.ProfileEntry.COLUMN_NAME_POSTS_NUMBER, profile.postsNumber)
        val result = db.insert(ProfileContract.ProfileEntry.TABLE_NAME, null, cv)
    }

    fun readData(): MutableList<PostDB> {
        val list: MutableList<PostDB> = ArrayList()
        val db = dbHelper.readableDatabase
        val query = "SELECT * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                val post = PostDB(
                        profileId = result.getString(result.getColumnIndex(COLUMN_NAME_PROFILE_ID)),
                        postId = result.getString(result.getColumnIndex(COLUMN_NAME_POST_ID)),
                        title = result.getString(result.getColumnIndex(COLUMN_NAME_TITLE)),
                        picture = result.getString(result.getColumnIndex(COLUMN_NAME_PICTURE)),
                        uploadTime = result.getString(result.getColumnIndex(COLUMN_NAME_UPLOAD_TIME))
                )
                list.add(post)
            }
            while(result.moveToNext())
        }
        return list
    }

    fun readProfile(id: String): ProfileDB{
        val db = dbHelper.readableDatabase
        val query = "SELECT * from ${ProfileContract.ProfileEntry.TABLE_NAME} where ${ProfileContract.ProfileEntry.COLUMN_NAME_PROFILE_ID} == $id"
        val result = db.rawQuery(query, null)
        return ProfileDB(
                        profileId = result.getString(result.getColumnIndex(ProfileContract.ProfileEntry.COLUMN_NAME_PROFILE_ID)),
                        name = result.getString(result.getColumnIndex(ProfileContract.ProfileEntry.COLUMN_NAME_NAME)),
                        description = result.getString(result.getColumnIndex(ProfileContract.ProfileEntry.COLUMN_NAME_DESCRIPTION)),
                        picture = result.getString(result.getColumnIndex(ProfileContract.ProfileEntry.COLUMN_NAME_PICTURE)),
                        followersNumber = result.getString(result.getColumnIndex(ProfileContract.ProfileEntry.COLUMN_NAME_FOLLOWERS_NUMBER)),
                        postsNumber = result.getString(result.getColumnIndex(ProfileContract.ProfileEntry.COLUMN_NAME_POSTS_NUMBER)))

    }



}