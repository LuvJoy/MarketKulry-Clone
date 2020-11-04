package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import kotlinx.android.synthetic.main.item_event_viewpager.view.*


class EventViewpagerViewHolder(private var itemView: View, private var context: Context) : 
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var imgEvent = itemView.findViewById<ImageView>(R.id.item_event_imageview)

    fun onBind(itemData: Int) {
        Glide.with(context)
            .load(itemData)
            .into(imgEvent)
    }

    override fun onClick(view: View?) {
        when (view) {
            
        }
    }

}


