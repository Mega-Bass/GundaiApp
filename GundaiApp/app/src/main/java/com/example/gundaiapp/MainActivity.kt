package com.example.gundaiapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button1 = findViewById<Button>(R.id.button1)
        var button2 = findViewById<Button>(R.id.button2)
        var button3 = findViewById<Button>(R.id.button3)
        var button4 = findViewById<Button>(R.id.button4)
        var Webview = findViewById<WebView>(R.id.Webview)

        //JavaScript有効化
        Webview.getSettings().setJavaScriptEnabled(true);


        //外部ブラウザを開かなくする
        Webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
            ): Boolean {
                return false
            }
        }
        //cookieを使用
        val cookieManager = CookieManager.getInstance()
        cookieManager.setCookie("https://www.gunma-u.ac.jp/", "")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(Webview, true)
            cookieManager.flush() // Cookieを永続化する
        }
        val exampleCookie = cookieManager.getCookie("https://www.gunma-u.ac.jp/")


        //最初のページ表示
        Webview.loadUrl("https://www.gunma-u.ac.jp/")
        button1.setOnClickListener {
            Webview.loadUrl("https://mdl2.media.gunma-u.ac.jp/GU/login/")
        }
        button2.setOnClickListener {
            Webview.loadUrl("https://www.kyomu-sys.gunma-u.ac.jp/Portal/")
        }
        button3.setOnClickListener {
            Webview.loadUrl("https://www.media.gunma-u.ac.jp/")
        }
        button4.setOnClickListener {
            Webview.loadUrl("https://www.gunma-u.ac.jp/")
        }

    }
}