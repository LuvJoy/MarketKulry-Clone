package com.joseph.marketkurly_clone

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joseph.marketkurly_clone.R

open class BaseActivity : AppCompatActivity(), View.OnClickListener {
    var mProgressDialog: ProgressDialog? = null

    fun showCustomToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this).apply {
                setMessage(getString(R.string.loading))
                isIndeterminate = true
            }
        }
        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mProgressDialog != null) {
            hideProgressDialog()
        }
    }

    override fun onClick(v: View?){}
}
