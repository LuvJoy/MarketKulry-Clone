package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.adapters.TabViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       initAdapters()

    }


    fun initAdapters() {
        home_tab_viewPager.adapter = TabViewPagerAdapter(this)
        home_tab_viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        home_tab_viewPager.requestDisallowInterceptTouchEvent(true)
        TabLayoutMediator(home_tablayout, home_tab_viewPager,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    when (position) {
                        0 ->  tab.text = "컬리추천"
                        1 ->  tab.text = "신상품"
                        2 ->  tab.text = "베스트"
                        3 ->  tab.text = "알뜰쇼핑"
                        4 ->  tab.text = "이벤트"
                    }
                }
        ).attach()

    }

}