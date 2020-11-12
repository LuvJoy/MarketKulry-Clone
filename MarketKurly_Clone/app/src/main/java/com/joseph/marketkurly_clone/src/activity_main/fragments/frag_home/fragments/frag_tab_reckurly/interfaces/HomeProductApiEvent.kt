package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.interfaces

import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL

interface HomeProductApiEvent {

    fun onProductLoadSuccess(list: ArrayList<ProductCompact>)
    fun onProductLoadFail(message: String)

    fun onLProductLoadSuccess(list: ArrayList<ProductCompactL>)
    fun onLProductLoadFail(message: String)
}