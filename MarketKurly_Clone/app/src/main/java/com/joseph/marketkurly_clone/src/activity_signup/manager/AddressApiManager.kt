package com.joseph.marketkurly_clone.src.activity_signup.manager

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Handler
import android.util.Log
import android.webkit.*
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddresApiEvent


class AddressApiManager(private var webView: WebView,
                        private var addressApiListener: AddresApiEvent) {

    private var handler: Handler? = Handler()

    val client: WebViewClient = object : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return false
        }
    }

    inner class AndroidBridge() {
        @JavascriptInterface
        fun setAddress(arg1: String, arg2: String) {
            Log.d(TAG, "[AndroidBridge] - setAddress() : $arg1 / $arg2")
            handler?.post(Runnable {
                addressApiListener.onAddressSelected(arg1, arg2)

                // WebView를 초기화 하지않으면 재사용할 수 없음
                initWebView()
            })
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {

        webView.apply {
            this.settings.javaScriptEnabled = true
            this.settings.setSupportMultipleWindows(true)
            this.settings.javaScriptCanOpenWindowsAutomatically = true
            webViewClient = client
            addJavascriptInterface(AndroidBridge(), "KurlyApp")
            webChromeClient = WebChromeClient()
        }
        webView.loadUrl("http://daum.marketkulry.shop/index.php")

    }


}




