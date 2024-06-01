package com.sanhuzhen.openeye.ui.fragment

import android.content.Intent
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.openeye.adapter.ViewPaperAdapter
import com.sanhuzhen.openeye.base.BaseFragment
import com.sanhuzhen.openeye.databinding.FragmentHomeBinding
import com.sanhuzhen.openeye.ui.activity.SearchActivity
import com.sanhuzhen.openeye.ui.fragment.home.DailyFragment
import com.sanhuzhen.openeye.ui.fragment.home.RecommendFragment
import com.sanhuzhen.openeye.ui.fragment.home.FoundFragment

/**
 * @author sanhuzhen
 * @since 2024/6/1
 * @description 首页
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var mAdapter: ViewPaperAdapter
    override fun initBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        initEvent()
    }

    private fun initEvent() {
        //TabLayout和ViewPager2的联动
        mAdapter = ViewPaperAdapter(this)
        mAdapter.apply { addFragment(FoundFragment(), "发现")
        addFragment(RecommendFragment(), "推荐")
        addFragment(DailyFragment(), "日报")}
        binding.viewpagerHome.adapter = mAdapter
        TabLayoutMediator(binding.tabHome, binding.viewpagerHome) { tab, position ->
            tab.text = mAdapter.getFragmentTitle(position)
        }.attach()
        //使一打开在RecommendFragment中显示
        binding.tabHome.getTabAt(1)?.select()


        binding.searchHome.setOnClickListener {
            startActivity(Intent(this.requireContext(),SearchActivity::class.java))
        }
    }
}
