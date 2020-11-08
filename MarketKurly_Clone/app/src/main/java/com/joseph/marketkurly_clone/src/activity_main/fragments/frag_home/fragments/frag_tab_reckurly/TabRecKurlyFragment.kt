package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters.EventViewPagerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters.ProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.interfaces.HomeProductApiEvent
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import kotlinx.android.synthetic.main.fragment_tab_rec_kurly.*

class TabRecKurlyFragment : BaseFragment(R.layout.fragment_tab_rec_kurly), HomeProductApiEvent {

    private lateinit var mSuggestRecyclerViewAdapter: ProductRecyclerAdapter
    private lateinit var mEventViewPagerAdapter: EventViewPagerAdapter
    private var mHomeProductService = HomeProductService(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()

        mHomeProductService.getRecommendProduct()
    }


    fun initAdapters() {

        val horizontalLayoutManager: LinearLayoutManager =
                LinearLayoutManager(fragContext, LinearLayoutManager.HORIZONTAL, false)
        horizontalLayoutManager.isItemPrefetchEnabled = true

        // [이 상품은 어때요?] 리사이클러뷰 초기화
        mSuggestRecyclerViewAdapter = ProductRecyclerAdapter(fragContext!!)

        reckurly_suggest_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = horizontalLayoutManager
            adapter = mSuggestRecyclerViewAdapter
        }


        // Event ViewPager 초기화 부분
        val imageList = arrayListOf<Int>(R.drawable.image_dummy_viewpager1, R.drawable.image_dummy_viewpager2, R.drawable.image_dummy_viewpager3)
        mEventViewPagerAdapter = EventViewPagerAdapter(activity?.applicationContext!!)
        mEventViewPagerAdapter.submitList(imageList)

        reckurly_event_viewpager.isUserInputEnabled = true
        reckurly_event_viewpager.adapter = mEventViewPagerAdapter
        reckurly_event_viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    override fun onProductLoadSuccess(list: ArrayList<ProductCompact>) {
        mSuggestRecyclerViewAdapter.submitList(list)
    }

    override fun onProductLoadFail(message: String) {
        showAlertDialog(message)
    }
}