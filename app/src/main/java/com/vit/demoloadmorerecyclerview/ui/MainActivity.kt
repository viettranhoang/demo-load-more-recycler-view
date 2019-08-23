package com.vit.demoloadmorerecyclerview.ui

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.databinding.MainActivityBinding
import com.vit.demoloadmorerecyclerview.ui.base.BaseActivity
import com.vit.demoloadmorerecyclerview.utils.EndlessRecyclerViewScrollListener
import com.vit.demoloadmorerecyclerview.utils.postDelay
import com.vit.demoloadmorerecyclerview.utils.setLoadMoreListener
import kotlin.random.Random

class MainActivity : BaseActivity<MainActivityBinding>() {

    override fun getLayoutResource(): Int = R.layout.main_activity

    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            adapter = mainAdapter


            rcv.setLoadMoreListener<GridLayoutManager> {
                postDelay(500) { mainAdapter.addList(fetchData(it)) }
            }

            (rcv.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (mainAdapter.getItemViewType(position)) {
                        R.layout.item_load_more -> 2
                        else -> 1
                    }
                }
            }

            layoutRefresh.setOnRefreshListener {
                getListFromServer()
            }
        }

        getListFromServer()
    }

    fun fetchData(offset: Int = 0) = ArrayList<MainModel>().apply {
        if(offset < 101) for (i in offset+1..offset+20) add(MainModel("id$i", "title $i"))
    }

    fun getListFromServer() {
        mainAdapter.loading()
        when(Random.nextInt(1, 4)) {
            1 -> postDelay(1000) { mainAdapter.setList(fetchData()) }
            2 -> postDelay(1000) { mainAdapter.setList(emptyList()) }
            3 -> postDelay(1000) { mainAdapter.error() }
        }
    }
}
