package a26052.pdmnewsapp.ui.bookmarks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import a26052.pdmnewsapp.domain.model.Article
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = viewModel(),
    onArticleClick: (Article) -> Unit
) {
    val savedArticles by viewModel.bookmarkedArticles.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Bookmarks") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(savedArticles) { article ->
                ArticleItem(article, onArticleClick)
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
