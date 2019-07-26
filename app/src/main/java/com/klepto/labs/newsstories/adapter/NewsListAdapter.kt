package com.klepto.labs.newsstories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.db.models.Article
import com.klepto.labs.newsstories.utils.getRelativeTimeString

class NewsListAdapter:PagedListAdapter<Article,NewsListAdapter.NewsItemViewHolder>(NewsDiffUtil()) {

   private lateinit var mContext:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        mContext = parent.context
        return NewsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_rv_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            with(holder){
                description.text = it.description
                title.text = it.title
                    Glide.with(image).load(it.urlToImage)
                        .apply(RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                        .into(image)
                val res = mContext.resources
                val source = it.source?.source_name?:""
                val relativeTimestamp:String = getRelativeTimeString(it.publishedAt?:"")
                timestamp.text = res.getString(R.string.swipe_more_hint,source,relativeTimestamp)
            }

        }
    }

    inner class NewsItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        val description = view.findViewById<TextView>(R.id.descriptionText)
        val title = view.findViewById<TextView>(R.id.titleText)
        val timestamp = view.findViewById<TextView>(R.id.timestampText)
        val image = view.findViewById<ImageView>(R.id.bgImage)
    }

    fun getItemUrl(position: Int):String{
        return getItem(position)?.url?:""
    }
}