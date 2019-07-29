package com.klepto.labs.newsstories.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.klepto.labs.newsstories.R

class CacheManager(maxSize:Int = (Runtime.getRuntime().maxMemory() / 1024).toInt()):LruCache<String,Bitmap>(maxSize) {

    fun setImage(key:String, url:String, context: Context,imageView:ImageView){
        if(this.hasBitmap(key)){
            imageView.setImageBitmap(getBitmap(key))
        }
        else{
            val options = RequestOptions().apply {
                this.placeholder(R.drawable.placeholder)
                this.downsample(DownsampleStrategy.AT_LEAST)
            }
            val requestListener = object :RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    put(key,resource)
                    imageView.setImageBitmap(resource)
                    return true
                    }

            }

            Glide.with(context)
                .asBitmap()
                .load(url)
                .addListener(requestListener)
                .apply(options)
                .into(imageView)


        }
    }


    fun hasBitmap(key:String):Boolean{
        val bitmap = this.get(key)
        return bitmap != null && !bitmap.isRecycled
    }

    fun getBitmap(key:String): Bitmap? {
        if(hasBitmap(key)){
            return get(key)
        }
        return null
    }
}