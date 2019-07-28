package com.klepto.labs.newsstories.db.daos

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

    @Query("SELECT * FROM 'Article' order by 'publishedAt' DESC")
    fun getArticlesPaged(): DataSource.Factory<Int, Article>
}