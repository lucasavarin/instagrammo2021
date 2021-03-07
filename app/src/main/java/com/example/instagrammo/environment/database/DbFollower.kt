package com.example.instagrammo.environment.database

import android.provider.BaseColumns

object DbFollower {

    const val TABLE_NAME = "Follower"
    object FollowerEntry : BaseColumns {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PICTURE = "picture"
    }

}