package com.joseph.marketkurly_clone.src.activity_detail_product

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.adapters.DetailTabLayoutAdapter
import com.joseph.marketkurly_clone.src.activity_detail_product.interfaces.LoadProductDetailEvent
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.SelectProductActivity
import com.joseph.marketkurly_clone.src.util.setGone
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.ab_inner_toolbar
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.div_line
import kotlinx.android.synthetic.main.actionbar_inner_page_top_thin.view.*
import kotlinx.android.synthetic.main.activity_detail_product.*

class DetailProductActivity : BaseActivity(), LoadProductDetailEvent{

    private var mDetailProductService = DetailProductService(this)
    private lateinit var mProductDetail: ProductDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        val productId = intent.extras?.getInt("productId")

        initActionBar()
        initView()
        showProgressBar()
        mDetailProductService.loadProductDetail(1)

    }

    fun initActionBar() {
        detail_actionbar.div_line.setGone()
        detail_actionbar.ab_thin_inner_toolbar.title = "제품 상세설명"
        detail_actionbar.ab_thin_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initViewPager(reviewCount: String) {
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

    fun initView() {
        detail_buy_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.detail_buy_button -> {
                val bundle = Bundle()
                val intent = Intent(this, SelectProductActivity::class.java)
                bundle.putSerializable("productDetail", mProductDetail)
                intent.putExtra("productBundle", bundle)
                startActivity(intent)
            }
        }
    }

    override fun onLoadDetailSuccess(detailData: ProductDetail) {
        hideProgressBar()
        mProductDetail = detailData
        initViewPager(detailData.reviewCount)
    }

    override fun onLoadDetailFail(message: String) {
        hideProgressBar()
        showAlertDialog(message)
    }

}