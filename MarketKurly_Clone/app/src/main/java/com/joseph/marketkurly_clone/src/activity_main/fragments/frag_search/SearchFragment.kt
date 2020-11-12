package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.adapters.QueryRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.interfaces.SearchApiEvent
import com.joseph.marketkurly_clone.src.activity_search_result.SearchResultActivity
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment(R.layout.fragment_search), SearchApiEvent {

    private var mSearchService = SearchService(this)
    private lateinit var mQueryRecyclerAdapter: QueryRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListener()
        mSearchService.getQueryList()
    }

    fun initRecyclerView() {
        mQueryRecyclerAdapter = QueryRecyclerAdapter(fragContext!!)

        search_frag_query_recyclerview.apply{
            layoutManager = object :
                LinearLayoutManager(fragContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mQueryRecyclerAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
    }

    fun initListener() {
        search_frag_query_edittext.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val intent = Intent(fragContext, SearchResultActivity::class.java)
                intent.putExtra("query", v.text.toString())
                fragContext?.startActivity(intent)
                Log.d(TAG, "[SearchFragment] - initListener() : ${v.text}")
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    override fun onQueryLoadSuccess(list: ArrayList<String>) {
        mQueryRecyclerAdapter.submitList(list)
    }

    override fun onQueryLoadFail(message: String) {
        var a = activity as BaseActivity
        a.showSnackBar(message)
    }


}