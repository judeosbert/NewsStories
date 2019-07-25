package com.klepto.labs.newsstories.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.adapter.NewsFragmentPagerAdapter
import com.klepto.labs.newsstories.adapter.NewsListAdapter
import com.klepto.labs.newsstories.viewmodels.NewsViewModel
import com.klepto.labs.newsstories.viewmodels.ViewModelFactory

import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()
    }



    private fun initView(){
        val adapter = NewsFragmentPagerAdapter(childFragmentManager)
        viewpager.adapter = adapter
        viewpager.currentItem  = 0


    }
}
