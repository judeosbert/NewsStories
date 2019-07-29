package com.klepto.labs.newsstories.network.models

import com.google.gson.annotations.SerializedName
import com.klepto.labs.newsstories.db.models.Article

data class BaseResponse(@SerializedName("status")val status: String?,
                        @SerializedName("totalResults")val totalResults: Int?,
                        @SerializedName("articles")val articles: MutableList<Article>?, @SerializedName("code")val code:String?,
                        @SerializedName("message")val message:String?)
