package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_newproduct

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.adapters.ProductLRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.adapters.ProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.HomeProductService
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.interfaces.HomeProductApiEvent
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL
import kotlinx.android.synthetic.main.fragment_tab_new_product.*


class TabNewProductFragment : BaseFragment(R.layout.fragment_tab_new_product), HomeProductApiEvent {

    private lateinit var mNewProductRecyclerAdapter: ProductLRecyclerAdapter
    private var mProductSevice = HomeProductService(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        mProductSevice.getNewProduct()
    }


    fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(fragContext, 2, GridLayoutManager.VERTICAL, false)
        mNewProductRecyclerAdapter = ProductLRecyclerAdapter(fragContext!!)

        tab_new_product_recyclerview.apply {
            adapter = mNewProductRecyclerAdapter
            layoutManager = object :
                GridLayoutManager(fragContext, 2, GridLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
    }

    override fun onProductLoadSuccess(list: ArrayList<ProductCompact>) {

    }

    override fun onProductLoadFail(message: String) {
        val ac = activity as BaseActivity
        ac.showSnackBar(message)
    }

    override fun onLProductLoadSuccess(list: ArrayList<ProductCompactL>) {
        mNewProductRecyclerAdapter.submitList(list)
    }

    override fun onLProductLoadFail(message: String) {
        TODO("Not yet implemented")
    }
}