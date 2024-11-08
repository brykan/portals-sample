package com.ionic.io.portalssample

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.getcapacitor.WebViewListener
import com.ionic.io.portalssample.ui.theme.PortalsSampleTheme
import io.ionic.portals.PortalView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortalsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content() {
    HorizontalPager(
        state = rememberPagerState(pageCount = { 2 }),
        beyondBoundsPageCount = 2
    ) { page ->
        when (page) {
            0 -> Page(portalId = "checkout", pageTitle = "Page $page: checkout")
            1 -> Page(portalId = "profile", pageTitle = "Page $page: profile")
            2 -> Page(portalId = "help", pageTitle = "Page $page: help")
            else -> Page(portalId = "checkout", pageTitle = "Page $page: checkout")
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Page: $page"
        )
    }
}

@Composable
private fun Page(
    pageTitle: String,
    portalId: String,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = pageTitle
        )
        Portal(portalId = portalId)
    }
}

@Composable
private fun Portal(portalId: String) {
    var isLoading by remember { mutableStateOf(false) }
    Box {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PortalView(context, portalId).apply {
                    getPortalFragment()?.addWebViewListener(object : WebViewListener() {
                        override fun onPageStarted(webView: WebView?) {
                            super.onPageStarted(webView)
                            isLoading = true
                        }

                        override fun onPageLoaded(webView: WebView?) {
                            super.onPageLoaded(webView)
                            isLoading = false
                        }
                    })
                }
            })
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortalsSampleTheme {
        Content()
    }
}