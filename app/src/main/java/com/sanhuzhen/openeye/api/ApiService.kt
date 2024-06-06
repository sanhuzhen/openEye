package com.sanhuzhen.openeye.api

import com.sanhuzhen.openeye.bean.ListData
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

interface ApiService {
    /**
     * 获取热门搜索词
     */
    @GET("v3/queries/hot")
    fun getHotWord(): Observable<List<String>>
    /**
     * 获取分类列表
     */
    @GET("v5/category/list")
    fun getCategoryList(): Observable<ListData>
}