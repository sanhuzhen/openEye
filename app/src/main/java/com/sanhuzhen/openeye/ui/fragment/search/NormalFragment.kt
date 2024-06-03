package com.sanhuzhen.openeye.ui.fragment.search


import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.openeye.adapter.HotWordRvAdapter
import com.sanhuzhen.openeye.base.BaseFragment
import com.sanhuzhen.openeye.base.BaseLazyFragment
import com.sanhuzhen.openeye.databinding.FragmentNormalBinding
import com.sanhuzhen.openeye.viewmodel.NormalFragmentViewModel

class NormalFragment : BaseFragment<FragmentNormalBinding>() {


    private val mViewModel by lazy {
        ViewModelProvider(this.requireActivity())[NormalFragmentViewModel::class.java]
    }


    override fun initBinding(): FragmentNormalBinding {
        return FragmentNormalBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.rvNormal.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)//设置瀑布流布局
        val rv_adapter = HotWordRvAdapter()
        mViewModel.hotWords.observe(requireActivity()) {hotWords->
            Log.d("tag","  ----------  ${hotWords}")
            rv_adapter.setData(hotWords)
        }
        binding.rvNormal.adapter = rv_adapter
    }


}