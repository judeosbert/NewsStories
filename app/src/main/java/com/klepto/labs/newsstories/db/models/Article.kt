package com.klepto.labs.newsstories.db.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "article")
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("source") @Embedded val source : Source?,
    @SerializedName("author") val author : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("url") val url : String?,
    @SerializedName("urlToImage") val urlToImage : String?,
    @SerializedName("publishedAt") val publishedAt : String?,
    @SerializedName("content") val content : String?
)