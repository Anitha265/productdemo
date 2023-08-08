package com.example.myapplication

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ProductListAdapter
import com.example.myapplication.model.Products
import com.example.myapplication.viewmodel.ProductViewModel


class MainActivity : AppCompatActivity() {

    var productList : ArrayList<Products> = ArrayList()
    lateinit var pAdapter: ProductListAdapter
     var rec_view : RecyclerView? = null
    var viewModel : ProductViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        viewModel?.fetchUsers()

        observeViewModel()
    }

    fun observeViewModel() {

        viewModel?.products?.observe(this, Observer { products ->

         //   products?.let {

            pAdapter = ProductListAdapter(this,productList)
            rec_view?.adapter = pAdapter
            var layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            rec_view?.layoutManager = layoutManager
            pAdapter?.notifyDataSetChanged()
        })

    }
}
