package com.costa.dataBaseSQLLite

import android.provider.BaseColumns

object PostContract {

    object PostsEntry : BaseColumns {
        const val TABLE_NAME = "posts"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_POST_ID = "postId"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_UPLOADTIME = "uploadTime"
    }
         const val SQL_CREATE_POSTS_ENTRIES =
            "CREATE TABLE ${PostsEntry.TABLE_NAME} (" +
                    "${PostsEntry.COLUMN_NAME_PROFILE_ID} TEXT," +
                    "${PostsEntry.COLUMN_NAME_POST_ID} TEXT PRIMARY KEY," +
                    "${PostsEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${PostsEntry.COLUMN_NAME_PICTURE} TEXT," +
                    "${PostsEntry.COLUMN_NAME_UPLOADTIME} TEXT)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PostsEntry.TABLE_NAME}"
    }
