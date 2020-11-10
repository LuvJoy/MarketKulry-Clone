package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_event.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R

class EventBannerRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView) {

    private var imgView = itemView.findViewById<ImageView>(R.id.item_event_banner_imageView)

    fun onBind(itemData: Int) {
        Glide.with(context)
            .load(itemData)
            .into(imgView)
    }

}


