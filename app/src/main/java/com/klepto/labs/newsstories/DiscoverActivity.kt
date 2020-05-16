package com.klepto.labs.newsstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.klepto.labs.newsstories.adapter.DiscoverAdapter
import kotlinx.android.synthetic.main.activity_discover.*

class DiscoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        rv.apply {
            layoutManager =
                GridLayoutManager(this@DiscoverActivity, 3, GridLayoutManager.VERTICAL, false)
            adapter = DiscoverAdapter()
        }
    }
}
