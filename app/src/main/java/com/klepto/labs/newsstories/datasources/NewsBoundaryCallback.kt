package com.klepto.labs.newsstories.datasources

import android.util.Log
import androidx.paging.PagedList
import com.klepto.labs.newsstories.base.BaseApplication

import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.androidx.paging.PagingRequestHelper
import com.klepto.labs.newsstories.db.DatabaseManager
import com.klepto.labs.newsstories.network.services.ApiService
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsBoundaryCallback (private val mApiService:ApiService, private val mDbManager: DatabaseManager,private val mSharedPrefManager: SharedPrefManager):PagedList.BoundaryCallback<Article>() {
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private var mDisposable:Disposable?=null

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL){helperCallback->
            mDisposable =mApiService.getTopHeadlines(pageNumber =  mSharedPrefManager.nextPageToFetch)
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                        onNext = {
                            it.articles?.let {list->
                                mSharedPrefManager.nextPageToFetch += 1
                                mDbManager.dbInstance.articleDao().insertArticles(list)
                                    helperCallback.recordSuccess()
                            }
                        },
                        onError = {
                            println(it.message)
                            helperCallback.recordFailure(it)
                        },
                        onComplete = {
                        }
                    )
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER){helperCallback->
            mDisposable =mApiService.getTopHeadlines(pageNumber =  mSharedPrefManager.nextPageToFetch)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {

                        it.articles?.let {list->
                            mSharedPrefManager.nextPageToFetch += 1
                            mDbManager.dbInstance.articleDao().insertArticles(list)
                            helperCallback.recordSuccess()
                        }
                    },
                    onError = {
                        println(it.message)
                        helperCallback.recordFailure(it)

                    },
                    onComplete = {
                    }
                )
        }
    }

    fun onCleared() {
        mDisposable?.dispose()
    }
}