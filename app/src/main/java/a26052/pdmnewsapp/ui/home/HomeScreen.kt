package a26052.pdmnewsapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import a26052.pdmnewsapp.domain.model.Article
import android.util.Log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onArticleClick: (Article) -> Unit
) {
    val articles by homeViewModel.articles.collectAsState()

    Log.d("UI_UPDATE", "📜 Articles count: ${articles.size}")

    Scaffold(
        topBar = { TopAppBar(title = { Text("News") }) }
    ) { padding ->
        if (articles.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(text = "No Articles Found")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(articles) { article ->
                    ArticleItem(article = article, onClick = onArticleClick)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, onClick: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(article) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.description ?: "No description", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
