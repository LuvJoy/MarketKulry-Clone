package com.joseph.marketkurly_clone.src.activity_cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joseph.marketkurly_clone.ApplicationClass.Companion.DB_CART
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.db.Cart
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initActionbar()

        CoroutineScope(Dispatchers.Main).launch {
            var a = withContext(Dispatchers.IO){
                DB_CART?.cartDao()?.loadAllCart()
            }

            a?.forEach {
                Log.d("[ 로그 ]", "[CartActivity] - onCreate() : ${it.toString()}")
            }
        }

    }

    fun initActionbar() {
        cart_actionbar.ab_inner_toolbar.apply {
            title = "장바구니"
            setNavigationOnClickListener { onBackPressed() }
        }
    }

}