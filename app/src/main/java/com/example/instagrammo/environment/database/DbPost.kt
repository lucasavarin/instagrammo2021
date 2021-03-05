package com.example.instagrammo.environment.database

import android.provider.BaseColumns

object DbPost {

    const val TABLE_NAME = "Post"
    object PostEntry : BaseColumns {
        const val COLUMN_ID = "post_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_UPLOAD = "upload_time"
        const val COLUMN_PROFILE_ID = "profile_id"
        const val COLUMN_PROFILE = "profile_bean"
        const val COLUMN_PICTURE = "picture"
    }


}