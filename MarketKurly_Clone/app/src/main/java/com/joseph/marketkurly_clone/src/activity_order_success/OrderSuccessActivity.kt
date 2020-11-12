package com.joseph.marketkurly_clone.src.activity_order_success

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.MainActivity
import com.joseph.marketkurly_clone.src.activity_order.models.Order
import com.joseph.marketkurly_clone.src.util.toDecimalFormat
import kotlinx.android.synthetic.main.activity_order_success.*

class OrderSuccessActivity : BaseActivity() {

    private lateinit var mOrder: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        initActionBar()
        getIntentData()
    }

    fun initActionBar() {
        order_success_close_button.setOnClickListener{ onBackPressed()}
        order_success_gohome_button.setOnClickListener(this)
    }

    fun getIntentData() {
        mOrder = intent.extras?.getSerializable("order") as Order

        order_success_totalcost_textview.text = mOrder.totalCost.toDecimalFormat()
        order_success_name_textview.text = mOrder.name+ " 님의 주문이 완료되었습니다."
        order_success_point_grade_textview.text = String.format("(일반 0.5%%)")
        order_success_point_textview.text = String.format(mOrder.points.toString()+"원 적립")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.order_success_gohome_button -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}