package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters.ProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.model.ProductCompact
import kotlinx.android.synthetic.main.fragment_tab_rec_kurly.*

class TabRecKurlyFragment : Fragment(R.layout.fragment_tab_rec_kurly) {

    private lateinit var mSuggestRecyclerViewAdapter: ProductRecyclerAdapter
    private lateinit var fragContext: Context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragContext = requireContext()
        initRecyclerView()

        val dummy = ProductCompact("00010","[해찬들] 태양초 순창 고추장", "ㅇㅇ", 30000, 30000, 30)
        val dummy2 = ProductCompact("00010","[남향푸드또띠아] 간편 간식 부리또 8종", "ㅇㅇ", 2800, null, null)
        val dummy3 = ProductCompact("00010","[해찬들] 태양초 순창 고추장", "ㅇㅇ", 6000, 4444, 30)
        val dummy4 = ProductCompact("00010","[해찬들] 태양초 순창 고추장", "ㅇㅇ", 30000, 30000, 30)
        val dummy5 = ProductCompact("00010","[해찬들] 태양초 순창 고추장", "ㅇㅇ", 30000, 30000, 30)
        val list = arrayListOf<ProductCompact>(dummy,dummy2,dummy3,dummy4,dummy5)
        list.add(dummy)
        mSuggestRecyclerViewAdapter.submitList(list)
    }

    fun initRecyclerView() {
        val mHorizontalLayoutManager: LinearLayoutManager =
            LinearLayoutManager(fragContext, LinearLayoutManager.HORIZONTAL, false)
        mHorizontalLayoutManager.isItemPrefetchEnabled = true

        // [이 상품은 어때요?] 리사이클러뷰 초기화
        mSuggestRecyclerViewAdapter = ProductRecyclerAdapter(fragContext)

        reckurly_suggest_recyclerview.apply {
            setHasFixedSize(true)
            setLayoutManager(mHorizontalLayoutManager)
            adapter = mSuggestRecyclerViewAdapter
        }

        //
    }
}