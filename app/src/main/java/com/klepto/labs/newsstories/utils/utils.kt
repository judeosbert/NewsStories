package com.klepto.labs.newsstories.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

fun getRelativeTimeString(publishedTime:String):String{
    if(publishedTime.isEmpty())
        return ""
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    try {
        var cleanedTimestamp: String = publishedTime.replace("Z","")
        cleanedTimestamp = cleanedTimestamp.replace("T"," ")
        val date = sdf.parse(cleanedTimestamp)
        return DateUtils.getRelativeTimeSpanString(date.time,System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString()
    }catch (e:Exception){
        e.printStackTrace()
        return ""
    }
    return ""
    }
