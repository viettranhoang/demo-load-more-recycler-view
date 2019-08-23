package com.vit.demoloadmorerecyclerview.utils

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline fun <reified T : LinearLayoutManager> RecyclerView.setLoadMoreListener(crossinline block: (offset: Int) -> Unit) {
    addOnScrollListener(object: EndlessRecyclerViewScrollListener(layoutManager as T) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            block(totalItemsCount)
        }
    })
}

inline fun postDelay(time: Long = 0, crossinline block: () -> Unit) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({ block() }, time)
}

