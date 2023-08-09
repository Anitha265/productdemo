package com.example.myapplication.retrofit

import com.example.myapplication.model.Products
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ProductService {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://fakestoreapi.com"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    interface GetDataService {
        @get:GET("/products")
        val allProducts: Call<List<Products?>?>?
    }
}
