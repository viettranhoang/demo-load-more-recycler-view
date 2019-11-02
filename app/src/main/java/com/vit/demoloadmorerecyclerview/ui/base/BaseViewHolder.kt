package com.vit.demoloadmorerecyclerview.ui.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(
    private val binding: ViewDataBinding,
    private val listener: Any?
) :
    RecyclerView.ViewHolder(binding.root) {

    open fun bindData(t: T? = null, childAdapter: BaseAdapter<*>? = null, parentItem: Any? = null) {
        with(binding) {
            t?.let { setVariable(BR.item, it) }
            listener?.let { setVariable(BR.listener, it) }
            childAdapter?.let { setVariable(BR.adapter, it) }
            parentItem?.let { setVariable(BR.parentItem, it) }

            executePendingBindings()
        }
    }
}
