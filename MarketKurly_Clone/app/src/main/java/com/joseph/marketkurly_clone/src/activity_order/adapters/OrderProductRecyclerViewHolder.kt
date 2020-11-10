package com.joseph.marketkurly_clone.src.activity_order.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import com.joseph.marketkurly_clone.src.util.toDecimalFormat

class OrderProductRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView) {

    private var tvName = itemView.findViewById<TextView>(R.id.item_order_product_name_textview)
    private var tvOptionName = itemView.findViewById<TextView>(R.id.item_order_product_option_name_textview)
    private var imgProduct = itemView.findViewById<ImageView>(R.id.item_order_product_imageview)
    private var tvCount = itemView.findViewById<TextView>(R.id.item_order_product_count_textview)
    private var tvCost = itemView.findViewById<TextView>(R.id.item_order_product_cost_textview)

    fun onBind(itemData: OrderProduct) {

        if(itemData.name == "" ) {
            tvName.setGone()
        } else {
            tvName.setVisible()
            tvName.text = itemData.name
        }

        tvOptionName.text = itemData.optionName
        tvCount.text = String.format("${itemData.count}개")
        tvCost.text= String.format(itemData.cost.toDecimalFormat()+"원")

        Glide.with(context)
            .load(itemData.thumbnailUrl)
            .into(imgProduct)
    }

}


