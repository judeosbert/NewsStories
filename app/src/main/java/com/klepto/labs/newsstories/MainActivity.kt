package com.klepto.labs.newsstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.klepto.labs.newsstories.adapter.NewsListAdapter
import com.klepto.labs.newsstories.fragments.HomeFragment
import com.klepto.labs.newsstories.viewmodels.NewsViewModel
import com.klepto.labs.newsstories.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import android.view.KeyEvent.KEYCODE_BACK



class MainActivity : AppCompatActivity(),HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment()
    }
    private fun switchFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.frameContainer,HomeFragment()).commit()
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment>  = dispatchingAndroidInjector

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KEYCODE_BACK) {
            if(viewpager.currentItem != 0) {
                viewpager.setCurrentItem(0, true)
            }
            else
                onBackPressed()
                true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onBackPressed() {
        if(viewpager.currentItem != 0)
            viewpager.setCurrentItem(0, true)
        else
         super.onBackPressed()

    }
}
