package com.sanhuzhen.openeye.ui.fragment.search

import com.sanhuzhen.openeye.base.BaseLazyFragment
import com.sanhuzhen.openeye.databinding.FragmentAfterBinding

class AfterFragment:  BaseLazyFragment<FragmentAfterBinding>() {
    override fun onFirstVisibleToUser() {

    }

    override fun onVisibleToUser() {

    }

    override fun onInvisibleToUser() {

    }

    override fun initBinding(): FragmentAfterBinding {
        return FragmentAfterBinding.inflate(layoutInflater)
    }
}