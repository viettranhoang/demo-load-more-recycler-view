package com.vit.demoloadmorerecyclerview.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

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
    }


}