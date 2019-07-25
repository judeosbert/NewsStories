package com.klepto.labs.newsstories.datasources

import androidx.paging.PagedList

import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.androidx.paging.PagingRequestHelper
import com.klepto.labs.newsstories.db.DatabaseManager
import com.klepto.labs.newsstories.di.AppComponent
import com.klepto.labs.newsstories.network.services.ApiService
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsBoundaryCallback (private val mApiService:ApiService, private val mDbManager: DatabaseManager):PagedList.BoundaryCallback<Article>() {
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private var mDisposable:Disposable?=null
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL){helperCallback->

            mDisposable =mApiService.getTopHeadlines()
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                        onNext = {
                            it.articles?.let {list->

                                mDbManager.dbInstance.articleDao().insertArticles(list)
                                    helperCallback.recordSuccess()
                            }
                        },
                        onError = {
                            println(it.message)
                            helperCallback.recordFailure(it)
                        },
                        onComplete = {
                            println("Completed Initial Load")
                        }
                    )
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER){helperCallback->
            mDisposable =mApiService.getTopHeadlines()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        it.articles?.let {list->
                            mDbManager.dbInstance.articleDao().insertArticles(list)
                            helperCallback.recordSuccess()
                        }
                    },
                    onError = {
                        println(it.message)
                        helperCallback.recordFailure(it)

                    },
                    onComplete = {
                        println("Completed Initial Load")
                    }
                )
        }
    }

    fun onCleared() {
        mDisposable?.dispose()
    }
}