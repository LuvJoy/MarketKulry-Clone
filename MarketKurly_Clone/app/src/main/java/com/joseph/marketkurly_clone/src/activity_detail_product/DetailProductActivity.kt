package com.joseph.marketkurly_clone.src.activity_detail_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.adapters.DetailTabLayoutAdapter
import com.joseph.marketkurly_clone.src.activity_detail_product.interfaces.LoadProductDetailEvent
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.adapters.TabViewPagerAdapter
import com.joseph.marketkurly_clone.src.util.setGone
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.fragment_home.*

class DetailProductActivity : BaseActivity(), LoadProductDetailEvent{

    private var mDetailProductService = DetailProductService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        val productId = intent.extras?.getInt("productId")

        initActionBar()
        showProgressBar()
        mDetailProductService.loadProductDetail(1)

    }

    fun initActionBar() {
        detail_actionbar.ab_inner_div_line.setGone()
        detail_actionbar.ab_inner_toolbar.title = "제품 상세설명"
        detail_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initViewPager(reviewCount: String) {
        val fm = this.supportFragmentManager
        detail_viewpager.adapter = DetailTabLayoutAdapter(this)

        TabLayoutMediator(detail_tablayout, detail_viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 ->  tab.text = "상품설명"
                    1 ->  tab.text = "상품이미지"
                    2 ->  tab.text = "상세정보"
                    3 ->  tab.text = "후기\n(${reviewCount})"
                    4 ->  tab.text = "상품문의"
                }
            }
        ).attach()
    }



    override fun onLoadDetailSuccess(detailData: ProductDetail) {
        hideProgressBar()
        initViewPager(detailData.reviewCount)
    }

    override fun onLoadDetailFail(message: String) {
        hideProgressBar()
        showAlertDialog(message)
    }
}