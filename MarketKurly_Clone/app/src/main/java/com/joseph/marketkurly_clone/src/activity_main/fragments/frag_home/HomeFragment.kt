package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.adapters.TabViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       initAdapters()

    }


    fun initAdapters() {
        home_tab_viewPager.adapter = TabViewPagerAdapter(this)
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