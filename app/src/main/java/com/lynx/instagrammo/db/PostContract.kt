package com.lynx.instagrammo.db

import android.provider.BaseColumns

object PostContract {
    object PostEntry : BaseColumns {

        const val TABLE_NAME = "Posts_Table"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_POST_ID = "postId"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_UPLOAD_TIME = "uploadTime"

    }

    const val SQL_CREATE_ENTRIES = "CREATE TABLE ${PostEntry.TABLE_NAME} (" +
            "${PostEntry.COLUMN_NAME_POST_ID} TEXT PRIMARY KEY," +
            "${PostEntry.COLUMN_NAME_PROFILE_ID} TEXT ," +
            "${PostEntry.COLUMN_NAME_TITLE} TEXT," +
            "${PostEntry.COLUMN_NAME_PICTURE} TEXT," +
            "${PostEntry.COLUMN_NAME_UPLOAD_TIME} TEXT )"



    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PostEntry.TABLE_NAME}"
}