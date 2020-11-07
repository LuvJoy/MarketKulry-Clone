package com.joseph.marketkurly_clone.src.activity_select_product

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.adapters.ProductOptionRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.PlusMinusButtonListener
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.ProductOptionApiEvent
import com.joseph.marketkurly_clone.src.activity_select_product.models.ProductOption
import com.joseph.marketkurly_clone.src.util.setVisible
import com.joseph.marketkurly_clone.src.util.toDecimalFormat
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_select_product.*

class SelectProductActivity : BaseActivity(), PlusMinusButtonListener, ProductOptionApiEvent {

    private lateinit var mProductOptionRecyclerViewAdapter: ProductOptionRecyclerAdapter
    private lateinit var mProductDetail: ProductDetail
    private var mSelectProductService = SelectProductService(this)
    private var mTotalCost: Int = 0
    private var mTotalPoint: Int = 0

    val TAG = "[ 로그 ]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_product)

        getIntentData()
        initActionbar()
        initRecyclerView()

        mSelectProductService.loadProductOption(mProductDetail.productId)

        Log.d(TAG, "[SelectProductActivity] - onCreate() : $mProductDetail")

    }

    fun getIntentData() {
        val bundle = intent.extras?.getBundle("productBundle")
        mProductDetail = bundle?.getSerializable("productDetail") as ProductDetail
    }

    fun initActionbar() {
        product_select_actionbar.ab_inner_toolbar.title = "상품 선택"
        product_select_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mProductOptionRecyclerViewAdapter = ProductOptionRecyclerAdapter(this, this)

        product_select_added_recyclerview.apply {
            this.adapter = mProductOptionRecyclerViewAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }

    }

    fun settingView() {
        product_select_title_textview.text = mProductDetail.name
        product_select_mileage_textview.text =
            String.format(mTotalPoint.toString() + "원 적립")

        product_select_price_textview.text =
            String.format(mTotalPoint.toString() + "원")

        product_select_save_mileage_badge.setVisible()
    }

    override fun onPlusClicked(count: Int, totalCost: Int, totalPoint: Int) {
        mTotalPoint += totalPoint
        mTotalCost += totalCost
        product_select_mileage_textview.text =
            String.format(mTotalPoint.toDecimalFormat() + "원 적립")

        product_select_price_textview.text =
            String.format(mTotalCost.toDecimalFormat() + "원")

    }

    override fun onMinusClicked(count: Int, totalCost: Int, totalPoint: Int) {
        mTotalPoint -= totalPoint
        mTotalCost -= totalCost
        product_select_mileage_textview.text =
            String.format(mTotalPoint.toDecimalFormat() + "원 적립")

        product_select_price_textview.text =
            String.format(mTotalCost.toDecimalFormat() + "원")

    }

    override fun onLoadSuccess(optionList: ArrayList<ProductOption>) {
        Log.d(TAG, "[SelectProductActivity] - onLoadSuccess() : Called")
        mProductOptionRecyclerViewAdapter.submitList(optionList)
        settingView()
    }

    override fun onLoadFail(message: String) {
        showAlertDialog(message)
    }

}