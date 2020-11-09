package com.joseph.marketkurly_clone.src.activity_address_manager.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_ADDRESS
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_ADDRESS_EDIT
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.interfaces.UserAddressHolderClickListener
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_edit_address.EditAddressActivity
import com.joseph.marketkurly_clone.src.activity_search_address.SearchAddressActivity
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible

class UserAddressRecyclerViewHolder(
    private var itemView: View,
    private var activity: Activity,
    private var listener: UserAddressHolderClickListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvAddress = itemView.findViewById<TextView>(R.id.item_address_textview)
    private var btnEdit = itemView.findViewById<ImageView>(R.id.item_address_edit_button)
    private var badgeDefaultAddress =
        itemView.findViewById<TextView>(R.id.item_address_default_address_badge)
    private var cbSetAddress =
        itemView.findViewById<CheckBox>(R.id.item_address_set_address_checkbox)
    private var tvShippingType =
        itemView.findViewById<TextView>(R.id.item_address_shipping_type_textview)

    private lateinit var holderData: UserAddress

    fun onBind(itemData: UserAddress) {
        holderData = itemData

        tvAddress.text = holderData.address

        if (holderData.isBasic == "Y") {
            badgeDefaultAddress.setVisible()
            cbSetAddress.isChecked = true
        } else {
            badgeDefaultAddress.setGone()
            cbSetAddress.isChecked = false
        }

        if (holderData.delivery == "샛별배송") {
            tvShippingType.setTextColor(ContextCompat.getColor(activity, R.color.kurly_purple))
        } else {
            tvShippingType.setTextColor(ContextCompat.getColor(activity, R.color.default_gray))
        }
        tvShippingType.text = holderData.delivery


        btnEdit.setOnClickListener(this)
        cbSetAddress.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }

    override fun onClick(view: View?) {
        when (view) {
            btnEdit -> {
                val bundle = Bundle()
                val intent = Intent(activity, EditAddressActivity::class.java)
                bundle.putSerializable("userAddress", holderData)
                intent.putExtra("bundle", bundle)
                activity.startActivityForResult(intent, REQUEST_CODE_ADDRESS_EDIT)
            }
        }
    }

}


