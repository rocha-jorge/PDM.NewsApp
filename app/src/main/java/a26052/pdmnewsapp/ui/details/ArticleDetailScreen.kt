package a26052.pdmnewsapp.ui.details

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import a26052.pdmnewsapp.domain.model.Article

@Composable
fun ArticleDetailScreen(article: Article) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    })
}
