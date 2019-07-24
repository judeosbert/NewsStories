package com.klepto.labs.newsstories.di

import com.klepto.labs.newsstories.MainActivity
import com.klepto.labs.newsstories.datasources.NewsBoundaryCallback
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract  fun mainActivity():MainActivity
}