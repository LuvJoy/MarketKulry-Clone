package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_info

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.util.setFadeInAnimation
import com.joseph.marketkurly_clone.src.util.setFadeOutAnimation
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.fragment_detail_tab_info.*


class InfoFragment : BaseFragment(R.layout.fragment_detail_tab_info) {

    var ICON_CHEVRON_UP: Drawable? = null
    var ICON_CHEVRON_DOWN: Drawable? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ICON_CHEVRON_UP = ContextCompat.getDrawable(fragContext!!, R.drawable.ic_chevron_up)
        ICON_CHEVRON_DOWN = ContextCompat.getDrawable(fragContext!!, R.drawable.ic_chevron_down)
        detail_info_see_order_info_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.detail_info_see_order_info_button -> {
                if(!detail_info_reveal_order_info_layout.isVisible) {
                    detail_info_see_order_info_button.setCompoundDrawablesWithIntrinsicBounds(null, null, ICON_CHEVRON_UP, null)
                    detail_info_reveal_order_info_layout.apply {
                        setVisible()
                        setFadeInAnimation(fragContext!!)
                    }

                } else {
                    detail_info_see_order_info_button.setCompoundDrawablesWithIntrinsicBounds(null, null, ICON_CHEVRON_DOWN, null)
                    detail_info_reveal_order_info_layout.apply {
                        setFadeOutAnimation(fragContext!!)
                        setGone()
                    }
                }
            }
        }

    }
}