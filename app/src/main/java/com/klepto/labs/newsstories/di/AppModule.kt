package com.klepto.labs.newsstories.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.klepto.labs.newsstories.datasources.NewsBoundaryCallback
import com.klepto.labs.newsstories.datasources.NewsRepository
import com.klepto.labs.newsstories.datasources.SharedPrefManager
import com.klepto.labs.newsstories.network.ApiServiceFactory
import com.klepto.labs.newsstories.network.services.ApiService
import com.klepto.labs.newsstories.db.DatabaseManager
import com.klepto.labs.newsstories.fragments.HomeFragment
import com.klepto.labs.newsstories.utils.CacheManager
import com.klepto.labs.newsstories.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private var app:Application){

    @Provides
    @Singleton
    fun provideApplication():Application = app

    @Provides
    @Singleton
    fun provideDatabase(): DatabaseManager = DatabaseManager()

    @Provides
    @Singleton
    fun providesApiService(): ApiService = ApiServiceFactory.create()

    @Provides
    @Singleton
    fun provideViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun providesNewsRepository(mApiService: ApiService,mDbManager: DatabaseManager,sharedPrefManager: SharedPrefManager)
            :NewsRepository = NewsRepository(mApiService, mDbManager,sharedPrefManager)

    @Provides
    @Singleton
    fun providesSharedPrefenceManager() = SharedPrefManager()

    @Provides
    @Singleton
    fun provideCachemanager() = CacheManager()

}