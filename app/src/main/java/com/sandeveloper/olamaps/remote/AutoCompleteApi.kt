package com.sandeveloper.olamaps.remote

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.sandeveloper.olamaps.model.response.autocompletesearch.OlaSearchAutoCompleteResponse
import com.sandeveloper.olamaps.util.DataConstants.MAP_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AutoCompleteApi {

    @GET("places/v1/autocomplete")
    suspend fun getAutoCompleteSearchResult(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("strictbounds") strictBounds: Boolean,
        @Query("input") search: String,
        @Query("api_key") apiKey: String
    ): OlaSearchAutoCompleteResponse

}


object AutoCompleteRetrofitClient {
    private val builder = OkHttpClient.Builder().addInterceptor(OkHttpProfilerInterceptor())
    private val client = builder.build()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(MAP_BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val apiService: AutoCompleteApi = retrofitBuilder.create(AutoCompleteApi::class.java)
}