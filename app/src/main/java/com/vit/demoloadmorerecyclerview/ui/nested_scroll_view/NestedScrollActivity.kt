package com.vit.demoloadmorerecyclerview.ui.nested_scroll_view

import android.os.Bundle
import android.util.Log
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.databinding.NestedScrollActivityBinding
import com.vit.demoloadmorerecyclerview.ui.MainAdapter
import com.vit.demoloadmorerecyclerview.ui.MainModel
import com.vit.demoloadmorerecyclerview.ui.base.BaseActivity
import com.vit.demoloadmorerecyclerview.utils.EndlessParentScrollListener
import com.vit.demoloadmorerecyclerview.utils.postDelay

class NestedScrollActivity : BaseActivity<NestedScrollActivityBinding>() {

    override fun getLayoutResource(): Int = R.layout.nested_scroll_activity

    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            adapter = mainAdapter

            //loadmore with nestScrollView
            viewScroll.setOnScrollChangeListener(object : EndlessParentScrollListener(rcv.layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    Log.i("Mainnn", "loadmore")
                    postDelay(500) { mainAdapter.addList(fetchData(totalItemsCount)) }
                }
            })


            layoutRefresh.setOnRefreshListener {
                getListFromServer()
                mainAdapter.isLoadMore = true
            }
        }

        getListFromServer()
    }

    fun fetchData(offset: Int = 0) = ArrayList<MainModel>().apply {
        if(offset < LIST_SIZE) for (i in offset + 1..offset + LOAD_MORE_LIMIT) add(MainModel("id$i", "title $i"))
    }

    fun getListFromServer() {
        mainAdapter.loading()
        postDelay(1000) { mainAdapter.setList(fetchData(0)) }
//        when (Random.nextInt(1, 4)) {
//            1 -> postDelay(1000) { mainAdapter.setList(fetchData(0)) }
//            2 -> postDelay(1000) { mainAdapter.setList(emptyList()) }
//            3 -> postDelay(1000) { mainAdapter.error() }
//        }
    }

    companion object {
        private const val LIST_SIZE = 100
        private const val LOAD_MORE_LIMIT = 12
    }
}
