package com.klepto.labs.newsstories.di

import com.klepto.labs.newsstories.adapter.NewsListAdapter
import com.klepto.labs.newsstories.base.BaseApplication
import com.klepto.labs.newsstories.datasources.NewsBoundaryCallback
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidInjectionModule::class),(AppModule::class),(BuildersModule::class)]
)
interface AppComponent {

    fun inject(instance: BaseApplication)
    fun inject(instance: NewsBoundaryCallback)
    fun inject(instance: NewsListAdapter)
}