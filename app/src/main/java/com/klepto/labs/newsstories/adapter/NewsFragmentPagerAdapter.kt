package com.klepto.labs.newsstories.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.klepto.labs.newsstories.fragments.ArticleDetailsFragment
import com.klepto.labs.newsstories.fragments.ArticleListFragment

class NewsFragmentPagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> ArticleListFragment.getInstance()
            else-> ArticleDetailsFragment.getInstance()
        }

    }

    override fun getCount(): Int  = 2

}