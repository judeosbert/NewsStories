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
import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.db.models.Article

class NewsListAdapter:PagedListAdapter<Article,NewsListAdapter.NewsItemViewHolder>(NewsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_rv_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            with(holder){
                authorName.text = it.author
                description.text = it.description
                title.text = it.title
                title.text = it.title

                    Glide.with(image).load(it.urlToImage)
                        .into(image)


            }
        }
    }

    inner class NewsItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        val authorName = view.findViewById<TextView>(R.id.authorName)
        val description = view.findViewById<TextView>(R.id.descriptionText)
        val title = view.findViewById<TextView>(R.id.titleText)
        val timestamp = view.findViewById<TextView>(R.id.timestampText)
        val image = view.findViewById<ImageView>(R.id.bgImage)
    }
}