package com.vit.demoloadmorerecyclerview.ui.nested_adapter

import android.util.Log
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.ui.MainModel
import com.vit.demoloadmorerecyclerview.ui.base.BaseAdapter
import com.vit.demoloadmorerecyclerview.utils.Utils

class NestedAdapter : BaseAdapter<MainModel>(MainModel.MainDiffCallback()){

    override fun getLayoutResource(position: Int): Int = R.layout.nested_item

    override var onClickItemListener: Any? = object : MainModel.OnClickMainItemListener {
        override fun onClickMainItem(item: MainModel) {
            Log.i("onClickMainItem ", item.title)
        }
    }

    override fun getChildAdapter(item: MainModel): BaseAdapter<*>? = NestedInnerAdapter().apply {
        setList(Utils.getDataRandom().map { it.apply { title += item.id } })
    }
}