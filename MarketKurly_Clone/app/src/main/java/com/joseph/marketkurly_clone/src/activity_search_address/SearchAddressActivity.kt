package com.joseph.marketkurly_clone.src.activity_search_address

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddresApiEvent
import com.joseph.marketkurly_clone.src.activity_signup.manager.AddressApiManager
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_search_address.*

class SearchAddressActivity : BaseActivity(), AddresApiEvent {

    private lateinit var mAddressApiManager: AddressApiManager
    private var mAddress = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_address)

        initActionbar()

        mAddressApiManager = AddressApiManager(search_address_webview, this)
        mAddressApiManager.initWebView()
    }
    fun initActionbar() {
        search_address_actionbar.ab_inner_toolbar.title = "주소 검색"
        search_address_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.search_address_detail_save_button -> {
                val intent = Intent()
                intent.putExtra("address", mAddress)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onAddressSelected(address: String, addressNum: String) {
        search_address_webview.setGone()
        search_address_actionbar.ab_inner_toolbar.title = "배송지"
        search_address_detail_input_layout.setVisible()
        search_address_detail_address_textview.text = address

        mAddress = address
        Log.d("로그", "$addressNum, $address")
    }
}