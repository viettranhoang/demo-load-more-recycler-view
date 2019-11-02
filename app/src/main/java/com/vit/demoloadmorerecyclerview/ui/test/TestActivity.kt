package com.vit.demoloadmorerecyclerview.ui.test

import android.os.Bundle
import android.util.Log
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.databinding.MainActivityBinding
import com.vit.demoloadmorerecyclerview.ui.MainAdapter
import com.vit.demoloadmorerecyclerview.ui.MainModel
import com.vit.demoloadmorerecyclerview.ui.base.BaseActivity
import com.vit.demoloadmorerecyclerview.utils.postDelay

class TestActivity : BaseActivity<MainActivityBinding>() {

    override fun getLayoutResource(): Int = R.layout.main_activity

    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            adapter = mainAdapter



            layoutRefresh.setOnRefreshListener {
                getListFromServer()
                mainAdapter.isLoadMore = true
            }
        }

        getListFromServer()
    }

    fun fetchData(offset: Int = 0) = ArrayList<MainModel>().apply {
        mainAdapter.loading()
        Log.i(TAG, "offset $offset")
        if (offset < LIST_SIZE) for (i in offset + 1..offset + LOAD_MORE_LIMIT)
            add(MainModel("id$i", "title $i"))
    }

    fun getListFromServer() {
        postDelay(1000) { mainAdapter.setList(fetchData()) }
//        when (Random.nextInt(1, 4)) {
//            1 -> postDelay(1000) { mainAdapter.setList(fetchData(0)) }
//            2 -> postDelay(1000) { mainAdapter.setList(emptyList()) }
//            3 -> postDelay(1000) { mainAdapter.error() }
//        }
    }

    companion object {
        val TAG = "Mainnn"

        private const val LIST_SIZE = 100
        private const val LOAD_MORE_LIMIT = 10
    }
}
