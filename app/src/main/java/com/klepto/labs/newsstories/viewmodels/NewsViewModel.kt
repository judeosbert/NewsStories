package com.klepto.labs.newsstories.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.klepto.labs.newsstories.datasources.NewsRepository
import com.klepto.labs.newsstories.datasources.SharedPrefManager
import com.klepto.labs.newsstories.db.models.Article
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val mRepository: NewsRepository,private val mSharedPrefManager: SharedPrefManager):ViewModel() {

    private val mExecutor = Executors.newFixedThreadPool(5)
    private val newsDataFactory = mRepository.getArticlesDataFactory()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .build()

    private val mArticlesLiveData = LivePagedListBuilder(newsDataFactory,   pagedListConfig)
        .setBoundaryCallback(mRepository.getNewsBoundaryCallback())
        .setFetchExecutor(mExecutor)
        .build()

    fun getArticlesLiveData(): LiveData<PagedList<Article>> {
        return mArticlesLiveData
    }

    override fun onCleared() {
        super.onCleared()
        mRepository.onCleared()
    }

    //
}