package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_recommend.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.util.*
import kotlinx.android.synthetic.main.layout_sign_sale.view.*

class AssociatedRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var imgProduct = itemView.findViewById<ImageView>(R.id.item_small_image_imageview)
    private var tvTitle = itemView.findViewById<TextView>(R.id.item_small_title_textview)
    private var tvPrice = itemView.findViewById<TextView>(R.id.item_small_price_textview)
    private var tvSalePrice = itemView.findViewById<TextView>(R.id.item_small_sale_price_textview)
    private var layoutSalePer = itemView.findViewById<ConstraintLayout>(R.id.item_small_sale_percentage_layout)
    private var btnCart = itemView.findViewById<ImageView>(R.id.item_small_product_grid_addcart_button)

    fun onBind(itemData: ProductCompact) {
        Glide.with(itemView)
            .load(itemData.thumbnailUrl)
            .into(imgProduct)

        tvTitle.text = itemData.name

        if(itemData.discountPercent != 0){
            tvPrice.text = itemData.discountCost.toDecimalFormat()
            tvSalePrice.text = itemData.cost.toDecimalFormat()+"원"
            tvSalePrice.setStrikeThru()
            tvSalePrice.setVisible()
            layoutSalePer.setVisible()
            layoutSalePer.salesign_percent_textview.text = itemData.discountPercent.toString()
        } else {
            tvPrice.text = itemData.cost.toDecimalFormat()
            tvSalePrice.setGone()
            layoutSalePer.setInVisible()
        }

    }

    override fun onClick(view: View?) {
        when (view) {

        }
    }

}


