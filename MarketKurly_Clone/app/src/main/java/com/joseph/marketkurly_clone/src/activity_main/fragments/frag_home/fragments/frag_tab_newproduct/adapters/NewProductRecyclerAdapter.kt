package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_newproduct.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact




class NewProductRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<NewProductRecyclerViewHolder>() {

    private var productList = ArrayList<ProductCompact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_grid, parent, false)
        return NewProductRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: NewProductRecyclerViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun submitList(list: ArrayList<ProductCompact>) {
        this.productList = list
        notifyDataSetChanged()
    }
}