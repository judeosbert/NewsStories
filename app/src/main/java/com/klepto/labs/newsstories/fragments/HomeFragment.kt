package com.klepto.labs.newsstories.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.adapter.NewsFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()
    }



    private fun initView(){
        val adapter = NewsFragmentPagerAdapter(childFragmentManager)
        viewpager.adapter = adapter
        viewpager.currentItem  = 0


    }
}
