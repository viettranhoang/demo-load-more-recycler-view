package com.vit.demoloadmorerecyclerview.ui

import android.os.Bundle
import android.util.Log
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.databinding.MainActivityBinding
import com.vit.demoloadmorerecyclerview.ui.base.BaseActivity
import com.vit.demoloadmorerecyclerview.utils.afterMeasured
import com.vit.demoloadmorerecyclerview.utils.postDelay
import com.vit.demoloadmorerecyclerview.utils.setLoadMoreListener
import kotlin.random.Random

class MainActivity : BaseActivity<MainActivityBinding>() {

    override fun getLayoutResource(): Int = R.layout.main_activity

    private val mainAdapter = MainAdapter()

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            adapter = mainAdapter

            rcv.setLoadMoreListener {
                postDelay(500) { mainAdapter.addList(fetchData()) }
            }

            layoutRefresh.setOnRefreshListener {
                getListFromServer()
            }
        }

        getListFromServer()
    }

    fun fetchData() = ArrayList<MainModel>().apply {
        if(count < 101) for (i in count+1..count+20) add(MainModel("id$i", "title $i"))
        count+=20
    }

    fun getListFromServer() {
        mainAdapter.loading()
        when(Random.nextInt(1, 4)) {
            1 -> mainAdapter.setList(fetchData())
            2 -> mainAdapter.setList(emptyList())
            3 -> mainAdapter.error()
        }
        /*var ran = Random.nextInt(1, 4)
        Log.i("aaaa", "$ran")
        when(ran) {
            1 -> postDelay(1000) { mainAdapter.setList(fetchData()) }
            2 -> postDelay(1000) { mainAdapter.setList(emptyList()) }
            3 -> postDelay(1000) { mainAdapter.error() }
        }*/
    }

}
