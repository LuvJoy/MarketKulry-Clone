package com.joseph.marketkurly_clone.src.activity_edit_address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_edit_address.*

class EditAddressActivity : BaseActivity() {

    private lateinit var mUserAddress: UserAddress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initActionbar()
        getIntentData()
        settingView()


    }

    private fun initActionbar() {
        edit_address_actionbar.ab_inner_toolbar.title = "배송지"
        edit_address_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun getIntentData() {
        val bundle = intent.extras?.getBundle("bundle")
        mUserAddress = bundle?.getSerializable("userAddress") as UserAddress
    }

    private fun settingView() {
        edit_address_default_textview.text = mUserAddress.address
        edit_address_detail_address_edittext.setText(mUserAddress.addressDetail)
    }
}