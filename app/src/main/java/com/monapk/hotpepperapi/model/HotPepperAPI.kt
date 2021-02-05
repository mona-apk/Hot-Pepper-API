package com.monapk.hotpepperapi.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HotPepperAPI {
    @GET("hotpepper/gourmet/v1/")
    fun getStoreInfo(@QueryMap params: Map<String, String>) : Call<StoreInfo>
}
