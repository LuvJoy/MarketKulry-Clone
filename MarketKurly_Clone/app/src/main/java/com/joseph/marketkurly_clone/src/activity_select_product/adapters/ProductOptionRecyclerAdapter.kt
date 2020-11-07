package com.joseph.marketkurly_clone.src.activity_select_product.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.PlusMinusButtonListener
import com.joseph.marketkurly_clone.src.activity_select_product.models.ProductOption

class ProductOptionRecyclerAdapter(var context: Context, private var listener: PlusMinusButtonListener) :
    RecyclerView.Adapter<ProductOptionRecyclerViewHolder>() {

    private var productList = ArrayList<ProductOption>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductOptionRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_select, parent, false)
        return ProductOptionRecyclerViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ProductOptionRecyclerViewHolder, position: Int) {
        Log.d(TAG, "[ProductOptionRecyclerAdapter] - onBindViewHolder() : ${productList[position]}")
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun submitList(list: ArrayList<ProductOption>) {
        Log.d(TAG, "[ProductOptionRecyclerAdapter] - submitList() : $list")
        this.productList = list
        notifyDataSetChanged()
    }

}