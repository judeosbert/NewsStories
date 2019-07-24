package com.klepto.labs.newsstories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.klepto.labs.newsstories.db.models.Article

class NewsDiffUtil:DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
    return oldItem.equals(newItem)
    }
}