package com.joseph.marketkurly_clone.src.activity_address_manager.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.interfaces.UserAddressHolderClickListener
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress

class UserAddressRecyclerAdapter(private var activity: Activity, private var listener: UserAddressHolderClickListener) :
    RecyclerView.Adapter<UserAddressRecyclerViewHolder>() {

    private var addressList = ArrayList<UserAddress>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAddressRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_address, parent, false)
        return UserAddressRecyclerViewHolder(view, activity, listener)
    }

    override fun onBindViewHolder(holder: UserAddressRecyclerViewHolder, position: Int) {
        holder.onBind(addressList[position])
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    fun submitList(list: ArrayList<UserAddress>) {
        this.addressList = list
        notifyDataSetChanged()
    }

}