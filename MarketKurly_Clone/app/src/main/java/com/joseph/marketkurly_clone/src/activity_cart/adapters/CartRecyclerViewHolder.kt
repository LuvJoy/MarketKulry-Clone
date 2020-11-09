package com.joseph.marketkurly_clone.src.activity_cart.adapters

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.util.toDecimalFormat

class CartRecyclerViewHolder(private var itemView: View, private var context: Context) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvCost = itemView.findViewById<TextView>(R.id.item_cart_cost_textview)
    private var tvName = itemView.findViewById<TextView>(R.id.item_cart_name_textview)
    private var tvOption = itemView.findViewById<TextView>(R.id.item_cart_option_name_textview)
    private var btnPlus = itemView.findViewById<ImageView>(R.id.item_cart_plus_button)
    private var btnMinus = itemView.findViewById<ImageView>(R.id.item_cart_minus_button)
    private var imgProduct = itemView.findViewById<ImageView>(R.id.item_cart_product_imageview)
    private var btnRemove = itemView.findViewById<ImageView>(R.id.item_cart_remove_button)
    private var tvUnitCount = itemView.findViewById<TextView>(R.id.item_cart_unit_count_textview)
    private var cbSelect = itemView.findViewById<CheckBox>(R.id.item_cart_select_checkbox)

    private lateinit var holderItem: Cart

    fun onBind(itemData: Cart) {
        holderItem = itemData

        holderItem.apply {
            tvCost.text = this.discountCost.toDecimalFormat()
            tvName.text = this.productName
            tvOption.text = this.optionName
            tvUnitCount.text = this.count.toString()
        }

        Glide.with(context)
            .load(holderItem.thumbnailUrl)
            .into(imgProduct)

        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnRemove.setOnClickListener(this)

        cbSelect.isChecked = true

    }

    override fun onClick(view: View?) {
        when (view) {
            btnPlus -> {

            }
            btnRemove -> {

            }
            btnMinus -> {

            }
        }
    }

}


