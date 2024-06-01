package com.sanhuzhen.openeye.ui.activity

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.sanhuzhen.openeye.R
import com.sanhuzhen.openeye.base.BaseActivity
import com.sanhuzhen.openeye.databinding.ActivityMainBinding
import com.sanhuzhen.openeye.ui.fragment.CommunityFragment
import com.sanhuzhen.openeye.ui.fragment.HomeFragment
import com.sanhuzhen.openeye.ui.fragment.MineFragment
import com.sanhuzhen.openeye.ui.fragment.NoticeFragment

/**
 * @author sanhuzhen
 * @since 2024/6/1
 * @description 实现底部导航栏
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mFragments = arrayListOf<Fragment>()
    override fun initView() {
        initEvent()
    }

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun initEvent() {
        changeFragment(HomeFragment::class.java.simpleName)
        binding.rgMain.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_home -> {
                    changeFragment(HomeFragment::class.java.simpleName)
                }

                R.id.rb_community -> {
                    changeFragment(CommunityFragment::class.java.simpleName)
                }

                R.id.rb_notice -> {
                    changeFragment(NoticeFragment::class.java.simpleName)
                }

                R.id.rb_mine -> {
                    changeFragment(MineFragment::class.java.simpleName)
                }
            }
        }
    }

    @SuppressLint("CommitTransaction")
    private fun changeFragment(tag: String) {
        hideFragment()
        val transaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(tag)

        if (fragment != null) {
            transaction.show(fragment)
        } else {
            when (tag) {
                HomeFragment::class.java.simpleName -> {
                    fragment = HomeFragment()
                }

                CommunityFragment::class.java.simpleName -> {
                    fragment = CommunityFragment()
                }

                NoticeFragment::class.java.simpleName -> {
                    fragment = NoticeFragment()
                }

                MineFragment::class.java.simpleName -> {
                    fragment = MineFragment()
                }
            }
            if (fragment != null) {
                mFragments.add(fragment)
                transaction.add(R.id.fl_main, fragment, tag)
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        for (f in mFragments) {
            transaction.hide(f)
        }
        transaction.commit()
    }
}