package com.joseph.marketkurly_clone.src.activity_order.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct

class OrderProductRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<OrderProductRecyclerViewHolder>() {

    private var listProduct = ArrayList<OrderProduct>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_product, parent, false)
        return OrderProductRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: OrderProductRecyclerViewHolder, position: Int) {
        holder.onBind(listProduct[position])
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    fun submitList(list: ArrayList<OrderProduct>){
        this.listProduct = list
        notifyDataSetChanged()
    }
}