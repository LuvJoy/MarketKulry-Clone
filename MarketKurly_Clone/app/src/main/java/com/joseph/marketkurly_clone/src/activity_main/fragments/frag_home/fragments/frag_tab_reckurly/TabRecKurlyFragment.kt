package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters.EventViewPagerAdapter
import com.joseph.marketkurly_clone.src.util.NestedScrollableHost
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters.ProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.model.ProductCompact
import com.joseph.marketkurly_clone.src.util.reduceDragSensitivity
import kotlinx.android.synthetic.main.fragment_tab_rec_kurly.*

class TabRecKurlyFragment : BaseFragment(R.layout.fragment_tab_rec_kurly) {

    private lateinit var mSuggestRecyclerViewAdapter: ProductRecyclerAdapter
    private lateinit var mEventViewPagerAdapter: EventViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragContext = requireContext()
        initAdapters()

        val dummy = ProductCompact("00010", "[해찬들] 태양초 순창 고추장", "ㅇㅇ", 30000, 30000, 30)
        val dummy2 = ProductCompact("00010", "[남향푸드또띠아] 간편 간식 부리또 8종", "ㅇㅇ", 2800, null, null)
        val dummy3 = ProductCompact("00010", "[해찬들] 태양초 순창 고추장", "ㅇㅇ", 6000, 4444, 30)
        val dummy4 = ProductCompact("00010", "[해찬들] 태양초 순창 고추장", "ㅇㅇ", 30000, 30000, 30)
        val dummy5 = ProductCompact("00010", "[해찬들] 태양초 순창 고추장", "ㅇㅇ", 30000, 30000, 30)
        val list = arrayListOf<ProductCompact>(dummy, dummy2, dummy3, dummy4, dummy5)
        list.add(dummy)
        mSuggestRecyclerViewAdapter.submitList(list)
    }


    fun initAdapters() {

        val horizontalLayoutManager: LinearLayoutManager =
                LinearLayoutManager(fragContext, LinearLayoutManager.HORIZONTAL, false)
        horizontalLayoutManager.isItemPrefetchEnabled = true

        // [이 상품은 어때요?] 리사이클러뷰 초기화
        mSuggestRecyclerViewAdapter = ProductRecyclerAdapter(fragContext!!)

        reckurly_suggest_recyclerview.apply {
            setHasFixedSize(true)
            setLayoutManager(horizontalLayoutManager)
            adapter = mSuggestRecyclerViewAdapter
        }


        // Event ViewPager 초기화 부분
        val imageList = arrayListOf<Int>(R.drawable.image_dummy_viewpager1, R.drawable.image_dummy_viewpager2, R.drawable.image_dummy_viewpager3)
        mEventViewPagerAdapter = EventViewPagerAdapter(activity?.applicationContext!!)
        mEventViewPagerAdapter.submitList(imageList)

        val nh = NestedScrollableHost(fragContext!!)
        reckurly_event_viewpager.isUserInputEnabled = true
        reckurly_event_viewpager.adapter = mEventViewPagerAdapter
        reckurly_event_viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        reckurly_event_viewpager.reduceDragSensitivity()

    }
}