package com.sanhuzhen.openeye.ui.fragment.search


import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.openeye.adapter.HotWordRvAdapter
import com.sanhuzhen.openeye.base.BaseFragment
import com.sanhuzhen.openeye.base.BaseLazyFragment
import com.sanhuzhen.openeye.databinding.FragmentNormalBinding
import com.sanhuzhen.openeye.viewmodel.NormalFragmentViewModel

class NormalFragment : BaseLazyFragment<FragmentNormalBinding>() {


    private var HotWords: List<String> = listOf()
    private val mViewModel by lazy {
        ViewModelProvider(this.requireActivity())[NormalFragmentViewModel::class.java]
    }


    override fun onFirstVisibleToUser() {
        binding.rvNormal.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)//设置瀑布流布局
        val rv_adapter = HotWordRvAdapter(requireContext())
        mViewModel.hotWords.observe(requireActivity()) {hotWords->
            Log.d("tag","  ----------  ${hotWords}")
            HotWords = hotWords
            rv_adapter.setData(hotWords)
        }
        binding.rvNormal.adapter = rv_adapter
    }

    override fun onVisibleToUser() {
        val rv_adapter = HotWordRvAdapter(requireContext())
        if (HotWords.isNotEmpty()) {
            rv_adapter.setData(HotWords)
        }
        if (binding.rvNormal.adapter != rv_adapter) {
            binding.rvNormal.adapter = rv_adapter
        }
    }

    override fun onInvisibleToUser() {

    }


    override fun initBinding(): FragmentNormalBinding {
        return FragmentNormalBinding.inflate(layoutInflater)
    }




}