package a26052.pdmnewsapp.ui.details

import a26052.pdmnewsapp.domain.model.Article
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ArticleDetailScreen(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Article Title
        Text(
            text = article.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Article Image (if available)
        article.imageUrl?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        // Article Description
        Text(
            text = article.description ?: "No description available",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Read Full Article Button (opens in WebView)
        Button(
            onClick = { /* Open WebView here in the next step */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Read Full Article")
        }
    }
}
