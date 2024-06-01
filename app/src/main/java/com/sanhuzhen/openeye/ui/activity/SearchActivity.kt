package com.sanhuzhen.openeye.ui.activity

import com.sanhuzhen.openeye.base.BaseActivity
import com.sanhuzhen.openeye.databinding.ActivitySearchBinding

/**
 * @author sanhuzhen
 * @since 2024/6/1
 * @description 搜索页
 */
class SearchActivity: BaseActivity<ActivitySearchBinding>() {
    override fun initView() {
        initEvent()
    }

    override fun initBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }
    private fun initEvent() {

    }

}