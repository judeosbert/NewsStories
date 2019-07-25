package com.klepto.labs.newsstories.di

import com.klepto.labs.newsstories.MainActivity
import com.klepto.labs.newsstories.datasources.NewsBoundaryCallback
import com.klepto.labs.newsstories.fragments.ArticleListFragment
import com.klepto.labs.newsstories.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity():MainActivity
    @ContributesAndroidInjector
    abstract fun contributeArticleFragment(): ArticleListFragment
}


