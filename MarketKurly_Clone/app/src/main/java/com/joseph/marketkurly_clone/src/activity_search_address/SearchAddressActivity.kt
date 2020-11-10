package com.joseph.marketkurly_clone.src.activity_search_address

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.KurlyConstants.STAR_SHIPPING
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_search_address.interfaces.SearchAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_search_address.models.Address
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddresApiEvent
import com.joseph.marketkurly_clone.src.activity_signup.manager.AddressApiManager
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.getShippingType
import com.joseph.marketkurly_clone.src.util.getShippingTypeYN
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_search_address.*

class SearchAddressActivity : BaseActivity(), AddresApiEvent, SearchAddressApiEvent {

    private var mSearchAddressService = SearchAddressService(this)
    private lateinit var mAddressApiManager: AddressApiManager
    private lateinit var mAddress: Address

    private var mAddressCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_address)

        initActionbar()
        initListener()

        mAddressApiManager = AddressApiManager(search_address_webview, this)
        mAddressApiManager.initWebView()
    }

    fun initActionbar() {
        search_address_actionbar.ab_inner_toolbar.title = "주소 검색"
        search_address_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initListener() {
        search_address_detail_save_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_address_detail_save_button -> {
                var isDefault = "N"
                if (LOGIN_STATUS == Login.NOT_LOGGED) {
                    isDefault = "Y"
                } else {
                    if (search_address_member_save_default_checkbox.isChecked) {
                        isDefault = "Y"
                    } else {
                        isDefault = "N"
                    }
                }

                mAddress = Address(
                    postCode = mAddressCode,
                    address = search_address_address_textview.text.toString(),
                    addressDetail = search_address_detail_address_edittext.text.toString(),
                    isDefault = isDefault,
                    isStarShipping = search_address_address_textview.text.toString()
                        .getShippingTypeYN()!!
                )

                if (LOGIN_STATUS == Login.NOT_LOGGED) {
                    val bundle = Bundle()
                    val intent = Intent()
                    bundle.putSerializable("address", mAddress)
                    intent.putExtra("bundle", bundle)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    mSearchAddressService.addAddress(mAddress)
                }

            }
        }
    }

    override fun onAddressSelected(address: String, addressNum: String) {
        search_address_webview.setGone()
        search_address_actionbar.ab_inner_toolbar.title = "배송지"
        search_address_detail_input_layout.setVisible()
        search_address_address_textview.text = address

        val shippingType = search_address_address_textview.text.toString().getShippingType()

        if (shippingType == STAR_SHIPPING) {
            search_address_detail_shipping_type_textview.setTextColor(ContextCompat.getColor(this, R.color.kurly_purple))
            search_address_detail_shipping_ment_textview.text = String.format("매일 아침, 문 앞까지 신선함을 전해드려요")
        } else {
            search_address_detail_shipping_type_textview.setTextColor(ContextCompat.getColor(this, R.color.default_gray))
            search_address_detail_shipping_ment_textview.text = String.format("오늘 주문하면 다음 날 바로 도착해요. (일요일 휴무)")
        }
        search_address_detail_shipping_type_textview.text = shippingType

        if (LOGIN_STATUS == Login.NOT_LOGGED) {
            search_address_member_save_default_checkbox.setGone()
            search_address_non_memeber_notice_textview.setVisible()
        } else {
            search_address_member_save_default_checkbox.setVisible()
            search_address_non_memeber_notice_textview.setGone()
        }
        mAddressCode = addressNum

        Log.d("로그", "$addressNum, $address")
    }

    override fun onAddAddressSuccess() {
        val bundle = Bundle()
        val intent = Intent()
        bundle.putSerializable("address", mAddress)
        intent.putExtra("bundle", bundle)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onAddAddressFail(message: String) {
        showSnackBar(message)
    }
}