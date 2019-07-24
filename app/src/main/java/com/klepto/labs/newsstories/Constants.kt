package com.klepto.labs.newsstories

const val NEWS_API_KEY = "8900032e3d4e463fa9e00ff57e13d2a8"
const val BASE_URL = "https://newsapi.org/v2/"


enum class ApiState{
    LOADING,SUCCESS,FAILURE
}
data class NetworkState(val apiState: ApiState,val message:String = "")