package com.klepto.labs.newsstories.datasources

import androidx.paging.DataSource
import com.klepto.labs.newsstories.base.BaseRepository
import com.klepto.labs.newsstories.db.DatabaseManager
import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.network.services.ApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val mApiService: ApiService, private val mDbManager: DatabaseManager,private val mSharedPrefManager: SharedPrefManager):
    BaseRepository() {
    private val newsBoundaryCallback = NewsBoundaryCallback(mApiService,mDbManager,mSharedPrefManager)
    fun getNewsBoundaryCallback() = newsBoundaryCallback
    fun getArticlesDataFactory(): DataSource.Factory<Int, Article> {
        return mDbManager.dbInstance.articleDao().getArticlesPaged()
    }

    fun onCleared() {
        newsBoundaryCallback.onCleared()
    }

}