package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters.EventViewpagerViewHolder

class EventViewPagerAdapter(var context: Context) :
    RecyclerView.Adapter<EventViewpagerViewHolder>() {

    private var eventList =  ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewpagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_viewpager, parent, false)
        return EventViewpagerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: EventViewpagerViewHolder, position: Int) {
        holder.onBind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun submitList(list: ArrayList<Int>) {
        this.eventList = list
    }
}
