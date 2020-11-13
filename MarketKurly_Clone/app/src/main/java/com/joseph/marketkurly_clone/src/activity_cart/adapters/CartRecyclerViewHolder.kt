package com.joseph.marketkurly_clone.src.activity_cart.adapters

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_cart.interfaces.ActivityClickListener
import com.joseph.marketkurly_clone.src.activity_cart.interfaces.ViewHolderClickListener
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.util.*

class CartRecyclerViewHolder(
    private var itemView: View,
    private var adapterName: String,
    private var clickListener: ViewHolderClickListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvCost = itemView.findViewById<TextView>(R.id.item_cart_cost_textview)
    private var tvSaleCost = itemView.findViewById<TextView>(R.id.item_cart_sale_cost_textview)
    private var tvName = itemView.findViewById<TextView>(R.id.item_cart_name_textview)
    private var tvOption = itemView.findViewById<TextView>(R.id.item_cart_option_name_textview)
    private var btnPlus = itemView.findViewById<ImageView>(R.id.item_cart_plus_button)
    private var btnMinus = itemView.findViewById<ImageView>(R.id.item_cart_minus_button)
    private var imgProduct = itemView.findViewById<ImageView>(R.id.item_cart_product_imageview)
    private var btnRemove = itemView.findViewById<ImageView>(R.id.item_cart_remove_button)
    private var tvUnitCount = itemView.findViewById<TextView>(R.id.item_cart_unit_count_textview)
    private var cbSelect = itemView.findViewById<CheckBox>(R.id.item_cart_select_checkbox)

    private lateinit var holderItem: Cart
    private var saleProduct: Boolean = false

    fun onBind(itemData: Cart) {
        holderItem = itemData

        holderItem.apply {
            tvCost.text = String.format((this.discountCost*this.count).toDecimalFormat()+"원")
            tvUnitCount.text = this.count.toString()

            if(this.cost == this.discountCost) {
                saleProduct = false
                tvSaleCost.setInVisible()
            } else {
                saleProduct = true
                tvSaleCost.text = String.format((this.cost*this.count).toDecimalFormat()+"원")
                tvSaleCost.setStrikeThru()
                tvSaleCost.setVisible()
            }
        }
        if(itemData.productName == "" || itemData.productName == null) {
            tvName.text = itemData.optionName
            tvOption.setGone()
        } else {
            tvName.text = itemData.productName
            tvOption.text = itemData.optionName
            tvOption.setVisible()
        }

        Glide.with(itemView.context)
            .load(holderItem.thumbnailUrl)
            .into(imgProduct)
        
        cbSelect.isChecked = true

        cbSelect.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d(TAG, "[CartRecyclerViewHolder] - onBind() : $isChecked")
            clickListener.onCheckBoxClicked(isChecked, adapterPosition, adapterName)
        }
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnRemove.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            btnPlus -> {
                if (holderItem.count < 100) {
                    holderItem.count += 1
                    tvUnitCount.text = holderItem.count.toString()

                    if(saleProduct) {
                        tvCost.text = String.format((holderItem.discountCost*holderItem.count).toDecimalFormat()+"원")
                        tvSaleCost.text = String.format((holderItem.cost*holderItem.count).toDecimalFormat()+"원")
                    } else {
                        tvCost.text = String.format((holderItem.discountCost*holderItem.count).toDecimalFormat()+"원")
                    }

                    clickListener.onPlusButtonClicked(holderItem.count, adapterPosition, adapterName)
                }
            }
            btnRemove -> {
                clickListener.onRemoveButtonClicked(holderItem, adapterPosition, adapterName)
            }
            btnMinus -> {
                if (holderItem.count > 1) {
                    holderItem.count -= 1
                    tvUnitCount.text = holderItem.count.toString()

                    if(saleProduct) {
                        tvCost.text = String.format((holderItem.discountCost*holderItem.count).toDecimalFormat()+"원")
                        tvSaleCost.text = String.format((holderItem.cost*holderItem.count).toDecimalFormat()+"원")
                    } else {
                        tvCost.text = String.format((holderItem.discountCost*holderItem.count).toDecimalFormat()+"원")
                    }

                    clickListener.onMinusButtonClicked(holderItem.count, adapterPosition, adapterName)
                }



            }
        }
    }

}


