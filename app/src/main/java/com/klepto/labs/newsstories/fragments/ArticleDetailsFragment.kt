package com.klepto.labs.newsstories.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.klepto.labs.newsstories.widgets.toast
import kotlinx.android.synthetic.main.fragment_article_details.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
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

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            url.toast()
            view?.webview?.loadUrl(url)
        } else {
            view?.webview?.loadUrl("about:blank")
        }
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


}
