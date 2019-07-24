package com.klepto.labs.newsapp.db.daos

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klepto.labs.newsstories.db.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles:List<Article>)

    @Query("SELECT * FROM 'Article'")
    fun getArticlesPaged(): DataSource.Factory<Int, Article>
}