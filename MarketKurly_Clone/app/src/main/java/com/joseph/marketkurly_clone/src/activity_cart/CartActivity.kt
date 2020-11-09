package com.joseph.marketkurly_clone.src.activity_cart

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_cart.adapters.CartRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_cart.interfaces.ViewHolderClickListener
import com.joseph.marketkurly_clone.src.activity_search_address.SearchAddressActivity
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.db.CartEvent
import com.joseph.marketkurly_clone.src.db.CartService
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : BaseActivity(), CartEvent, ViewHolderClickListener {

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
        initListener()
        initRecyclerView()
        initlayout()

        mCartService.loadAllCart()
    }

    fun initActionbar() {
        cart_actionbar.ab_inner_toolbar.apply {
            title = "장바구니"
            setNavigationOnClickListener { onBackPressed() }
        }
    }


    fun initListener() {
        cart_put_address_button.setOnClickListener(this)

        cart_select_all_checkbox.isChecked = true
        cart_select_all_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            mRoomRecyclerAdapter.checkAll(isChecked)
            mFridgeRecyclerAdapter.checkAll(isChecked)
            mFreezerRecyclerAdapter.checkAll(isChecked)
        }
    }

    fun initlayout() {
        if(LOGIN_STATUS == Login.LOGGED) {
            cart_member_address_layout.setVisible()
            cart_put_address_button.setGone()
        } else {
            cart_member_address_layout.setGone()
            cart_put_address_button.setVisible()
        }
    }


    fun initRecyclerView() {
        mFreezerRecyclerAdapter = CartRecyclerAdapter(this, this, "냉동")
        mFridgeRecyclerAdapter = CartRecyclerAdapter(this, this, "냉장")
        mRoomRecyclerAdapter = CartRecyclerAdapter(this, this, "상온")

        cart_freezer_recyclerview.apply {
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mFreezerRecyclerAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
        cart_fridge_recyclerview.apply {
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mFridgeRecyclerAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
        cart_room_recyclerview.apply {
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mRoomRecyclerAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cart_put_address_button -> {
                val intent = Intent(this, SearchAddressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun checkRecyclerView() {
        if (freezerList.isEmpty()) {
            cart_freezer_layout.setGone()
        } else {
            cart_freezer_layout.setVisible()
            mFreezerRecyclerAdapter.submitList(freezerList)
            Log.d(TAG, "[CartActivity] - Freezer submitList() : $freezerList")
        }

        if (fridgeList.isEmpty()) {
            cart_fridge_layout.setGone()
        } else {
            cart_fridge_layout.setVisible()
            mFridgeRecyclerAdapter.submitList(fridgeList)
            Log.d(TAG, "[CartActivity] - Freezer submitList() : $fridgeList")
        }

        if (roomList.isEmpty()) {
            cart_room_layout.setGone()
        } else {
            cart_room_layout.setVisible()
            mRoomRecyclerAdapter.submitList(roomList)
            Log.d(TAG, "[CartActivity] - Freezer submitList() : $roomList")
        }
    }

    override fun onCartLoadSuccess(list: List<Cart>?) {
        list?.forEach {
            Log.d(TAG, "[CartActivity] - onCartLoadSuccess() : ${it.toString()}")
            when (it.packageX) {
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

    // 카트 이벤트 처리
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

    //  뷰홀더 클릭이벤트 처리
    override fun onRemoveButtonClicked(item: Cart, position: Int, adapterName: String) {

            AlertDialog.Builder(this).setTitle("정말 삭제하시겠습니까?")
                .setPositiveButton("확인") { dialog, which ->
                    when (adapterName) {
                        "냉장" -> {
                            mFridgeRecyclerAdapter.removeItem(position)
                        }
                        "냉동" -> {
                            mFreezerRecyclerAdapter.removeItem(position)
                        }
                        "상온" -> {
                            mRoomRecyclerAdapter.removeItem(position)
                        }
                    }
                    checkRecyclerView()
                    mCartService.deleteCart(listOf(item))
                }
                .setNegativeButton("취소") { dialog, which ->
                    dialog.dismiss()
                }
                .show()

    }

    override fun onCheckBoxClicked(isChecked: Boolean, position: Int, adapterName: String) {
        when (adapterName) {
            "냉장" -> {
                mFridgeRecyclerAdapter.checkChange(isChecked, position)
            }
            "냉동" -> {
                mFreezerRecyclerAdapter.checkChange(isChecked, position)
            }
            "상온" -> {
                mRoomRecyclerAdapter.checkChange(isChecked, position)
            }
        }
    }

    override fun onPlusButtonClicked(count: Int, position: Int, adapterName: String) {
        when (adapterName) {
            "냉장" -> {
                mFridgeRecyclerAdapter.changeCount(count, position)
            }
            "냉동" -> {
                mFreezerRecyclerAdapter.changeCount(count, position)
            }
            "상온" -> {
                mRoomRecyclerAdapter.changeCount(count, position)
            }
        }
    }

    override fun onMinusButtonClicked(count: Int, position: Int, adapterName: String) {
        when (adapterName) {
            "냉장" -> {
                mFridgeRecyclerAdapter.changeCount(count, position)
            }
            "냉동" -> {
                mFreezerRecyclerAdapter.changeCount(count, position)
            }
            "상온" -> {
                mRoomRecyclerAdapter.changeCount(count, position)
            }
        }
    }
}