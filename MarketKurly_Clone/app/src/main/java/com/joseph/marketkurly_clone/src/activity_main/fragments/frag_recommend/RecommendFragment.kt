package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joseph.marketkurly_clone.ApplicationClass.Companion.CURRENT_USER
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.HomeProductService
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.interfaces.HomeProductApiEvent
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_recommend.adapters.AssociatedRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_recommend.*

class RecommendFragment : BaseFragment(R.layout.fragment_recommend), HomeProductApiEvent {

    private lateinit var mAssociatedRecyclerAdapter: AssociatedRecyclerAdapter
    private lateinit var mAssociatedRecyclerAdapter2: AssociatedRecyclerAdapter
    private var mHomeProductService =  HomeProductService(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        recommend_name_textview.text = CURRENT_USER?.name

        mHomeProductService.getRecommendProduct()
    }

    fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(fragContext, LinearLayoutManager.HORIZONTAL, false)
        mAssociatedRecyclerAdapter = AssociatedRecyclerAdapter(fragContext!!)

        recommend_associate_product_recylcerview.apply {
            layoutManager = linearLayoutManager
            adapter = mAssociatedRecyclerAdapter
            setHasFixedSize(true)
        }

        val linearLayoutManager2 = LinearLayoutManager(fragContext, LinearLayoutManager.HORIZONTAL, false)
        mAssociatedRecyclerAdapter2 = AssociatedRecyclerAdapter(fragContext!!)

        recommend_associate2_product_recylcerview.apply {
            layoutManager = linearLayoutManager2
            adapter = mAssociatedRecyclerAdapter2
            setHasFixedSize(true)
        }
    }

    override fun onProductLoadSuccess(list: ArrayList<ProductCompact>) {
        mAssociatedRecyclerAdapter.submitList(list)
        mAssociatedRecyclerAdapter2.submitList(list)

        mAssociatedRecyclerAdapter2.shuffleList()
    }

    override fun onProductLoadFail(message: String) {
        var a = activity as BaseActivity
        a.showSnackBar(message)
    }

    override fun onLProductLoadSuccess(list: ArrayList<ProductCompactL>) {
        TODO("Not yet implemented")
    }

    override fun onLProductLoadFail(message: String) {
        TODO("Not yet implemented")
    }

}