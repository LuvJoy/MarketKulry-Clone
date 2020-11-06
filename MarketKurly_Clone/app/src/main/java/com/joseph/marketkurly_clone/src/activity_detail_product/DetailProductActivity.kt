package com.joseph.marketkurly_clone.src.activity_detail_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.adapters.DetailTabLayoutAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.adapters.TabViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.fragment_home.*

class DetailProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        initViewPager()
    }

    fun initViewPager() {
        val fm = this.supportFragmentManager
        detail_viewpager.adapter = DetailTabLayoutAdapter(this)

        TabLayoutMediator(detail_tablayout, detail_viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 ->  tab.text = "상품설명"
                    1 ->  tab.text = "상품이미지"
                    2 ->  tab.text = "상세정보"
                    3 ->  tab.text = "후기"
                    4 ->  tab.text = "상품문의"
                }
            }
        ).attach()
    }
}