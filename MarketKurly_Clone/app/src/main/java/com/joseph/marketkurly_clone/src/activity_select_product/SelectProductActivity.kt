package com.joseph.marketkurly_clone.src.activity_select_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.adapters.SelectProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.PlusMinusButtonListener
import com.joseph.marketkurly_clone.src.util.setVisible
import com.joseph.marketkurly_clone.src.util.toDecimalFormat
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.activity_select_product.*

class SelectProductActivity : BaseActivity(), PlusMinusButtonListener {

    private lateinit var mProductDetail: ProductDetail
    private var mProductCost: Int = 0
    private var mProductPoint: Int = 0

    val TAG = "[ 로그 ]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_product)

        getIntentData()
        initActionbar()
        settingView()
        initRecyclerView()
        Log.d(TAG, "[SelectProductActivity] - onCreate() : $mProductDetail")

    }

    fun getIntentData() {
        val bundle = intent.extras?.getBundle("bundleData")
        mProductDetail = bundle?.getSerializable("productDetail") as ProductDetail
        mProductCost = mProductDetail.cost
       // mProductPoint = mProductDetail.point.toInt()
    }

    fun initActionbar() {
        product_select_actionbar.ab_inner_toolbar.title = "상품 선택"
        product_select_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = SelectProductRecyclerAdapter(this, this)

        val productTypeList: ArrayList<ProductDetail> = arrayListOf(mProductDetail)
        adapter.submitList(productTypeList)

        product_select_added_recyclerview.apply {
            this.adapter = adapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }

    }

    fun settingView() {
        product_select_title_textview.text = mProductDetail.name
        product_select_mileage_textview.text =
            String.format((mProductDetail.point).toDecimalFormat() + "원 적립")

        product_select_price_textview.text =
            String.format(mProductDetail.cost.toDecimalFormat() + "원")
        product_select_save_mileage_badge.setVisible()
    }

    override fun onPlusClicked(count: Int) {
        product_select_mileage_textview.text =
            String.format((mProductDetail.point * count).toDecimalFormat() + "원 적립")
        product_select_price_textview.text =
            String.format((mProductDetail.cost * count).toDecimalFormat() + "원")
    }

    override fun onMinusClicked(count: Int) {
        product_select_mileage_textview.text =
            String.format((mProductDetail.point * count).toDecimalFormat() + "원 적립")
        product_select_price_textview.text =
            String.format((mProductDetail.cost * count).toDecimalFormat() + "원")
    }

}