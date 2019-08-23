package com.vit.demoloadmorerecyclerview.utils

import android.view.View
import androidx.databinding.BindingAdapter

class BindingUtils {

    companion object {
        @BindingAdapter("visibility")
        @JvmStatic
        fun setVisibility(view: View, value: Boolean) {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }
    }
}