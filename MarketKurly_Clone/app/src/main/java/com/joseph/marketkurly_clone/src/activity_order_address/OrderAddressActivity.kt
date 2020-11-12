package com.joseph.marketkurly_clone.src.activity_order_address

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_ORDER_ADDRESS_EDIT
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.adapters.OrderProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order_address.adapters.OrderAddressRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_order_address.interfaces.OrderAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine
import com.joseph.marketkurly_clone.src.activity_order_address_edit.model.OrderAddressEdit
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order_address.*
import kotlinx.android.synthetic.main.activity_order_address_edit.*

class OrderAddressActivity : BaseActivity(), OrderAddressApiEvent {

    private var mOrderAddressService = OrderAddressService(this)
    private lateinit var mOrderAddressAdapter: OrderAddressRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_address)

        initActionbar()
        initRecyclerView()
        initListener()

        mOrderAddressService.getOrderAddressList()
    }

    fun initActionbar() {
        address_order_actionbar.ab_inner_toolbar.title = "배송지 선택"
        address_order_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        mOrderAddressAdapter = OrderAddressRecyclerAdapter(this)
        address_order_recyclerview.apply {
            adapter = mOrderAddressAdapter
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }

    }

    fun initListener() {
        address_order_save_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.address_order_save_button -> {
                var data = mOrderAddressAdapter.listAddress[0]
                var address = OrderAddress(
                    address = data.address,
                    addressId = data.addressId,
                    delivery = data.delivery,
                    isBasic = data.isBasic,
                    name = data.name,
                    phoneNumber = data.phoneNumber,
                    place = data.place,
                    placeDetail = data.place,
                    postCode = data.postCode
                )
                val intent = Intent()
                intent.putExtra("orderAddress", address)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onGetOrderAddressSuccess(list: ArrayList<OrderAddressCombine>) {
        mOrderAddressAdapter.submitList(list)
    }

    override fun onGetOrderAddressFail(message: String) {
        showSnackBar(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE_ORDER_ADDRESS_EDIT -> {
                if(resultCode == RESULT_OK){
                    val addressId = data?.extras?.getInt("addressId")
                    val addressData = data?.extras?.getSerializable("addressData") as OrderAddressEdit
                    mOrderAddressAdapter.updataItem(addressId!!, addressData!!)
                }
            }
        }
    }

}