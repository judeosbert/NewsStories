package com.klepto.labs.newsstories.datasources

import com.klepto.labs.newsstories.base.BaseApplication
import com.klepto.labs.newsstories.SHARED_PREF_KEY_NEXT_PAGE

class SharedPrefManager(){
    private val sharedPref  = BaseApplication.applicationContext().getSharedPreferences("base_sharedpreference",0)
    private val editor = sharedPref.edit()

    var nextPageToFetch:Int = 1
    get() = sharedPref.getInt(SHARED_PREF_KEY_NEXT_PAGE,1)
    set(value) {
        field = value
        editor.putInt(SHARED_PREF_KEY_NEXT_PAGE,value).commit()
    }
}