package com.joseph.marketkurly_clone.src.activity_order_address.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine

class OrderAddressRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<OrderAddressRecyclerViewHolder>() {

    private var listAddress = ArrayList<OrderAddressCombine>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderAddressRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_address, parent, false)
        return OrderAddressRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: OrderAddressRecyclerViewHolder, position: Int) {
        holder.onBind(listAddress[position])
    }

    override fun getItemCount(): Int {
        return listAddress.size
    }

    fun submitList(list: ArrayList<OrderAddressCombine>) {
        this.listAddress = list
        notifyDataSetChanged()
    }

}