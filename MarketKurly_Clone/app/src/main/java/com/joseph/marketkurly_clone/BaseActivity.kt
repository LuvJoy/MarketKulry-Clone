package com.joseph.marketkurly_clone

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity(), View.OnClickListener {
    var mProgressDialog: ProgressDialog? = null

    fun showCustomToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialog(message: String? = null) {
        if (mProgressDialog == null) {
            if (message != null) {
                mProgressDialog = ProgressDialog(this, R.style.BaseProgressDialog).apply {
                    setMessage(message)
                    isIndeterminate = true
                }
            } else {
                mProgressDialog = ProgressDialog(this, R.style.BaseProgressDialog).apply {
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

    override fun onStop() {
        super.onStop()
        if (mProgressDialog != null) {
            hideProgressDialog()
        }
    }

    fun showAlertDialog(text: String) {
        AlertDialog.Builder(this)
                .setTitle(text)
                .setPositiveButton("확인") { dialog, which -> dialog?.cancel() }.create().show()
    }

    override fun onClick(v: View?) {}

    // 키보드를 다른곳 터치 시 내려주는 메소드
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val view = currentFocus
        if (view != null && (ev!!.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass.name.startsWith("android.webkit.")) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x = ev.rawX + view.getLeft() - scrcoords[0]
            val y = ev.rawY + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(this.window.decorView.applicationWindowToken, 0)
        }

        return super.dispatchTouchEvent(ev)
    }

}
