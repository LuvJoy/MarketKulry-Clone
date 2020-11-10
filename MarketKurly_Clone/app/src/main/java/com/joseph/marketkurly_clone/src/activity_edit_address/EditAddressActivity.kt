package com.joseph.marketkurly_clone.src.activity_edit_address

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants.RESULT_CODE_REMOVE_ADDRESS
import com.joseph.marketkurly_clone.Constants.RESULT_CODE_SAVE_ADDRESS
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_edit_address.interfaces.EditAddressApiEvent
import com.joseph.marketkurly_clone.src.util.isAgree
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_edit_address.*

class EditAddressActivity : BaseActivity(), EditAddressApiEvent {

    private lateinit var mUserAddress: UserAddress
    private var mPhoneNumValidation = false
    private var mEditAddressService = EditAddressService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initActionbar()
        getIntentData()
        settingView()
        setListener()

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
        edit_address_textview.text = mUserAddress.address
        edit_address_detail_address_edittext.setText(mUserAddress.addressDetail)
    }

    fun setListener() {
        edit_address_save_button.setOnClickListener(this)
        edit_address_remove_button.setOnClickListener(this)

        edit_address_phone_number_edittext.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if(!checkPhoneNumber(edit_address_phone_number_edittext.text.toString())) {
                    showAlertDialog("올바른 휴대폰 번호를 입력하세요")
                    mPhoneNumValidation = false
                } else {
                    mPhoneNumValidation = true
                }
            }
        }
    }

    fun checkPhoneNumber(text: String): Boolean {
        val pattern = Regex("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})\$")
        return text.matches(pattern)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.edit_address_save_button -> {

                if(mPhoneNumValidation) {
                    mUserAddress.apply {
                        addressDetail = edit_address_detail_address_edittext.text.toString()
                        name = edit_address_people_name_edittext.text.toString()
                        phoneNumber = edit_address_phone_number_edittext.text.toString()
                        isBasic = edit_address_save_default_checkbox.isChecked.isAgree()
                    }
                    mEditAddressService.editAddress(mUserAddress)
                } else {
                    showAlertDialog("올바른 휴대폰 번호를 입력해 주세요")
                }

            }

            R.id.edit_address_remove_button -> {

                mEditAddressService.removeAddress(mUserAddress.addressId)
            }
        }
    }

    override fun onEditSuccess() {
        Log.d(TAG, "[EditAddressActivity] - onEditSuccess() : 서버 반영 성공")

        val bundle = Bundle()
        val intent = Intent()

        bundle.putSerializable("userAddress", mUserAddress)
        intent.putExtra("bundle", bundle)

        setResult(RESULT_CODE_SAVE_ADDRESS, intent)
        finish()

    }

    override fun onEditFail(message: String) {
        Log.d(TAG, "[EditAddressActivity] - onEditSuccess() : 서버 반영 실")
    }

    override fun onRemoveSuccess() {
        val bundle = Bundle()
        val intent = Intent()
        bundle.putSerializable("userAddress", mUserAddress)
        intent.putExtra("bundle", bundle)
        setResult(RESULT_CODE_REMOVE_ADDRESS, intent)
        finish()
    }

    override fun onRemoveFail(message: String) {
        showSnackBar(message)
    }


}