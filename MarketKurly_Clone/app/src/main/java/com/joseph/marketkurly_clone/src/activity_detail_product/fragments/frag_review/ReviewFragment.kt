package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.adpaters.ReviewRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_detail_tab_review.*


class ReviewFragment : BaseFragment(R.layout.fragment_detail_tab_review) {

    private lateinit var mReviewRecyclerViewAdapter: ReviewRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(fragContext, LinearLayoutManager.VERTICAL, false)
        mReviewRecyclerViewAdapter = ReviewRecyclerAdapter(fragContext!!)

        review_frag_recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = mReviewRecyclerViewAdapter
        }

        mReviewRecyclerViewAdapter.submitList(DummyData.reviewList)
    }


}