package com.klepto.labs.newsstories

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.klepto.labs.newsstories.fragments.HomeFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(),HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment()
        fab.setOnClickListener {
            startActivity(Intent(this,DiscoverActivity::class.java))
        }
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
