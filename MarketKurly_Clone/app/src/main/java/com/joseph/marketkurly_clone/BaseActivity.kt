package com.joseph.marketkurly_clone

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.joseph.marketkurly_clone.src.util.setVisible


open class BaseActivity : AppCompatActivity(), View.OnClickListener {
    var mProgressDialog: ProgressDialog? = null
    var mProgressBar: ProgressBarHandler? = null
    var mSnackBar: Snackbar? = null

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

    fun showSnackBar(message: String?) {
        if (null != this && null != message) {
            Snackbar.make(
                this.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT
            ).show()
        }
    }


    fun showProgressBar() {
        if (mProgressBar == null) {
            mProgressBar = ProgressBarHandler(this)
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
