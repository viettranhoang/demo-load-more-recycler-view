package com.vit.demoloadmorerecyclerview.ui.nested_adapter

import android.os.Bundle
import com.vit.demoloadmorerecyclerview.R
import com.vit.demoloadmorerecyclerview.databinding.NestedActivityBinding
import com.vit.demoloadmorerecyclerview.ui.base.BaseActivity
import com.vit.demoloadmorerecyclerview.utils.Utils

class NestedActivity : BaseActivity<NestedActivityBinding>() {

    override fun getLayoutResource(): Int = R.layout.nested_activity

    private val nestedAdapter = NestedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            adapter = nestedAdapter
        }

        nestedAdapter.setList(Utils.getDataRandom())
    }
}
