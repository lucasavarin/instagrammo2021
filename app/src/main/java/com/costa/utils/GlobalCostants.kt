package com.costa.utils

import com.costa.dataBaseSQLLite.DbHelper


val prefs: Prefs by lazy{
    ApplicationContext.prefs!!
}
val db: DbHelper by lazy{
    ApplicationContext.db!!
}