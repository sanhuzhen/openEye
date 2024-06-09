package com.sanhuzhen.openeye.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sanhuzhen.openeye.ui.fragment.HomeFragment

class ViewPaperAdapter(fa: Fragment): FragmentStateAdapter(fa) {
    private val fragmentList = arrayListOf<Fragment>()
    private val fragmentTitleList = arrayListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getFragmentTitle(position: Int): String {
        return fragmentTitleList[position]
    }


}