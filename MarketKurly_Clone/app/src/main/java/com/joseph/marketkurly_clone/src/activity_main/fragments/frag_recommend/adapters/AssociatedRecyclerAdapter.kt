package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_recommend.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact

class AssociatedRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<AssociatedRecyclerViewHolder>() {

    private var itemList = ArrayList<ProductCompact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssociatedRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_small, parent, false)
        return AssociatedRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: AssociatedRecyclerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    fun submitList(list: ArrayList<ProductCompact>) {
        this.itemList = list
        notifyDataSetChanged()
    }

    fun shuffleList() {
        itemList.shuffle()
        notifyDataSetChanged()
    }
}