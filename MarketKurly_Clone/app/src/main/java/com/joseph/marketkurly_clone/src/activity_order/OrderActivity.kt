package com.joseph.marketkurly_clone.src.activity_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.adapters.OrderProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_order.interfaces.OrderApiEvnet
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order.models.OrderPrice
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct
import com.joseph.marketkurly_clone.src.activity_order.models.OrderUserInfo
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseActivity(), OrderApiEvnet {

    private var mOrderService = OrderService(this)
    private lateinit var mOrderProductAdapter: OrderProductRecyclerAdapter

    private var mProductLayoutExpaneded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initActionbar()
        initListener()
        initRecyclerView()

        var cartList = intent.getSerializableExtra("cartList") as ArrayList<Cart>
        Log.d(Constants.TAG, "[OrderActivity] - onCreate() : $cartList")
        mOrderService.getOrderSheet(cartList)

    }

    fun initActionbar() {
        order_actionbar.ab_inner_toolbar.title = "주문서"
        order_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        mOrderProductAdapter = OrderProductRecyclerAdapter(this)

        order_product_recyclerview.apply {
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mOrderProductAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }

    }
    fun initListener() {
        order_product_expanded_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.order_product_expanded_button -> {
                if(mProductLayoutExpaneded) {
                    order_product_expanded_button.setImageResource(R.drawable.ic_chevron_down)
                    mProductLayoutExpaneded = false
                    order_product_recyclerview.setVisible()
                } else {
                    order_product_expanded_button.setImageResource(R.drawable.ic_chevron_up)
                    mProductLayoutExpaneded = true
                    order_product_recyclerview.setGone()
                }

            }
        }
    }

    override fun onGetSheetSuccess(
        products: ArrayList<OrderProduct>,
        price: OrderPrice,
        address: OrderAddress?,
        userInfo: OrderUserInfo?
    ) {
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $products")
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $price")
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $address")
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $userInfo")
        mOrderProductAdapter.submitList(products)
        order_product_count_textview.text = "${products.size} 건"

    }

    override fun onGetSheetFail(message: String) {
        showSnackBar(message)
    }
}