package com.joseph.marketkurly_clone.src.activity_select_product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_select_product.adapters.ProductOptionRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.PlusMinusButtonListener
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.ProductOptionApiEvent
import com.joseph.marketkurly_clone.src.activity_select_product.models.ProductOption
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.db.CartEvent
import com.joseph.marketkurly_clone.src.db.CartService
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.convertPackage
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import com.joseph.marketkurly_clone.src.util.toDecimalFormat
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_select_product.*

class SelectProductActivity : BaseActivity(), PlusMinusButtonListener, ProductOptionApiEvent, CartEvent {

    private lateinit var mProductOptionRecyclerViewAdapter: ProductOptionRecyclerAdapter
    private lateinit var mProductDetail: ProductDetail
    private var mSelectProductService = SelectProductService(this)
    private var mTotalCost: Int = 0
    private var mTotalPoint: Int = 0

    private var mIdxCounterHash = hashMapOf<ProductOption, Int>()
    private var mOptionList = ArrayList<ProductOption>()
    private var mCartService = CartService(this)

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
        product_select_add_cart_button.setOnClickListener(this)
        product_select_title_textview.text = mProductDetail.name
        product_select_price_textview.text =
            String.format(mTotalPoint.toString() + "원")



        if(LOGIN_STATUS == Login.LOGGED) {
            product_select_mileage_dummy.setVisible()
            product_select_mileage_textview.setVisible()
            product_select_mileage_textview.text =
                String.format(mTotalPoint.toString() + "원 적립")
        } else {
            product_select_mileage_dummy.setGone()
            product_select_mileage_textview.setGone()
            product_select_mileage_non_memeber_textview.setVisible()
        }

        product_select_save_mileage_badge.setVisible()
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.product_select_add_cart_button -> {
                val addedList = ArrayList<Cart>()
                val optionCount = mIdxCounterHash.keys.size

                if(optionCount == 1) {
                    var firstOption = mIdxCounterHash.keys.first()
                    mProductDetail.name = firstOption.name
                }
                mIdxCounterHash.keys.forEach {
                    if(mIdxCounterHash[it] != 0) {

                        var addedItem = Cart(
                            cost = it.cost,
                            count = mIdxCounterHash[it]!!,
                            discountCost = it.discountCost,
                            optionName = it.name,
                            soldOut = it.soldOut,
                            productId = it.productId,
                            productName = mProductDetail.name,
                            thumbnailUrl = mProductDetail.thumbnailUrl,
                            packageX = mProductDetail.packageX.convertPackage(),
                            optionIdx = it.optionIdx
                        )
                        addedList.add(addedItem)
                    }
                }

                if(addedList.size == 0) {
                    showSnackBar("수량은 반드시 1이여야 합니다.")
                } else {
                    mCartService.addCartItem(addedList)
                }
            }
        }
    }

    override fun onPlusClicked(idx: ProductOption, count: Int, plusCost: Int, plusPoint: Int) {
        mTotalPoint += plusPoint
        mTotalCost += plusCost
        mIdxCounterHash[idx] = count
        product_select_mileage_textview.text =
            String.format(mTotalPoint.toDecimalFormat() + "원 적립")

        product_select_price_textview.text =
            String.format(mTotalCost.toDecimalFormat() + "원")
        Log.d(TAG, "[SelectProductActivity] - onPlusClicked() : ${mIdxCounterHash.toString()}")

    }

    override fun onMinusClicked(idx: ProductOption, count: Int, minusCost: Int, minusPoint: Int) {
        mTotalPoint -= minusPoint
        mTotalCost -= minusCost
        mIdxCounterHash[idx] = count
        product_select_mileage_textview.text =
            String.format(mTotalPoint.toDecimalFormat() + "원 적립")

        product_select_price_textview.text =
            String.format(mTotalCost.toDecimalFormat() + "원")
        Log.d(TAG, "[SelectProductActivity] - onPlusClicked() : ${mIdxCounterHash.toString()}")
    }

    override fun onLoadSuccess(optionList: ArrayList<ProductOption>) {
        Log.d(TAG, "[SelectProductActivity] - onLoadSuccess() : Called")
        mProductOptionRecyclerViewAdapter.submitList(optionList)
        optionList.forEach {
            mIdxCounterHash[it] = 0
        }

        settingView()
    }

    override fun onLoadFail(message: String) {
        showAlertDialog(message)
    }

    // 장바구니
    override fun onCartAddedSuccess() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onCartAddedFail() {
        val intent = Intent()
        setResult(999, intent)
        finish()
    }

    override fun onCartLoadSuccess(list: List<Cart>?) {
        TODO("Not yet implemented")
    }

    override fun onCartLoadFail() {
        TODO("Not yet implemented")
    }

    override fun onCartSizeLoadSuccess(size: Int) {
        TODO("Not yet implemented")
    }

    override fun onCartSizeLoadFail() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        mCartService.onCleared()
    }
}