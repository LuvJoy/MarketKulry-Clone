package com.joseph.marketkurly_clone.src.activity_search_result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.adapters.ProductLRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL
import com.joseph.marketkurly_clone.src.activity_search_result.interfaces.SearchResultApiEvent
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : BaseActivity(), SearchResultApiEvent {

    private lateinit var mRecyclerAdapter: ProductLRecyclerAdapter
    private var mSearchResultService = SearchResultService(this)
    var mQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        mQuery = intent.extras?.getString("query")!!
        initActionbar(mQuery)
        initRecyclerView()
        showProgressBar()
        mSearchResultService.getQueryResult(mQuery)

    }

    fun initActionbar(query: String) {
        search_result_actionbar.ab_inner_toolbar.title = query
        search_result_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {

        mRecyclerAdapter = ProductLRecyclerAdapter(this)
        search_result_recyclerview.apply{
            adapter = mRecyclerAdapter
            layoutManager = object :
                GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
    }

    override fun onQueryResultSuccess(list: ArrayList<ProductCompactL>) {
        mRecyclerAdapter.submitList(list)
        search_result_actionbar.ab_inner_toolbar.title = "$mQuery 검색결과(${list.size})"
        hideProgressBar()
    }

    override fun onQueryResultFail(message: String) {
        showSnackBar(message)
    }
}