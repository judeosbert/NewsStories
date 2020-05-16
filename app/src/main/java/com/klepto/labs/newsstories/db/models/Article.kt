package com.klepto.labs.newsstories.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
open class Article (
    @SerializedName("source") @Embedded val source : Source?,
    @SerializedName("author") val author : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("url") val url : String?,
    @SerializedName("urlToImage") val urlToImage : String?,
    @PrimaryKey
    @SerializedName("publishedAt") val publishedAt : String,
    @SerializedName("content") val content : String?

){
    @Ignore
     open var type: ItemType = ItemType.CONTENT
}

open class ArticleAd:Article(null,null,null,null,null,null,"",null){
    @Ignore
    override var type: ItemType = ItemType.AD
}

enum class ItemType{
    AD,CONTENT
}