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


class CustomAdapter(private val context: Context, private val productList: List<Products?>?) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var textViewName: TextView? = null
        var textViewCategory : TextView? = null
        var imageView: ImageView? = null

        init {

            textViewName = mView.findViewById(R.id.title)
            textViewCategory = mView.findViewById(R.id.category)
            imageView = mView.findViewById<ImageView>(R.id.imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val user = productList?.get(position)

        holder.textViewName?.text = user?.title
            holder.textViewCategory?.text = user?.category

        Glide.with(context)
            .load(user?.image)
            .into(holder.imageView!!)

    }

    override fun getItemCount(): Int {
        return productList!!.size
    }
}