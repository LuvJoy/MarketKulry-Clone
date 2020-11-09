package com.joseph.marketkurly_clone.src.activity_address_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_search_address.SearchAddressActivity
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_address_manager.*

class AddressManagerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_manager)

        initActionbar()
        initListener()
    }

    fun initActionbar(){
        add_manager_actionbar.ab_inner_toolbar.title = "주소지 관리"
        add_manager_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initListener() {
        add_manager_add_address_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.add_manager_add_address_button -> {
                val intent = Intent(this, SearchAddressActivity::class.java)
                startActivity(intent)
            }
        }
    }
}