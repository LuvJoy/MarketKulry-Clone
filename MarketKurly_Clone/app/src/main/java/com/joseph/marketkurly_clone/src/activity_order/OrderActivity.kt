package com.joseph.marketkurly_clone.src.activity_order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.ApplicationClass
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_ORDER_ADDRESS_CHANGE
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_order.adapters.OrderProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_order.interfaces.OrderApiEvnet
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order.models.OrderPrice
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct
import com.joseph.marketkurly_clone.src.activity_order.models.OrderUserInfo
import com.joseph.marketkurly_clone.src.activity_order_address.OrderAddressActivity
import com.joseph.marketkurly_clone.src.activity_order.models.Order
import com.joseph.marketkurly_clone.src.activity_order_success.OrderSuccessActivity
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import com.joseph.marketkurly_clone.src.util.toDecimalFormat
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class OrderActivity : BaseActivity(), OrderApiEvnet {

    private var mOrderService = OrderService(this)
    private lateinit var mOrderProductAdapter: OrderProductRecyclerAdapter

    private var mProductLayoutExpaneded = false
    private var mUserInfoLayoutExpanded = false

    private lateinit var mPrice: OrderPrice
    private lateinit var mAddress: OrderAddress
    private lateinit var mUserInfo: OrderUserInfo
    private lateinit var mCartList: ArrayList<Cart>

    private var job = SupervisorJob()
    private var coroutineScope = CoroutineScope(Dispatchers.Main + job)

    private var paymentHash: HashMap<String, Boolean> = hashMapOf(
        Pair("CreditCard", false),
        Pair("Chai", false),
        Pair("Toss", false),
        Pair("Npay", false),
        Pair("Payco", false),
        Pair("SmilePay", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initActionbar()
        initListener()
        initRecyclerView()

        mCartList = intent.getSerializableExtra("cartList") as ArrayList<Cart>
        Log.d(Constants.TAG, "[OrderActivity] - onCreate() : $mCartList")
        mOrderService.getOrderSheet(mCartList)
    }

    fun initActionbar() {
        order_actionbar.ab_inner_toolbar.title = "주문서"
        order_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        mOrderProductAdapter = OrderProductRecyclerAdapter(this)

        order_product_recyclerview.apply {
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mOrderProductAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }

    }

    fun initListener() {
        order_product_expanded_button.setOnClickListener(this)
        order_userinfo_expanded_button.setOnClickListener(this)
        order_address_change_button.setOnClickListener(this)

        order_payment_creditcard_textview.setOnClickListener(this)
        order_payment_toss_textview.setOnClickListener(this)
        order_payment_npay_textview.setOnClickListener(this)
        order_payment_chai_textview.setOnClickListener(this)
        order_payment_payco_textview.setOnClickListener(this)
        order_payment_smilepay_textview.setOnClickListener(this)
        order_order_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.order_product_expanded_button -> {
                if (mProductLayoutExpaneded) {
                    order_product_expanded_button.setImageResource(R.drawable.ic_chevron_up)
                    mProductLayoutExpaneded = false
                    order_product_recyclerview.setVisible()
                } else {
                    order_product_expanded_button.setImageResource(R.drawable.ic_chevron_down)
                    mProductLayoutExpaneded = true
                    order_product_recyclerview.setGone()
                }
            }
            R.id.order_userinfo_expanded_button -> {
                if (mUserInfoLayoutExpanded) {
                    order_userinfo_expanded_button.setImageResource(R.drawable.ic_chevron_up)
                    order_userinfo_expanded_layout.setVisible()
                    mUserInfoLayoutExpanded = false
                } else {
                    order_userinfo_expanded_button.setImageResource(R.drawable.ic_chevron_down)
                    order_userinfo_expanded_layout.setGone()
                    mUserInfoLayoutExpanded = true
                }
            }

            R.id.order_address_change_button -> {
                val intent = Intent(this, OrderAddressActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_ORDER_ADDRESS_CHANGE)
            }

            R.id.order_order_button -> {
                var paymentMethod =""
                paymentHash.keys.forEach {
                    if(paymentHash[it]!!) paymentMethod = it
                }

                var order = Order(
                    paymentMethod = paymentMethod,
                    totalProductCost = mPrice.totalProduct,
                    deliveryCost = mPrice.deliveryFee,
                    totalDiscountCost = mPrice.totalDiscount!!,
                    totalCost = mPrice.totalCost,
                    points = mPrice.points,
                    usedPoints = order_point_edittext.text.toString().toInt(),
                    usedCoupon = null,
                    couponDiscountCost = null,
                    name = mUserInfo.name,
                    phoneNumber = mUserInfo.phoneNumber,
                    postCode = mAddress.postCode.toString(),
                    address = mAddress.address,
                    addressDetail = mAddress.address,
                    morningDelivery = mAddress.delivery,
                    place = mAddress.place,
                    entrancePw = mAddress.place,
                    entranceFree = null,
                    entranceEtc = null,
                    securityInfo = null,
                    mailBoxInfo = null,
                    edtInfo = null,
                    message = "Y",
                )

                mOrderService.order(order, mCartList)


            }

            R.id.order_payment_creditcard_textview -> {
                setPaymentButtonInit()
                if(paymentHash["CreditCard"]!!) {
                    paymentHash["CreditCard"] = false
                    order_payment_creditcard_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
                } else {
                    paymentHash["CreditCard"] = true
                    order_payment_creditcard_textview.background.setTint(ContextCompat.getColor(this, R.color.kurly_purple))
                }
            }

            R.id.order_payment_toss_textview -> {
                setPaymentButtonInit()
                if(paymentHash["Toss"]!!) {
                    paymentHash["Toss"] = false
                    order_payment_toss_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
                } else {
                    paymentHash["Toss"] = true
                    order_payment_toss_textview.background.setTint(ContextCompat.getColor(this, R.color.kurly_purple))
                }

            }

            R.id.order_payment_npay_textview -> {
                setPaymentButtonInit()
                if(paymentHash["Npay"]!!) {
                    paymentHash["Npay"] = false
                    order_payment_npay_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
                } else {
                    paymentHash["Npay"] = true
                    order_payment_npay_textview.background.setTint(ContextCompat.getColor(this, R.color.kurly_purple))
                }
            }

            R.id.order_payment_payco_textview -> {
                setPaymentButtonInit()
                if(paymentHash["Payco"]!!) {
                    paymentHash["Payco"] = false
                    order_payment_payco_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
                } else {
                    paymentHash["Payco"] = true
                    order_payment_payco_textview.background.setTint(ContextCompat.getColor(this, R.color.kurly_purple))
                }
            }

            R.id.order_payment_smilepay_textview -> {
                setPaymentButtonInit()
                if(paymentHash["SmilePay"]!!) {
                    paymentHash["SmilePay"] = false
                    order_payment_smilepay_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
                } else {
                    paymentHash["SmilePay"] = true
                    order_payment_smilepay_textview.background.setTint(ContextCompat.getColor(this, R.color.kurly_purple))
                }
            }
            R.id.order_payment_chai_textview -> {
                setPaymentButtonInit()
                if(paymentHash["Chai"]!!) {
                    paymentHash["Chai"] = false
                    order_payment_chai_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
                } else {
                    paymentHash["Chai"] = true
                    order_payment_chai_textview.background.setTint(ContextCompat.getColor(this, R.color.kurly_purple))
                }
            }


        }
    }

    override fun onGetSheetSuccess(
        products: ArrayList<OrderProduct>,
        price: OrderPrice,
        address: OrderAddress?,
        userInfo: OrderUserInfo?
    ) {
        mAddress = address!!
        mPrice = price
        mUserInfo = userInfo!!

        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $products")
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $price")
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $address")
        Log.d(Constants.TAG, "[OrderActivity] - onGetSheetSuccess() : $userInfo")

        // Products 세팅
        mOrderProductAdapter.submitList(products)
        order_product_count_textview.text = "${products.size} 건"

        // userinfo setting
        if (userInfo != null) {
            order_userinfo_textview.text =
                String.format(userInfo.name + ", " + userInfo.phoneNumber)
            order_userinfo_email_textview.text = userInfo.email
            order_userinfo_name_textview.text = userInfo.name
            order_userinfo_phone_textview.text = userInfo.phoneNumber
        }

        if (address?.delivery == "샛별배송") {
            order_address_shipping_morning_badge.setVisible()
            order_address_shipping_post_badge.setGone()
        } else {
            order_address_shipping_morning_badge.setGone()
            order_address_shipping_post_badge.setVisible()
        }
        order_address_textview.text = address?.address

        order_coupon_count_textview.text = "사용 가능 쿠폰 0장 / 전체 ${userInfo?.coupon}장"
        order_point_available_textview.text = (userInfo?.points?.toDecimalFormat()+"원 사용가능")

        order_price_textview.text = String.format(price.totalProduct.toDecimalFormat() + " 원")
        order_price_product_textview.text =
            String.format(price.totalProduct.toDecimalFormat() + " 원")
        order_price_discount_textview.text =
            String.format(price.totalDiscount?.toDecimalFormat() + " 원")

        order_price_shipping_textview.text =
            String.format(price.deliveryFee.toDecimalFormat() + " 원")

        order_price_total_textview.text = String.format(price.totalCost.toDecimalFormat()+" 원")

        order_price_add_mileage_textview.text = String.format("구매 시 ${price.points}원(${userInfo?.pointPercentage}%%)")

    }

    override fun onGetSheetFail(message: String) {
        showSnackBar(message)
    }

    override fun onOrderSuccess(order: Order) {
        showSnackBar("주문 성공")
        coroutineScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                ApplicationClass.DB_CART?.cartDao()?.deleteAllCart()
            }
        }
        val intent = Intent(this, OrderSuccessActivity::class.java)
        intent.putExtra("order", order)
        startActivity(intent)
    }

    override fun onOrderFail(message: String) {
        showSnackBar(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_ORDER_ADDRESS_CHANGE -> {
                if (resultCode == RESULT_OK) {
                    mAddress = data?.extras?.getSerializable("orderAddress") as OrderAddress
                    if (mAddress?.delivery == "샛별배송") {
                        order_address_shipping_morning_badge.setVisible()
                        order_address_shipping_post_badge.setGone()
                    } else {
                        order_address_shipping_morning_badge.setGone()
                        order_address_shipping_post_badge.setVisible()
                    }
                    order_address_receiver_textview.text =
                        String.format("${mAddress.name}, ${mAddress.phoneNumber}")
                    order_address_textview.text = mAddress.address
                }
            }
        }
    }

    fun setPaymentButtonInit(){
        order_payment_creditcard_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
        order_payment_toss_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
        order_payment_npay_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
        order_payment_chai_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
        order_payment_payco_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
        order_payment_smilepay_textview.background.setTint(ContextCompat.getColor(this, R.color.text_whitegray))
    }
}