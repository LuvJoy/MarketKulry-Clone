package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.util.setFadeInAnimation
import com.joseph.marketkurly_clone.src.util.setFadeOutAnimation
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.fragment_detail_tab_info.*


class InfoFragment : Fragment(R.layout.fragment_detail_tab_info), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        detail_info_see_order_info_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.detail_info_see_order_info_button -> {
                if(!detail_info_reveal_order_info_layout.isVisible) {
                    detail_info_reveal_order_info_layout.setVisible()
                    detail_info_reveal_order_info_layout.setFadeInAnimation(requireContext())
                } else {
                    detail_info_reveal_order_info_layout.setGone()
                    detail_info_reveal_order_info_layout.setFadeOutAnimation(requireContext())
                }
            }
        }

    }
}