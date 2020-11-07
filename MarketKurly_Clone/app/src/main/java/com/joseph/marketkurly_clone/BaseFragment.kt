package com.joseph.marketkurly_clone

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), View.OnClickListener {

    var mProgressDialog: ProgressDialog? = null
    var mProgressBar: ProgressBarHandler? = null
    var fragContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        fragContext = requireContext()
        super.onCreate(savedInstanceState)
    }
    fun showCustomToast(message: String) {
        Toast.makeText(fragContext, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialog(message: String? = null) {
        if (mProgressDialog == null) {
            if (message != null) {
                mProgressDialog = ProgressDialog(fragContext, R.style.BaseProgressDialog).apply {
                    setMessage(message)
                    isIndeterminate = true
                }
            } else {
                mProgressDialog = ProgressDialog(fragContext, R.style.BaseProgressDialog).apply {
                    setMessage(getString(R.string.loading))
                    isIndeterminate = true
                }
            }
        }
        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    fun showProgressBar() {
        if (mProgressBar == null) {
            mProgressBar = ProgressBarHandler(fragContext!!)
        }
        mProgressBar!!.show()
    }

    fun hideProgressBar() {
        mProgressBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        if (mProgressDialog != null) {
            hideProgressDialog()
        }
    }

    fun showAlertDialog(text: String) {
        AlertDialog.Builder(fragContext)
                .setTitle(text)
                .setPositiveButton("확인") { dialog, which -> dialog?.cancel() }.create().show()
    }

    override fun onClick(v: View?) {}

    inner class ProgressBarHandler(private val mContext: Context) {
        private val mProgressBar: ProgressBar
        fun show() {
            mProgressBar.visibility = View.VISIBLE
        }

        fun hide() {
            mProgressBar.visibility = View.INVISIBLE
        }

        init {
            val layout = (mContext as Activity).findViewById<View>(android.R.id.content).rootView as ViewGroup
            mProgressBar = ProgressBar(mContext, null, android.R.attr.progressBarStyleInverse)
            mProgressBar.isIndeterminate = true
            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            val rl = RelativeLayout(mContext)
            rl.gravity = Gravity.CENTER
            rl.addView(mProgressBar)
            layout.addView(rl, params)
            hide()
        }
    }

}