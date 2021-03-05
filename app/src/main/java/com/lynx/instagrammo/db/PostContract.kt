package com.lynx.instagrammo.db

import android.provider.BaseColumns

object PostContract {
    object FeedEntry : BaseColumns {

        const val TABLE_NAME = "Posts_Table"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_POST_ID = "postId"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_UPLOAD_TIME = "uploadTime"

    }

    const val SQL_CREATE_ENTRIES = "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
            "${FeedEntry.COLUMN_NAME_POST_ID} TEXT PRIMARY KEY," +
            "${FeedEntry.COLUMN_NAME_PROFILE_ID} TEXT ," +
            "${FeedEntry.COLUMN_NAME_TITLE} TEXT," +
            "${FeedEntry.COLUMN_NAME_PICTURE} TEXT," +
            "${FeedEntry.COLUMN_NAME_UPLOAD_TIME} TEXT )"



    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
}