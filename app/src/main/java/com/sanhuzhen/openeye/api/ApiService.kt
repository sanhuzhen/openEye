package com.sanhuzhen.openeye.api

import com.sanhuzhen.openeye.bean.Data
import com.sanhuzhen.openeye.bean.Item
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

interface ApiService {
    /**
     * 获取热门搜索词
     */
    @GET("queries/hot")
    fun getHotWord(): Observable<List<String>>
    /**
     * 获取分类列表
     */
    @GET("category/list")
    fun getCategoryList(): Observable<List<Data>>
}