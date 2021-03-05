package com.example.instagrammo.environment.database

import android.provider.BaseColumns

object FeedReaderContract {
    // Le colonne delle tabelle sono ragruppate in oggetti

    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_SUBTITLE = "subtitle"
    }

}