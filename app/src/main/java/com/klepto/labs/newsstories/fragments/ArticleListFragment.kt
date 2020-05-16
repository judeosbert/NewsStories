package com.klepto.labs.newsstories.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.adapter.NewsListAdapter
import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.db.models.ArticleAd
import com.klepto.labs.newsstories.viewmodels.NewsViewModel
import com.klepto.labs.newsstories.viewmodels.ViewModelFactory
import com.klepto.labs.newsstories.widgets.OnSnapPositionChangeListener
import com.klepto.labs.newsstories.widgets.SnapOnScrollListener
import com.klepto.labs.newsstories.widgets.attachSnapHelperWithListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_article_list.*
import javax.inject.Inject


class ArticleListFragment : Fragment(), OnSnapPositionChangeListener {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mViewModel: NewsViewModel
    private val mAdapter = NewsListAdapter()
    val snapHelper = PagerSnapHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        setupObservers()
    }
    private fun initView(){
        newsRvList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        newsRvList.itemAnimator = DefaultItemAnimator()

        snapHelper.attachToRecyclerView(newsRvList)
        newsRvList.adapter = mAdapter
        setupItemChangeListener(newsRvList)

    }

    private fun setupItemChangeListener(newsRvList: RecyclerView) {
            newsRvList.attachSnapHelperWithListener(snapHelper,SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,this)
    }

    private fun setupObservers(){
        mViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(NewsViewModel::class.java)

        mViewModel.getArticlesLiveData().observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object{
        private var instance:ArticleListFragment? = null
        fun getInstance():ArticleListFragment{
            if(instance == null){
                instance = ArticleListFragment()
            }
            return instance as ArticleListFragment
        }
    }

    override fun onSnapPositionChange(position: Int) {
        val urlToLoad = mAdapter.getItemUrl(position)
        if(urlToLoad.isNotEmpty())
            ArticleDetailsFragment.getInstance().url = urlToLoad
    }

}
