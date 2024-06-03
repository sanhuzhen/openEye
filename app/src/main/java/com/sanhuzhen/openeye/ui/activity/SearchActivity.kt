package com.sanhuzhen.openeye.ui.activity

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.sanhuzhen.openeye.R
import com.sanhuzhen.openeye.base.BaseActivity
import com.sanhuzhen.openeye.databinding.ActivitySearchBinding
import com.sanhuzhen.openeye.ui.fragment.search.AfterFragment
import com.sanhuzhen.openeye.ui.fragment.search.NormalFragment
import com.sanhuzhen.openeye.utils.ToastUtils

/**
 * @author sanhuzhen
 * @since 2024/6/1
 * @description 搜索页
 */
class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val mFragments = arrayListOf<Fragment>()
    override fun initView() {
        initEvent()
    }

    override fun initBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

    private fun initEvent() {
        binding.searchBack.setOnClickListener {
            finish()
        }
        if (binding.searchEdit.text.isEmpty()) {
            binding.searchClear.visibility = View.GONE
        }
        binding.searchEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.searchClear.visibility = View.VISIBLE
                binding.searchClear.setOnClickListener {
                    binding.searchEdit.text.clear()
                }
            }
        }
        changeFragment(NormalFragment::class.java.simpleName)
        binding.searchText.setOnClickListener {
            when (binding.searchText.text.toString()) {

                "搜索" -> {
                    if (binding.searchEdit.text.isEmpty()) {
                        ToastUtils.ShortToast(this, "请输入搜索内容")
                    } else {
                        changeFragment(AfterFragment::class.java.simpleName)
                        binding.searchText.text = "取消"
                        binding.searchEdit.isFocusable = true
                        binding.searchEdit.isFocusableInTouchMode = false
                        binding.searchEdit.setSelection(binding.searchEdit.text.length)
                    }
                }

                "取消" -> {
                    binding.searchText.text = "搜索"
                    binding.searchEdit.text.clear()
                    changeFragment(NormalFragment::class.java.simpleName)
                }
            }
        }

    }

    @SuppressLint("CommitTransaction")
    private fun changeFragment(tag: String) {
        hideFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(tag)

        if (fragment != null) {
            fragmentTransaction.show(fragment)
        } else {
            when (tag) {
                AfterFragment::class.java.simpleName -> {
                    fragment = AfterFragment()
                }

                NormalFragment::class.java.simpleName -> {
                    fragment = NormalFragment()
                }
            }
            if (fragment != null) {
                mFragments.add(fragment)
                fragmentTransaction.add(R.id.search_frame, fragment, tag)
            }
        }
        fragmentTransaction.commitAllowingStateLoss()

    }

    @SuppressLint("CommitTransaction")
    private fun hideFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        for (fragment in mFragments) {
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.commit()
    }

}