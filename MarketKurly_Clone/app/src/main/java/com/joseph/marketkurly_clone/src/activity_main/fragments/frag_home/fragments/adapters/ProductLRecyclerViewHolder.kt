package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.DetailProductActivity
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL
import com.joseph.marketkurly_clone.src.util.*
import kotlinx.android.synthetic.main.layout_sign_sale.view.*
import java.util.*

class ProductLRecyclerViewHolder(private var itemView: View, private var context: Context) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvName = itemView.findViewById<TextView>(R.id.item_product_grid_name_textview)
    private var btnCart = itemView.findViewById<ImageView>(R.id.item_product_grid_addcart_button)

    private var tvCost = itemView.findViewById<TextView>(R.id.item_product_grid_cost_textview)
    private var tvDiscountCost =
        itemView.findViewById<TextView>(R.id.item_product_grid_discount_cost_textview)
    private var imgProduct = itemView.findViewById<ImageView>(R.id.item_product_grid_imageview)

    private var layoutDiscountPercentage =
        itemView.findViewById<ConstraintLayout>(R.id.item_product_grid_percentage_layout)
    private var badgeKurlyOnly =
        itemView.findViewById<TextView>(R.id.item_product_grid_kurlyonly_badge)
    private var badgeLimit = itemView.findViewById<TextView>(R.id.item_product_grid_limit_badge)

    private lateinit var viewHolderData: ProductCompactL

    fun onBind(itemData: ProductCompactL) {
        viewHolderData = itemData

        tvName.text = viewHolderData.name
        tvDiscountCost.text = String.format(viewHolderData.discountCost.toDecimalFormat()+"원")

        if (viewHolderData.discountPercent != 0) {
            tvCost.text = String.format(itemData.cost.toDecimalFormat() + "원")
            tvCost.setStrikeThru()
            tvCost.setVisible()
            layoutDiscountPercentage.setVisible()
            layoutDiscountPercentage.salesign_percent_textview.text =
                viewHolderData.discountPercent.toString()
        } else {
            tvCost.setGone()
            layoutDiscountPercentage.setInVisible()
        }

        Glide.with(context)
            .load(viewHolderData.thumbnailUrl)
            .into(imgProduct)


        itemData.label.forEach {
            if(it == "Kurly only") {
                badgeKurlyOnly.setVisible()
            }

            if(it == "한정수량") {
                badgeLimit.setVisible()
            }
        }
        btnCart.setOnClickListener(this)
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            itemView -> {
                val intent = Intent(context, DetailProductActivity::class.java)
                intent.putExtra("productId", viewHolderData.productId)
                context.startActivity(intent)
            }
        }
    }

}


