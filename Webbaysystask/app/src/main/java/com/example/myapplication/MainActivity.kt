package com.example.myapplication

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CustomAdapter
import com.example.myapplication.model.Products
import com.example.myapplication.retrofit.ProductService
import com.example.myapplication.retrofit.ProductService.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

     var rec_view : RecyclerView? = null

    private var adapter: CustomAdapter? = null
    var progressDoalog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDoalog = ProgressDialog(this@MainActivity)
        progressDoalog!!.setMessage("Loading....")
        progressDoalog!!.show()


        val service = retrofitInstance!!.create(
            ProductService.GetDataService::class.java
        )
        val call = service.allProducts
        call!!.enqueue(object : Callback<List<Products?>?> {
            override fun onResponse(
                call: Call<List<Products?>?>,
                response: Response<List<Products?>?>
            ) {
                progressDoalog!!.dismiss()
                generateDataList(response.body())
            }

            override fun onFailure(call: Call<List<Products?>?>, t: Throwable) {
                progressDoalog!!.dismiss()
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
         fun generateDataList(productList: List<Products?>?) {
            rec_view = findViewById<RecyclerView>(R.id.rec_view)
            adapter = CustomAdapter(this, productList)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
            rec_view?.layoutManager = layoutManager
            rec_view?.adapter = adapter
        }
}
