package com.joseph.marketkurly_clone.src.activity_cart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.db.Cart
import kotlinx.android.synthetic.main.item_cart.view.*

class CartRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<CartRecyclerViewHolder>() {

    private var cartList = ArrayList<Cart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: CartRecyclerViewHolder, position: Int) {
        holder.onBind(cartList[position])
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    fun submitList(list: ArrayList<Cart>) {
        this.cartList = list
        notifyDataSetChanged()
    }

}