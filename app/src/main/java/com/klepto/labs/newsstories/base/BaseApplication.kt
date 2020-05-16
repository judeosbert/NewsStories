package com.klepto.labs.newsstories.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.multidex.MultiDexApplication
import com.greedygame.core.AppConfig
import com.greedygame.core.GreedyGameAds
import com.greedygame.core.interfaces.GreedyGameAdsEventsListener
import com.greedygame.core.models.InitErrors
import com.klepto.labs.newsstories.di.AppComponent
import com.klepto.labs.newsstories.di.AppModule
import com.klepto.labs.newsstories.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication:MultiDexApplication(),HasActivityInjector, GreedyGameAdsEventsListener {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>  = activityInjector

    init {
        instance = this
    }
    companion object{
        var appComponent:AppComponent?=null
        private var instance: BaseApplication? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

    }
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent?.inject(this)

        val appConfig:AppConfig = AppConfig.Builder(this)
            .withAppId("75512866")
            .build()
        GreedyGameAds.initWith(appConfig,this)

    }

    override fun onDestroyed() {
        
    }

    override fun onInitFailed(cause: InitErrors) {
        Log.d("BaseApplication","Init Failed $cause")
    }

    override fun onInitSuccess() {
        Toast.makeText(this,"Init Success",Toast.LENGTH_SHORT).show()
    }
}