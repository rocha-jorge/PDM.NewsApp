package a26052.pdmnewsapp.ui.details

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun ArticleDetailScreen(article: Article, repository: ArticlesRepository) {
    val scope = rememberCoroutineScope() // ✅ Required for suspend function calls

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = article.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        article.image_url?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Article Image",
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        }

        Text(
            text = article.description ?: "No Description",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp)
        )

        Button(
            onClick = {
                scope.launch { // ✅ Ensure suspend function is called in coroutine
                    repository.saveArticle(article)
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Bookmark Article")
        }
    }
}


