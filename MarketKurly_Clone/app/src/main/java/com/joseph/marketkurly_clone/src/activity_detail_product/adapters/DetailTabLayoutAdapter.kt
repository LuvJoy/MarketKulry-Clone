package com.joseph.marketkurly_clone.src.activity_detail_product.adapters

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_explain.ExplainFragment
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_image.ImageFragment
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_info.InfoFragment
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_inquiry.InquiryFragment
import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.ReviewFragment
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_alshopping.TabAlShoppingFragment
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_best.TabBestFragment
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_event.TabEventFragment
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_newproduct.TabNewProductFragment
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.TabRecKurlyFragment

class DetailTabLayoutAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ExplainFragment()
            1 -> return ImageFragment()
            2 -> return InfoFragment()
            3 -> return ReviewFragment()
            4 -> return InquiryFragment()

            else -> return ExplainFragment()
        }
    }

}