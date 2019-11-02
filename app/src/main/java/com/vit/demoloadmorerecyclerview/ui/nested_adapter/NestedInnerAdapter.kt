package com.vit.demoloadmorerecyclerview.ui.nested_adapter

import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.ui.MainModel
import com.vit.demoloadmorerecyclerview.ui.base.BaseAdapter

class NestedInnerAdapter : BaseAdapter<MainModel>(MainModel.MainDiffCallback()){

    override fun getLayoutResource(position: Int): Int = R.layout.nested_inner_item
}