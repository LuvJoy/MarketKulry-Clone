package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_event.adapters.EventBannerRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_tab_event.*

class TabEventFragment : BaseFragment(R.layout.fragment_tab_event) {

    private var bannerList = arrayListOf<Int>(
        R.drawable.image_event_banner1, R.drawable.image_event_banner2, R.drawable.image_event_banner3, R.drawable.image_event_banner4,
        R.drawable.image_event_banner5, R.drawable.image_event_banner6, R.drawable.image_event_banner7, R.drawable.image_event_banner8,
        R.drawable.image_event_banner9, R.drawable.image_event_banner10, R.drawable.image_event_banner11, R.drawable.image_event_banner12,
    )

    private lateinit var mEventBannerRecyclerAdapter: EventBannerRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(fragContext, LinearLayoutManager.VERTICAL, false)
        mEventBannerRecyclerAdapter = EventBannerRecyclerAdapter(fragContext!!)
        tab_event_recyclerview.apply {
            adapter = mEventBannerRecyclerAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
        mEventBannerRecyclerAdapter.submitList(bannerList)
    }

}