package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_newproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_newproduct.adapters.NewProductRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.HomeProductService
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.interfaces.HomeProductApiEvent
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.fragment_tab_new_product.*


class TabNewProductFragment : BaseFragment(R.layout.fragment_tab_new_product), HomeProductApiEvent {

    private lateinit var mNewProductRecyclerAdapter: NewProductRecyclerAdapter
    private var mProductSevice = HomeProductService(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        mProductSevice.getRecommendProduct()
    }


    fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(fragContext, 2, GridLayoutManager.VERTICAL, false)
        mNewProductRecyclerAdapter = NewProductRecyclerAdapter(fragContext!!)

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
        mNewProductRecyclerAdapter.submitList(list)
    }

    override fun onProductLoadFail(message: String) {
        val ac = activity as BaseActivity
        ac.showSnackBar(message)
    }
}