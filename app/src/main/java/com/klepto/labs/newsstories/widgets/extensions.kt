package com.klepto.labs.newsstories.widgets

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.klepto.labs.newsstories.base.BaseApplication

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
    onSnapPositionChangeListener: OnSnapPositionChangeListener
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior,onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}

fun String.toast(){
    Toast.makeText(BaseApplication.applicationContext(),this,Toast.LENGTH_SHORT).show()
}