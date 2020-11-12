package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_alshopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.fragment_tab_al_shopping.*
import kotlinx.android.synthetic.main.fragment_tab_best.*
import kotlinx.android.synthetic.main.fragment_tab_best.tab_best_product_recyclerview

class TabAlShoppingFragment : BaseFragment(R.layout.fragment_tab_al_shopping), HomeProductApiEvent {

    private lateinit var mAlShoppingProductRecyclerAdapter: ProductLRecyclerAdapter
    private var mProductSevice = HomeProductService(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        mProductSevice.getSaleProduct()
    }

    fun initRecyclerView() {

        mAlShoppingProductRecyclerAdapter = ProductLRecyclerAdapter(fragContext!!)

        tab_al_shopping_product_recyclerview.apply {
            adapter = mAlShoppingProductRecyclerAdapter
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
        mAlShoppingProductRecyclerAdapter.submitList(list)
    }

    override fun onLProductLoadFail(message: String) {
        TODO("Not yet implemented")
    }
}