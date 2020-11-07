package com.joseph.marketkurly_clone.src.activity_select_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.activity_select_product.*

class SelectProductActivity : AppCompatActivity() {

    private lateinit var mProductDetail: ProductDetail

    val TAG = "[ 로그 ]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_product)

        val bundle = intent.extras?.getBundle("bundleData")
        mProductDetail = bundle?.getSerializable("productDetail") as ProductDetail


        Log.d(TAG, "[SelectProductActivity] - onCreate() : $mProductDetail")
        initActionbar()

    }

    fun initActionbar() {
        product_select_actionbar.ab_inner_toolbar.title = "상품 선택"
        product_select_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun settingView(productDetail: ProductDetail) {
        product_select_title_textview.text = productDetail.name

    }

}