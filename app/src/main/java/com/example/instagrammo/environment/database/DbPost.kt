package com.example.instagrammo.environment.database

import android.provider.BaseColumns

object DbPost {

    const val TABLE_NAME = "post"
    object PostEntry : BaseColumns {
        const val COLUMN_PROFILEID = "profileId"
        const val COLUMN_POSTID = "postId"
        const val COLUMN_TITLE = "title"
        const val COLUMN_PICTURE = "picture"
        const val COLUMN_UPLOADTIME = "uploadTime"
        const val COLUMN_PROFILE = "profile"
    }


}