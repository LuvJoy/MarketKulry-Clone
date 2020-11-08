package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact

class ProductRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<ProductRecyclerViewHolder>() {

    private var productList = ArrayList<ProductCompact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ProductRecyclerViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun submitList(list: ArrayList<ProductCompact>){
        productList = list
        notifyDataSetChanged()
    }

}
