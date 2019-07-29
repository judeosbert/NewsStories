package com.klepto.labs.newsstories.db

import androidx.room.Room
import com.klepto.labs.newsstories.base.BaseApplication

class DatabaseManager {
    val dbInstance: AppDatabase = Room.databaseBuilder(
        BaseApplication.applicationContext()
    , AppDatabase::class.java, "news-database").build()


}