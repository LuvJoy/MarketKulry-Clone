package com.joseph.marketkurly_clone.src.activity_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.interfaces.OrderApiEvnet
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order.models.OrderPrice
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct
import com.joseph.marketkurly_clone.src.activity_order.models.OrderUserInfo
import com.joseph.marketkurly_clone.src.db.Cart

class OrderActivity : BaseActivity(), OrderApiEvnet {

    private var mOrderService = OrderService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var cartList = intent.getSerializableExtra("cartList") as ArrayList<Cart>
        Log.d(Constants.TAG, "[OrderActivity] - onCreate() : $cartList")
        mOrderService.getOrderSheet(cartList)
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
    }

    override fun onGetSheetFail(message: String) {
        showSnackBar(message)
    }
}