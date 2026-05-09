package com.xingchidongli.robot.compose_ui

import android.R
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.xingchidongli.robot.theme.color_5aa
import com.xingchidongli.robot.theme.color_e45


@Composable
fun LocalHtmlStringWebViewComponent(
    htmlContent: String,
    backColor: Color,
    loadingCompose: @Composable () -> Unit
) {
    val context = LocalContext.current
    var isShowLoading by remember {
        mutableStateOf(true)
    }
    val webView = remember {
        WebView(context).apply {
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            settings.javaScriptEnabled = true // 启用JS，根据需求设置
            settings.allowFileAccess = true
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    if (newProgress == 100) {
                        isShowLoading = false
                    }
                }

            }
        }
    }

    LaunchedEffect(htmlContent) {
        isShowLoading = true
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)
    }

    Box(
        modifier = Modifier
            .background(backColor)
            .padding(16.dp)
    ) {
        AndroidView(
            factory = { webView },
            modifier = Modifier.fillMaxSize(),
        )
        if (isShowLoading) {
            loadingCompose()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webView.destroy()
        }
    }


}


@Composable
fun LoadLinkWebViewComponent(
    htmlLink: String,
    backColor: Color,
    loadingCompose: @Composable () -> Unit,
    emptyCompose: @Composable () -> Unit
) {
    val context = LocalContext.current
    var isShowLoading by remember {
        mutableStateOf(true)
    }
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true // 启用JS，根据需求设置
            settings.allowFileAccess = true
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    if (newProgress == 100) {
                        isShowLoading = false
                    }
                }
            }
        }
    }



    LaunchedEffect(htmlLink) {
        webView.loadUrl(htmlLink)
    }

    if (isShowLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            loadingCompose()
        }
    } else {
        if (htmlLink.isBlank() || htmlLink.isEmpty()) {
            emptyCompose()
        } else {
            AndroidView(
                factory = {
                    webView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                    webView
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backColor)
                    .wrapContentHeight()
                    .padding(16.dp),
            )
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            webView.destroy()
        }
    }
}

@Composable
fun CommonWebLoadComponent() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.size(500.dp, height = 500.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GifFileImage("gif_loading.gif")
            Text("稍等片刻……", fontSize = 17.sp, color = color_e45)
        }
    }

}




