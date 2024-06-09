package com.sanhuzhen.openeye.api

import com.sanhuzhen.openeye.bean.FollowData
import com.sanhuzhen.openeye.bean.ListData
import com.sanhuzhen.openeye.bean.NoticeData
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

    /**
     * 获取关注内容
     */
    @GET("v5/community/tab/follow")
    fun getFollowData(): Observable<FollowData>
    /**
     * 获取通知内容
     */
    @GET("v3/messages")
    fun getNoticeData(): Observable<NoticeData>
}