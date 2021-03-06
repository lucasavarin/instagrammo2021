package com.lynx.instagrammo.db

import android.provider.BaseColumns

object ProfileContract {

    object ProfileEntry : BaseColumns{
        const val TABLE_NAME = "Profile_Table"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_FOLLOWERS_NUMBER = "followersNumber"
        const val COLUMN_NAME_POSTS_NUMBER = "postsNumber"
    }

    const val SQL_CREATE_PROFILE_ENTRIES = "CREATE TABLE ${ProfileEntry.TABLE_NAME} (" +
            "${ProfileEntry.COLUMN_NAME_PROFILE_ID} TEXT PRIMARY KEY," +
            "${ProfileEntry.COLUMN_NAME_NAME} TEXT ," +
            "${ProfileEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
            "${ProfileEntry.COLUMN_NAME_PICTURE} TEXT," +
            "${ProfileEntry.COLUMN_NAME_FOLLOWERS_NUMBER} TEXT," +
            "${ProfileEntry.COLUMN_NAME_POSTS_NUMBER} TEXT )"



    const val SQL_DELETE_PROFILE_ENTRIES = "DROP TABLE IF EXISTS ${ProfileEntry.TABLE_NAME}"

}