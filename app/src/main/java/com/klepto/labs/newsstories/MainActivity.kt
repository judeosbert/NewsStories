package com.klepto.labs.newsstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.klepto.labs.newsstories.adapter.NewsListAdapter
import com.klepto.labs.newsstories.viewmodels.NewsViewModel
import com.klepto.labs.newsstories.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mViewModel:NewsViewModel
    private val mAdapter = NewsListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        setupObservers()
        initView()
    }

    private fun setupObservers(){
        mViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(NewsViewModel::class.java)

        mViewModel.getArticlesLiveData().observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

    private fun initView(){
        newsRvList.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        newsRvList.itemAnimator = DefaultItemAnimator()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(newsRvList)
        newsRvList.adapter = mAdapter

    }
}
