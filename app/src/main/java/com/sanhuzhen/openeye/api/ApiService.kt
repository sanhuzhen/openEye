package com.sanhuzhen.openeye.api

import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

interface ApiService {
    @GET("queries/hot")
    fun getHotWord(): Observable<List<String>>


}