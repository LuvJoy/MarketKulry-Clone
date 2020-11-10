package com.joseph.marketkurly_clone.src.activity_cart.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_cart.interfaces.ViewHolderClickListener
import com.joseph.marketkurly_clone.src.db.Cart

class CartRecyclerAdapter(
    private var context: Context,
    private var itemListener: ViewHolderClickListener,
    adapterName: String
) :
    RecyclerView.Adapter<CartRecyclerViewHolder>() {

    var cartList = ArrayList<Cart>()
    private var apapterName = adapterName
    var positionSelectedChecker = hashMapOf<Int, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartRecyclerViewHolder(view, apapterName, itemListener)
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

    fun removeItem(position: Int) {
        cartList.removeAt(position)
        positionSelectedChecker.remove(position)
        notifyItemRemoved(position)
    }

    fun changeCount(count: Int, position: Int) {
        cartList[position].count = count
    }

    fun checkChange(isChecked: Boolean, position: Int) {
        positionSelectedChecker[position] = isChecked
        Log.d(TAG, "[CartRecyclerAdapter] - checkChange() : ${positionSelectedChecker.toString()}")
    }

    fun checkAll(isChecked: Boolean) {

        for (position in cartList.indices) {
            positionSelectedChecker[position] = isChecked
            notifyItemChanged(position)
        }

        cartList.forEach {

        }
    }

    fun getTotalCost(): Int {
        var totalCost = 0

        cartList.forEach {
            totalCost += (it.discountCost * it.count)
        }
        return totalCost
    }

    fun getTotalDiscount(): Int {
        var totalDiscount = 0
        cartList.forEach {
            totalDiscount += (it.discountCost - it.cost) * it.count
        }
        return totalDiscount
    }

    fun getPositionCart(position: Int): Cart {
        return cartList[position]
    }
}