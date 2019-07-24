package com.klepto.labs.newsstories.network.services

import com.klepto.labs.newsapp.network.models.BaseResponse
import com.klepto.labs.newsstories.NEWS_API_KEY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country:String="in"
                        ,@Query("apiKey") apiKey:String = NEWS_API_KEY
                        ,@Query("p") pageNumber:Int = 1
    ):Observable<BaseResponse>
}