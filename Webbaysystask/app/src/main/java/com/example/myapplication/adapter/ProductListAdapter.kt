package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Products


class ProductListAdapter(private val context: Context, var productList : ArrayList<Products>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    fun updateProducts(newProducts: List<Products>) {
        productList.clear()
        productList.addAll(newProducts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById(R.id.title) as TextView
        val textViewEmail = itemView.findViewById(R.id.category) as TextView
        val imageView = itemView.findViewById(R.id.imgView) as ImageView

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = productList[position]
        holder.textViewName.text = user.title
        holder.textViewEmail.text = user.category
        Glide.with(context)
            .load(user.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}