package com.klepto.labs.newsstories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.core.adview.interfaces.AdLoadCallback
import com.greedygame.core.adview.modals.AdRequestErrors
import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.base.BaseApplication
import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.db.models.ItemType
import com.klepto.labs.newsstories.utils.CacheManager
import com.klepto.labs.newsstories.utils.getRelativeTimeString
import kotlinx.android.synthetic.main.news_rv_ad_item.view.*
import kotlinx.android.synthetic.main.news_rv_item.view.*
import javax.inject.Inject

class NewsListAdapter :
    PagedListAdapter<Article, NewsListAdapter.NewsItemViewHolder>(NewsDiffUtil()) {

    @Inject
    lateinit var mCacheManager: CacheManager
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        BaseApplication.appComponent?.inject(this)
        mContext = parent.context
        return NewsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = getItem(position)
        val listener = AdLoadListener(position)
        holder.bind(listener, if(position%2 ==0) ItemType.AD else ItemType.CONTENT, article)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position%2 ) {
            0 -> R.layout.news_rv_ad_item
            else -> R.layout.news_rv_item
        }
    }

    inner class NewsItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listener: AdLoadListener, type: ItemType?, article: Article?) {
            if (type == ItemType.AD) {
                view.adUnit.loadAd(listener)
            } else {
                view.descriptionText.text = article?.description
                view.titleText.text = article?.title
                view.bgImage.setImageDrawable(mContext.getDrawable(R.drawable.placeholder))
                mCacheManager.setImage(
                    article?.urlToImage ?: "",
                    article?.urlToImage ?: "",
                    mContext,
                    view.bgImage
                )
                val res = mContext.resources
                val source = article?.source?.source_name ?: ""
                val relativeTimestamp: String = getRelativeTimeString(article?.publishedAt ?: "")
                view.timestampText.text =
                    res.getString(R.string.swipe_more_hint, source, relativeTimestamp)
            }
        }
    }

    fun getItemUrl(position: Int): String {
        return getItem(position)?.url ?: ""
    }

    inner class AdLoadListener(private val position: Int) : AdLoadCallback {
        override fun onAdLoadFailed(cause: AdRequestErrors) {

        }

        override fun onAdLoaded() {
        }

        override fun onReadyForRefresh() {
        }

        override fun onUiiClosed() {
        }

        override fun onUiiOpened() {
        }

    }
}