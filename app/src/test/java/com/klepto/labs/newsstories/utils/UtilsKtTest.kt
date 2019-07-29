package com.klepto.labs.newsstories.utils

import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import org.hamcrest.CoreMatchers.`is` as Is

@RunWith(MockitoJUnitRunner::class)
class UtilsKtTest {
    private lateinit var sdf:SimpleDateFormat

    @Before
    fun setUp() {
        sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getRelativeTimeString_Empty(){
        var parameter = ""
        var result = getRelativeTimeString(parameter)
        assertThat(result,Is(""))
    }


}