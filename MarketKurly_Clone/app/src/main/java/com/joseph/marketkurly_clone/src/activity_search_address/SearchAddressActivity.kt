package com.joseph.marketkurly_clone.src.activity_search_address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddressApiEvent
import com.joseph.marketkurly_clone.src.activity_signup.manager.AddressApiManager
import kotlinx.android.synthetic.main.activity_search_address.*

class SearchAddressActivity : BaseActivity(), AddressApiEvent {

    private lateinit var mAddressApiManager: AddressApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_address)

        mAddressApiManager = AddressApiManager(search_address_webview, this)
        mAddressApiManager.initWebView()
    }

    override fun onAddressSelected(addressNum: String, address: String) {
        Log.d("로그", "$addressNum, $address")
    }
}