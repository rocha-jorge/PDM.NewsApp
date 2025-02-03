package a26052.pdmnewsapp.ui.bookmarks

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.ui.navigation.ArticleItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    navController: NavController,
    viewModel: BookmarksViewModel,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier // ✅ Accept modifier
) {
    val bookmarkedArticles by viewModel.bookmarkedArticles.collectAsState()

    LazyColumn(modifier = modifier.padding(16.dp)) { // ✅ Apply modifier
        items(bookmarkedArticles) { article ->
            ArticleItem(article = article, onClick = { onArticleClick(article) })
        }
    }
}


@Composable
fun BookmarkedArticleItem(article: Article, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        article.image_url?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Article Image",
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        }

        Text(text = article.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = article.description ?: "No description available", fontSize = 14.sp)
    }
}
