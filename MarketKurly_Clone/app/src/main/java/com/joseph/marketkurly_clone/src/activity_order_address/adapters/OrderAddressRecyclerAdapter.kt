package com.joseph.marketkurly_clone.src.activity_order_address.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine
import com.joseph.marketkurly_clone.src.activity_order_address_edit.model.OrderAddressEdit

class OrderAddressRecyclerAdapter(private var activity: Activity) :
    RecyclerView.Adapter<OrderAddressRecyclerViewHolder>() {

    var listAddress = ArrayList<OrderAddressCombine>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderAddressRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_address, parent, false)
        return OrderAddressRecyclerViewHolder(view, activity)
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

    fun updataItem(addressId: Int, addressData: OrderAddressEdit) {

        var index = 0
        for(i in listAddress.indices) {
            if(listAddress[i].addressId == addressId) index = i
        }

        listAddress[index].apply {
            address = addressData.address
            delivery = addressData.delivery
            phoneNumber = addressData.phoneNumber
            place = addressData.placeInfo
            name = addressData.name
        }

        notifyItemChanged(index)

    }

}