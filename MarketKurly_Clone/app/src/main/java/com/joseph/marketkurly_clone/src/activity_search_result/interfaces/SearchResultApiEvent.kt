package com.joseph.marketkurly_clone.src.activity_search_result.interfaces

import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL

interface SearchResultApiEvent {

    fun onQueryResultSuccess(list: ArrayList<ProductCompactL>)
    fun onQueryResultFail(message: String)
}