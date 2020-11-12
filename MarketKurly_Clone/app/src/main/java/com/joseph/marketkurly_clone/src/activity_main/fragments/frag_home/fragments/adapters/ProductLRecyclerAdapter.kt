package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL

class ProductLRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<ProductLRecyclerViewHolder>() {

    private var productList = ArrayList<ProductCompactL>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductLRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_grid, parent, false)
        return ProductLRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ProductLRecyclerViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun submitList(list: ArrayList<ProductCompactL>) {
        this.productList = list
        notifyDataSetChanged()
    }
}