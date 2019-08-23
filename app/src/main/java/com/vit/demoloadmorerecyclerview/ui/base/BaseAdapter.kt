package com.vit.demoloadmorerecyclerview.ui.base


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.ui.MainModel
import java.util.concurrent.Executors

abstract class BaseAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, BaseViewHolder<T>>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    @LayoutRes
    @NonNull
    abstract fun getLayoutResource(position: Int): Int

    open fun getListener(): Any? = null

    open fun getChildAdapter(item: T): Any? = null

    open var isLoadMore = false

    val state = ObservableInt().apply {
        set(INIT_STATE)
    }

    private val childAdapters = ArrayList<Any>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(viewGroup.context), viewType, viewGroup, false)
        return BaseViewHolder(binding, getListener())
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if (position >= currentList.size) {
            holder.bindData()
            return
        }
        val childAdapter = getChildAdapter(getItem(position))
        childAdapter?.let { childAdapters.add(it) }
        holder.bindData(getItem(position), childAdapter = childAdapter)
    }

    override fun getItemCount(): Int {
        return if(isLoadMore) currentList.size + 1 else currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) R.layout.item_load_more
        else getLayoutResource(position)
    }

    fun setList(list: List<T>) {
        if (list.isEmpty()) {
            isLoadMore = false
            notifyItemChanged(currentList.size)
        } else {
            isLoadMore = true
        }
        submitList(list)
        state.apply {
            set(list.size)
            notifyChange()
        }
    }

    fun addList(list: List<T>) {
        if (list.isEmpty()) {
            isLoadMore = false
            notifyItemChanged(currentList.size)
            return
        }
        val l = currentList.toMutableList()
        l.addAll(list)
        setList(l)
    }

    fun loading() {
        state.apply {
            set(LOADING_STATE)
            notifyChange()
        }
    }

    fun error() {
        isLoadMore = false
        notifyItemChanged(currentList.size)
        state.apply {
            set(ERROR_STATE)
            notifyChange()
        }
    }

    @Nullable
    fun getItemByPosition(position: Int): T? = if (position >= itemCount) null else currentList[position]

    @Nullable
    fun getChildAdapterByPosition(position: Int): Any? = if (position >= itemCount) null else childAdapters[position]

    companion object {
        const val EMPTY_STATE = 0
        const val INIT_STATE = -1
        const val LOADING_STATE = -2
        const val ERROR_STATE = -3
    }
}
