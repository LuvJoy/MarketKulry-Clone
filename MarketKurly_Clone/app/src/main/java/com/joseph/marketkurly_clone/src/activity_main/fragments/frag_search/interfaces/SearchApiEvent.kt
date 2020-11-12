package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.interfaces

interface SearchApiEvent {

    fun onQueryLoadSuccess(list: ArrayList<String>)
    fun onQueryLoadFail(message: String)
}