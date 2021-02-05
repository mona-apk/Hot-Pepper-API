package com.monapk.hotpepperapi.model

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class HotPepperApiService {
    private val BASEURL = "https://webservice.recruit.co.jp/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(HotPepperAPI::class.java)

    fun getRestaurant(apiKey: String, latitude:Double = 34.6413, longitude:Double = 135.5629): StoreInfo? {
        val queryParams: Map<String, String> = mapOf(
            "key" to apiKey,
            "lat" to latitude.toString(),
            "lng" to longitude.toString(),
            "range" to "5",
            "count" to "100",
            "format" to "json"
        )
        try {
            val response = api.getStoreInfo(queryParams).execute()
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.d("tag", "GET ERROR")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("tag", e.toString())
        }
        return null
    }
}
