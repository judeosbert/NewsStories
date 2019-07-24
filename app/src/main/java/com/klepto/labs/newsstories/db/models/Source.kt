package com.klepto.labs.newsstories.db.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity
data class Source (
    @PrimaryKey(autoGenerate = true)
    val index:Int,
    @SerializedName("id") val source_id : String?,
    @SerializedName("name") val source_name : String?
)