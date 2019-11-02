package com.vit.demoloadmorerecyclerview.ui

import androidx.recyclerview.widget.DiffUtil

data class MainModel(val id: String, var title: String) {

    class MainDiffCallback : DiffUtil.ItemCallback<MainModel>() {

        override fun areItemsTheSame(oldItem: MainModel, newItem: MainModel) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MainModel, newItem: MainModel) = oldItem == newItem
    }

    interface OnClickMainItemListener{
        fun onClickMainItem(item: MainModel)
    }
}