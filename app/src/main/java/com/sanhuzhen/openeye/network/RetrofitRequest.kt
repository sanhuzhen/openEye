package com.sanhuzhen.openeye.network

import com.sanhuzhen.openeye.api.ApiService
import com.sanhuzhen.openeye.bean.Data
import com.sanhuzhen.openeye.bean.Item
import com.sanhuzhen.openeye.bean.ListData
import com.sanhuzhen.openeye.bean.NoticeData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRequest {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://baobab.kaiyanapp.com/api/")
        //这里添加Gson的converter
        .addConverterFactory(GsonConverterFactory.create())
        //这里添加加 callAdapter把 call 转换成 Observable
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    private val apiService = retrofit.create(ApiService::class.java)

    /**
     * 对热词进行网络请求
     */
    fun getHotWord(): Observable<List<String>> {
        return apiService.getHotWord()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    /**
     * 对分类列表进行网络请求
     */
    fun getCategoryList(): Observable<ListData> {
        return apiService.getCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    /**
     * 对通知内容进行网络请求
     */
    fun getNoticeData(): Observable<NoticeData> {
        return apiService.getNoticeData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}