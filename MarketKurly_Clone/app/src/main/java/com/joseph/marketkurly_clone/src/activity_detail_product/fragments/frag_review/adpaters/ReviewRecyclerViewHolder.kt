package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.adpaters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.model.Review
import com.joseph.marketkurly_clone.src.util.setInVisible
import com.joseph.marketkurly_clone.src.util.setVisible

class ReviewRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var tvTitle = itemView.findViewById<TextView>(R.id.item_review_title_textview)
    private var imgIcon = itemView.findViewById<ImageView>(R.id.item_review_image_check_icon)
    private var tvUser = itemView.findViewById<TextView>(R.id.item_review_user_textview)
    private var tvDate = itemView.findViewById<TextView>(R.id.item_review_date_textview)


    fun onBind(itemData: Review) {
        tvTitle.text = itemData.title
        tvDate.text = itemData.date
        tvUser.text = itemData.writer

        if(itemData.imageURL =="") {
            imgIcon.setInVisible()
        } else {
            imgIcon.setVisible()
        }
    }

    override fun onClick(view: View?) {
        when (view) {

        }
    }

}


