//package com.klepto.labs.newsstories.utils
//
//import android.text.format.DateUtils
//import android.text.format.DateUtils.getRelativeTimeSpanString
//import com.nhaarman.mockitokotlin2.doReturn
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.verify
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.core.Is.`is`
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.ArgumentMatchers.any
//import org.mockito.ArgumentMatchers.eq
//import org.mockito.Mockito
//import org.powermock.api.mockito.PowerMockito
//import org.powermock.core.classloader.annotations.PrepareForTest
//import org.powermock.modules.junit4.PowerMockRunner
//import java.text.SimpleDateFormat
//import org.hamcrest.CoreMatchers.`is` as Is
//
//@RunWith(PowerMockRunner::class)
//@PrepareForTest(android.text.format.DateUtils::class)
//class UtilsKtTest {
//    private lateinit var sdf:SimpleDateFormat
//    @Before
//    fun setUp() {
//        PowerMockito.mockStatic(android.text.format.DateUtils::class.java)
//        PowerMockito. `when`(android.text.format.DateUtils
//            .getRelativeTimeSpanString(any(Long::class.java), Mockito.eq(System.currentTimeMillis()),any(Long::class.java)))
//            .thenAnswer{
//                "Today"
//        }
//        sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//    }
//
//    @After
//    fun tearDown() {
//    }
//
//    @Test
//    fun getRelativeTimeString_Empty(){
//        var parameter = ""
//        var result = getRelativeTimeString(parameter)
//        assertThat(result,Is(""))
//    }
//
//    @Test
//    fun getRelativeTimeString_Today(){
//        var  parameter =sdf.format(System.currentTimeMillis())
//        val result = getRelativeTimeString(parameter)
//        assert(result.contentEquals("Today"))
//
//    }
//
//}