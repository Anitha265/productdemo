package com.example.myapplication.retrofit

import com.example.myapplication.model.Products
import com.example.myapplication.model.Rating
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ProductService{

    @GET("products")
    suspend fun getProducts() : Response<List<Products>>

 object NetworkModule {

        var BASE_URL = "https://fakestoreapi.com/"

        fun getProductService(): ProductService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductService::class.java)
        }
    }
}
