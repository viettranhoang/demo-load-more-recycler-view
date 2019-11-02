package com.vit.demoloadmorerecyclerview.utils

import com.vit.demoloadmorerecyclerview.ui.MainModel

object Utils {

    fun getDataRandom(offset: Int = 0, limit: Int = 50) = ArrayList<MainModel>().apply {
        if(offset < 300) for (i in offset + 1..offset + limit) add(MainModel("id$i", "title $i"))
    }
}