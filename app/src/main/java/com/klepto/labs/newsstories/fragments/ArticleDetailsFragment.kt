package com.klepto.labs.newsstories.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_article_details.*
import kotlinx.android.synthetic.main.fragment_article_details.view.*
import java.net.URL


class ArticleDetailsFragment : Fragment() {

    var url:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(com.klepto.labs.newsstories.R.layout.fragment_article_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        backImageView.setOnClickListener {
            activity?.onBackPressed()
        }
        webview.webChromeClient = CustomWebChromeClient()
        webview.webViewClient =  CustomWebViewClient()
        webview.settings.javaScriptEnabled = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            view?.baseurl?.text = getBaseUrlFrom(url)
            view?.webview?.loadUrl(url)
        }
        else{
            view?.webview?.loadUrl("about:blank")
            view?.baseurl?.text = ""
        }
    }

    private fun getBaseUrlFrom(url:String):String{
        val completeUrl = URL(url)
        return completeUrl.host
    }
    companion object{
        private var instance:ArticleDetailsFragment? = null
        fun getInstance():ArticleDetailsFragment{
            if(instance == null){
                instance = ArticleDetailsFragment()
            }
            return instance as ArticleDetailsFragment
        }
    }

    inner class CustomWebChromeClient:WebChromeClient(){


        override fun onProgressChanged(webView: WebView?, newProgress: Int) {
            view?.progressBar?.progress = newProgress
        }
    }
    inner class CustomWebViewClient:WebViewClient(){
        override fun onPageFinished(webView: WebView?, url: String?) {
            view?.progressBar?.progress = 0
        }
    }
}

