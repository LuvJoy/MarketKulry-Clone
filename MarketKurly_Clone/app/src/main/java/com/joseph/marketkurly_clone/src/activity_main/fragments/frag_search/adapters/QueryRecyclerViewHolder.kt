package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.adapters

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R

class QueryRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvName = itemView.findViewById<TextView>(R.id.item_query_name_textview)

    fun onBind(itemData: String) {
        tvName.text = itemData
        tvName.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {

        }
    }

}


