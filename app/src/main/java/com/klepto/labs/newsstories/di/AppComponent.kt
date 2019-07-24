package com.klepto.labs.newsstories.di

import com.klepto.labs.newsapp.base.BaseApplication
import com.klepto.labs.newsstories.datasources.NewsBoundaryCallback
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidInjectionModule::class),(AppModule::class),(BuildersModule::class)]
)
interface AppComponent {

    fun inject(app:BaseApplication)
}