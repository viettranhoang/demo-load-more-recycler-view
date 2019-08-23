package com.vit.demoloadmorerecyclerview.ui.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(
    private val binding: ViewDataBinding,
    private val listener: Any?
) :
    RecyclerView.ViewHolder(binding.root) {

    open fun bindData(t: T? = null, childAdapter: Any? = null) {
        if (t != null) binding.setVariable(BR.item, t)
        if (listener != null) {
            binding.setVariable(BR.listener, listener)
        }
        if (childAdapter != null) {
            binding.setVariable(BR.adapter, childAdapter)
        }
        binding.executePendingBindings()
    }
}
