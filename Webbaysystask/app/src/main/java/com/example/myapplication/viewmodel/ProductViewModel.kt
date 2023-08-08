package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.model.Products
import com.example.myapplication.retrofit.ProductService.NetworkModule.getProductService
import kotlinx.coroutines.*


class ProductViewModel : ViewModel() {

    val productService =  getProductService()
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val products = MutableLiveData<List<Products>>()

     fun fetchUsers(){

          job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

             val response = productService.getProducts()

             withContext(Dispatchers.Main) {
                 if (response.isSuccessful) {

                     products.value = response.body()
                     Log.e("Response", products.value.toString())

                     for (i in 0 until products.value?.size!!) {
                         var title = products.value!![i].title
                         var category = products.value!![i].category
                         var img = products.value!![i].image

                         Log.e("Name", title.toString())
                         Log.e("Pub", category.toString())
                         Log.e("ImgUrl", img.toString())
                     }

                 } else {
                     onError("Error : ${response.message()} ")
                 }
             }
         }
    }
    private fun onError(message :String){
    }

    override fun onCleared(){
        super.onCleared()
        job?.cancel()
    }
}
