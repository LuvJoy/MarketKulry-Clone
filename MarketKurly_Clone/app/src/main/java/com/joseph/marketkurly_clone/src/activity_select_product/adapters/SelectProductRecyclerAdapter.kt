package com.joseph.marketkurly_clone.src.activity_select_product.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.PlusMinusButtonListener

class SelectProductRecyclerAdapter(var context: Context, private var listener: PlusMinusButtonListener) :
    RecyclerView.Adapter<SelectProductRecyclerViewHolder>() {

    private var productList = ArrayList<ProductDetail>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectProductRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_select, parent, false)
        return SelectProductRecyclerViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SelectProductRecyclerViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun submitList(list: ArrayList<ProductDetail>) {
        this.productList = list
    }

}