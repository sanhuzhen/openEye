package com.sanhuzhen.openeye.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * 实现懒加载的Fragment
 */
abstract class BaseLazyFragment<VB: ViewBinding>: Fragment() {
    protected lateinit var binding: VB

    //第一次显示出来（用户第一次看到时）
    protected abstract fun onFirstVisibleToUser()
    //每次显示出来时（除第一次）
    protected abstract fun onVisibleToUser()
    //每次隐藏时
    protected abstract fun onInvisibleToUser()
    //获得ViewBinding
    protected abstract fun initBinding(): VB

    private var isFirstVisible = true
    private var isPrepared = false

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding()
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        if (isFirstVisible) {
            initPrepare()
            isFirstVisible = false
        } else {
            onVisibleToUser()
        }
    }

    override fun onPause() {
        super.onPause()
        onInvisibleToUser()
    }

    private fun initPrepare() {
        if (isPrepared) {
            onFirstVisibleToUser()
        } else {
            isPrepared = true
        }
    }
}