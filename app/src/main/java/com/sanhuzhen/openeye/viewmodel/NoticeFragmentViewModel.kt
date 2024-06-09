package com.sanhuzhen.openeye.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.openeye.bean.NoticeData
import com.sanhuzhen.openeye.network.RetrofitRequest
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class NoticeFragmentViewModel: ViewModel() {
    private var _NoticeList: MutableLiveData<NoticeData> = MutableLiveData()
    init{
        getNoticeList()
    }
    val NoticeList : MutableLiveData<NoticeData>
        get() = _NoticeList

    fun getNoticeList() {
        RetrofitRequest.getNoticeData().subscribe(object : Observer<NoticeData> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onError(e: Throwable) {
                Log.d("Error","-------------  ${e.message}")
            }
            override fun onComplete() {
            }
            override fun onNext(t: NoticeData) {
                Log.d("NoticeFragmentViewModel","-------------    ${t}")
                _NoticeList.postValue(t)
            }
        })
    }
}