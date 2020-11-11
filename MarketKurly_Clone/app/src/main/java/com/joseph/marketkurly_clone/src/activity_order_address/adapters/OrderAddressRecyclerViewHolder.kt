package com.joseph.marketkurly_clone.src.activity_order_address.adapters

import android.content.Context
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible

class OrderAddressRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvAddress = itemView.findViewById<TextView>(R.id.item_order_address_textview)
    private var tvReceiverAddress = itemView.findViewById<TextView>(R.id.item_order_address_receiver_textview)
    private var btnEdit = itemView.findViewById<TextView>(R.id.item_order_address_edit_button)
    private var btnRemove = itemView.findViewById<TextView>(R.id.item_order_address_remove_button)
    private var badgePost = itemView.findViewById<TextView>(R.id.item_order_address_shipping_post_badge)
    private var badgeMorning = itemView.findViewById<TextView>(R.id.item_order_address_shipping_morning_badge)
    private var badgeBase = itemView.findViewById<TextView>(R.id.item_order_address_base_address_badge)
    private var radioSelect = itemView.findViewById<RadioButton>(R.id.item_order_address_checkbox)

    fun onBind(itemData: OrderAddressCombine) {

        tvAddress.text = itemData.address
        tvReceiverAddress.text = String.format("받으실 장소: ${itemData.place}\n${itemData.name}, ${itemData.phoneNumber}")

        if(itemData.delivery == "샛별배송") {
            badgeMorning.setVisible()
            badgePost.setGone()
        } else {
            badgeMorning.setGone()
            badgePost.setVisible()
        }

        if(itemData.isBasic == "Y") {
            badgeBase.setVisible()
            btnRemove.setTextColor(ContextCompat.getColor(context, R.color.text_whitegray))
        } else {
            badgeBase.setGone()
            btnRemove.setTextColor(ContextCompat.getColor(context, R.color.text_black))
        }

        if(itemData.isChosen == "Y"){
            radioSelect.isChecked = true
        } else {
            radioSelect.isChecked = false
        }
        btnEdit.setOnClickListener(this)
        btnRemove.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btnEdit -> {

            }

            btnRemove -> {

            }
        }
    }

}


