package a26052.pdmnewsapp.ui.navigation

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.ui.components.MyTopBar
import a26052.pdmnewsapp.ui.home.HomeViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    onArticleClick: (Article) -> Unit
) {
    val articlesState = viewModel.articles.collectAsState() // ✅ Get State
    val articles = articlesState.value // ✅ Extract value

    Scaffold(
        topBar = { MyTopBar(navController) } // ✅ Ensure TopBar gets navController
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(articles) { article ->
                ArticleItem(article = article, onClick = { onArticleClick(article) })
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, onClick: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onClick(article) } // ✅ Correct onClick behavior
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = (article.description ?: "No Description").take(200),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
