package com.sanhuzhen.openeye.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.openeye.adapter.NoticeRvAdapter
import com.sanhuzhen.openeye.base.BaseFragment
import com.sanhuzhen.openeye.databinding.FragmentNoticeBinding
import com.sanhuzhen.openeye.viewmodel.NoticeFragmentViewModel

class NoticeFragment: BaseFragment<FragmentNoticeBinding>() {
    override fun initBinding(): FragmentNoticeBinding {
        return FragmentNoticeBinding.inflate(layoutInflater)
    }
    private val mViewModel by lazy {
        ViewModelProvider(requireActivity())[NoticeFragmentViewModel::class.java]
    }

    override fun initView() {
        binding.rvNotice.layoutManager = LinearLayoutManager(requireContext())
        val rv_adapter = NoticeRvAdapter(requireContext())
        mViewModel.NoticeList.observe(requireActivity()){
            Log.d("NoticeFragment","${it.messageList}")
            rv_adapter.submitList(it.messageList)
        }
        binding.rvNotice.adapter = rv_adapter
    }
}