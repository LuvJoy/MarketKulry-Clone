package com.joseph.marketkurly_clone.src.activity_address_manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_ADDRESS
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_address_manager.adapters.UserAddressRecyclerAdapter
import com.joseph.marketkurly_clone.src.activity_address_manager.interfaces.UserAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_address_manager.interfaces.UserAddressHolderClickListener
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_search_address.SearchAddressActivity
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_address_manager.*
import kotlinx.android.synthetic.main.activity_cart.*

class AddressManagerActivity : BaseActivity(), UserAddressApiEvent, UserAddressHolderClickListener {

    private var mUserAddressService = AddressManagerService(this)
    private lateinit var mAddressRecyclerAdapter: UserAddressRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_manager)

        initActionbar()
        initListener()
        initRecyclerView()

        mUserAddressService.getUserAddress()
    }

    fun initActionbar(){
        add_manager_actionbar.ab_inner_toolbar.title = "주소지 관리"
        add_manager_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initListener() {
        add_manager_add_address_button.setOnClickListener(this)
    }

    fun initRecyclerView() {
        mAddressRecyclerAdapter = UserAddressRecyclerAdapter(this, this)

        add_manager_address_recyclerview.apply {
            layoutManager = object :
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = mAddressRecyclerAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.add_manager_add_address_button -> {
                val intent = Intent(this, SearchAddressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onLoadUserAddressSuccess(list: ArrayList<UserAddress>) {
        mAddressRecyclerAdapter.submitList(list)
    }

    override fun onLoadUserAddressFail(message: String) {
        showSnackBar(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE_ADDRESS -> {
                if(resultCode == RESULT_OK){
                    var address = data?.extras?.getString("address")
                }
            }
        }
    }

    override fun onCheckBoxClicked(userAddress: UserAddress) {
        TODO("Not yet implemented")
    }


}