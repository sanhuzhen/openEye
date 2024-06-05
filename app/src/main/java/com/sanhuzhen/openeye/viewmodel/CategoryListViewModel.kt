package com.sanhuzhen.openeye.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.openeye.bean.Data
import com.sanhuzhen.openeye.bean.Item
import com.sanhuzhen.openeye.network.RetrofitRequest
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class CategoryListViewModel: ViewModel() {
    private var _CategoryList: MutableLiveData<List<Data>> = MutableLiveData()

    val CategoryList: MutableLiveData<List<Data>>
        get() = _CategoryList

    fun getCategoryList() {
        RetrofitRequest.getCategoryList().subscribe(object : Observer<List<Data>> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onError(e: Throwable) {
                Log.d("Error","-------------  ${e.message}")
            }
            override fun onComplete() {
            }
            override fun onNext(t: List<Data>) {
                Log.d("CategoryListViewModel","-------------    ${t}")
               _CategoryList.postValue(t)
           }
       })
   }
}