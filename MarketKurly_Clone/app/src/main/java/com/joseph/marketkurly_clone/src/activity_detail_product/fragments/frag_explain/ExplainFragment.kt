package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_explain

import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.util.setStrikeThru
import kotlinx.android.synthetic.main.fragment_detail_tab_explain.*

class ExplainFragment : BaseFragment(R.layout.fragment_detail_tab_explain) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/marketkurly-63933.appspot.com/o/product1_thumbnail.png?alt=media&token=f4ffa46a-5f86-4a50-b327-c0afcb31dffe")
            .into(detail_explain_product_imageview)


        Glide.with(this)
            .load("https://sub.marketkulry.shop/img/product1_description.png")
            .into(detail_explain_product_explain_imageview)

        detail_explain_sale_price_textview.setStrikeThru()
    }


}