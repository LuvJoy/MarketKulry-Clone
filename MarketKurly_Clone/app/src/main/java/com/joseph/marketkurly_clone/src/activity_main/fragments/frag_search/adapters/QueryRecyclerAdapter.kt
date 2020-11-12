package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R

class QueryRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<QueryRecyclerViewHolder>() {

    private var queryList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_query, parent, false)
        return QueryRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: QueryRecyclerViewHolder, position: Int) {
        holder.onBind(queryList[position])
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    fun submitList(list: ArrayList<String>) {
        this.queryList = list
        notifyDataSetChanged()
    }

}