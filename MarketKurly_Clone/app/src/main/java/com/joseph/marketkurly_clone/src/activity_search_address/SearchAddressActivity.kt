package com.joseph.marketkurly_clone.src.activity_search_address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddressApiEvent
import com.joseph.marketkurly_clone.src.activity_signup.manager.AddressApiManager
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_search_address.*

class SearchAddressActivity : BaseActivity(), AddressApiEvent {

    private lateinit var mAddressApiManager: AddressApiManager

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

    override fun onAddressSelected(addressNum: String, address: String) {
        search_address_webview.setGone()
        search_address_actionbar.ab_inner_toolbar.title = "배송지"
        search_address_detail_input_layout.setVisible()
        search_address_detail_address_textview.text = address
        Log.d("로그", "$addressNum, $address")
    }
}