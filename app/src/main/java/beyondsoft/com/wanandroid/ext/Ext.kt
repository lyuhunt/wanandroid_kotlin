package beyondsoft.com.wanandroid.ext

import android.app.Activity
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import beyondsoft.com.wanandroid.R
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient


fun String.getAgentWeb(activity: Activity,
                       webContent: ViewGroup,
                       layoutParams: ViewGroup.LayoutParams,
                       webView: WebView,
                       webChromeClient: WebChromeClient?,
                       webViewClient: WebViewClient
) = AgentWeb.with(activity)//传入Activity or Fragment
        .setAgentWebParent(webContent, 1, layoutParams)//传入AgentWeb 的父控件
        .useDefaultIndicator()// 使用默认进度条
        .setWebView(webView)
        .setWebChromeClient(webChromeClient)
        .setWebViewClient(webViewClient)
        .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
        .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
        .createAgentWeb()//
        .ready()
        .go(this)