package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.model.Review

class ReviewRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<ReviewRecyclerViewHolder>() {

    private var reviewList = ArrayList<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewRecyclerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ReviewRecyclerViewHolder, position: Int) {
        holder.onBind(reviewList[position])
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun submitList(list: ArrayList<Review>){
        this.reviewList = list
        notifyDataSetChanged()
    }

}