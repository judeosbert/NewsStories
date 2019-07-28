package com.klepto.labs.newsstories.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.klepto.labs.newsstories.db.daos.ArticleDao
import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.db.models.Source

@Database(entities = [Article::class, Source::class],version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}