package com.example.instagrammo.environment.database

import android.provider.BaseColumns

object DbProfile {

    const val TABLE_NAME = "profile"
    object ProfileEntry : BaseColumns {
        const val COLUMN_PROFILEID = "profileId"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PICTURE = "picture"
        const val COLUMN_FOLLOWERSNUMBER = "followersNumber"
        const val COLUMN_POSTSNUMBER = "postsNumber"
    }

}