package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_my_kurly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_signin.SignInActivity
import kotlinx.android.synthetic.main.fragment_mykurly.*

class MyKurlyFragment : Fragment(R.layout.fragment_mykurly) {

    private lateinit var fragContext: Context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragContext = requireContext()

        mykurly_login_button.setOnClickListener {
            val intent = Intent(fragContext, SignInActivity::class.java)
            fragContext.startActivity(intent)
        }
    }
}