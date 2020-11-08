package com.joseph.marketkurly_clone.src.activity_cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joseph.marketkurly_clone.ApplicationClass.Companion.DB_CART
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.db.CartEvent
import com.joseph.marketkurly_clone.src.db.CartService
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CartActivity : BaseActivity(), CartEvent {
    val TAG = "[ 로그 ]"
    private var mCartService = CartService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initActionbar()

        mCartService.loadAllCart()

        sticky_scrollview

    }

    fun initActionbar() {
        cart_actionbar.ab_inner_toolbar.apply {
            title = "장바구니"
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    override fun onCartAddedSuccess() {
        Log.d(TAG, "[CartActivity] - onAddedSuccess() : good")
    }

    override fun onCartAddedFail() {
        Log.d(TAG, "[CartActivity] - onAddedSuccess() : bad")
    }

    override fun onCartLoadSuccess(list: List<Cart>?) {
        list?.forEach {
            Log.d(TAG, "[CartActivity] - onCreate() : ${it.toString()}")
        }
        Log.d(TAG, "[CartActivity] - onCreate() : fgsd")
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