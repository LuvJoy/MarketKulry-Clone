package com.joseph.marketkurly_clone.src.activity_cart.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.src.db.Cart

class CartRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun onBind(itemData: Cart) {

    }

    override fun onClick(view: View?) {
        when (view) {

        }
    }

}


