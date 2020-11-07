package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_my_kurly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.ApplicationClass.Companion.CURRENT_USER
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.ApplicationClass.Companion.sSharedPreferences
import com.joseph.marketkurly_clone.BaseFragment

import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_signin.SignInActivity
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.*
import kotlinx.android.synthetic.main.fragment_mykurly.*

class MyKurlyFragment : BaseFragment(R.layout.fragment_mykurly) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initButton()
    }

    fun initView() {

        if(LOGIN_STATUS == Login.LOGGED) {
            mykurly_member_info_section.setVisible()
            mykurly_member_order_section.setVisible()
            mykurly_member_logout_section.setVisible()
            mykurly_member_property_section.setVisible()
            mykurly_member_user_edit_layout.setVisible()
            mykurly_non_member_section.setGone()
            mykurly_login_section.setGone()
            CURRENT_USER.apply {
                mykurly_member_grade_textview.text = this?.level
                mykurly_member_name_textview.text = String.format(this?.name + "님")
                mykurly_member_grade_benefit_textview.text = this?.pointPercentage.toString()
                mykurly_member_coupon_textview.text = String.format(this?.coupon.toString() + " 장")
                mykurly_member_mileage_textview.text = String.format(this?.points?.toDecimalFormat() + " 원")
            }
        } else {
            mykurly_member_info_section.setGone()
            mykurly_member_order_section.setGone()
            mykurly_member_logout_section.setGone()
            mykurly_member_property_section.setGone()
            mykurly_member_user_edit_layout.setGone()
            mykurly_non_member_section.setVisible()
            mykurly_login_section.setVisible()
        }
    }
    fun initButton() {
        mykurly_login_button.setOnClickListener(this)
        mykurly_member_logout_section.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        initView()
    }
    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.mykurly_login_button -> {
                val intent = Intent(fragContext, SignInActivity::class.java)
                fragContext?.startActivity(intent)
            }
            R.id.mykurly_member_logout_section -> {
                sSharedPreferences?.setToken(null)
                LOGIN_STATUS = Login.NOT_LOGGED
                CURRENT_USER = null
                initView()
            }
        }
    }
}