package com.joseph.marketkurly_clone.src.activity_select_product.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.PlusMinusButtonListener
import com.joseph.marketkurly_clone.src.util.setStrikeThru
import com.joseph.marketkurly_clone.src.util.toDecimalFormat

class SelectProductRecyclerViewHolder(
    private var itemView: View,
    private var listener: PlusMinusButtonListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvCount = itemView.findViewById<TextView>(R.id.item_select_count_textview)
    private var btnMinus = itemView.findViewById<ImageView>(R.id.item_select_minus_button)
    private var btnPlus = itemView.findViewById<ImageView>(R.id.item_select_plus_button)
    private var tvPrice = itemView.findViewById<TextView>(R.id.item_select_price_textview)
    private var tvSalePrice = itemView.findViewById<TextView>(R.id.item_select_sale_price_textview)
    private var tvTitle = itemView.findViewById<TextView>(R.id.item_select_title_textview)

    private var productCount = 1

    fun onBind(itemData: ProductDetail) {
        tvTitle.text = itemData.name
        tvSalePrice.text = String.format(itemData.discountCost.toDecimalFormat()+"원")
        tvSalePrice.setStrikeThru()
        tvPrice.text = String.format(itemData.cost.toDecimalFormat()+"원")
        tvCount.text = productCount.toString()
        btnMinus.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btnMinus -> {
                if (productCount >= 1) {
                    productCount -= 1
                    listener.onMinusClicked(productCount)
                }
                tvCount.text = productCount.toString()
            }

            btnPlus -> {
                if (productCount < 10) {
                    productCount += 1
                    listener.onPlusClicked(productCount)
                }
                tvCount.text = productCount.toString()
            }
        }
    }

}


