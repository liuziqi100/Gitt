package com.example.gitt

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.WEB_URL
import android.webkit.*
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var webView : WebView? = null
    private val lazyOne : Int by lazy { 1 }
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
        //设置js交互和弹窗权限
        webView!!.settings.javaScriptEnabled
        webView!!.settings.javaScriptCanOpenWindowsAutomatically

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webView!!.loadUrl("file:///android_asset/javascript.html")
        webView!!.webChromeClient = object : WebChromeClient(){
            override fun onJsPrompt(
                view: WebView?,
                url: String?,
                message: String?,
                defaultValue: String?,
                result: JsPromptResult?
            ): Boolean {
                var uri = Uri.parse(message)
                if (uri.scheme!!.equals("js")) {
                    if (uri.authority.equals("webview")) {
                        println("js调用了Android的方法")
                    }
                    return true
                }
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return super.onJsAlert(view, url, message, result)
            }

            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return super.onJsConfirm(view, url, message, result)
            }
        }
    }
}
