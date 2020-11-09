package com.joseph.marketkurly_clone.src.activity_cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_cart.adapters.CartRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_search_address.SearchAddressActivity
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.db.CartEvent
import com.joseph.marketkurly_clone.src.db.CartService
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlin.math.log

class CartActivity : BaseActivity(), CartEvent {

    val TAG = "[ 로그 ]"
    private var mCartService = CartService(this)
    private lateinit var mFreezerRecyclerAdapter: CartRecyclerAdapter
    private lateinit var mFridgeRecyclerAdapter: CartRecyclerAdapter
    private lateinit var mRoomRecyclerAdapter: CartRecyclerAdapter

    private var freezerList = ArrayList<Cart>()
    private var fridgeList = ArrayList<Cart>()
    private var roomList = ArrayList<Cart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initActionbar()
        initViews()
        initRecyclerView()

        mCartService.loadAllCart()
    }

    fun initActionbar() {
        cart_actionbar.ab_inner_toolbar.apply {
            title = "장바구니"
            setNavigationOnClickListener { onBackPressed() }
        }
    }
    fun initViews() {
        cart_put_address_button.setOnClickListener(this)
    }

    fun initRecyclerView() {
        mFreezerRecyclerAdapter = CartRecyclerAdapter(this)
        mFridgeRecyclerAdapter = CartRecyclerAdapter(this)
        mRoomRecyclerAdapter = CartRecyclerAdapter(this)

        cart_freezer_recyclerview.apply {
            layoutManager = object: LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mFreezerRecyclerAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
        cart_fridge_recyclerview.apply {
            layoutManager = object: LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mFridgeRecyclerAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
        cart_room_recyclerview.apply {
            layoutManager = object: LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mRoomRecyclerAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cart_put_address_button -> {
                val intent = Intent(this, SearchAddressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCartLoadSuccess(list: List<Cart>?) {
        list?.forEach {
            Log.d(TAG, "[CartActivity] - onCartLoadSuccess() : ${it.toString()}")
            when(it.packageX) {
                "냉동" -> {
                    freezerList.add(it)
                }
                "냉장" -> {
                    fridgeList.add(it)
                }
                "상온" -> {
                    roomList.add(it)
                }
            }
        }

        checkRecyclerView()
    }

    fun checkRecyclerView() {
        if(freezerList.isEmpty()) {
            cart_freezer_layout.setGone()
        } else {
            cart_freezer_layout.setVisible()
            mFreezerRecyclerAdapter.submitList(freezerList)
            Log.d(TAG, "[CartActivity] - Freezer submitList() : $freezerList")
        }

        if(fridgeList.isEmpty()) {
            cart_fridge_layout.setGone()
        } else {
            cart_fridge_layout.setVisible()
            mFridgeRecyclerAdapter.submitList(fridgeList)
            Log.d(TAG, "[CartActivity] - Freezer submitList() : $fridgeList")
        }

        if(roomList.isEmpty()) {
            cart_room_layout.setGone()
        } else {
            cart_room_layout.setVisible()
            mRoomRecyclerAdapter.submitList(roomList)
            Log.d(TAG, "[CartActivity] - Freezer submitList() : $roomList")
        }
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
    override fun onCartAddedSuccess() {
        Log.d(TAG, "[CartActivity] - onAddedSuccess() : good")
    }

    override fun onCartAddedFail() {
        Log.d(TAG, "[CartActivity] - onAddedSuccess() : bad")
    }

    override fun onDestroy() {
        super.onDestroy()
        mCartService.onCleared()
    }
}