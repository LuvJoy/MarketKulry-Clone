package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.*

class TabViewPagerAdapter(frag: Fragment): FragmentStateAdapter(frag){
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return TabRecKurlyFragment()
            1 -> return TabNewProductFragment()
            2 -> return TabBestFragment()
            3 -> return TabAlShoppingFragment()
            4 -> return TabEventFragment()

            else -> return TabRecKurlyFragment()
        }
    }
}