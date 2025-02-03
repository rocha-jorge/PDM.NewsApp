package a26052.pdmnewsapp.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val results: List<ArticleDto>?  // ✅ Use imported ArticleDto
)
