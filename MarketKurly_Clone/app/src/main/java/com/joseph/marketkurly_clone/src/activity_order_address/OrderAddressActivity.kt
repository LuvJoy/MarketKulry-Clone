package com.joseph.marketkurly_clone.src.activity_order_address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.adapters.OrderProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_order_address.adapters.OrderAddressRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_order_address.interfaces.OrderAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_order_address.*

class OrderAddressActivity : BaseActivity(), OrderAddressApiEvent {

    private var mOrderAddressService = OrderAddressService(this)
    private lateinit var mOrderAddressAdapter: OrderAddressRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_address)

        initActionbar()
        initRecyclerView()

        mOrderAddressService.getOrderAddressList()
    }

    fun initActionbar() {
        address_order_actionbar.ab_inner_toolbar.title = "배송지 선택"
        address_order_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        mOrderAddressAdapter = OrderAddressRecyclerAdapter(this)
        address_order_recyclerview.apply {
            adapter = mOrderAddressAdapter
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }

    }

    override fun onGetOrderAddressSuccess(list: ArrayList<OrderAddressCombine>) {
        mOrderAddressAdapter.submitList(list)
    }

    override fun onGetOrderAddressFail(message: String) {
        showSnackBar(message)
    }


}