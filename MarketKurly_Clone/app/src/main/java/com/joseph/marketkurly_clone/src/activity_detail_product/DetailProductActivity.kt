package com.joseph.marketkurly_clone.src.activity_detail_product

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_CART
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.adapters.DetailTabLayoutAdapter
import com.joseph.marketkurly_clone.src.activity_detail_product.interfaces.LoadProductDetailEvent
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.SelectProductActivity
import com.joseph.marketkurly_clone.src.util.*
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

        mDetailProductService.loadProductDetail(productId!!)
    }

    fun initActionBar() {
        detail_actionbar.div_line.setGone()

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
                    3 ->  tab.text = "후기"
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
                startActivityForResult(intent, REQUEST_CODE_CART)
            }
        }
    }

    override fun onLoadDetailSuccess(detailData: ProductDetail) {
        hideProgressBar()
        mProductDetail = detailData
        ProductObject.data = detailData
        initViewPager(detailData.reviewCount) // 후기 개수때문에 여기서 초기화 해준다.

        detail_actionbar.ab_thin_inner_toolbar.title = detailData.name
    }

    override fun onLoadDetailFail(message: String) {
        hideProgressBar()
        showAlertDialog(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE_CART -> {
                if(resultCode == RESULT_OK) {
                    showSnackBar("장바구니에 추가되었습니다!")
                } else if(resultCode == 999){
                    showSnackBar("오류가 발생했습니다. 잠시후 다시 시도해 주세요")
                }
            }
        }
    }
}