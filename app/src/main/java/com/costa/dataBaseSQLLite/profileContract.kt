package com.costa.dataBaseSQLLite

import android.provider.BaseColumns

object profileContract {
    object ProfileEntry : BaseColumns {
        const val TABLE_NAME = "profiles"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_FOLLOWER_NUMBER = "followersNumber"
        const val COLUMN_NAME_POSTS_NUMBER = "postsNumber"
    }
    const val SQL_CREATE_PROFILES_ENTRIES =
        "CREATE TABLE ${ProfileEntry.TABLE_NAME} (" +
                "${ProfileEntry.COLUMN_NAME_PROFILE_ID} TEXT PRIMARY KEY," +
                "${ProfileEntry.COLUMN_NAME_NAME} TEXT," +
                "${ProfileEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                "${ProfileEntry.COLUMN_NAME_PICTURE} TEXT," +
                "${ProfileEntry.COLUMN_NAME_FOLLOWER_NUMBER} TEXT," +
                "${ProfileEntry.COLUMN_NAME_POSTS_NUMBER} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ProfileEntry.TABLE_NAME}"
}