package com.klepto.labs.newsstories.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
data class Article (
    @SerializedName("source") @Embedded val source : Source?,
    @SerializedName("author") val author : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("url") val url : String?,
    @SerializedName("urlToImage") val urlToImage : String?,
    @PrimaryKey
    @SerializedName("publishedAt") val publishedAt : String,
    @SerializedName("content") val content : String?
)