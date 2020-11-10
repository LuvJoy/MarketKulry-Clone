package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_event.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R

class EventBannerRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<EventBannerRecyclerViewHolder>() {

    private var imageList = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventBannerRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_banner, parent, false)
        return EventBannerRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: EventBannerRecyclerViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun submitList(list: ArrayList<Int>) {
        this.imageList = list
        notifyDataSetChanged()
    }
}