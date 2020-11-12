package com.joseph.marketkurly_clone.src.activity_order_address_edit

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_ORDER_PLACE
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order_address_edit.interfaces.OrderAddressEditApiEvent
import com.joseph.marketkurly_clone.src.activity_order_address_edit.model.OrderAddressEdit
import com.joseph.marketkurly_clone.src.activity_place_edit.OrderPlace
import com.joseph.marketkurly_clone.src.activity_place_edit.PlaceEditActivity
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_order_address_edit.*

class OrderAddressEditActivity : BaseActivity(), OrderAddressEditApiEvent {

    private var mAddressId: Int? = null
    private var mOrderAddressEditService = OrderAddressEditService(this)
    private var mShippingPlace = OrderPlace("", "", "", "", "", "", "", "")
    private lateinit var mEditAddress: OrderAddressEdit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_address_edit)

        initListener()
        initActionbar()
        getIntentData()

        mOrderAddressEditService.loadEditData(mAddressId!!)
    }

    fun initActionbar() {
        order_address_edit_actionbar.ab_inner_toolbar.title = "배송지 입력"
        order_address_edit_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun getIntentData() {
        mAddressId = intent.extras?.getInt("addressId") as Int
    }

    fun initListener() {
        order_address_edit_save_button.setOnClickListener(this)
        order_address_edit_shipping_place_textview.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.order_address_edit_save_button -> {
                var shippingType = ""
                if (order_address_edit_morning_shipping_badge.isVisible) {
                    shippingType = "샛별배송"
                } else {
                    shippingType = "택배배송"
                }

                var orderAddress = OrderAddressEdit(
                    name = order_address_edit_name_edittext.text.toString(),
                    addressDetail = order_address_edit_detail_address_edittext.text.toString(),
                    address = order_address_edit_address_textview.text.toString(),
                    phoneNumber = order_address_edit_phone_edittext.text.toString(),
                    delivery = shippingType,
                    entranceEtc = mShippingPlace.entranceEtc!! ?: "",
                    entranceFree = mShippingPlace.entranceFree!! ?: "",
                    entrancePw = mShippingPlace.entrancePw!! ?: "",
                    etcInfo = mShippingPlace.etcInfo!! ?: "",
                    mailboxInfo = mShippingPlace.mailboxInfo!! ?: "",
                    place = mShippingPlace.place!! ?: "",
                    placeInfo = mShippingPlace.placeInfo!! ?: "",
                    securityInfo = mShippingPlace.securityInfo!! ?: "",
                    postCode = mEditAddress.postCode
                )

                var intent = Intent()
                intent.putExtra("addressData", orderAddress)
                intent.putExtra("addressId", mAddressId)
                setResult(RESULT_OK, intent)
                finish()
            }
            R.id.order_address_edit_shipping_place_textview -> {
                val intent = Intent(this, PlaceEditActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_ORDER_PLACE)
            }
        }
    }

    override fun onEditDataLoadSuccess(data: OrderAddressEdit) {
        mEditAddress = data

        order_address_edit_name_edittext.setText(data.name)
        order_address_edit_phone_edittext.setText(data.phoneNumber)

        if (data.delivery == "샛별배송") {
            order_address_edit_morning_shipping_badge.setVisible()
            order_address_edit_post_shipping_badge.setGone()
        } else {
            order_address_edit_morning_shipping_badge.setGone()
            order_address_edit_post_shipping_badge.setVisible()
        }

        order_address_edit_address_textview.text = data.address
        order_address_edit_detail_address_edittext.setText(data.addressDetail)
    }

    override fun onEditDataLoadFail(message: String) {
        showSnackBar(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_ORDER_PLACE -> {
                if (resultCode == RESULT_OK) {
                    mShippingPlace = data?.extras?.getSerializable("placeData") as OrderPlace
                    order_address_edit_shipping_place_textview.text = mShippingPlace.placeInfo
                }
            }
        }
    }
}