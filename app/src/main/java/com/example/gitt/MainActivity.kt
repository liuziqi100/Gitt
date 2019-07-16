package com.example.gitt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.WEB_URL
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var webView : WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        initWebView()
    }

    fun initWebView() {
        val frameLayout = findViewById(R.id.frameLayout) as FrameLayout
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        webView = WebView(applicationContext)
        frameLayout.addView(webView,params)
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webView!!.loadUrl("http://www.baidu.com")
    }
}
