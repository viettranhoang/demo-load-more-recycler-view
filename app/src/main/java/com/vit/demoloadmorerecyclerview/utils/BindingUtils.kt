package com.vit.demoloadmorerecyclerview.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingUtils {

    companion object {
        @BindingAdapter("visibility")
        @JvmStatic
        fun setVisibility(view: View, value: Boolean) {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }

        @BindingAdapter("gone")
        @JvmStatic
        fun setGone(view: View, value: Boolean) {
            if (value) view.visibility = View.GONE
        }

        @BindingAdapter("view_pool")
        @JvmStatic
        fun setViewPool(view: RecyclerView, value: RecyclerView.RecycledViewPool) {
            view.setRecycledViewPool(value)
        }
    }


}