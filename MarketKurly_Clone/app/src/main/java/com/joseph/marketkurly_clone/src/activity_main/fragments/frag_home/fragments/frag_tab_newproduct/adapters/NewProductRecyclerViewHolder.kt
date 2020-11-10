package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_newproduct.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.util.*
import kotlinx.android.synthetic.main.layout_sign_sale.view.*
import java.util.*

class NewProductRecyclerViewHolder(private var itemView: View, private var context: Context) :
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

    private lateinit var viewHolderData: ProductCompact

    fun onBind(itemData: ProductCompact) {
        viewHolderData = itemData

        tvName.text = viewHolderData.name
        tvDiscountCost.text = viewHolderData.discountCost.toDecimalFormat()

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

        val random = Random().nextInt(3)
        when (random) {
            0 -> {
                badgeKurlyOnly.setVisible()
                badgeLimit.setGone()
            }
            1 -> {
                badgeKurlyOnly.setGone()
                badgeLimit.setVisible()
            }
            2 -> {
                badgeKurlyOnly.setGone()
                badgeLimit.setGone()
            }
        }

        btnCart.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            btnCart -> {

            }
        }
    }

}


