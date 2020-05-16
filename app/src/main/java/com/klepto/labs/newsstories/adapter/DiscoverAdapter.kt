package com.klepto.labs.newsstories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygame.core.adview.interfaces.AdLoadCallback
import com.greedygame.core.adview.modals.AdRequestErrors
import com.klepto.labs.newsstories.R
import com.klepto.labs.newsstories.db.models.ItemType
import com.klepto.labs.newsstories.network.models.BaseMenuItem
import com.klepto.labs.newsstories.network.models.DiscoverAdItem
import com.klepto.labs.newsstories.network.models.DiscoverMenuItem
import kotlinx.android.synthetic.main.news_rv_item.view.*
import kotlinx.android.synthetic.main.rv_ad_item.view.*
import kotlinx.android.synthetic.main.rv_content_item.view.*

class DiscoverAdapter: RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    val data = mutableListOf<BaseMenuItem>(
        DiscoverMenuItem("India","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverMenuItem("Travel","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverAdItem(),
        DiscoverMenuItem("Startup","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverMenuItem("International","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverMenuItem("Others","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverAdItem(),
        DiscoverMenuItem("Startup","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverMenuItem("International","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg"),
        DiscoverMenuItem("Others","https://cdn.webshopapp.com/shops/94414/files/54025310/india-flag-icon-free-download.jpg")

    )
    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view){
            fun bind(listener:AdLoadListener,item: BaseMenuItem){
                when(item.type){
                    ItemType.AD -> {
                        view.rvAdItem.loadAd(listener)
                    }
                    ItemType.CONTENT -> {
                        val menuItem = item as DiscoverMenuItem
                        Glide.with(view.context).load(item.icon).into(view.icon)
                        view.headline.text = menuItem.name
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return when(item.type){
            ItemType.CONTENT -> { R.layout.rv_content_item}
            ItemType.AD -> {R.layout.rv_ad_item}
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           val listener = AdLoadListener(position)
            holder.bind(listener,data[position])
    }

    inner class AdLoadListener(private val position:Int):AdLoadCallback{
        override fun onAdLoadFailed(cause: AdRequestErrors) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }

        override fun onAdLoaded() {
            
        }

        override fun onReadyForRefresh() {
            
        }

        override fun onUiiClosed() {
            
        }

        override fun onUiiOpened() {
            
        }

    }

}