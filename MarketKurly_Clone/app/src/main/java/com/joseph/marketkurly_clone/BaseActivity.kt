package com.joseph.marketkurly_clone

import android.app.ProgressDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {
    lateinit var mProgressDialog: ProgressDialog

    fun showCustomToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialog () {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog.setMessage(getString(R.string.loading))
            mProgressDialog.isIndeterminate = true
        }

        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

    override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
}