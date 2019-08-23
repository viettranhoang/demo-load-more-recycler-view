package com.vit.demoloadmorerecyclerview.utils

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.setLoadMoreListener(crossinline block: () -> Unit) {
    addOnScrollListener(object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!canScrollVertically(RecyclerView.VERTICAL)) {
                block()
            }
        }
    })
}

inline fun postDelay(time: Long = 0, crossinline block: () -> Unit) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({ block() }, time)
}

inline fun <T : View> T.afterMeasured(crossinline block: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            block()
        }
    })
}