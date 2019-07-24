package com.klepto.labs.newsstories.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.klepto.labs.newsstories.datasources.NewsBoundaryCallback
import com.klepto.labs.newsstories.db.DatabaseManager
import com.klepto.labs.newsstories.db.models.Article
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsViewModel @Inject constructor(mDbManager:DatabaseManager, mNewsBoundaryCallback: NewsBoundaryCallback):ViewModel() {

    private val mExecutor = Executors.newFixedThreadPool(5)
    private val newsDataFactory = mDbManager.dbInstance.articleDao().getArticlesPaged()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .build()

    private val mArticlesLiveData = LivePagedListBuilder(newsDataFactory,   pagedListConfig)
        .setBoundaryCallback(mNewsBoundaryCallback)
        .setFetchExecutor(mExecutor)
        .build()

    fun getArticlesLiveData(): LiveData<PagedList<Article>> {
        return mArticlesLiveData
    }
//
}