package com.klepto.labs.newsapp.base

import android.app.Activity
import android.app.Application
import android.content.Context
import com.klepto.labs.newsstories.di.AppModule
import com.klepto.labs.newsstories.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication:Application(),HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>  = activityInjector

    init {
        instance = this
    }
    companion object{
        private var instance: BaseApplication? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }
}