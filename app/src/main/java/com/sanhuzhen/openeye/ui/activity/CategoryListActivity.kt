package com.sanhuzhen.openeye.ui.activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.openeye.adapter.CategoryListRvAdapter
import com.sanhuzhen.openeye.base.BaseActivity
import com.sanhuzhen.openeye.bean.Data
import com.sanhuzhen.openeye.databinding.ActivityCategorylistBinding
import com.sanhuzhen.openeye.viewmodel.CategoryListViewModel

class CategoryListActivity: BaseActivity<ActivityCategorylistBinding>() {

    private lateinit var mCategoryLists: MutableList<Data?>

    private val mViewModel by lazy {
        ViewModelProvider(this)[CategoryListViewModel::class.java]
    }
    override fun initView() {
        initEvent()

    }

    override fun initBinding(): ActivityCategorylistBinding {
        return ActivityCategorylistBinding.inflate(layoutInflater)
    }
    @SuppressLint("CommitPrefEdits")
    private fun initEvent() {
        binding.categoryBack.setOnClickListener {
            finish()
        }
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        val rv_adapter = CategoryListRvAdapter()
        mViewModel.CategoryList.observe(this){
            Log.d("tag","--------------  ${it}")
            mCategoryLists = (it as MutableList<Data>).toMutableList()
            rv_adapter.submitList(mCategoryLists)
        }

            binding.categoryList.adapter = rv_adapter

    }
}