package com.sanhuzhen.openeye.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Observer
import androidx.lifecycle.ViewModel
import com.sanhuzhen.openeye.network.RetrofitRequest
import io.reactivex.rxjava3.disposables.Disposable

class NormalFragmentViewModel : ViewModel() {
    private var _hotWords: MutableLiveData<List<String>> = MutableLiveData()

    init {
        getHotWords()
    }
    val hotWords: MutableLiveData<List<String>>
        get() = _hotWords

    /**
     * 获取热词
     */
    fun getHotWords() {
        RetrofitRequest.getHotWord().subscribe(object : Observer<List<String>> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onError(e: Throwable) {
                Log.d("Error","-------------  ${e.message}")
            }
            override fun onComplete() {
            }
            override fun onNext(t: List<String>) {
                Log.d("NormalFragmentViewModel","-------------    ${t}")
                _hotWords.postValue(t)
            }
        })

    }
}
