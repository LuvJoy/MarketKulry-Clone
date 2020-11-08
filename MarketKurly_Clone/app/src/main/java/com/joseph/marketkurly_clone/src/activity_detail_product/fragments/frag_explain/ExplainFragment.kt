package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_explain

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.ApplicationClass
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_detail_product.ProductObject
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.*
import kotlinx.android.synthetic.main.fragment_detail_tab_explain.*

class ExplainFragment : BaseFragment(R.layout.fragment_detail_tab_explain) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingView(ProductObject.data!!)
    }

    fun settingView(data: ProductDetail) {
        if (ApplicationClass.LOGIN_STATUS == Login.LOGGED) {
            detail_explain_member_benefit_textview.setInVisible()
            detail_explain_member_info_layout.setVisible()
            detail_explain_member_grade_chip.text = ApplicationClass.CURRENT_USER?.level
            detail_explain_member_added_cash_textview.text = String.format("${data.point}원 적립")
        } else {
            detail_explain_member_info_layout.setInVisible()
            detail_explain_member_benefit_textview.setVisible()
        }

        Glide.with(this)
            .load(data.thumbnailUrl)
            .into(detail_explain_product_imageview)

        Glide.with(this)
            .load(data.descriptionImgUrl)
            .into(detail_explain_product_explain_imageview)

        detail_explain_name_textview.text = data.name
        detail_explain_overview_textview.text = data.shortContent
        detail_explain_price_textview.text = data.discountCost.toDecimalFormat()

        if (data.discountPercent != 0) {
            detail_explain_sale_percent_textview.text =
                String.format(data.discountPercent.toString() + "%%")
            detail_explain_sale_price_textview.text = String.format(data.cost.toDecimalFormat() + "원")
            detail_explain_sale_price_textview.setStrikeThru()
        } else {
            detail_explain_sale_percent_textview.setGone()
            detail_explain_sale_price_textview.setGone()
        }

        detail_explain_unit_textview.text = data.unit
        detail_explain_weight_textview.text = data.weight
        detail_explain_package_textview.text = data.packageX
        detail_explain_delivery_textview.text = data.delivery


        if (data.origin == "") {
            detail_explain_origin_textview.setGone()
            detail_explain_origin_title_textview.setGone()
        } else {
            detail_explain_origin_textview.text = data.origin
            detail_explain_origin_textview.setVisible()
            detail_explain_origin_title_textview.setVisible()
        }

        if (data.expiration == "") {
            detail_explain_expiration_textview.setGone()
            detail_explain_expiration_title_textview.setGone()
        } else {
            detail_explain_expiration_textview.text = data.expiration
            detail_explain_expiration_textview.setVisible()
            detail_explain_expiration_title_textview.setVisible()
        }

        if (data.sweet == "") {
            detail_explain_sweet_textview.setGone()
            detail_explain_sweet_title_textview.setGone()
        } else {
            detail_explain_sweet_textview.text = data.sweet
            detail_explain_sweet_textview.setVisible()
            detail_explain_sweet_title_textview.setVisible()
        }

        if (data.allergy == "") {
            detail_explain_alllergy_textview.setGone()
            detail_explain_alllergy_title_textview.setGone()
        } else {
            detail_explain_alllergy_textview.text = data.allergy
            detail_explain_alllergy_textview.setVisible()
            detail_explain_alllergy_title_textview.setVisible()
        }


    }


}