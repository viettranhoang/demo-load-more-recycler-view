package com.vit.demoloadmorerecyclerview.ui

import android.util.Log
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.ui.base.BaseAdapter

class MainAdapter : BaseAdapter<MainModel>(MainModel.MainDiffCallback()) {

    override fun getLayoutResource(position: Int): Int = R.layout.main_item

    override var isLoadMore: Boolean = true

    override fun getListener(): Any? = object : MainModel.OnClickMainItemListener {
        override fun onClickMainItem(item: MainModel) {
            Log.i("onClickMainItem ", item.title)
        }
    }
}