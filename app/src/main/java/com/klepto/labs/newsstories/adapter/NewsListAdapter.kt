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
                description.text = cleanContent(it.content)
                title.text = it.title
                    Glide.with(image).load(it.urlToImage)
                        .apply(RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                        .into(image)
                timestamp.text = "swipe left for more at ${article.source?.source_name}/ 5 mins ago"
            }

        }
    }

    private fun cleanContent(content:String?):String{
        content?.let {
            return it.replace("([+[0-9]+ chars])","")
        }
        return ""
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